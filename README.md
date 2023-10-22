# picpaysimplificado
projeto criado sobre um desafio de vaga backend para a empresa picpay

https://www.linkedin.com/in/deivid-zioto-6735b9289/

API para gerenciar transações bancarias (CRUD) que faz parte de um treino pessoal.

Tecnologias

Spring Boot Spring MVC Spring Data JPA SpringDoc OpenAPI 3 Mysql

Práticas adotadas

SOLID, DRY, YAGNI, KISS API REST Consultas com Spring Data JPA Injeção de Dependências Tratamento de respostas de erro

Como Executar

Clonar repositório git Construir o projeto: $ ./mvnw clean package Executar a aplicação: $ java -jar target/picpaysimplificado-0.0.1-SNAPSHOT.jar A API poderá ser acessada em localhost:8080.

API Endpoints Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta httpie:

Criar usuarios $ http POST :8080/users firstName="name" lastName="name 2" document="15345687" email="email@gmail.com" password="password" balance=150 userType="commom" (ou MERCHANT)

[ { "id": 1, "firstName":"name", "lastName": "name 2", "document" : "15345687", "email": "email@gmail.com", "password" : "password", "balance": 150, "userType": "commom" } ] 

Listar employees $ http GET :8080/users

 Atualizar user $ http PUT :8080/users/1 firstName="newName" lastName="name 2" document="15345687" email="email@gmail.com" password="password" balance=150 userType="commom"
 
[ { "id": 1, "firstName":"newName", "lastName": "name 2", "document" : "15345687", "email": "email@gmail.com", "password" : "password", "balance": 150, "userType": "commom" } ]

Remover user http DELETE :8080/users/1

[ {  "id": 1, "firstName":"newName", "lastName": "name 2", "document" : "15345687", "email": "email@gmail.com", "password" : "password", "balance": 150, "userType": "commom" } ] 
[ ]
