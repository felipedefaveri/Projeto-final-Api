package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.dto.ClienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public Cliente findByEmail (String email);
	public Cliente findByCpf(String cpf);
	public Cliente findByNomeUsuario(String nomeUsuario);
	public Page<ClienteDTO> findByNomeCompletoContainingIgnoreCase(String paramNome, Pageable pageable);
}