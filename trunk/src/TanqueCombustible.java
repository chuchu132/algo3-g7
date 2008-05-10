
public class TanqueCombustible extends Autoparte{
    
	public double capacidadMaxima;
    public double cantidadCombustible;
	public int octanage;
	
	public TanqueCombustible(double precio,double capMax){
		super(precio,capMax,1);
		this.capacidadMaxima = capMax;
		this.cantidadCombustible = 0.0;
		this.octanage= 0;
	}
	
	/*
	 * true si la carga fue normal 
	 * false si revalso el rebalso
	 * */
    public boolean cargarCombustible(double cuanto,int oct){
      cantidadCombustible += cuanto;
    	octanage = oct;
    	if(cantidadCombustible > capacidadMaxima){
    		cantidadCombustible = capacidadMaxima;
    		return false;
    	}else 
    		{
    		return true;
    		}
    	
    }
    
    public double getPeso(){
    	return ( super.getPeso() + cantidadCombustible );
    }
	
	public void darCombustible(double cuanto) {
		if(cantidadCombustible > cuanto ){
			cantidadCombustible -= cuanto;
		}else{
			cantidadCombustible = 0.0;
		}
		
	}
	
	public double cantidadCombustible(){
		return cantidadCombustible;
	}
	
	public boolean estaVacio(){
		return cantidadCombustible == 0.0;
	}

	public String getDetalles(){
		return (" Capacidad Maxima: " + capacidadMaxima + " Cantidad de Combustible: " + cantidadCombustible() );
		
	}
}
