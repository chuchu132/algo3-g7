
public class TanqueCombustible extends Autoparte{
    
	public double capacidadMaxima;
    public double cantidadCombustible;
	public int octanage;
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
	public void darCombustible(double cuanto) {
		if(cantidadCombustible > cuanto ){
			cantidadCombustible -= cuanto;
		}else{
			cantidadCombustible = 0.0;
		}
		
	}
	
	public boolean estaVacio(){
		return cantidadCombustible == 0.0;
	}

}
