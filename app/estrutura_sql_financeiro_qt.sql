CREATE DATABASE IF NOT EXISTS financeiro;
use financeiro;

CREATE TABLE administradores (
    id int(11) NOT NULL AUTO_INCREMENT,
    nome varchar(20),
    cpf varchar(14),
    senha varchar(255),
	PRIMARY KEY (id)
);

CREATE TABLE categorias (
    id int NOT NULL AUTO_INCREMENT,
    descricao varchar(20),
    PRIMARY KEY (id)
);

CREATE TABLE usuarios (
    id int(11) NOT NULL AUTO_INCREMENT,
    nome varchar(20),
    cpf varchar(14),
    senha varchar(255),
    suspenso varchar(1),
    PRIMARY KEY (id)
);

CREATE TABLE contas (
    id INT(11) NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    nome_conta varchar(20),
    banco varchar(3),
    agencia varchar(6),
    conta_corrente varchar(6),
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) references usuarios (id) on delete cascade
);

CREATE TABLE lancamentos (
    id INT(11) NOT NULL AUTO_INCREMENT,
    id_conta INT NOT NULL,
    id_categoria INT NOT NULL,
    valor decimal(10,2),
    operacao varchar(1),
	token varchar(36);,
    data date,
    descricao varchar(100),
	processado varchar(1) DEFAULT 'N',
    PRIMARY KEY (id),
    FOREIGN KEY (id_conta) references contas (id) on delete cascade,    
    FOREIGN KEY (id_categoria) references categorias (id) on delete cascade    
);