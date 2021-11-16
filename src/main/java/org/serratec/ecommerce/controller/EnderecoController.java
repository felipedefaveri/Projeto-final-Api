package org.serratec.ecommerce.controller;

import org.serratec.ecommerce.dto.EnderecoDTO;
import org.serratec.ecommerce.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("{cep}")
	@ApiOperation(value = "Buscar um determinado endereço", notes = "Buscar endereço")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Endereço listado"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<EnderecoDTO> buscar (@PathVariable String cep) {
		EnderecoDTO enderecoDTO = enderecoService.buscar(cep);
		if(enderecoDTO == null) {
			return ResponseEntity.notFound().build();					
		}else {
			return ResponseEntity.ok(enderecoDTO);
		}
	}
}
