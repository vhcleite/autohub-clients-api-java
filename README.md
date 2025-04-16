# AutoHub Clients API (Java)

API responsável pelo gerenciamento de perfis de clientes da plataforma de revenda de veículos AutoHub. Esta versão é
implementada em Java com Maven.

## Índice

* [Descrição](#descrição)
* [Tecnologias Principais](#tecnologias-principais)
* [Pré-requisitos](#pré-requisitos)
* [Configuração do Ambiente de Desenvolvimento](#configuração-do-ambiente-de-desenvolvimento)
* [Rodando a Aplicação Localmente](#rodando-a-aplicação-localmente)
* [Construindo o Artefato para Deploy](#construindo-o-artefato-para-deploy)
* [Testando a API Localmente](#testando-a-api-localmente)
* [Documentação da API](#documentação-da-api)
* [Infraestrutura e Deploy](#infraestrutura-e-deploy)

## Descrição

Este microserviço fornece endpoints RESTful para criar, atualizar, consultar e deletar informações de perfil de
clientes. A autenticação é gerenciada pelo AWS Cognito e os dados são persistidos no AWS DynamoDB (gerenciados em um
repositório de infraestrutura separado). A API é projetada para rodar no AWS Lambda via API Gateway, utilizando a
biblioteca `aws-serverless-java-container`.

## Tecnologias Principais

* **Java 17**
* **Spring Boot 3.2.x**
* **Maven**
* **Arquitetura Hexagonal** (Ports & Adapters)
* **AWS DynamoDB** (Integração via AWS SDK v2 Enhanced Client)
* **AWS Cognito** (Autenticação via JWT - Infra gerenciada separadamente)
* **AWS Lambda + API Gateway** (Plataforma de deploy alvo - Infra gerenciada separadamente)
* **`aws-serverless-java-container`** (Para rodar Spring Boot no Lambda)
* **Docker / Docker Compose / LocalStack** (Ambiente de desenvolvimento local)
* **Springdoc OpenAPI (Swagger)** (Documentação da API)
* **MapStruct** (Mapeamento entre camadas)
* **Maven Shade Plugin** (Empacotamento para Lambda)

## Pré-requisitos

Antes de começar, garanta que você tenha as seguintes ferramentas instaladas:

* **JDK 17** (Certifique-se que seu `JAVA_HOME` aponta para ele)
* **Maven** (Pode usar o Maven Wrapper incluído - `./mvnw`)
* **Docker e Docker Compose**

## Configuração do Ambiente de Desenvolvimento

1. **Iniciar Ambiente Local (LocalStack + DynamoDB):**
    * Este projeto usa Docker Compose (`docker-compose.yml`) para iniciar o LocalStack, que simula o DynamoDB
      localmente. Um script de inicialização (`localstack/init/setup.sh`) cria a tabela automaticamente.
    * Execute na raiz do projeto:
      ```bash
      docker-compose up -d
      ```
    * **Verificação:**
      ```bash
      docker ps # Deve mostrar 'localstack_revenda' como 'Up'
      curl http://localhost:4566/health # Deve mostrar dynamodb 'running'
      # Logs: docker logs localstack_revenda
      ```

2. **Configuração da Aplicação:**
    * As configurações principais estão em `src/main/resources/application.yml`.
    * Configurações específicas para o ambiente local (endpoint do LocalStack, credenciais dummy) estão em
      `src/main/resources/application-local.yml`.
    * O perfil Spring `local` é ativado por padrão via `application.yml` (
      `spring.profiles.active: ${ENVIRONMENT:local}`) se a variável de ambiente `ENVIRONMENT` não estiver definida. Ele
      é necessário para conectar ao LocalStack.

## Rodando a Aplicação Localmente

1. **Via IDE:**
    * Importe o projeto como um projeto Maven (ex: no IntelliJ IDEA, abra o arquivo `pom.xml`).
    * Configure uma execução Spring Boot para a classe principal (`AutohubClientsApiJavaApplication.java`).
    * **Importante:** Certifique-se de que o perfil Spring `local` esteja ativo nas configurações de execução da IDE (
      geralmente em "VM options" `-Dspring.profiles.active=local` ou na interface da IDE).
    * Execute a aplicação.

2. **Via Maven Wrapper:**
    * Execute no terminal, na raiz do projeto:
      ```bash
      ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
      ```
    * A API estará disponível por padrão em `http://localhost:8080`.

## Construindo o Artefato para Deploy

Para gerar o JAR que será enviado ao AWS Lambda (o "uber JAR" criado pelo Shade plugin):

```bash
./mvnw clean package
```

## Infraestrutura e Deploy

A infraestrutura AWS (Cognito, DynamoDB, Lambda, API Gateway, IAM Roles, S3 Buckets) necessária para esta API é
gerenciada como código usando Terraform em um **repositório separado**:

* **Repositório Terraform:
  ** [https://github.com/vhcleite/autohub-clients-terraform](https://github.com/vhcleite/autohub-clients-terraform)

O deploy da aplicação no AWS Lambda é feito através da atualização da função Lambda (gerenciada pelo Terraform naquele
repositório) para usar o artefato JAR gerado por este projeto (ver seção "Construindo o Artefato para Deploy"). Consulte
o README do repositório de infraestrutura para detalhes sobre como aplicar o Terraform e realizar o deploy.