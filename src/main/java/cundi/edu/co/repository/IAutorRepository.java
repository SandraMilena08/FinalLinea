package cundi.edu.co.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.entity.Autor;



@Repository
public interface IAutorRepository extends JpaRepository<Autor, Integer> {
	
	public Autor findByCedula(String cedula);
	
	public Autor findByCorreo(String correo);
	
	public Boolean existsByCedula(String cedula);
	
	public Boolean existsByCorreo(String correo);
	
	
	@Query(
			value = "SELECT * FROM autor WHERE autor.cedula LIKE %:filtro%",
			nativeQuery = true	
			)
	
	public List<Autor> buscarCedula(@Param("filtro") String filtro);

	
}

	