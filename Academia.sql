CREATE DATABASE academia;

USE academia;

CREATE TABLE aluno (
aluno_id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
cpf VARCHAR(50) NOT NULL,
data_ingresso DATE NOT NULL
);

CREATE TABLE instrutor (
instrutor_id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
especialidade VARCHAR(20)
);

CREATE TABLE PlanoTreino (
id_plano INT AUTO_INCREMENT PRIMARY KEY,
id_aluno INT,
id_instrutor INT,
descricao VARCHAR(50),
duracao_semanas INT, 
FOREIGN KEY (id_aluno) REFERENCES aluno(aluno_id),
FOREIGN KEY (id_instrutor) REFERENCES instrutor(instrutor_id)
);

CREATE TABLE Frequencia (
id_frequencia INT AUTO_INCREMENT PRIMARY KEY,
id_aluno INT,
data DATE NOT NULL,
presenca BOOLEAN NOT NULL,
FOREIGN KEY (id_aluno) REFERENCES aluno(aluno_id)
);


