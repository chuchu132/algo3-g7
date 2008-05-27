package Modelo;

public class Carroceria extends Autoparte{
	 private String modelo;
	 private int color;
	 private double plusVelocidad;
	 
	public Carroceria(double precio,double peso, String modelo, int color, double plus ){
		super(peso,precio);
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
	
	public void simular(double tiempo) {
		//CODIFICAR
	}
	
	
}
