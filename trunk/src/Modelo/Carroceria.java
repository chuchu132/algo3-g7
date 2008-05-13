package Modelo;

public class Carroceria extends Autoparte{
	 private String modelo;
	 private int color;
	 private double plusVelocidad;
	 
	public Carroceria(double precio,double peso, String modelo, int color, double plus ){
		super(peso,precio,1);
		this.modelo= modelo;
		this.color= color;
		this.plusVelocidad= plus;
		
	}
	  
	public String getDetalles(){
		return ("Carroceria: " + modelo + super.getDetalles());
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
	
	
}
