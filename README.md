# picpaysimplificado

projeto criado sobre um desafio de vaga backend para a empresa picpay

https://www.linkedin.com/in/deivid-zioto-6735b9289/

API para gerenciar transações bancarias (CRUD) que faz parte de um treino pessoal. Validando o tipo do usuario onde o MERCHANT nao pode fazer transações só receber e validando o valor da conta do usurario o balance. Não tem validação do email ou senha apenas demonstrativo.
Metodos criados por mim onde a logica de uma transação esta sendo aplicada, com cauculos do balance e forma de reveter a transação caso seja enviada errada.

Tecnologias

Spring Boot Spring MVC Spring Data JPA SpringDoc OpenAPI 3 Mysql

Práticas adotadas

SOLID, DRY, YAGNI, KISS API REST Consultas com Spring Data JPA Injeção de Dependências Tratamento de respostas de erro

Como Executar

Clonar repositório git Construir o projeto: $ ./mvnw clean package Executar a aplicação: $ java -jar target/picpaysimplificado-0.0.1-SNAPSHOT.jar A API poderá ser acessada em localhost:8080.

API Endpoints Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta httpie:

Criar usuarios $ http POST :8080/api/users firstName="name" lastName="name 2" document="15345687" email="email@gmail.com" password="password" balance=150 userType="commom" (ou MERCHANT)

[ { "id": 1, "firstName":"name", "lastName": "name 2", "document" : "15345687", "email": "email@gmail.com", "password" : "password", "balance": 150, "userType": "commom" } ]

Listar user $ http GET :8080/api/users

User por id $ http GET :8080/api/users/{id}

User por document $ http GET :8080/api/users/15345687

Atualizar user $ http PUT :8080/users/{id} firstName="newName" lastName="name 2" document="15345687" email="email@gmail.com" password="password" balance=150 userType="commom"

[ { "id": 1, "firstName":"newName", "lastName": "name 2", "document" : "15345687", "email": "email@gmail.com", "password" : "password", "balance": 150, "userType": "commom" } ]

Remover user $ http DELETE :8080/users/1

[ { "id": 1, "firstName":"newName", "lastName": "name 2", "document" : "15345687", "email": "email@gmail.com", "password" : "password", "balance": 150, "userType": "commom" } ] [ ]


Criar transações $ http POST :8080/api/transactions senderId=1 receiverId=2 amount=50

[ { "id": 1, "senderId" : 1, "receiverId": 2, "amount": 50, "dateTime": 15/10/2023 } ]

Listar transações $ http GET :8080/api/transactions

Todas transações recebidas e enviadas do usuario por id  $ http GET :8080/api/transactions/usuario/{id do usuario}

Atualizar transações com logica de extorno de dinheiro $ http PUT :8080/api/transactions/{id}

Apagar transação com logica de extorno de dinheiro $ http DELETE :8080/api/transactions/{id}
