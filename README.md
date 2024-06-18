Trabalho em grupo atividade AT3_N2, Progrmacacao_Concorrente_Distribuida_Noturno

Caio Vinicius de Oliveira Santos
Tales

Crie um projeto (em Java 17) para representar um servidor utilizando sockets. O servidor terá a função de
controlar um registro/cadastro de livros de uma biblioteca, sendo capaz de executar as seguintes
funcionalidades:
● Listagem dos livros;
● Aluguel e devolução de livros;
● Cadastro de livros.
Os livros devem ser representados por uma classe, que deve conter no mínimo os seguintes atributos:
● Autor;
● Nome;
● Gênero;
● Número de exemplares.
Os livros deverão ficar armazenados em um arquivo JSON (divulgado previamente pelo professor), que terá
10 livros inicialmente. As alterações aplicadas pelo usuário (cadastro e aluguel) devem refletir no
arquivo, pois o mesmo representará uma “base de dados” da biblioteca.
As operações devem ser realizadas por um cliente socket, que também deve ser implementado pelo
grupo. Ou seja, deve haver o envio e recebimento de dados entre o cliente e o servidor.
Observações:
● O arquivo JSON deverá manter sua estrutura correta (conforme explicado aqui) após a aplicação das
operações;
● A comunicação entre cliente e servidor deve ser feita obrigatoriamente por meio de sockets.