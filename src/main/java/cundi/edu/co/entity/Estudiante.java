package cundi.edu.co.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre", length=15, nullable = false)
	private String nombre;
	
	@Column(name="cedula", length=10, nullable = false, unique = true)
	private String cedula;
	
	@Column(name="apellido", length=15, nullable = false)
	private String apellido;
	
	@Column(name="correo", length=60, nullable = false, unique = true)
	private String correo;
	
	public Estudiante() {
		
	}
	
	public Estudiante(Integer id, String nombre, String cedula , String apellido, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cedula = cedula;
		this.apellido = apellido;
		this.correo = correo;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
	
}
