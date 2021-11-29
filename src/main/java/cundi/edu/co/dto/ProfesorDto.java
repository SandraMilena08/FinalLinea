package cundi.edu.co.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;

public class ProfesorDto extends RepresentationModel<ProfesorDto>{
	
	@ApiModelProperty(notes = "Nombre de profesor",name="nombre", required=true)
	@NotBlank(message = "EL nombre no puede estar vacio")
	private String nombre;
	
	@ApiModelProperty(notes = "Codigo del profesor",name="profesor", required=true)
	@NotNull
	@Size(min = 6, max = 6, message = "Ingrese un valor de 6 caracteres")
	private String codigo;
	
	@ApiModelProperty(notes = "Celular del profesor",name="celular", required=true)
	@NotNull
	@Size(min = 10, max = 10, message = "Ingrese un valor de 10 caracteres")
	private String celular;
	
	@ApiModelProperty(notes = "Edad del profesor",name="edad", required=true)
	@NotNull
	@Min(18)
	@Max(99)
	private short edad;
	
	@ApiModelProperty(notes = "Email de profesor",name="email", required=true)
	@NotNull
	@Email(message = "Debe ser valido el email")
	private String email;
	
	@ApiModelProperty(notes = "Lista de materias del profesor",name="materias", required=false)
	private List<Materias> materias;
	ProfesorDto(){
		
	}
	
	public ProfesorDto(@NotBlank(message = "EL nombre no puede estar vacio") String nombre,
			@NotNull @Size(min = 6, max = 6, message = "Ingrese un valor de 6 caracteres") String codigo,
			@NotNull @Size(min = 10, max = 10, message = "Ingrese un valor de 10 caracteres") String celular,
			@NotNull @Min(18) @Max(99) short edad, @NotNull @Email(message = "Debe ser valido el email") String email,
			List<Materias> materias) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.celular = celular;
		this.edad = edad;
		this.email = email;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public short getEdad() {
		return edad;
	}

	public void setEdad(short edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
