package cundi.edu.co.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cundi.edu.co.entity.Libro;

public interface ILibroService extends ICrud<Libro, Integer> {

	@Query(value = "SELECT l FROM Libro l INNER JOIN Autor a ON l.autor = a.id WHERE a.id = ?1")
	public List<Libro> buscarAutor(int id);
}
