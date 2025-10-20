# API de Cadastro de Veículos - Teste Técnico Tinnova

API RESTful para gerenciamento de veículos, desenvolvida como parte do processo seletivo da Tinnova. O projeto permite listar, criar, editar, excluir e filtrar veículos, além de prover endpoints de estatísticas.

## 📄 Documentação

A documentação OpenAPI (Swagger UI) é gerada automaticamente pela aplicação. Após iniciar o servidor, ela ficará disponível neste endereço:

[http://localhost:8080/q/swagger-ui/](http://localhost:8080/q/swagger-ui/)

## 🛠️ Tecnologias Principais

* **Java 21**
* **Quarkus** (Framework)
* **Hibernate ORM + Panache** (Acesso a dados)
* **Flyway** (Migrações de banco)
* **PostgreSQL** (Banco de Dados)
* **Gradle** (Build)
* **Docker** (Containerização do banco)

## 🚀 Como Executar

1.  **Clone o repositório**
    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```

2.  **Inicie o Banco de Dados**
    O projeto usa Docker Compose para subir o PostgreSQL. As credenciais já estão configuradas no `application.properties`.
    ```bash
    docker-compose up -d
    ```

3.  **Execute a aplicação**
    O Flyway executará as migrações automaticamente ao iniciar.
    ```bash
    ./gradlew quarkusDev
    ```
    A API estará disponível em `http://localhost:8080`.

## 🚀 Exercícios de Lógica
Para executar os exercicios de logica basta executar a classe `MainExercises.java` ela executara os 4 exercicios.
