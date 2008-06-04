package Modelo;

public class Nafta {
	private int octanaje;
	private String nombre;
	private double precio;
	public Nafta(String name,int oct,double cost){
		octanaje = oct;
		nombre = name;
		precio = cost;
	}	
	
	public int getOctanaje(){
		return octanaje;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public double getPrecio(){
		return precio;
	}
}
