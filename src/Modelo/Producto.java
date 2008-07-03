package Modelo;



public class Producto {
	private int numero;
	private String nombre;
	private String descripcion;
	private String nombreAutoparte;
	private double precio;



	//contructor
	public Producto(int numero, String nombreAutoparte, String nombre, String descripcion,
			double precio) {

		this.numero = numero;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.nombreAutoparte = nombreAutoparte;
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

	public String getNombreAutoparte() {
		return nombreAutoparte;
	}

	public void setNombreAutoparte(String nombreAutoparte) {
		this.nombreAutoparte = nombreAutoparte;
	}




}
