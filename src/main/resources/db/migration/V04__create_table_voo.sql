CREATE TABLE voos (
    id SERIAL PRIMARY KEY,
    compania VARCHAR(255) NOT NULL,
    status_voo VARCHAR(20) NOT NULL,
    reserva VARCHAR(20) NOT NULL
);