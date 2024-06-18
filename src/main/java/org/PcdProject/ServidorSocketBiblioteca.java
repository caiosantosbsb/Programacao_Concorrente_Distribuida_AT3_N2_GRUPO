package org.PcdProject;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServidorSocketBiblioteca {
    private static final int PORT = 12345;
    private final Biblioteca biblioteca;

    public ServidorSocketBiblioteca() {
        this.biblioteca = new Biblioteca();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, biblioteca).start();
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServidorSocketBiblioteca().start();
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private final Biblioteca biblioteca;

        public ClientHandler(Socket socket, Biblioteca biblioteca) {
            this.clientSocket = socket;
            this.biblioteca = biblioteca;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String request;
                while ((request = in.readLine()) != null) {
                    String[] parts = request.split(":", 2);
                    String command = parts[0];

                    switch (command) {
                        case "LISTAR":
                            List<Livro> livros = biblioteca.listarLivros();
                            out.println(new Gson().toJson(livros));
                            break;

                        case "CADASTRAR":
                            Livro novoLivro = new Gson().fromJson(parts[1], Livro.class);
                            if (biblioteca.cadastrarLivro(novoLivro)) {
                                out.println("Livro cadastrado com sucesso.");
                            } else {
                                out.println("Erro ao cadastrar o livro. Livro já existe.");
                            }
                            break;

                        case "ALUGAR":
                            String tituloAlugar = parts[1];
                            if (biblioteca.alugarLivro(tituloAlugar)) {
                                out.println("Livro alugado com sucesso.");
                            } else {
                                out.println("Erro ao alugar o livro. Livro não disponível.");
                            }
                            break;

                        case "DEVOLVER":
                            String tituloDevolver = parts[1];
                            if (biblioteca.devolverLivro(tituloDevolver)) {
                                out.println("Livro devolvido com sucesso.");
                            } else {
                                out.println("Erro ao devolver o livro. Livro não encontrado.");
                            }
                            break;

                        default:
                            out.println("Comando desconhecido.");
                            break;
                    }
                }
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }
    }
}
