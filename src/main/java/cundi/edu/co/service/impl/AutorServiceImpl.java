package cundi.edu.co.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import cundi.edu.co.entity.Autor;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.repository.IAutorRepository;
import cundi.edu.co.service.IAutorService;
import javassist.NotFoundException;

@Service
public class AutorServiceImpl implements IAutorService {

	@Autowired
	private IAutorRepository repo;

	@Override
	public Page<Autor> retornarPaginado(int page, int size) {
		Page<Autor> pageAutor = repo.findAll(PageRequest.of(page,size));
		pageAutor.getContent().forEach(aut ->{
			     aut.setLibro(null);
		});
		return pageAutor;
	}

	@Override
	public Page<Autor> retornarPaginado(Pageable page) {
		Page<Autor> pageAutor = repo.findAll(page);
		pageAutor.getContent().forEach(aut ->{
			     aut.setLibro(null);
		});
		
		return pageAutor;
	}
	
	@Override
	public Autor retonarPorId(Integer idEntity) throws ModelNotFoundException {
		return repo.findById(idEntity).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));
	}

	@Override
	public void editar(Autor entity) throws ArgumentRequiredException, ModelNotFoundException, ConflicException {
		Autor autorAux = this.retonarPorId(entity.getId());
		autorAux.setApellido(entity.getApellido());
		autorAux.setNombre(entity.getNombre());
		autorAux.setCedula(entity.getCedula());
		autorAux.setCorreo(entity.getCorreo());
		
		this.repo.save(autorAux);
	}

	@Override
	public void eliminar(int idEntity) throws ModelNotFoundException {
		if(validarExistenciaPorId(idEntity)) {
			this.repo.deleteById(idEntity);
		}else {
			throw new ModelNotFoundException("Autor no encontrado");
		}		
	}

	@Override
	public void crear(Autor entity) throws ConflicException, AccessDeniedException {
			if(entity.getLibro() != null) {
				entity.getLibro().forEach(libro ->{
						libro.setAutor(entity);
				});
			}
		this.repo.save(entity);
	}

	@Override
	public List<Autor> obtener(){
		List<Autor> autores = repo.findAll();
		 return autores;
	}
	
	private Boolean validarExistenciaPorId(int idAutor) {
		return repo.existsById(idAutor);
	}

	@Override
	public List<Autor> buscarCedula(String filtro) {
		return repo.buscarCedula(filtro);
	}

	

}