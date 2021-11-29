package cundi.edu.co.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.entity.Autor;
import cundi.edu.co.entity.Libro;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.repository.ILibroRepository;
import cundi.edu.co.service.ILibroService;

@Service
public class LibroServiceImpl implements ILibroService{

	@Autowired
	private ILibroRepository repo;
	
	@Override
	public Page<Libro> retornarPaginado(int page, int size) {
		Page<Libro> pageAutor = repo.findAll(PageRequest.of(page,size));
		pageAutor.getContent().forEach(aut ->{
			     aut.setAutor(null);
		});
		return pageAutor;
	}

	@Override
	public Page<Libro> retornarPaginado(Pageable page) {
		Page<Libro> pageAutor = repo.findAll(page);
		pageAutor.getContent().forEach(aut ->{
			     aut.setAutor(null);
		});
		
		return pageAutor;
	}

	@Override
	public Libro retonarPorId(Integer idEntity) throws ModelNotFoundException {
		return repo.findById(idEntity).orElseThrow(() -> new ModelNotFoundException("Libro no encontrado"));
	}

	@Override
	public List<Libro> obtener() {
		List<Libro> libros = repo.findAll();
		return libros;
	}

	@Override
	public void crear(Libro libro) throws ConflicException {
		repo.save(libro);
	}

	@Override
	public void editar(Libro libro) throws ArgumentRequiredException, ModelNotFoundException, ConflicException {
		if(validarExistenciaPorId(libro.getId())) {
			repo.save(libro);
		}else {
			throw new ModelNotFoundException("Libro no encontrado para actualizar");
		}
		
		
	}

	@Override
	public void eliminar(int idEntity) throws ModelNotFoundException {
		if(validarExistenciaPorId(idEntity)) {
			repo.deleteById(idEntity);
		}else {
			throw new ModelNotFoundException("Libro no encontrado");
		}
		
	}
	
	private Boolean validarExistenciaPorId(int idLibro) {
		return repo.existsById(idLibro);
	}

	@Override
	public List<Libro> buscarAutor(int id) {
		return repo.buscarAutor(id);
	}



}
