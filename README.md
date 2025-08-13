# API de Agendamentos - Spring Boot

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-blue)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

Este projeto é uma **API REST** desenvolvida em **Spring Boot** para gerenciar **agendamentos de serviços** como consultas ou atendimentos.  
Ele foi criado como um desafio pessoal: anteriormente, durante meu curso, desenvolvi um projeto parecido com **duas entidades**, mas desta vez resolvi me desafiar e adicionar uma terceira entidade para lidar com um caso mais completo e realista.

## Objetivo do Projeto
O objetivo é criar um sistema capaz de:
- Cadastrar clientes e serviços
- Agendar atendimentos
- Gerenciar status dos agendamentos
- Aplicar regras de negócio e validações

## Entidades Principais

### **Cliente**
- `id`
- `nome`
- `email`
- `telefone`

### **Servico**
- `id`
- `nome`
- `descricao`
- `preco`

### **Agendamento**
- `id`
- `cliente`
- `servico`
- `dataHora`
- `status`

## Funcionalidades
- **CRUD completo** para `Cliente`, `Servico` e `Agendamento`
- Relacionamento **ManyToOne** entre Agendamento e Cliente/Servico
- Uso de **DTOs** para entrada e saída de dados
- **Validações** (ex.: impedir agendamento no passado)
- Regras de negócio:
  - Alterar status
  - Cancelar agendamento
- **Filtros** para buscar agendamentos por data, cliente ou status
- Personalização de respostas com **ResponseEntity**
- **Mappers** para conversão entre entidades e DTOs
- Testes de endpoints com **Postman**

