package cundi.edu.co.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditorialDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "El nombre no puede estar vacia")
	@Size(min = 2, max = 50, message = "Ingrese un valor entre 2 y 50 caracteres")
	@Column(name="nombre", length=50, nullable = false)
	private String nombre;
	
	@NotNull(message = "el NIT no puede estar vacia")
	@Size(min = 1, max = 10, message = "Ingrese un valor entre 1 y 10 caracteres")
	@Column(name="nit", length=10, nullable = false, unique = true)
	private String nit;
	
	@NotNull(message = "El correo no puede estar vacia")
	@Size(min = 2, max = 60, message = "Ingrese un valor entre 2 y 60 caracteres")
	@Email
	@Column(name="correo", length=60, nullable = false, unique = true)
	private String correo;

	
	public EditorialDto(String nombre, String correo, String nit) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.nit = nit;
	}

	public EditorialDto() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
}
