CREATE DATABASE IF NOT EXISTS micro_saas_aulas;
USE micro_saas_aulas;

-- Tabela de usuários (genérica para login, com tipo)
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo ENUM('CLIENT', 'TEACHER') NOT NULL
);

-- Clientes (alunos)
CREATE TABLE cliente (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    endereco TEXT,
    telefone VARCHAR(20),
    FOREIGN KEY (id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Professores (prestadores)
CREATE TABLE professor (
    id INT PRIMARY KEY,
    nome_fantasia VARCHAR(100),
    nome_completo VARCHAR(100) NOT NULL,
    foto_perfil VARCHAR(255),
    especialidade VARCHAR(100),
    endereco TEXT,
    descricao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Imagens do portfólio do professor
CREATE TABLE imagem_portfolio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    professor_id INT NOT NULL,
    caminho_arquivo VARCHAR(255) NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE CASCADE
);

-- Dias e horários disponíveis
CREATE TABLE disponibilidade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    professor_id INT NOT NULL,
    dia_semana ENUM('DOM', 'SEG', 'TER', 'QUA', 'QUI', 'SEX', 'SAB') NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE CASCADE
);

-- Cidades de atuação (limitadas)
CREATE TABLE cidade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Relaciona professor com as cidades onde atua
CREATE TABLE professor_cidade (
    professor_id INT,
    cidade_id INT,
    PRIMARY KEY (professor_id, cidade_id),
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE CASCADE,
    FOREIGN KEY (cidade_id) REFERENCES cidade(id) ON DELETE CASCADE
);

-- Agendamentos
CREATE TABLE agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    professor_id INT NOT NULL,
    data DATE NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    status ENUM('SOLICITADO', 'ACEITO', 'CONCLUIDO') DEFAULT 'SOLICITADO',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

-- Log de alterações de status de agendamento
CREATE TABLE log_agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    agendamento_id INT NOT NULL,
    status_anterior ENUM('SOLICITADO', 'ACEITO', 'CONCLUIDO'),
    status_novo ENUM('SOLICITADO', 'ACEITO', 'CONCLUIDO'),
    alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (agendamento_id) REFERENCES agendamento(id)
);