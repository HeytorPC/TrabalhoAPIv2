package org.serratec.agencia.repository;

import org.serratec.agencia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
