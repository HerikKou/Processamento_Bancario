# Gateway de Pagamentos 

## Sobre o Projeto

Este projeto simula um fluxo real de processamento de pagamentos utilizado em ambientes financeiros, onde usuários externos enviam requisições de pagamento e o sistema interno é responsável por validar saldo, processar a transação e retornar o status final da operação.

A proposta representa um cenário comum em grandes empresas e bancos, onde sistemas internos precisam ser disponibilizados de forma segura, escalável e resiliente para usuários externos, com comunicação assíncrona via Kafka aproximando a solução de uma arquitetura real de mercado financeiro.

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot 4.0.5
- Spring Security
- Spring Cloud Gateway
- Apache Kafka
- MySQL 8
- Docker e Docker Compose
- JWT (Auth0)
- JUnit 5 e Mockito
- Em breve Datadog e AWS
---

## Arquitetura

O sistema é composto por 4 microsserviços independentes, um API Gateway e uma infraestrutura com Kafka e MySQL, todos orquestrados via Docker Compose.

<img width="946" height="685" alt="image" src="https://github.com/user-attachments/assets/0d5dddc2-7627-47f4-85e7-0c5277c75705" />




Cada microsserviço possui seu próprio banco de dados, garantindo independência, baixo acoplamento e maior facilidade de escalabilidade.

---

## Microsserviços

### ClienteService
Responsável pelo cadastro e autenticação de clientes. Gera o token JWT após o login e publica eventos no Kafka quando um novo cliente é cadastrado.

### ContaService
Consome o evento de cadastro do ClienteService e cria automaticamente uma conta bancária para o cliente. Expõe endpoints para consulta e validação de saldo.

### TransacaoService
Consome eventos do ContaService e processa as transações financeiras entre contas. Publica eventos para o PagamentoService após o processamento.

### PagamentoService
Consome os eventos do TransacaoService e realiza o processamento final do pagamento, registrando o tipo e o status da operação.

---

## Modelagem

### Entidades

**Cliente**
- ID, Nome, Email, CPF, Telefone, Senha, Role

**Conta**
- ID, NumeroConta, ClienteID, Saldo, TipoConta, Agência

**Transacao**
- ID, ContaID, ContaDestinoID, ClienteID, Valor, Status, Data, Tipo

**Pagamento**
- ID, TransacaoID, StatusPagamento, TipoPagamento

### Relacionamentos

| Entidade | Relacionamento | Entidade |
|---|---|---|
| Cliente | 1:N | Conta |
| Conta | 1:N | Transacao |
| Transacao | 1:1 | Pagamento |

---

## Segurança

A segurança da aplicação é baseada em JWT com Spring Security, com os seguintes componentes:

- **SecurityFilter** — intercepta requisições e valida o token JWT no header Authorization
- **TokenConfig** — gera e valida tokens JWT com expiração de 24 horas
- **AuthConfig** — localiza o usuário pelo email para autenticação no Spring Security
- **BCrypt** — criptografa senhas antes de armazenar no banco
- **SecurityConfig** — define rotas públicas e privadas por role
- **JWTClienteData** — representa os dados extraídos do token
- **Role** — enum com os perfis USER e ADMIN

---

## Tópicos Kafka

| Tópico | Produtor | Consumidor |
|---|---|---|
| cliente_cadastrado | ClienteService | ContaService |
| conta_criada | ContaService | TransacaoService |
| transacao_realizada | TransacaoService | PagamentoService |

---

## Endpoints

### ClienteService — porta 8081

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| POST | /api/client/registry | Público | Cadastro de cliente |
| POST | /api/client/login | Público | Login e geração de token |
| GET | /api/client/getAllClient | ADMIN | Lista todos os clientes |

### ContaService — porta 8082

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| GET | /api/conta/checkBalance | USER | Consulta saldo |
| GET | /api/conta/validateBalance | USER | Valida saldo suficiente |
| GET | /api/conta/getAllAccount | ADMIN | Lista todas as contas |

### TransacaoService — porta 8083

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| POST | /api/transacao/create | USER | Cria uma transação |
| GET | /api/transacao/searchById/{id} | USER | Busca transação por ID |
| GET | /api/transacao/searchByAccount/{accountId} | USER | Busca por conta |
| GET | /api/transacao/searchByClient/{clientId} | USER | Busca por cliente |

---

## Como Rodar o Projeto

### Pré-requisitos

- Docker Desktop instalado e rodando
- Java 17
- Maven

### Subindo o ambiente completo

Clone o repositório e na pasta raiz do projeto rode:

```bash
docker-compose up -d
```

Para acompanhar os logs:

```bash
docker-compose logs -f
```

Para parar tudo:

```bash
docker-compose down
```

### Testando a aplicação

1. Cadastre um cliente:
```
POST http://localhost:8081/api/client/registry
```

2. Faça o login e copie o token retornado:
```
POST http://localhost:8081/api/client/login
```

3. Use o token nas próximas requisições no header:
```
Authorization: Bearer seu_token_aqui
```

4. Consulte o saldo da conta criada automaticamente:
```
GET http://localhost:8082/api/conta/checkBalance?accountId=1
```

---

## Estrutura do Projeto

```
ProcessamentoBancario/
├── ServiceCliente/
├── ServiceConta/
├── ServiceTransacao/
├── ServicePagamento/
├── Gateway/
└── docker-compose.yml
```

---

## Autor

Desenvolvido por Herik Kato
