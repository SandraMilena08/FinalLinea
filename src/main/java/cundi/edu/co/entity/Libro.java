package cundi.edu.co.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="libro")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "REGIST_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;

	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 50, message = "El nombre debe estar entre 3 y 50 caracteres")
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;	
	
	@NotNull(message = "descripcion es obligatorio")
	@Size(min = 3, max = 50, message = "la descripcion debe estar entre 3 y 50 caracteres")
	@Column(name = "descripcion", length = 50, nullable = false)
	private String descripcion;	
	
	@NotNull(message = "numero es obligatorio")
	@Column(name = "numero_paginas", nullable = false)
	private Integer numeroPaginas;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_autor", nullable = false, foreignKey = @ForeignKey(name = "FK_Autor_Libro"))
	private Autor autor;
	
	public Libro() {
		
	}

	public Libro(Date fechaRegistro, String nombre, String descripcion, Integer numeroPaginas) {
		super();
		this.fechaRegistro = fechaRegistro;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numeroPaginas = numeroPaginas;
	}


	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}	
	
	
}