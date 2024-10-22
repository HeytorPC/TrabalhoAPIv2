# E-commerce API

# Feito por:

Heytor Pinel Cantelmo da Silva
Guilherme Senna Pires
Gabriel dos Santos Siqueira
Mateus Azevedo Faria
Victor Hugo Barros Schuenk
Vitor Ferreira Leite de Paula


## Descrição

Este projeto consiste no desenvolvimento de uma API RESTFul para um E-commerce, com base nas regras de negócio e requisitos descritos neste documento. O objetivo é implementar uma solução de e-commerce com funcionalidades completas de gestão de pedidos, produtos, clientes e autenticação de usuários.

Cada grupo desenvolverá um tema de e-commerce diferente, garantindo que todos os requisitos e regras de negócio sejam atendidos.

## Funcionalidades

- API com endpoints para gerenciar pedidos, clientes, produtos, e relatórios.
- Métodos CRUD (Create, Read, Update, Delete) para todos os recursos da API.
- Uso de DTOs (Data Transfer Objects) para transição dos dados nos métodos CRUD.
- Relatório detalhado de pedidos, contendo:
  - ID do pedido
  - Data do pedido
  - Valor total
  - Itens do pedido com código, nome do produto, preço unitário, quantidade, percentual de desconto e valor líquido.
- Tratamento de exceções e validações com mensagens personalizadas.
- Envio automático de e-mail contendo o Relatório de Pedido após cada novo cadastro de pedido.
- Autenticação e controle de acesso utilizando Spring Security com JWT.

## Requisitos

- **Banco de dados**: O banco de dados será criado de acordo com o Diagrama Entidade-Relacionamento (DER) fornecido, utilizando scripts de migração com o Flyway.
- **CRUD**: Todos os recursos da API terão métodos CRUD totalmente funcionais.
- **DTOs**: Utilizados para a transição de dados nos métodos CRUD, facilitando a comunicação entre as camadas da aplicação.
- **Serviços**: Implementação de classes de serviço para garantir desacoplamento entre Repository e Controladores.
- **Relatório de Pedidos**: Geração de relatório detalhado dos pedidos, com cálculo de valores bruto e líquido.
- **Validações**: Prevenção de erros de cadastro com validações personalizadas.
- **Envio de e-mails**: Envio de e-mail automático contendo o Relatório de Pedido sempre que um novo pedido for cadastrado.
- **Autenticação**: Implementação de autenticação e controle de acesso com Spring Security + JWT.
- **Consulta de CEP**: Integração com API externa para obter os dados de endereço do cliente com base no CEP.

## Regras de Negócio

- **Cálculo de valores**: 
  - Valor bruto de cada item (preço de venda x quantidade).
  - Valor líquido (valor bruto - desconto, calculado com base no percentual de desconto aplicado).
  - Cálculo e armazenamento do valor total do pedido (soma dos valores líquidos dos itens).
- **Restrições**:
  - Data de pedido não pode ser retroativa.
  - Produtos com descrições idênticas não podem ser cadastrados.
  - Clientes com CPF ou e-mail duplicados não podem ser cadastrados.

## Regras Opcionais

- Validação de pedidos com data retroativa.
- Garantia de unicidade na descrição de produtos, CPF e e-mails de clientes.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework utilizado para construir a API.
- **Flyway**: Gerenciamento de migrações de banco de dados.
- **Spring Data JPA**: Interface para manipulação dos dados no banco de dados.
- **Spring Security + JWT**: Implementação de autenticação e autorização.
- **Swagger**: Documentação automática da API.
- **Trello**: Ferramenta de gestão de tarefas para acompanhamento do desenvolvimento.

# TrabalhoAPIv2
