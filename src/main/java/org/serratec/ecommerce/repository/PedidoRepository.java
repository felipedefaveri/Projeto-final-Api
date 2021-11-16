package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.domain.Pedido;
import org.serratec.ecommerce.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	public Pedido findByStatus(StatusPedido status);
}