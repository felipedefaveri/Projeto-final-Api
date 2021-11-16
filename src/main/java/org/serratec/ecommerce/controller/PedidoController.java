package org.serratec.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Pedido;
import org.serratec.ecommerce.dto.PedidoDTO;
import org.serratec.ecommerce.dto.PedidoInserirDTO;
import org.serratec.ecommerce.exception.ItemPedidoException;
import org.serratec.ecommerce.exception.PedidoException;
import org.serratec.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	@ApiOperation(value = "Listagem de todos os pedidos", notes = "Listagem de pedidos")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Pedidos listados com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<List<PedidoDTO>> listar() {
		return ResponseEntity.ok(pedidoService.listar());
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Buscar pedido por id", notes = "Buscar Pedido")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Pedido listado"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		if (pedidoService.buscarPedidoPorId(id) != null) {
			return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasRole('usuario')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar um determinado pedido", notes = "Cadastrar pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Pedido cadastrado com sucesso"),
			@ApiResponse (code = 401, message = "Erro de autenticação"),
			@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse (code = 404, message = "Recurso indisponível"),
			@ApiResponse (code = 500, message = "Erro interno no servidor"),
			@ApiResponse (code = 505, message = "Ocorreu uma exceção")
			}
			)
	public Pedido inserir(@Valid @RequestBody PedidoInserirDTO pedidoInserir)
			throws ItemPedidoException, PedidoException {
		Pedido pedido = pedidoService.inserir(pedidoInserir);
		return pedido;
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('admin')")
	@ApiOperation(value = "Deletar pedidos", notes = "Deletar pedidos")
	@ApiResponses(value = {
	@ApiResponse(code = 204, message = "Sem conteúdo disponível"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<PedidoDTO> deletar(@PathVariable Long id) {
		if (pedidoService.deletar(id) == false) {
			return ResponseEntity.notFound().build();
		}
		pedidoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	@PreAuthorize("hasRole('usuario')")
	@ApiOperation(value = "Atualizar um pedido por id", notes = "Atualizar pedido")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Pedido atualizado com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<PedidoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PedidoInserirDTO pedidoInserirDTO) throws PedidoException, ItemPedidoException{
			PedidoDTO pedidoFinal = pedidoService.atualizar(id, pedidoInserirDTO);
		if(pedidoFinal == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(pedidoFinal);
		}
	}
}