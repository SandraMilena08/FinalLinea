package cundi.edu.co.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;

import cundi.edu.co.entity.Autor;
import cundi.edu.co.entity.Editorial;

public class AutorEditorialDto {
	@Id
	private Autor autor;
	
	@Id
	private Editorial editorial;
	
	@Column(name = "fecha", nullable = false) 
	private LocalDate fecha;
	
	public AutorEditorialDto() {
	}
	
	public AutorEditorialDto(Autor autor, Editorial editorial, LocalDate fecha) {
		super();
		this.autor = autor;
		this.editorial = editorial;
		this.fecha = fecha;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}

