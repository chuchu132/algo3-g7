package Modelo;

public class Carroceria extends Autoparte{
	 private String modelo;
	 private int color;
	 private double plusVelocidad;
	 /*
	  * ej si vale 0.1 a la velocidad final del auto en cierto cambio
	  * la multiplicamos x 1.1 y obtenemos 10% mas de velocidad.
	  * */
	public Carroceria(double precio,double peso, String modelo, int color, int plus ){
		super(precio,peso,1);
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
		return plusVelocidad;
	}
	public void setPlusVelocidad(double plusVelocidad) {
		this.plusVelocidad = plusVelocidad;
	}
	
}
