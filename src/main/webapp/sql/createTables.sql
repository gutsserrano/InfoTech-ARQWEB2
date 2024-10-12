CREATE DATABASE infotech;
USE infotech;

CREATE TABLE customer
(
	id INT PRIMARY KEY auto_increment ,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(60) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE serviceOrder
(
	id INT PRIMARY KEY auto_increment,
    customerId INT NOT NULL,
    description VARCHAR(200) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES customer(id)
);