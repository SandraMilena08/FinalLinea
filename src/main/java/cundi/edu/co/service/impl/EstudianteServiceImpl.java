package cundi.edu.co.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.dto.EstudianteDto;
//import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.dto.Materias;
import cundi.edu.co.entity.Estudiante;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.repository.IRepositoryEstudiante;
import cundi.edu.co.service.IEstudianteService;
import cundi.edu.co.service.IMaterias;
//JPQL Y NATIVA SQL
@Service
@Qualifier("estudiante")
public class EstudianteServiceImpl implements IEstudianteService, IMaterias {
	
	List<Materias> materias = new ArrayList<Materias>();
	
	@Autowired
	private IRepositoryEstudiante repo;
	
	//No se usa, solo para el ejemplo
		@Override
		public List<Estudiante> retornarTodo() {
			return repo.findAll();
		}	
		
		@Override
		public Page<Estudiante> retornarPaginado(int page, int size) {
			return repo.findAll(PageRequest.of(page,size));
		}	
		
		@Override
		public Page<Estudiante> retornarPaginado(Pageable page) {
			return repo.findAll(page);
		}	
	
	@Override
	public EstudianteDto retornarEstudiante(int i) {
		materias.add(new Materias ("Español"));
		EstudianteDto estudiante = new EstudianteDto("Sandra","Moreno" + i, materias);
		return estudiante;
		
	}

	@Override
	public void crearEstudiante(Estudiante estudiante) throws ConflicException {
		//Estudiante estudianteBusqueda = repo.findByCedula(estudiante.getCedula());
		//if(estudianteBusqueda != null)
			//throw new ConflicException("Cedula ya existe");
			if(repo.existsByCedula(estudiante.getCedula()))
				throw new ConflicException("Cedula ya existe");
			
			if(repo.existsByCorreo(estudiante.getCorreo()))
				throw new ConflicException("Correo ya existe");
			
		this.repo.save(estudiante);
		
	}

	@Override
	public void actualizarEstudiante(Estudiante estudiante) throws ArgumentRequiredException, ModelNotFoundException, ConflicException  {
		if(estudiante.getId() != null) {
			if(validarExistenciaPorId(estudiante.getId())) {
				
				Estudiante estudianteAux = this.repo.findById(estudiante.getId()).get();
				estudiante.setCedula(estudianteAux.getCedula());
				
				if(estudiante.getCorreo().equals(estudianteAux.getCorreo()))
					this.repo.save(estudiante);
				else {
					if(!repo.existsByCorreo(estudiante.getCorreo())) {
						this.repo.save(estudiante);
					} else {
						 throw new ConflicException("Correo ya existe");
					}
				}
				
			} else
				throw new ModelNotFoundException("Estudiante no encontrado");		
		} else {
			throw new ArgumentRequiredException("IdEstudiante es requerido");
		}
	}

	@Override
	public void eliminarEstudiante(int idEstudiante) throws ModelNotFoundException {
		if(repo.existsById(idEstudiante))
			this.repo.deleteById(idEstudiante);
		else
			throw new ModelNotFoundException("Estudiante no creado");
	}

	@Override
	public int numeroMaterias() {
		return materias.size();
	}

	@Override
	public Estudiante retonarPorId(Integer idEstudiante) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Boolean validarExistenciaPorId(int idEstudiante) {
		return repo.existsById(idEstudiante);
	}

}
