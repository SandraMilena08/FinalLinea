package cundi.edu.co.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cundi.edu.co.dto.EstudianteDto;
import cundi.edu.co.entity.Estudiante;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;

public interface IEstudianteService {
	public EstudianteDto retornarEstudiante(int i);
	public void crearEstudiante(Estudiante estudiante) throws ConflicException;
	public void actualizarEstudiante(Estudiante estudiante) throws ArgumentRequiredException, ModelNotFoundException, ConflicException;
	public void eliminarEstudiante(int i) throws ModelNotFoundException;
	public List<Estudiante> retornarTodo();
	public Page<Estudiante> retornarPaginado(int page, int size);
	public Page<Estudiante> retornarPaginado(Pageable page);
	public Estudiante retonarPorId(Integer idEstudiante) throws ModelNotFoundException;
}
