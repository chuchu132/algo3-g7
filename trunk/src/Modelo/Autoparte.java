package Modelo;

//import Excepciones.ProblemaTecnicoException;

public abstract class Autoparte {
	private double peso;
	private double precio;
	private long vidaUtil;
	private long vidaUtilInicial;
	
	public Autoparte(double peso, double precio, long vidaUtilInicial){
		this.peso = peso;
		this.precio = precio;
		this.vidaUtil = this.vidaUtilInicial = vidaUtilInicial;
	}
	
	public double getPorcentajeVidaUtil() {
		return (vidaUtil*100)/vidaUtilInicial;
	}
	
	public String toString(){
		return ( " Precio: Algo$ " + precio + " , Vida Util: " + (vidaUtil * 100 ) + " % ");
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
