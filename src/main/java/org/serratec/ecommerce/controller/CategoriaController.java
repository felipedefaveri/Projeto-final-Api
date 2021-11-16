package org.serratec.ecommerce.controller;



import java.util.List;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Categoria;
import org.serratec.ecommerce.dto.CategoriaDTO;
import org.serratec.ecommerce.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping
	@ApiOperation(value = "Listagem de todos as categorias", notes = "Listagem de categorias")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Categorias listadas com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<List<CategoriaDTO>> listar(){
		return ResponseEntity.ok(categoriaService.listar());
	}
	
	@GetMapping("{nome}")
	@ApiOperation(value = "Buscar categoria por nome", notes = "Buscar Categorias")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Categoria listada"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<CategoriaDTO> buscar(@PathVariable String nome){
		if (categoriaService.buscar(nome) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaService.buscar(nome));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar uma determinada categoria", notes = "Cadastrar categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Categoria cadastrada com sucesso"),
			@ApiResponse (code = 401, message = "Erro de autenticação"),
			@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse (code = 404, message = "Recurso indisponível"),
			@ApiResponse (code = 500, message = "Erro interno no servidor"),
			@ApiResponse (code = 505, message = "Ocorreu uma exceção")
			}
			)
	public CategoriaDTO inserir (@Valid @RequestBody Categoria categoria) {
		CategoriaDTO categoriaDTO = categoriaService.inserir(categoria);
		return categoriaDTO;	
		
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasRole('admin')")
	@ApiOperation(value = "Atualizar uma categoria por id", notes = "Atualizar categoria")
	@ApiResponses(value = {
	
	@ApiResponse(code = 201, message = "Dependente atualizado com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria){
		if(categoriaService.atualizar(id, categoria) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaService.atualizar(id, categoria));
		
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('admin')")
	@ApiOperation(value = "Deletar categorias", notes = "Deletar categorias")
	@ApiResponses(value = {
	@ApiResponse(code = 204, message = "Sem conteúdo disponível"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<CategoriaDTO> deletar (@PathVariable Long id){
		if (categoriaService.deletar(id) == false) {
			return ResponseEntity.notFound().build();
			
		}
		categoriaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}