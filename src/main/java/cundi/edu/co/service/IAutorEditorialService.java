package cundi.edu.co.service;

import org.springframework.data.domain.Page;

import cundi.edu.co.dto.EditorialAutorDto;
import cundi.edu.co.dto.IAutorDto;
import cundi.edu.co.entity.AutorEditorial;
import cundi.edu.co.exception.ModelNotFoundException;




public interface IAutorEditorialService extends ICrud<AutorEditorial, Integer> {

	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException;	
	
	public void asociarAutorEditoial();

	public void eliminar(Integer idAutor, Integer idEditorial) throws ModelNotFoundException;

	Page<EditorialAutorDto> retornarPaginanoEditorialesPorAutores(Integer idAutor, Integer page, Integer size)
			throws ModelNotFoundException;

	Page<IAutorDto> retornarPaginadoAutoresPorEditorial(Integer idEditorial, Integer page, Integer size)
			throws ModelNotFoundException;
	
}
