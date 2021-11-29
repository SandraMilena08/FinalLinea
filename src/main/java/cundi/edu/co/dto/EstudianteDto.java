package cundi.edu.co.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;

public class EstudianteDto extends RepresentationModel<EstudianteDto> {
	
	@ApiModelProperty(notes = "Nombre de Estudiante",name="nombre", required=true)
	@NotBlank(message = "EL nombre no puede estar vacio")
	@Size(min = 6, max = 15, message = "Ingrese un valor entre 6 y 15 caracteres")
	private String nombre;
	@ApiModelProperty(notes = "Nombre de Estudiante",name="apellido", required=true)
	@NotBlank(message = "EL apellido no puede estar vacio")
	@Size(min = 6, max = 15, message = "Ingrese un valor entre 6 y 15 caracteres")
	private String apellido;
	private List<Materias> materias;
	
	EstudianteDto(){
		
	}
	
	public EstudianteDto(@NotBlank(message = "EL nombre no puede estar vacio") String nombre,
			@NotBlank(message = "EL apellido no puede estar vacio") String apellido, List<Materias> materias) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.materias = materias;
	}

	public List<Materias> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materias> materias) {
		this.materias = materias;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
}