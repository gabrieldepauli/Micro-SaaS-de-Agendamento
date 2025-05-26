CREATE DATABASE IF NOT EXISTS micro_saas_aulas;
USE micro_saas_aulas;

drop database micro_saas_aulas;

-- Tabela de usuários (genérica para login, com tipo)
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo ENUM('CLIENT', 'TEACHER') NOT NULL
);

select * from usuario;

-- Clientes (alunos)
CREATE TABLE cliente (
    id INT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    adress TEXT,
    phone VARCHAR(20),
    FOREIGN KEY (id) REFERENCES usuario(id) ON DELETE CASCADE
);

select * from cliente;

-- Professores (prestadores)
CREATE TABLE professor (
    id INT PRIMARY KEY,
    businessName VARCHAR(100),
    fullName VARCHAR(100) NOT NULL,
    profilePicture VARCHAR(255),
    specialty VARCHAR(100),
    address TEXT,
    descricao VARCHAR(100),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id) REFERENCES usuario(id) ON DELETE CASCADE
);

select * from professor;

-- Imagens do portfólio do professor
CREATE TABLE imagem_portfolio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    professor_id INT NOT NULL,
    caminho_arquivo VARCHAR(255) NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE CASCADE
);

select * from imagem_portfolio;

-- Dias e horários disponíveis
CREATE TABLE disponibilidade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    professor_id INT NOT NULL,
    dia_semana INT NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    horario_descanso_inicio TIME NOT NULL,
    horario_descanso_fim TIME NOT NULL,
    tempo_servico TIME NOT NULL,
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE CASCADE,
    UNIQUE (professor_id, dia_semana, horario_inicio, horario_fim, horario_descanso_inicio, horario_descanso_fim, tempo_servico)
);

select * from disponibilidade;

-- Agendamentos
CREATE TABLE agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    professor_id INT NOT NULL,
    data_agendamento DATE NOT NULL,
    horario TIME NOT NULL,
    status ENUM('SOLICITADO', 'ACEITO', 'CONCLUIDO') DEFAULT 'SOLICITADO',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (professor_id) REFERENCES professor(id),
    UNIQUE (professor_id, data_agendamento, horario) 
);

select * from agendamento;

-- Log de alterações de status de agendamento
CREATE TABLE log_agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    agendamento_id INT NOT NULL,
    status_anterior ENUM('SOLICITADO', 'ACEITO', 'CONCLUIDO'),
    status_novo ENUM('SOLICITADO', 'ACEITO', 'CONCLUIDO'),
    alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (agendamento_id) REFERENCES agendamento(id)
);

select * from log_agendamento;