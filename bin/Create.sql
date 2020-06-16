CREATE TABLE Casa (
    id int NOT NULL,
    idPessoa int NOT NULL,
    qtQuartos int,
    PRIMARY KEY (id),
    FOREIGN KEY (idPessoa) REFERENCES Pessoa(id)
);


create table Pessoa (
	id int,
	nome varchar(50),
	idade int,
	primary key(id)
);