DROP TABLE IF EXISTS pedidos;
CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    data_pedido DATE NOT NULL,
    metodo_pagamento VARCHAR(20) NOT NULL,
    status_pedido VARCHAR(20) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    valor_liquido DECIMAL(10, 2),
    valor_bruto DECIMAL(10, 2),
    desconto DECIMAL(10, 2),
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)

);