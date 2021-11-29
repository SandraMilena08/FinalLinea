package cundi.edu.co.service;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cundi.edu.co.entity.Autor;


public interface IAutorService extends ICrud<Autor, Integer>{
	
	@Query(
			value = "SELECT * FROM autor WHERE autor.cedula LIKE %:filtro%",
			nativeQuery = true	
			)
	
	public List<Autor> buscarCedula(@Param("filtro") String filtro);
	
}
