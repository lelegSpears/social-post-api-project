# REST API de Mensagens

API REST desenvolvida em Java utilizando Spring Boot para gerenciamento de mensagens.

O projeto segue arquitetura em camadas (Controller, Service e Repository) e utiliza Spring Data JPA para persistência de dados.

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Maven
- Git

## Arquitetura do projeto

A aplicação foi estruturada seguindo o padrão de camadas comum em aplicações backend:

- **Entity** → representação da entidade Message
- **Repository** → acesso aos dados utilizando JPA
- **Service** → regras de negócio
- **Controller (Resource)** → exposição dos endpoints REST

## Endpoints

### Criar mensagem

POST /messages

Exemplo de requisição:

```json
{
  "content": "Hello World"
}
