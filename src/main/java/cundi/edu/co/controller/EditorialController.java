package cundi.edu.co.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.dto.AutorDto;
import cundi.edu.co.dto.EditorialAutorDto;
import cundi.edu.co.dto.EditorialDto;
import cundi.edu.co.dto.IAutorDto;
import cundi.edu.co.entity.Autor;
import cundi.edu.co.entity.AutorEditorial;
import cundi.edu.co.entity.Editorial;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.service.IAutorEditorialService;
import cundi.edu.co.service.IEditorialService;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {
	
	@Autowired
	private IAutorEditorialService serviceAutorEditorial;
	
	@Autowired
	private IEditorialService service;
	
	@PostMapping(value = "/crear", consumes = "application/json")
	public ResponseEntity<?> crearEditorial(@Valid @RequestBody Editorial editorial) throws ConflicException {
		service.crear(editorial);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@PostMapping(value = "/asociarEditorial", consumes = "application/json")
	public ResponseEntity<?> asociarEditorial(@Valid @RequestBody AutorEditorial autorEditorial) throws ConflicException {
		serviceAutorEditorial.crear(autorEditorial);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/eliminar/{idAutor}/{idEditorial}")
	public ResponseEntity<?> eliminarEditorial(@PathVariable Integer idAutor,@PathVariable Integer idEditorial) throws ModelNotFoundException {
		serviceAutorEditorial.eliminar(idAutor,idEditorial);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/obtener/{id}", produces = "application/json")
	public ResponseEntity<?> ObtenerAutoresId(@PathVariable Integer id) throws ModelNotFoundException{
		return ResponseEntity.ok(service.retonarPorId(id));
	}
	
	@GetMapping(value = "/obtenerPaginadoAutoresEditorial/{id}/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int id, @PathVariable int page, @PathVariable int size) throws ModelNotFoundException {
		Page<IAutorDto> listaAutorEditorial = serviceAutorEditorial.retornarPaginadoAutoresPorEditorial(id, page, size);
		return new ResponseEntity<Page<IAutorDto>>(listaAutorEditorial, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPaginadoEditorialAutor/{id}/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginadoEditorial(@PathVariable int id, @PathVariable int page, @PathVariable int size) throws ModelNotFoundException {
		Page<EditorialAutorDto> listaEditorialAutor = serviceAutorEditorial.retornarPaginanoEditorialesPorAutores(id, page, size);
		return new ResponseEntity<Page<EditorialAutorDto>>(listaEditorialAutor, HttpStatus.OK);	
	}	
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminarEditorial(@PathVariable("id") @NotNull @Min(1) int id) throws ModelNotFoundException {
		service.eliminar(id);
		return new ResponseEntity<EditorialDto>(HttpStatus.NO_CONTENT);
	}
}