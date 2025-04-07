# Atividade em grupo - Projeto Integrador II

## PROJETO REFRIGERA ❄️

- **Faculdade Alpha**
- **Professor Orientador:** Rafael Marinho dos Anjos

### 👥 Equipe

  | Alunos |
  |--------|
  | João Marcelo| https://github.com/Marselor |
  | Maykoo Cunha| https://github.com/MaykooCunha |
  | Rayane Vitória | https://github.com/rayanehonorio/rayanehonorio |
  | Sinval Custódio| https://github.com/SinvalCustodio |
 
## 📌 Introdução

🔹 **O PROJETO REFRIGERA** é uma plataforma digital criada para conectar clientes que precisam de serviços de refrigeração (como manutenção, instalação e reparo de equipamentos) a especialistas competentes no campo.

🎯 **Metas centrais:**
-  Promover o agendamento de serviços de refrigeração de maneira ágil e confiável
-  Proporcionar aos prestadores de serviços um instrumento para administrar suas tarefas
-  Garantir transparência no procedimento por meio de avaliações e registros de serviços
-  Oferecer diversas opções de pagamento integradas

👥 **Destinatários:**
-  Pessoas físicas e jurídicas que necessitem de serviços de refrigeração
-  Profissionais e empresas dedicadas à refrigeração e climatização

✨ **Principais características:**
-  Registro diferenciado para clientes e prestadores
-  Sistema de agendamento integrado com calendário
-  Múltiplas opções de pagamento (Pix, cartão, boleto)

## 🗃️ Banco de Dados

- [Código de criação do BD](./data/codigo_criacao_banco.sql)

## Tabelas


- Usuario_Cliente:

| Coluna          | Tipo          | Descrição                                      |
|----------------|--------------|------------------------------------------------|
| id          | int        | Identificador do cliente (PK)                  |
| nome        | varchar(100)| Nome do cliente                               |
| email       | varchar(100)| Email do cliente (único)                      |
| senha       | varchar(255)| Senha criptografada                           |
| cpf         | varchar(14) | CPF do cliente (único)                        |
| telefone    | varchar(20) | Telefone do cliente                           |
| data_cadastro | datetime  | Data de cadastro                              |
| ativo       | boolean     | Status da conta                               |

-  Prestador_Servico:

| Coluna          | Tipo          | Descrição                                      |
|----------------|--------------|------------------------------------------------|
| id          | int        | Identificador do prestador (PK)                |
| nome        | varchar(100) | Nome/Razão Social                             |
| email       | varchar(100) | Email profissional (único)                    |
| senha       | varchar(255) | Senha criptografada                           |
| cnpj        | varchar(18) | CNPJ da empresa (único)                       |
| telefone    | varchar(20) | Telefone para contato                         |
| especialidade | varchar(100)| Área de atuação                              |
| data_cadastro | datetime  | Data de cadastro                              |
| ativo       | boolean     | Status da conta                               |

- Endereco_Cliente:

| Coluna          | Tipo          | Descrição                                      |
|----------------|--------------|------------------------------------------------|
| id          | int        | Identificador do endereço (PK)                 |
| cliente_id  | int        | ID do cliente (FK)                            |
| cep         | varchar(10) | CEP do endereço                               |
| logradouro  | varchar(100)| Nome da rua/avenida                           |
| numero      | varchar(10) | Número do imóvel                             |
| complemento | varchar(50) | Complemento do endereço                      |
| bairro      | varchar(50) | Bairro                                        |
| cidade      | varchar(50) | Cidade                                        |
| estado      | varchar(2)  | Estado (UF)                                   |
| tipo        | enum       | Tipo de endereço                             |
| principal   | boolean     | Endereço principal                           |
| data_cadastro | datetime  | Data de cadastro                             |

- Endereco_Prestador:

| Coluna          | Tipo          | Descrição                                      |
|----------------|--------------|------------------------------------------------|
| id          | int        | Identificador do endereço (PK)                 |
| prestador_id | int        | ID do prestador (FK)                          |
| cep         | varchar(10) | CEP do endereço                               |
| logradouro  | varchar(100) | Nome da rua/avenida                           |
| numero      | varchar(10) | Número do imóvel                             |
| complemento | varchar(50) | Complemento do endereço                      |
| bairro      | varchar(50) | Bairro                                        |
| cidade      | varchar(50) | Cidade                                        |
| estado      | varchar(2)  | Estado (UF)                                   |
| tipo        | enum       | Tipo de endereço profissional                |
| principal   | boolean     | Endereço principal                           |
| data_cadastro | datetime  | Data de cadastro                             |
| ativo       | boolean     | Status do endereço                           |

- Servicos:

| Coluna          | Tipo          | Descrição                                      |
|----------------|--------------|------------------------------------------------|
| id          | int        | Identificador do serviço (PK)                  |
| nome        | varchar(50) | Nome do serviço                               |
| descricao   | text       | Descrição do serviço                          |
| categoria   | enum       | Categoria do serviço                         |
| ativo       | boolean     | Status do serviço                            |

- Agendamentos:

| Coluna          | Tipo          | Descrição                                      |
|----------------|--------------|------------------------------------------------|
| id          | int        | Identificador do agendamento (PK)              |
| cliente_id  | int        | ID do cliente (FK)                            |
| prestador_id| int        | ID do prestador (FK)                          |
| servico_id  | int        | ID do serviço (FK)                            |
| data_agendamento | datetime | Data e hora do agendamento                   |
| data_conclusao | datetime  | Data de conclusão                            |
| modelo_equipamento | varchar(100) | Modelo do equipamento                      |
| primeira_manutencao | boolean | Primeira manutenção                         |
| observacoes | text       | Observações                                  |
| endereco_id | int        | ID do endereço (FK)                          |
| status      | enum       | Status do agendamento                        |
| valor       | decimal(10,2) | Valor do serviço                            |
| data_criacao | datetime  | Data de criação                              |

- Pagamentos:

| Coluna          | Tipo          | Descrição                                      |
|----------------|--------------|------------------------------------------------|
| id          | int        | Identificador do pagamento (PK)                |
| agendamento_id | int     | ID do agendamento (FK)                        |
| metodo      | enum       | Método de pagamento                           |
| valor       | decimal(10,2) | Valor do pagamento                          |
| status      | enum       | Status do pagamento                          |
| codigo_transacao | varchar(100) | Código da transação                      |
| data_pagamento | datetime  | Data do pagamento                           |
| dados_pagamento | json     | Dados do pagamento                          |

