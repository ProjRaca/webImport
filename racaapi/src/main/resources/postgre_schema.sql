CREATE TABLE raca.usuario(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120),
    email VARCHAR(50),
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    admin BOOLEAN DEFAULT FALSE,
    status VARCHAR(2) NOT NULL,
	endereco VARCHAR(255),
	bairro VARCHAR(100),
	municipio VARCHAR(100),
	tipo VARCHAR(50)
);


CREATE TABLE raca.contas (
    ID SERIAL PRIMARY KEY,
  matriculafuncionario INTEGER NOT NULL,
  nomefuncionario VARCHAR(60) NOT NULL,
  cpffuncionario VARCHAR(11) NOT NULL,
  iddepartamento VARCHAR(3) NOT NULL,
  tipoparceiro CHAR(1) DEFAULT NULL,
  idconta VARCHAR(6) NOT NULL,
  descricao VARCHAR(45) NOT NULL
);

CREATE TABLE raca.historico (
  idhistorico SERIAL PRIMARY KEY,
  descricao VARCHAR(45) NOT NULL
);

CREATE TABLE raca.movimentacao (
  idmovimentacao SERIAL PRIMARY KEY,
  codigofilial VARCHAR(1) DEFAULT NULL,
  cnpjempresa VARCHAR(14) DEFAULT NULL,
  idfuncionario INTEGER DEFAULT NULL,
  nomefuncionario VARCHAR(100) DEFAULT NULL,
  cpffuncionario VARCHAR(11) DEFAULT NULL,
  tipoparceiro CHAR(1) DEFAULT NULL,
  contaDebito VARCHAR(6) DEFAULT NULL,
  historico VARCHAR(60) DEFAULT NULL,
  agencia VARCHAR(15) DEFAULT NULL,
  agenciadv VARCHAR(2) DEFAULT NULL,
  contacorrente VARCHAR(15) DEFAULT NULL,
  contacorrentedv VARCHAR(2) DEFAULT NULL,
  valor DECIMAL(10,2) DEFAULT NULL,
  vencimento DATE DEFAULT NULL,
  competencia DATE DEFAULT NULL,
  nota VARCHAR(7) DEFAULT NULL,
  status CHAR(1) DEFAULT NULL,
  ultimousuario VARCHAR(15) DEFAULT NULL,
  ultimaalteracao TIMESTAMP DEFAULT NULL
);

CREATE TABLE raca.documento (
    id SERIAL PRIMARY KEY,
    filial VARCHAR(100),
    emissor VARCHAR(100),
    datadocumentesc DATE,
    datavalidade DATE,
    documento BYTEA
);

CREATE TABLE raca.responsavel (
  id SERIAL PRIMARY KEY,
  cpfcnpj VARCHAR(20) NOT NULL,
  nome VARCHAR(255),
	email VARCHAR(120),
telefone VARCHAR(20));

