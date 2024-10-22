CREATE TABLE enderecos (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(8) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(255)
);