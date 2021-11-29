package cundi.edu.co.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.dto.EstudianteDto;
import cundi.edu.co.entity.Estudiante;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.service.IEstudianteService;

@PreAuthorize("hasAuthority('Administrador')")
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService service;
	
	//@RequestMapping(value = "/obtener", method = RequestMethod.GET)
	@GetMapping(value = "/obtener" ,produces = "application/json")
	public ResponseEntity<?> retonar() {
		List<Estudiante> listaEstudiante = service.retornarTodo();
		return new ResponseEntity<List<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Estudiante> listaEstudiante = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Estudiante> listaEstudiante = service.retornarPaginado(page);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPorId/{idEstudiante}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int idEstudiante) throws ModelNotFoundException {
		Estudiante estudainte = service.retonarPorId(idEstudiante);
		return new ResponseEntity<Estudiante>(estudainte, HttpStatus.OK);	
	}		
		
	
	/*
	
	//@RequestMapping(value = "/obtener", method = RequestMethod.GET)
	@GetMapping(value = "/obtener/{i}" ,produces = "application/json")
	public ResponseEntity<?> retonar(@PathVariable int i) throws ModelNotFoundException, Exception {
		EstudianteDto estudiante;
	    estudiante = service.retornar(i);
		return new ResponseEntity<EstudianteDto>(estudiante, HttpStatus.OK);	
		//return ResponseEntity.ok(est);
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody Estudiante estudiante) throws ConflicException {
		service.guardar(estudiante);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody Estudiante estudiante)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflicException{
		this.service.editar(estudiante);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}	
	
	//204
	@DeleteMapping(value = "/eliminar/{i}")
	public ResponseEntity<?> eliminar(@PathVariable int i)  throws ModelNotFoundException{
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}	
	
	

	@DeleteMapping(value = "/eliminarHeader/{i}")
	public ResponseEntity<?> eliminarConHeader(@PathVariable int i) {
		EstudianteDto est  = new EstudianteDto("Johans", "Gonzalez " + i);
		HttpHeaders header = new HttpHeaders();
		header.add("info1", "valor 1");
		header.add("info2", "valor 2");
		return new ResponseEntity<Object>(header, HttpStatus.NO_CONTENT);
	}	
	*/
}