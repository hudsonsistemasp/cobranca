package com.algaworks.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.cobranca.model.Titulo;
/*JpaRepository<T, Serializable> O primeiro parâmetro pede a entidade que será usada, no caso a Titulo que aponta pro front
 o segundo parâmetro pede o tipo que foi declarado do ID da tabela, que está na model, no atributo ID, que é do tipo LONG */
public interface Titulos extends JpaRepository<Titulo, Long>{
	
}
