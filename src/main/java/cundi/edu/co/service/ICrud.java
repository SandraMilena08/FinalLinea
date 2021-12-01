package cundi.edu.co.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cundi.edu.co.entity.Autor;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;




public interface ICrud<T, ID> {
	
	public Page<T> retornarPaginado(int page, int size);
	
	public Page<T> retornarPaginado(Pageable page);
	
	public T retonarPorId(ID idEntity) throws ModelNotFoundException;
	
	public List<T> obtener();
	
	public void crear(T entity) throws ConflicException;
	
	public void editar(T entity)  throws ArgumentRequiredException, ModelNotFoundException, ConflicException;
	
	public void eliminar(int idEntity) throws ModelNotFoundException;

}