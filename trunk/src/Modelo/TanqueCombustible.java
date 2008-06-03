package Modelo;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class TanqueCombustible extends Autoparte{
    
	private double capacidadMaxima;
    private double cantidadCombustible;
	private int octanage;
	// costante utilizada para calcular el desgaste al ser utilizado
	private final double factorDeDesgaste = 0.001;
	
	public TanqueCombustible(double precio,double capMax, float vidaUtilInicial){
		super((capMax/10.0),precio, vidaUtilInicial);
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

	public String toString(){
		return (" Capacidad Maxima: " + capacidadMaxima + " Cantidad de Combustible: " + cantidadCombustible() );
		
	}
	
	public void simular(double tiempo) throws ProblemaTecnicoException{
		/* el tanque se desgastara si tiene que hacer un mayor esfuerzo en 
 		 * obtener el combustible, es decir si no le queda mucho 
 		*/
		double desgaste = tiempo * (factorDeDesgaste /(cantidadCombustible + 1));
		gastar(desgaste);
		 if (getPorcentajeVidaUtil()<getVidaUtilMinima()){
			   throw new ProblemaTecnicoException();}
	}
}
