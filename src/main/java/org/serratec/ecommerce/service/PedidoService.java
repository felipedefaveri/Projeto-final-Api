package org.serratec.ecommerce.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.config.MailConfig;
import org.serratec.ecommerce.domain.ItemPedido;
import org.serratec.ecommerce.domain.Pedido;
import org.serratec.ecommerce.dto.ItemPedidoInserirDTO;
import org.serratec.ecommerce.dto.PedidoDTO;
import org.serratec.ecommerce.dto.PedidoInserirDTO;
import org.serratec.ecommerce.enums.StatusPedido;
import org.serratec.ecommerce.exception.ItemPedidoException;
import org.serratec.ecommerce.exception.PedidoException;
import org.serratec.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private MailConfig mailConfig;

	public Pedido inserir(PedidoInserirDTO pedidoInserirDTO) throws ItemPedidoException, PedidoException {
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.ABERTO);
		pedido.setCliente(clienteService.buscarPorId(pedidoInserirDTO.getIdCliente()));
		pedido.setDataPedido(LocalDate.now());	
		pedido.setItensPedidos(itemPedidoService.salvarListaItemPedido(pedidoInserirDTO.getItensPedidosDTO(), pedido));
		pedido = pedidoRepository.save(pedido);

		return pedido;
	}

	public List<PedidoDTO> listar() {
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		List<Pedido> pedidos = pedidoRepository.findAll();
		for (Pedido ped : pedidos) {
			PedidoDTO dto = new PedidoDTO(ped);
			Double valorTotal = 0.0;
			for (ItemPedido item : ped.getItensPedidos()) {
				Double ipTotal = 0.0;
				ipTotal = item.getPrecoVenda() * item.getQuantidade();
				item.setTotal(ipTotal);
				valorTotal += ipTotal;
			}
			dto.setValorTotal(valorTotal);
			pedidosDTO.add(dto);
		}
		return pedidosDTO;
	}

	public Pedido buscarPedidoPorId(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			return pedido.get();
		}
		return null;
	}

	public PedidoDTO atualizar(Long id, PedidoInserirDTO pedidoInserirDTO) throws PedidoException, ItemPedidoException {
		Pedido pedido = pedidoRepository.findById(id).get();
		Integer status = pedidoInserirDTO.getStatus().getCodigo();
		List<ItemPedidoInserirDTO> listaIDTO = new ArrayList<>();

		switch (status) {
		case 0:
			if (pedido.getStatus().getCodigo() == 0) {
				for (ItemPedidoInserirDTO ip : pedidoInserirDTO.getItensPedidosDTO()) {
					listaIDTO.add(ip);
				}
				pedido.setItensPedidos(itemPedidoService.salvarListaItemPedido(listaIDTO, pedido));
				pedido.setDataPedido(LocalDate.now());

				pedidoRepository.save(pedido);
				return new PedidoDTO(pedido);
			} else {
				throw new PedidoException("Esse pedido não está aberto");
			}
		case 1:
			pedido.setStatus(StatusPedido.PAGO);
			pedidoRepository.save(pedido);
			return new PedidoDTO(pedido);
		case 2:
			pedido.setStatus(StatusPedido.ENVIADO);
			pedido.setDataEnvio(LocalDate.now());
			pedidoRepository.save(pedido);
			return new PedidoDTO(pedido);
		case 3:
			pedido.setStatus(StatusPedido.ENTREGUE);
			pedido.setDataEntrega(LocalDate.now());
			Double valorTotal = 0.0;
			for (ItemPedido item : pedido.getItensPedidos()) {
				Double ipTotal = 0.0;
				ipTotal = item.getPrecoVenda() * item.getQuantidade();
				item.setTotal(ipTotal);
				valorTotal += ipTotal;
			}
			pedido.setValorTotal(valorTotal);
			mailConfig.sendEmail(pedido.getCliente().getEmail(), "Envio Confirmado!!!", pedido.toString());
			pedidoRepository.save(pedido);
			return new PedidoDTO(pedido);
		case 4:
			pedido.setStatus(StatusPedido.CANCELADO);
			for (ItemPedido itemPedido : pedido.getItensPedidos()) {
				itemPedidoService.reestabelecerEstoque(itemPedido);
			}
			pedidoRepository.save(pedido);
			return new PedidoDTO(pedido);
		}
		return null;
	}

	public Boolean deletar(Long id) {
		if (!pedidoRepository.existsById(id)) {
			return false;
		} else {
			Pedido pedido = pedidoRepository.findById(id).get();
			for (ItemPedido itemPedido : pedido.getItensPedidos()) {
				itemPedidoService.reestabelecerEstoque(itemPedido);
			}
			itemPedidoService.deletarPorPedido(id);
			pedidoRepository.deleteById(id);
			return true;
		}
	}

}