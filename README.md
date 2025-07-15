# 📦 Estoque API

API de Controle de Estoque desenvolvida com **Java 21**, **Spring Boot**, **Docker**, **SQL Server** e **Angular**. O projeto segue princípios de **DDD (Domain-Driven Design)**, possui **alta cobertura de testes unitários (>90%)**, ambientes de execução separados (**dev** e **prod**) e pipeline com validações automáticas para **PR** e **push**.

---

## 🧱 Tecnologias Utilizadas

- Java 21 + Spring Boot
- SQL Server (via Docker)
- Angular (2 profiles: `admin` e `operador`)
- Docker + Docker Compose
- JUnit + Mockito
- Jacoco (para cobertura de testes)
- GitHub Actions / GitLab CI (pipeline de CI/CD)
- DDD (Domain, Application, Infrastructure, Web)

---

## 🧠 Principais Funcionalidades

- ✅ Cadastro de produtos com níveis mínimos
- ✅ Entradas e saídas de estoque
- ✅ Validação de saldo e regras de negócio
- ✅ Controle de concorrência (otimista)
- ✅ Autenticação com JWT
- ✅ Relatórios por período e movimentação
- ✅ Controle multiusuário (admin vs operador)

---

## 🔐 Segurança

- JWT Token
- Autenticação por perfil
- Controle de acesso por endpoint e regra de domínio

---

## 🧪 Testes

- >90% de cobertura de código com **JUnit 5 + Mockito**
- Estratégia de testes:
    - Unitários para camada de domínio
    - Mockados para controllers e serviços
- Validações automatizadas no pipeline (CI)

---

## 🛠️ Como Executar

### 🧪 Ambiente de Desenvolvimento

Pré-requisitos:
- Java 21
- Docker + Docker Compose
- Git

```bash
# Suba o ambiente com banco de dados e API
docker compose up --build
```

## 🌐 URL da API

- [http://localhost:8080](http://localhost:8080)

---

## 📦 Ambiente de Produção

O profile `prod` usa SQL Server via Docker com configuração de autenticação, persistência e `application-prod.properties`.

```bash
# Suba o ambiente com banco de dados e API
mvn clean install -DskipTests
docker compose up --build
```

Chamadas para a API devem ser feitas em `http://localhost:8080/
```bash
# Cria ADMIN POST
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--data '{
    "username": "admin",
    "password": "123",
    "role": "ADMIN"
}'
```
```bash

# Cria USER POST
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--data '{
"username": "user",
"password": "124",
"role": "USER"
}'
```
```bash
# Autentica usuario POST
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "admin",
    "password": "124"
}'
```

```bash
# Cria produto PUT
curl --location 'http://localhost:8080/produtos' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzUyNTUyOTYxLCJleHAiOjE3NTI1NTQ3NjF9.LJVU9tv3DEODVck-VOqNE4lVK1jOZhkkeyCW157btss' \
--data '{
    "nome": "Teclado",
    "quantidade": 10,
    "quantidadeMinima": 10,
    "versao": 0
}'
```
```bash
# Lista produtos
curl --location 'http://localhost:8080/produtos' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzUyNTUyOTYxLCJleHAiOjE3NTI1NTQ3NjF9.LJVU9tv3DEODVck-VOqNE4lVK1jOZhkkeyCW157btss'
```

```bash
# Busca produto por ID GET
curl --location 'http://localhost:8080/produtos/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzUyNTUyOTYxLCJleHAiOjE3NTI1NTQ3NjF9.LJVU9tv3DEODVck-VOqNE4lVK1jOZhkkeyCW157btss' \
--data '{
    "nome": "Teclado",
    "quantidade": 10,
    "quantidadeMinima": 10,
    "versao": 0
}'
```

```bash
# Atualiza produto PUT
curl --location 'http://localhost:8080/produtos/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzUyNTUyOTYxLCJleHAiOjE3NTI1NTQ3NjF9.LJVU9tv3DEODVck-VOqNE4lVK1jOZhkkeyCW157btss' \
--data '{
    "nome": "Teclado",
    "quantidade": 10,
    "quantidadeMinima": 10,
    "versao": 0
}'
```

```bash
# Deleta produto por ID DELETE
curl --location 'http://localhost:8080/produtos/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzUyNTUyOTYxLCJleHAiOjE3NTI1NTQ3NjF9.LJVU9tv3DEODVck-VOqNE4lVK1jOZhkkeyCW157btss' \
```


```bash
# Executa com o profile prod
SPRING_PROFILES_ACTIVE=prod java -jar target/estoque-0.0.1-SNAPSHOT.jar
```

## 📁 Estrutura do Projeto

```text
estoque/
├── domain/              # Agregados, entidades, objetos de valor
├── application/         # Casos de uso e interfaces de serviço
├── infrastructure/      # Repositórios, conexões externas, JWT
├── web/                 # Controllers e DTOs
├── config/              # Configurações (Security, Swagger, etc)
├── test/                # Testes unitários e de integração
├── resources/           # Arquivos estáticos, templates, etc
├── Dockerfile           # Dockerfile para produção
├── docker-compose.yml   # Docker Compose para dev
├── pom.xml              # Dependências do Maven
└── README.md            # Documentação do projeto
``` 
## 🔄 Pipeline CI/CD

A aplicação conta com um pipeline automatizado com:

- ✅ Validação de PRs e Pushes
- ✅ Execução de testes unitários
- ✅ Verificação de cobertura com Jacoco
- ✅ Build da aplicação
- 🚀 (Opcional) Deploy automatizado  

## 📜 Testes Locais

```bash
# Executa os testes unitários
./mvnw test

# Gera relatório de cobertura
./mvnw jacoco:report
# Gera o JAR da aplicação
./mvnw clean package
```

## ✅ Status

- Backend finalizado com alta cobertura de testes
- Pipeline de CI funcionando
- Docker Compose com SQL Server
- Separação de ambientes (dev/prod)
- DDD aplicado
- Segurança com JWT
- Deploy na nuvem (em progresso)

## ✒️ Autor



**Filipe Ferreira**  
Desenvolvedor Java Sênior | Spring | AWS | Docker | Kafka  
🔗 [LinkedIn](https://www.linkedin.com/in/seu-usuario-aqui)
