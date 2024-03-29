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
  matriculafuncionario INTEGER NOT NULL,
  nomefuncionario VARCHAR(60) NOT NULL,
  cpffuncionario VARCHAR(11) NOT NULL,
  iddepartamento VARCHAR(3) NOT NULL,
  tipoparceiro CHAR(1) DEFAULT NULL,
  idconta VARCHAR(6) NOT NULL,
  descricao VARCHAR(45) NOT NULL
);

CREATE TABLE raca.historico (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(45) NOT NULL
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


INSERT INTO raca.contas
VALUES(7897,'ADEMIR AZEVEDO DA SILVA','38593041434',201,'L',201027,'FERIAS');
INSERT INTO raca.contas
VALUES(7897,'ADEMIR AZEVEDO DA SILVA','38593041434',201,'L',201015,'COMISSAO DE VENDEDORES');
INSERT INTO raca.contas
VALUES(7897,'ADEMIR AZEVEDO DA SILVA','38593041434',201,'L',201025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7897,'ADEMIR AZEVEDO DA SILVA','38593041434',201,'L',201028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7897,'ADEMIR AZEVEDO DA SILVA','38593041434',201,'L',201044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7995,'ALBERIS LUIZ DOS SANTOS FILHO','04931303498',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7995,'ALBERIS LUIZ DOS SANTOS FILHO','04931303498',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7995,'ALBERIS LUIZ DOS SANTOS FILHO','04931303498',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7995,'ALBERIS LUIZ DOS SANTOS FILHO','04931303498',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7898,'ALCIMERE PALMEIRA GUIMARAES','65593588449',201,'L',201025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7898,'ALCIMERE PALMEIRA GUIMARAES','65593588449',201,'L',201027,'FERIAS');
INSERT INTO raca.contas
VALUES(7898,'ALCIMERE PALMEIRA GUIMARAES','65593588449',201,'L',201015,'COMISSAO DE VENDEDORES');
INSERT INTO raca.contas
VALUES(7898,'ALCIMERE PALMEIRA GUIMARAES','65593588449',201,'L',201028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7898,'ALCIMERE PALMEIRA GUIMARAES','65593588449',201,'L',201044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(36,'ALEXANDRE ALVES DE MOURA ','74590600463',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(36,'ALEXANDRE ALVES DE MOURA ','74590600463',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(36,'ALEXANDRE ALVES DE MOURA ','74590600463',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(36,'ALEXANDRE ALVES DE MOURA ','74590600463',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7994,'ALEXANDRE BARBOSA DO NASCIMENTO','05629907476',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7994,'ALEXANDRE BARBOSA DO NASCIMENTO','05629907476',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7994,'ALEXANDRE BARBOSA DO NASCIMENTO','05629907476',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7994,'ALEXANDRE BARBOSA DO NASCIMENTO','05629907476',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(58,'ALEXANDRE FERREIRA ','08501104400',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(58,'ALEXANDRE FERREIRA ','08501104400',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(58,'ALEXANDRE FERREIRA ','08501104400',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(58,'ALEXANDRE FERREIRA ','08501104400',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7997,'ALEXANDRO ANTONIO DA SILVA','03975414499',205,'L',205027,'FERIAS');
INSERT INTO raca.contas
VALUES(7997,'ALEXANDRO ANTONIO DA SILVA','03975414499',205,'L',205044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7997,'ALEXANDRO ANTONIO DA SILVA','03975414499',205,'L',205025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7993,'ALUIZIO OLIVEIRA DE LIMA JUNIOR','01542544440',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7993,'ALUIZIO OLIVEIRA DE LIMA JUNIOR','01542544440',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7993,'ALUIZIO OLIVEIRA DE LIMA JUNIOR','01542544440',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7993,'ALUIZIO OLIVEIRA DE LIMA JUNIOR','01542544440',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(72,'AMANDA BEATRIZ DA SILVA GOMES','71037835433',205,'L',205025,'SALARIOS');
INSERT INTO raca.contas
VALUES(72,'AMANDA BEATRIZ DA SILVA GOMES','71037835433',205,'L',205044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(72,'AMANDA BEATRIZ DA SILVA GOMES','71037835433',205,'L',205027,'FERIAS');
INSERT INTO raca.contas
VALUES(7978,'ANDERSON AUGUSTO SANTOS SILVA','06860955457',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7978,'ANDERSON AUGUSTO SANTOS SILVA','06860955457',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7978,'ANDERSON AUGUSTO SANTOS SILVA','06860955457',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7978,'ANDERSON AUGUSTO SANTOS SILVA','06860955457',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7922,'ANDRE LUIS SILVA SANTOS','10407175431',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7922,'ANDRE LUIS SILVA SANTOS','10407175431',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7922,'ANDRE LUIS SILVA SANTOS','10407175431',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7922,'ANDRE LUIS SILVA SANTOS','10407175431',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7923,'ANTONIO DA PAZ MARTINS','02694178421',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7923,'ANTONIO DA PAZ MARTINS','02694178421',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7923,'ANTONIO DA PAZ MARTINS','02694178421',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7923,'ANTONIO DA PAZ MARTINS','02694178421',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(57,'ANTONIO DE FREITAS LINS ','71252061404',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(57,'ANTONIO DE FREITAS LINS ','71252061404',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(57,'ANTONIO DE FREITAS LINS ','71252061404',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(57,'ANTONIO DE FREITAS LINS ','71252061404',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7924,'ANTONIO GOMES DA SILVA','45183813434',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7924,'ANTONIO GOMES DA SILVA','45183813434',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7924,'ANTONIO GOMES DA SILVA','45183813434',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7924,'ANTONIO GOMES DA SILVA','45183813434',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7925,'ANTONIO JOSE DA SILVA','02245046498',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7925,'ANTONIO JOSE DA SILVA','02245046498',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7925,'ANTONIO JOSE DA SILVA','02245046498',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7925,'ANTONIO JOSE DA SILVA','02245046498',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(8000,'ANTONIO MARCOLINO DOS SANTOS','04228203426',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(8000,'ANTONIO MARCOLINO DOS SANTOS','04228203426',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(8000,'ANTONIO MARCOLINO DOS SANTOS','04228203426',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(8000,'ANTONIO MARCOLINO DOS SANTOS','04228203426',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7911,'AQUISSIA JOSEFA DA SILVA','06360384400',205,'L',205025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7911,'AQUISSIA JOSEFA DA SILVA','06360384400',205,'L',205044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7911,'AQUISSIA JOSEFA DA SILVA','06360384400',205,'L',205027,'FERIAS');
INSERT INTO raca.contas
VALUES(7808,'BENONI PEREIRA DE SA DOS SANTOS ','02100246402',201,'L',201015,'COMISSAO DE VENDEDORES');
INSERT INTO raca.contas
VALUES(7808,'BENONI PEREIRA DE SA DOS SANTOS ','02100246402',201,'L',201025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7808,'BENONI PEREIRA DE SA DOS SANTOS ','02100246402',201,'L',201027,'FERIAS');
INSERT INTO raca.contas
VALUES(7808,'BENONI PEREIRA DE SA DOS SANTOS ','02100246402',201,'L',201028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7808,'BENONI PEREIRA DE SA DOS SANTOS ','02100246402',201,'L',201044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(8022,'BRENDA KELLY SILVA DOS SANTOS','71268018406',205,'L',205027,'FERIAS');
INSERT INTO raca.contas
VALUES(8022,'BRENDA KELLY SILVA DOS SANTOS','71268018406',205,'L',205044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(8022,'BRENDA KELLY SILVA DOS SANTOS','71268018406',205,'L',205025,'SALARIOS');
INSERT INTO raca.contas
VALUES(73,'BRUNO GADELHA PEIXOTO','04311839456',201,'L',201028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(73,'BRUNO GADELHA PEIXOTO','04311839456',201,'L',201015,'COMISSAO DE VENDEDORES');
INSERT INTO raca.contas
VALUES(73,'BRUNO GADELHA PEIXOTO','04311839456',201,'L',201025,'SALARIOS');
INSERT INTO raca.contas
VALUES(73,'BRUNO GADELHA PEIXOTO','04311839456',201,'L',201027,'FERIAS');
INSERT INTO raca.contas
VALUES(73,'BRUNO GADELHA PEIXOTO','04311839456',201,'L',201044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(55,'BRUNO RAFAEL SALES LIMA','07170014408',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(55,'BRUNO RAFAEL SALES LIMA','07170014408',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(55,'BRUNO RAFAEL SALES LIMA','07170014408',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(55,'BRUNO RAFAEL SALES LIMA','07170014408',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(17,'BRUNO RICARDO VARELA DO NASCIMENTO','09334101407',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(17,'BRUNO RICARDO VARELA DO NASCIMENTO','09334101407',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(17,'BRUNO RICARDO VARELA DO NASCIMENTO','09334101407',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(17,'BRUNO RICARDO VARELA DO NASCIMENTO','09334101407',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7926,'CARLOS RODRIGUES CAMPOS','29069173468',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7926,'CARLOS RODRIGUES CAMPOS','29069173468',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7926,'CARLOS RODRIGUES CAMPOS','29069173468',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7926,'CARLOS RODRIGUES CAMPOS','29069173468',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7927,'CICERO ANTONIO DA SILVA','41050398491',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7927,'CICERO ANTONIO DA SILVA','41050398491',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7927,'CICERO ANTONIO DA SILVA','41050398491',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7927,'CICERO ANTONIO DA SILVA','41050398491',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7928,'CLAUDIO MANOEL DA SILVA','60949520497',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7928,'CLAUDIO MANOEL DA SILVA','60949520497',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7928,'CLAUDIO MANOEL DA SILVA','60949520497',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7928,'CLAUDIO MANOEL DA SILVA','60949520497',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7899,'CLEBER ADRIANO DE MELO','02077122471',201,'L',201015,'COMISSAO DE VENDEDORES');
INSERT INTO raca.contas
VALUES(7899,'CLEBER ADRIANO DE MELO','02077122471',201,'L',201025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7899,'CLEBER ADRIANO DE MELO','02077122471',201,'L',201027,'FERIAS');
INSERT INTO raca.contas
VALUES(7899,'CLEBER ADRIANO DE MELO','02077122471',201,'L',201044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7899,'CLEBER ADRIANO DE MELO','02077122471',201,'L',201028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7929,'COSME FRANCISCO DE LIMA','02844504485',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7929,'COSME FRANCISCO DE LIMA','02844504485',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7929,'COSME FRANCISCO DE LIMA','02844504485',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7929,'COSME FRANCISCO DE LIMA','02844504485',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7930,'COSMO JOSE SANTOS DO NASCIMENTO','07051793462',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7930,'COSMO JOSE SANTOS DO NASCIMENTO','07051793462',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7930,'COSMO JOSE SANTOS DO NASCIMENTO','07051793462',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7930,'COSMO JOSE SANTOS DO NASCIMENTO','07051793462',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7949,'COSMO SOUZA DA SILVA','05053776407',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7949,'COSMO SOUZA DA SILVA','05053776407',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7949,'COSMO SOUZA DA SILVA','05053776407',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7949,'COSMO SOUZA DA SILVA','05053776407',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7900,'CRISTIANE MARIA PEREIRA','04580945441',201,'L',201044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7900,'CRISTIANE MARIA PEREIRA','04580945441',201,'L',201028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7900,'CRISTIANE MARIA PEREIRA','04580945441',201,'L',201015,'COMISSAO DE VENDEDORES');
INSERT INTO raca.contas
VALUES(7900,'CRISTIANE MARIA PEREIRA','04580945441',201,'L',201025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7900,'CRISTIANE MARIA PEREIRA','04580945441',201,'L',201027,'FERIAS');
INSERT INTO raca.contas
VALUES(7977,'CRISTIANO FRANCISCO FERREIRA','06928195410',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7977,'CRISTIANO FRANCISCO FERREIRA','06928195410',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7977,'CRISTIANO FRANCISCO FERREIRA','06928195410',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7977,'CRISTIANO FRANCISCO FERREIRA','06928195410',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7931,'DAMIAO FRANCISCO DE LIMA','03128283494',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7931,'DAMIAO FRANCISCO DE LIMA','03128283494',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7931,'DAMIAO FRANCISCO DE LIMA','03128283494',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7931,'DAMIAO FRANCISCO DE LIMA','03128283494',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(74,'DAMIAO ROBERTO DA SILVA','08750690450',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(74,'DAMIAO ROBERTO DA SILVA','08750690450',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(74,'DAMIAO ROBERTO DA SILVA','08750690450',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(74,'DAMIAO ROBERTO DA SILVA','08750690450',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7954,'DANILO FERREIRA DA SILVA','01785928465',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7954,'DANILO FERREIRA DA SILVA','01785928465',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7954,'DANILO FERREIRA DA SILVA','01785928465',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7954,'DANILO FERREIRA DA SILVA','01785928465',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(61,'DAYANE ADRIELLY DA SILVA NASCIMENTO','09427807440',205,'L',205025,'SALARIOS');
INSERT INTO raca.contas
VALUES(61,'DAYANE ADRIELLY DA SILVA NASCIMENTO','09427807440',205,'L',205027,'FERIAS');
INSERT INTO raca.contas
VALUES(61,'DAYANE ADRIELLY DA SILVA NASCIMENTO','09427807440',205,'L',205044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7895,'DAYANE MADALENA DE FRANCA','10204500451',208,'L',208025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7895,'DAYANE MADALENA DE FRANCA','10204500451',208,'L',208027,'FERIAS');
INSERT INTO raca.contas
VALUES(7895,'DAYANE MADALENA DE FRANCA','10204500451',208,'L',208044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7895,'DAYANE MADALENA DE FRANCA','10204500451',208,'L',208028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7989,'DAYVSON DA SILVA NASCIMENTO','06007282422',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7989,'DAYVSON DA SILVA NASCIMENTO','06007282422',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7989,'DAYVSON DA SILVA NASCIMENTO','06007282422',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7989,'DAYVSON DA SILVA NASCIMENTO','06007282422',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(62,'DOUGLAS PEREIRA LIMA','16564791410',205,'L',205044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(62,'DOUGLAS PEREIRA LIMA','16564791410',205,'L',205025,'SALARIOS');
INSERT INTO raca.contas
VALUES(62,'DOUGLAS PEREIRA LIMA','16564791410',205,'L',205027,'FERIAS');
INSERT INTO raca.contas
VALUES(7932,'EDILSON FERNANDO BATISTA','03648978489',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7932,'EDILSON FERNANDO BATISTA','03648978489',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7932,'EDILSON FERNANDO BATISTA','03648978489',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7932,'EDILSON FERNANDO BATISTA','03648978489',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7933,'EDILSON SIMAO DE SANTANA','10042605474',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7933,'EDILSON SIMAO DE SANTANA','10042605474',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7933,'EDILSON SIMAO DE SANTANA','10042605474',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7933,'EDILSON SIMAO DE SANTANA','10042605474',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(7946,'EDJAIR DE SOUZA VIEIRA','29853502801',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(7946,'EDJAIR DE SOUZA VIEIRA','29853502801',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(7946,'EDJAIR DE SOUZA VIEIRA','29853502801',202,'L',202044,'13º SALARIO');
INSERT INTO raca.contas
VALUES(7946,'EDJAIR DE SOUZA VIEIRA','29853502801',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(43,'EDVALDO MIRANDA DE SOUZA','01065919409',202,'L',202025,'SALARIOS');
INSERT INTO raca.contas
VALUES(43,'EDVALDO MIRANDA DE SOUZA','01065919409',202,'L',202028,'RESCISOES TRABALHISTAS');
INSERT INTO raca.contas
VALUES(43,'EDVALDO MIRANDA DE SOUZA','01065919409',202,'L',202027,'FERIAS');
INSERT INTO raca.contas
VALUES(43,'EDVALDO MIRANDA DE SOUZA','01065919409',202,'L',202044,'13º SALARIO');

ALTER TABLE raca.movimentacao ADD COLUMN historicodescricao VARCHAR(120);

ALTER TABLE raca.documento ADD COLUMN tipodocumento varchar(60);
ALTER TABLE raca.documento ADD COLUMN iddocpai integer;
ALTER TABLE raca.documento ADD COLUMN restrito boolean;

ALTER TABLE raca.documento ADD COLUMN nome varchar(255);
ALTER TABLE raca.documento ADD COLUMN numerodocumento integer;
ALTER TABLE raca.responsavel ADD COLUMN filial boolean;
ALTER TABLE raca.documento ADD COLUMN responsavel varchar(120);
ALTER TABLE raca.documento ADD COLUMN idresponsavel integer;

CREATE INDEX idx_nome ON raca.documento (nome);