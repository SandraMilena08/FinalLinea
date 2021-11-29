package cundi.edu.co.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.entity.Libro;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer>{

	@Query(value = "SELECT l FROM Libro l INNER JOIN Autor a ON l.autor = a.id WHERE a.id = ?1")
	public List<Libro> buscarAutor(int id);
}
