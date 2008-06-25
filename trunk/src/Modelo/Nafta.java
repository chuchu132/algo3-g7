package Modelo;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Nafta implements Vendible{
	private int octanaje;
	private String nombre;
	private double precio;
	
	public Nafta(String name,int oct){
		octanaje = oct;
		nombre = name;
	}
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
	
	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element nafta = document.addElement("nafta");
		nafta.addAttribute("octanaje",Integer.toString(this.getOctanaje()));
		nafta.addAttribute("nombre",this.getNombre());
		nafta.addAttribute("precio",Double.toString(this.getPrecio()));
		return nafta;
	}
}
