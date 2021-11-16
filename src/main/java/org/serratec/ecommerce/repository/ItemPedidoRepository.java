package org.serratec.ecommerce.repository;

import java.util.List;

import org.serratec.ecommerce.domain.ItemPedido;
import org.serratec.ecommerce.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	public List<ItemPedido> findAllByPedido(Pedido pedido);
}