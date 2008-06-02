package Modelo;

import java.lang.reflect.Method;

public class Producto {
	private int numero;
	private String nombre;
	private String descripcion;
	private double precio;
	private Method contructor;
	
	//contructor
	public Producto(int numero, String nombre, String descripcion,
			double precio, Method contructor) {
		
		this.numero = numero;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.contructor = contructor;
	}	
	
	//getters
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Method getContructor() {
		return contructor;
	}
	public void setContructor(Method contructor) {
		this.contructor = contructor;
	}

	//métodos
	
	public Object construir() {
		return "sdfd";
	}
	
	


	
}
