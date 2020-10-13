# clientdatabase
Aplicação para gerenciamento de um banco de clientes de uma empresa

# Como subir o projeto:

- Necessario ter o docker instalado na maquina para rodar o banco de dados
- Necessario Jdk 8 ou superior
- Necessario Postman para testes das api's
- Comandos a executar no docker:

* docker pull postgres
* docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres
* docker exec -it postgres psql -U postgres --password & digitar a senha 1234 quando requisitado (para acessar o banco via terminal)
    
- Para executar a aplicação, navegue ate a pasta que contenha o pom.xml e rode o comando 

* mvn spring-boot:run

-Uma coleção com requisições de exemplo está disponivel na raiz do projeto
