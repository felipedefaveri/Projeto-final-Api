package org.serratec.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.ecommerce.dto.ClienteDTO;
import org.serratec.ecommerce.dto.ClienteInserirDTO;
import org.serratec.ecommerce.exception.ClienteException;
import org.serratec.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@PreAuthorize("hasRole('usuario')")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar um determinado cliente", notes = "Cadastrar cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
			@ApiResponse (code = 401, message = "Erro de autenticação"),
			@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse (code = 404, message = "Recurso indisponível"),
			@ApiResponse (code = 500, message = "Erro interno no servidor"),
			@ApiResponse (code = 505, message = "Ocorreu uma exceção")
			}
			)
	public ClienteDTO inserir(@Valid @RequestBody ClienteInserirDTO clienteInserirDTO) throws ClienteException {
	ClienteDTO c = clienteService.inserir(clienteInserirDTO);
	return c;
	}

	@GetMapping("{nome}")
	@PreAuthorize("hasRole('usuario')")
	@ApiOperation(value = "Buscar cliente por nome", notes = "Buscar Clientes")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Categoria listada"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<Page<ClienteDTO>> buscarPorNome(@PathVariable String nome, Pageable pageable) {
		Page<ClienteDTO> clientes = clienteService.buscarPorNome(nome, pageable);
		return ResponseEntity.ok(clientes);
	}

	@GetMapping
	@PreAuthorize("hasRole('usuario')")
	@ApiOperation(value = "Listagem de todos os cliente", notes = "Listagem de clientes")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Clientes listados com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<List<ClienteDTO>> listar() {
		List<ClienteDTO> clientes = clienteService.listar();
		return ResponseEntity.ok(clientes);
	}

	@PutMapping("{id}")
	@PreAuthorize("hasRole('usuario')")
	@ApiOperation(value = "Atualizar um cliente por id", notes = "Atualizar cliente")
	@ApiResponses(value = {
	
	@ApiResponse(code = 201, message = "Cliente atualizado com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteInserirDTO clienteInserirDTO) {
		if (clienteService.atualizar(id, clienteInserirDTO) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteService.atualizar(id, clienteInserirDTO));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('usuario')")
	@ApiOperation(value = "Deletar clientes", notes = "Deletar clientes")
	@ApiResponses(value = {
	@ApiResponse(code = 204, message = "Sem conteúdo disponível"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<ClienteDTO> deletar(@PathVariable Long id) {
		if (clienteService.deletar(id) == false) {
			return ResponseEntity.notFound().build();
		}
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}