CREATE TABLE viagem (
    id SERIAL PRIMARY KEY,
    local VARCHAR(200) NOT NULL,
    data_viagem DATE NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    status_viagem VARCHAR(20) NOT NULL,
    voo_id BIGINT,
    FOREIGN KEY (voo_id) REFERENCES voos(id)
);
