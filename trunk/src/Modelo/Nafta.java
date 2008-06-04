package Modelo;

public class Nafta {
	private int octanaje;
	private String nombre;
	public Nafta(String name,int oct){
		octanaje = oct;
		nombre = name;
	}	
	
	public int getOctanaje(){
		return octanaje;
	}
	
	public String getNombre(){
		return nombre;
	}
}
