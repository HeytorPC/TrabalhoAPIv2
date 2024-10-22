package org.serratec.agencia.repository;

import org.serratec.agencia.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
 
}
