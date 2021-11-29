package cundi.edu.co.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.entity.Editorial;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.repository.IEditorialRepo;
import cundi.edu.co.service.IEditorialService;

@Service
public class EditorialServiceImpl implements IEditorialService {
	
	@Autowired
	IEditorialRepo repo;

	@Override
	public Page<Editorial> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Editorial> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Editorial retonarPorId(Integer idEntity) throws ModelNotFoundException {
		return repo.findById(idEntity).orElseThrow(() -> new ModelNotFoundException("Editorial no encontrada"));
	}

	@Override
	public List<Editorial> obtener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crear(Editorial entity) throws ConflicException {
		repo.save(entity);
		
	}

	@Override
	public void editar(Editorial entity) throws ArgumentRequiredException, ModelNotFoundException, ConflicException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(int idEntity) throws ModelNotFoundException {
		if(validarExistenciaPorId(idEntity)) {
			this.repo.deleteById(idEntity);
		}else {
			throw new ModelNotFoundException("Editorial no encontrado");
		}	
		
	}
	
	private Boolean validarExistenciaPorId(int idEditorial) {
		return repo.existsById(idEditorial);
	}


}
