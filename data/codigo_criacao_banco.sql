CREATE DATABASE refrigeradb;

USE refrigeradb;

CREATE TABLE usuario_cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE
);
 
CREATE TABLE prestador_servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    especialidade VARCHAR(100),
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE endereco_cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    cep VARCHAR(10) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(50),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    tipo ENUM('residencial', 'comercial', 'outro') NOT NULL DEFAULT 'residencial',
    principal BOOLEAN DEFAULT FALSE,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES usuario_cliente(id) ON DELETE CASCADE
);

CREATE TABLE endereco_prestador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prestador_id INT NOT NULL,
    cep VARCHAR(10) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(50),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    tipo ENUM('sede', 'filial', 'residencial', 'comercial', 'outro') NOT NULL DEFAULT 'comercial',
    principal BOOLEAN DEFAULT FALSE,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (prestador_id) REFERENCES prestador_servico(id) ON DELETE CASCADE
);

CREATE TABLE servicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao TEXT,
    categoria ENUM('manutencao', 'instalacao', 'conserto') NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE agendamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    prestador_id INT,
    servico_id INT NOT NULL,
    data_agendamento DATETIME NOT NULL,
    data_conclusao DATETIME,
    modelo_equipamento VARCHAR(100) NOT NULL,
    primeira_manutencao BOOLEAN DEFAULT FALSE,
    observacoes TEXT,
    endereco_id INT NOT NULL,
    status ENUM('pendente', 'confirmado', 'em_andamento', 'cancelado', 'concluido') DEFAULT 'pendente',
    valor DECIMAL(10,2),
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES usuario_cliente(id),
    FOREIGN KEY (prestador_id) REFERENCES prestador_servico(id),
    FOREIGN KEY (servico_id) REFERENCES servicos(id)
);

CREATE TABLE pagamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    agendamento_id INT NOT NULL,
    metodo ENUM('pix', 'cartao_credito', 'boleto', 'dinheiro') NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    status ENUM('pendente', 'processando', 'pago', 'cancelado', 'reembolsado') DEFAULT 'pendente',
    codigo_transacao VARCHAR(100),
    data_pagamento DATETIME,
    dados_pagamento JSON,
    FOREIGN KEY (agendamento_id) REFERENCES agendamentos(id)
);
