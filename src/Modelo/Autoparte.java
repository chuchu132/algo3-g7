package Modelo;

import java.util.Observable;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

//import Excepciones.ProblemaTecnicoException;

public abstract class Autoparte extends Observable implements Vendible {
	private double peso;
	private double precio;
	private float vidaUtil;
	private float vidaUtilInicial;
	private final double porcentajeVidaUtilMinima = 0.2; //20% --> pongo 0.2
	
	public Autoparte(double peso, double precio, float vidaUtilInicial){
		this.peso = peso;
		this.precio = precio;
		this.vidaUtil = this.vidaUtilInicial = vidaUtilInicial;
		
	}
	
	public double getPorcentajeVidaUtil() {
		return (vidaUtil)/vidaUtilInicial;
	}
	
	public double getVidaUtilMinima(){
		return porcentajeVidaUtilMinima;
	}
	
	public String toString(){
		return ( " Precio: Algo$ " + precio + " , Vida Util: " + (getPorcentajeVidaUtil() * 100 ) + " % ");
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getVidaUtil() {
		return vidaUtil;
	}
	/**
	 * Si se gasta el 10% pongo 0.10
	 * */
	public void gastar(double desgaste){
		if(vidaUtil - desgaste >= 0)
			vidaUtil -= desgaste;
		else
			vidaUtil = 0;
	}
	
	public double getPeso(){
		return this.peso;
	}
	
	public void grabar(Element autoParte){
		autoParte.addAttribute("peso",Double.toString(this.getPeso()));
		autoParte.addAttribute("precio",Double.toString(this.getPrecio()));
		autoParte.addAttribute("vidaUtil",Float.toString(vidaUtil));
		autoParte.addAttribute("vidaUtilInicial",Float.toString(vidaUtilInicial));
		autoParte.addAttribute("vidaUtilMinima",Double.toString(this.getVidaUtilMinima()));
	}
	
	//abstract public void simular(double tiempo)throws ProblemaTecnicoException;
	

}
