package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestamo")
public class Prestamo {

	@Id
	@Column(name = "Id")
	private int id;
	@Column(name = "Libro")
	private int idLibro;
	@Column(name = "Lector")
	private int idLector;
	@Column(name = "FechaPrestamo")
	private String fechaPrestamo;
	@Column(name = "FechaDevolucion")
	private String fechaDevolucion;	
	
	public Prestamo() {
		
	}
	
	public Prestamo(int idLibro, int idLector, String fechaPrestamo, String fechaDevolucion) {				
		this.idLibro = idLibro;
		this.idLector = idLector;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public int getIdLector() {
		return idLector;
	}
	public void setIdLector(int idLector) {
		this.idLector = idLector;
	}
	public String getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	
	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", idLibro=" + idLibro + ", idLector=" + idLector + ", fechaPrestamo="
				+ fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + "]";
	}
	
}
