
CREATE TABLE raca.tblcontas (
  matriculafuncionario INTEGER NOT NULL,
  nomefuncionario VARCHAR(60) NOT NULL,
  cpffuncionario VARCHAR(11) NOT NULL,
  iddepartamento VARCHAR(3) NOT NULL,
  tipoparceiro CHAR(1) DEFAULT NULL,
  idconta VARCHAR(6) NOT NULL,
  descricao VARCHAR(45) NOT NULL
);

CREATE TABLE raca.tblhistorico (
  idhistorico SERIAL PRIMARY KEY,
  descricao VARCHAR(45) NOT NULL
);

CREATE TABLE raca.tblmovimentacao (
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

ALTER TABLE raca.usuario
ADD COLUMN descricao VARCHAR(120),
ADD COLUMN endereco VARCHAR(255),
ADD COLUMN bairro VARCHAR(100),
ADD COLUMN municipio VARCHAR(100),
ADD COLUMN tipo VARCHAR(50),
ADD COLUMN status VARCHAR(20);

CREATE TABLE raca.documento (
    id SERIAL PRIMARY KEY,
    filial VARCHAR(100),
    emissor VARCHAR(100),
    datadocumentesc DATE,
    datavalidade DATE,
    documento BYTEA
);