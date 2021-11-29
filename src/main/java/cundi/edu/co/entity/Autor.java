package cundi.edu.co.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="autor")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "El nombre no puede estar vacia")
	@Size(min = 2, max = 50, message = "Ingrese un valor entre 2 y 30 caracteres")
	@Column(name="nombre", length=15, nullable = false)
	private String nombre;
	
	@NotNull(message = "La cedula no puede estar vacia")
	@Size(min = 2, max = 10, message = "Ingrese un valor entre 2 y 30 caracteres")
	@Column(name="cedula", length=10, nullable = false, unique = true)
	private String cedula;
	
	@NotNull(message = "El apellido no puede estar vacia")
	@Size(min = 2, max = 50, message = "Ingrese un valor entre 2 y 30 caracteres")
	@Column(name="apellido", length=15, nullable = false)
	private String apellido;
	
	@NotNull(message = "El correo no puede estar vacia")
	@Size(min = 2, max = 50, message = "Ingrese un valor entre 2 y 30 caracteres")
	@Email
	@Column(name="correo", length=60, nullable = false, unique = true)
	private String correo;

	@OneToMany(mappedBy = "autor", cascade = { CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Libro> libro;
	
	public Autor() {
		
	}
	public Autor(String nombre, String cedula, String apellido, String correo) {
		super();
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	
	public List<Libro> getLibro() {
		return libro;
	}
	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
