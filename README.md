# clientdatabase
Aplicação para gerenciamento de um banco de clientes de uma empresa

# Como subir o projeto:

- Necessario ter o docker instalado na maquina para rodar o banco de dados
- Comandos a executar no docker:
    -docker pull postgres
    -docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres
    -docker exec -it postgres psql -U postgres --password & digitar a senha 1234 quando requisitado (para acessar o banco via terminal)
    
- Subir o Spring normal pela classe de Aplication e o projeto estará pronto para ser utilizado!
