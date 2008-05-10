public abstract class Autoparte {
	private double peso;
	private double precio;
	private double vidaUtil; // % de vida restante 1 = 100%
	
	public Autoparte(double peso, double precio,double vidaUtil){
		this.peso = peso;
		this.precio = precio;
		this.vidaUtil = vidaUtil;
	}
	
	public String getDetalles(){
		return ( " Precio: Algo$ " + precio + " , Vida Util: " + vidaUtil + " % ");
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
	public void gastar(double porcentage){
		vidaUtil *= (1- porcentage);
	}
	
	public double getPeso(){
		return this.peso;
	}
	
	
}
