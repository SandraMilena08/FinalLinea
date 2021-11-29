package cundi.edu.co.service.impl;

import java.util.List;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import cundi.edu.co.dto.EditorialAutorDto;
import cundi.edu.co.dto.IAutorDto;
import cundi.edu.co.entity.AutorEditorial;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.repository.IAutorEditorialRepo;
import cundi.edu.co.repository.IAutorRepository;
import cundi.edu.co.repository.IEditorialRepo;
import cundi.edu.co.service.IAutorEditorialService;


@Service
public class AutorEditorialServiceImpl implements IAutorEditorialService {

	@Autowired
	private IAutorEditorialRepo repo;
	
	@Autowired
	private IAutorRepository autorRepo;
	
	@Autowired
	private IEditorialRepo editorialRepo;
	

	@Override
	public Page<EditorialAutorDto> retornarPaginanoEditorialesPorAutores(Integer idAutor, Integer page, Integer size) throws ModelNotFoundException {

		if(autorRepo.existsById(idAutor)) {
			return repo.findEditorialesPorAutor(idAutor,PageRequest.of(page, size));
			
		}else {
			throw new ModelNotFoundException("Autor no existente");
		}
		
	}
	
	@Override
	public Page<IAutorDto> retornarPaginadoAutoresPorEditorial(Integer idEditorial, Integer page, Integer size) throws ModelNotFoundException {
		if(editorialRepo.existsById(idEditorial)) {
			
			return repo.findAutoresPorEditorial(idEditorial,PageRequest.of(page, size));
		}else {
			throw new ModelNotFoundException("Editorial no existente");
		}
					
	}
	
	@Override
	public Page<AutorEditorial> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AutorEditorial> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutorEditorial retonarPorId(Integer idObj) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editar(AutorEditorial obj) throws ArgumentRequiredException, ModelNotFoundException, ConflicException {
		// TODO Auto-generated method stub
		//Generalmente no se utiliza el editar en una table intermedia 
	}

	@Override
	public void eliminar(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {

			if(repo.countByIdAutorAndIdEditorial(idAutor,idEditorial)>0) {	
				repo.eliminarAutorEditorial(idAutor, idEditorial);
			}else {
				throw new ModelNotFoundException("Relacion no encontrada");
			}
	}

	@Override
	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		//Validaciones pertinentes
		this.repo.eliminarNativa(idAutor, idEditorial);
	}

	@Transactional
	@Override
	public void asociarAutorEditoial() {
		this.repo.guardarNativo(1, 1, LocalDate.now());
		this.repo.guardarNativo(2, 1, LocalDate.now());
		this.repo.guardarNativo(3, 1, LocalDate.now());
		this.repo.guardarNativo(6, 1, LocalDate.now());
	}

	@Override
	public List<AutorEditorial> obtener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crear(AutorEditorial entity) throws ConflicException {
		this.repo.guardarNativo(entity.getAutor().getId(), entity.getEditorial().getId(), entity.getFecha());
	}

	@Override
	public void eliminar(int idEntity) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
