package Modelo;

import Excepciones.ProblemaTecnicoException;

public class Carroceria extends Autoparte{
	 private String modelo;
	 private int color;
	 private double plusVelocidad;
	 private final double factorDeDesgaste = 0.001; 
	 
	public Carroceria(double precio,double peso, String modelo, int color, double plus, long vidaUtilInicial ){
		super(peso,precio, vidaUtilInicial);
		this.modelo= modelo;
		this.color= color;
		this.plusVelocidad= plus;
		
	}
	  
	public String toString(){
		return ("Carroceria: " + modelo + super.toString());
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public double getPlusVelocidad() {
		return (plusVelocidad * super.getVidaUtil());
	}
	
	public void simular(double tiempo) throws ProblemaTecnicoException{
		double desgaste = tiempo * factorDeDesgaste;
		gastar(desgaste);
		if (getPorcentajeVidaUtil()<getVidaUtilMinima()){
			throw new ProblemaTecnicoException();}
		}
	
	
	
}
