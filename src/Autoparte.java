public abstract class Autoparte {
	private float peso;
	private float precio;
	private float vidaUtil; // % de vida restante
	
	
	
	public String getDetalles(){
		return ( " Precio: Algo$ " + precio + " , Vida Util: " + vidaUtil + " % ");
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public float getVidaUtil() {
		return vidaUtil;
	}
	
	public float getPeso(){
		return this.peso;
	}
	
	
}
