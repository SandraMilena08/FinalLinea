package cundi.edu.co.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.dto.LibroDto;
import cundi.edu.co.entity.Libro;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.service.ILibroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/libro")
@Validated
@Api(description="Servicios de libros")
public class LibroController {
	@Autowired
	private ILibroService service;
	

	@ApiOperation(value = "Obtener Libros"
            ,notes = "Este servicio traera los Libros que estan en el sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = LibroDto.class ),
            @ApiResponse(code = 400, message = "Bad Request, sucedio un error"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
	
	@GetMapping(value = "/obtener/{id}", produces = "application/json")
	public ResponseEntity<?> ObtenerLibrosId(@PathVariable Integer id) throws ModelNotFoundException{
		return ResponseEntity.ok(service.retonarPorId(id));
	}
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Libro> listaLibros = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Libro>>(listaLibros, HttpStatus.OK);	
	}	
		
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Libro> listaLibros = service.retornarPaginado(page);
		return new ResponseEntity<Page<Libro>>(listaLibros, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/buscarAutor/{id}")
	public ResponseEntity<?> buscar (@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.buscarAutor(id));
		}catch (Exception e){
			return null;
			}
	}
	
	@ApiOperation(value = "Crear Libros"
            ,notes = "Este servicio se encarga de crear los libros que se desean ingresar")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created(Creado)", response = LibroDto.class),
            @ApiResponse(code = 400, message = "Bad Request(solicitud incorrecta), sucedio un error"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@PostMapping(value = "/crear", consumes = "application/json")
	public ResponseEntity<?> crearLibro(@Valid @RequestBody Libro libro) throws ConflicException {
		service.crear(libro);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Actualizar Libro"
            ,notes = "Este servicio se encarga de actualizar libros")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = LibroDto.class ),
            @ApiResponse(code = 400, message = "Bad Request(solicitud incorrecta), sucedio un error"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
	
	@PutMapping(value = "/actualizar", consumes="application/json")
	public ResponseEntity<?> actualizarLibro(@Valid @RequestBody Libro libro) throws ArgumentRequiredException, ModelNotFoundException, ConflicException{
		service.editar(libro);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Eliminar libro"
            ,notes = "Este servicio se encarga de eliminar libros")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content", response = LibroDto.class ),
            @ApiResponse(code = 400, message = "Bad Request(solicitud incorrecta), sucedio un error"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema") })
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminarLibro(@PathVariable("id") @NotNull @Min(1) int id) throws ModelNotFoundException {
		service.eliminar(id);
		return new ResponseEntity<LibroDto>(HttpStatus.NO_CONTENT);
	}
	
}
