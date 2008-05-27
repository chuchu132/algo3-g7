package Modelo;

import Excepciones.TanqueVacioException;

public class TanqueCombustible extends Autoparte{
    
	private double capacidadMaxima;
    private double cantidadCombustible;
	private int octanage;
	
	public TanqueCombustible(double precio,double capMax){
		super((capMax/10.0),precio);
		this.capacidadMaxima = capMax;
		this.cantidadCombustible = 0.0;
		this.octanage= 94; // 94 - 100 valores posibles. A mayor octanage menos combustible gasta.
	}
	
	 public void cargarCombustible(double cuanto,int oct){
		
    	if(cantidadCombustible + cuanto  > capacidadMaxima){
    		octanage = (int) ((octanage*(cantidadCombustible/(capacidadMaxima)))	+  (oct*(1 - (cantidadCombustible/capacidadMaxima))));
    		cantidadCombustible = capacidadMaxima;
    	   }
    	else {
    		octanage = (int) ((octanage*(cantidadCombustible/(cantidadCombustible+cuanto)))	+  (oct*(cuanto/(cantidadCombustible+cuanto))));
    		cantidadCombustible += cuanto;
    	}
	 }
    
    public double getPeso(){
    	return ( super.getPeso() + cantidadCombustible );
    }
	
    public int getOctanage(){
    	return this.octanage;
    }
    
	public void darCombustible(double cuanto) throws TanqueVacioException {
		double segunOctanage = (cuanto*(2.0 - (octanage / 100.0)));
		if(cantidadCombustible > segunOctanage ){
			cantidadCombustible -= segunOctanage;
		}else{
			cantidadCombustible = 0.0;
			throw new TanqueVacioException();
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
