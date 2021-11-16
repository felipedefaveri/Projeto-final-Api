package org.serratec.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Foto;
import org.serratec.ecommerce.domain.Produto;
import org.serratec.ecommerce.dto.ProdutoDTO;
import org.serratec.ecommerce.service.FotoService;
import org.serratec.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping
	@ApiOperation(value = "Listagem de todos os produtos", notes = "Listagem de produtos")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Produtos listados com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<List<ProdutoDTO>> listar() {
		return ResponseEntity.ok(produtoService.listar());
	}
	
	@GetMapping("{nome}")
	@ApiOperation(value = "Buscar produto por nome", notes = "Buscar produtos")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Produto listado"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<ProdutoDTO> buscarPorNome (@PathVariable String nome){
		if(produtoService.buscarPorNome(nome) == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(produtoService.buscarPorNome(nome));
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar um determinado produto", notes = "Cadastrar produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Produto cadastrado com sucesso"),
			@ApiResponse (code = 401, message = "Erro de autenticação"),
			@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse (code = 404, message = "Recurso indisponível"),
			@ApiResponse (code = 500, message = "Erro interno no servidor"),
			@ApiResponse (code = 505, message = "Ocorreu uma exceção")
			}
			)
	public ProdutoDTO inserir(@RequestParam MultipartFile file, @Valid @RequestPart Produto produto) throws IOException {
		return produtoService.inserir(file, produto);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasRole('admin')")
	@ApiOperation(value = "Atualizar um produto por id", notes = "Atualizar produto")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Pdroduto atualizado com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<ProdutoDTO> atualizar (@PathVariable Long id, @Valid @RequestBody Produto produto){
		if(produtoService.atualizar(id, produto)==null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(produtoService.atualizar(id, produto));
		}
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('admin')")
	@ApiOperation(value = "Deletar produtos", notes = "Deletar produtos")
	@ApiResponses(value = {
	@ApiResponse(code = 204, message = "Sem conteúdo disponível"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<Produto> deletar (@PathVariable Long id){
		if(produtoService.deletar(id)) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}/foto")
	@ApiOperation(value = "Buscar produto por id ", notes = "Buscar produtos")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Produto listado"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
    public ResponseEntity<byte[]> buscarPorFoto(@PathVariable Long id){
        Foto foto = fotoService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type",foto.getTipo());
        headers.add("content-length",String.valueOf(foto.getDados().length));
        return new ResponseEntity<>(foto.getDados(),headers,HttpStatus.OK);
    }

}