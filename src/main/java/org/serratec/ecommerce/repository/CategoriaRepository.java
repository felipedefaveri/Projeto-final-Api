package org.serratec.ecommerce.repository;

import java.util.Optional;

import org.serratec.ecommerce.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public Optional<Categoria> findByNome(String nome);
}