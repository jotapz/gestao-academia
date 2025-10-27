-- Criando o banco de dados --
CREATE DATABASE academia;

-- Usando o SCHEMA academia --
USE academia;

-- Criando a tabela aluno --
CREATE TABLE aluno (
id_aluno INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
cpf VARCHAR(50) NOT NULL,
data_ingresso DATE NOT NULL
);

-- Criando a tabela instrutor --
CREATE TABLE instrutor (
id_instrutor INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
especialidade VARCHAR(20)
);

-- Criandoo a tabela PlanoTreino --
CREATE TABLE PlanoTreino (
id_plano INT AUTO_INCREMENT PRIMARY KEY,
id_aluno INT,
id_instrutor INT,
descricao VARCHAR(50),
duracao_semanas INT, 
FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno),
FOREIGN KEY (id_instrutor) REFERENCES instrutor(id_instrutor)
);

-- Criando a tabela Frequencia --
CREATE TABLE Frequencia (
id_frequencia INT AUTO_INCREMENT PRIMARY KEY,
id_aluno INT,
data DATE NOT NULL,
presenca BOOLEAN NOT NULL,
FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno)
);
