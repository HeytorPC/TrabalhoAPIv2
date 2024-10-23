CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    data_pedido DATE NOT NULL,
    valor_total DECIMAL(10, 2),
    cliente_id BIGINT REFERENCES clientes(id)
);