package cundi.edu.co.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import cundi.edu.co.dto.EditorialAutorDto;
import cundi.edu.co.dto.IAutorDto;
import cundi.edu.co.entity.Libro;

@Repository
public interface IAutorEditorialRepo  extends JpaRepository<Libro, Integer> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO autor_editorial(id_autor, id_editorial, fecha) VALUES(:idAutor, :idEditorial, :fecha)"
				,nativeQuery = true)
	void guardarNativo(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial, @Param("fecha") LocalDate fecha);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM autor_editorial where id_autor = :idAutor and id_editorial = :idEditorial ",  nativeQuery = true)
	void eliminarNativa(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial);
	
	@Transactional
	@Modifying
	@Query(
			value = "DELETE FROM autor_editorial WHERE id_autor = ?1 AND id_editorial =?2",
			nativeQuery =  true )
	public void eliminarAutorEditorial(int idAutor,int idEditorial);
	
	@Query(
			value = "SELECT COUNT(*) FROM autor_editorial WHERE id_autor =?1 AND id_editorial =?2",
			nativeQuery = true)
	public long countByIdAutorAndIdEditorial(int idAutor,int idEditorial);

	@Query(
			value= "select e.id, e.correo,e.nit,e.nombre  from editorial as e left  join autor_editorial on autor_editorial.id_editorial = e.id where autor_editorial.id_autor = ?1",
			nativeQuery = true			
			)	
	
	Page<EditorialAutorDto> findEditorialesPorAutor(Integer idAutor,Pageable pageable);
	
	@Query(
			value = "select a.id, a.apellido,a.cedula,a.correo,a.nombre from autor as a left  join autor_editorial on autor_editorial.id_autor = a.id where autor_editorial.id_editorial =?1",
			nativeQuery =  true
			)
	Page<IAutorDto> findAutoresPorEditorial(Integer idEditorial,Pageable pageable);
}
