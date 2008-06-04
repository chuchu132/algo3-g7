package Modelo;

import java.util.Observable;

//import Excepciones.ProblemaTecnicoException;

public abstract class Autoparte extends Observable {
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
	
	//abstract public void simular(double tiempo)throws ProblemaTecnicoException;
	

}
