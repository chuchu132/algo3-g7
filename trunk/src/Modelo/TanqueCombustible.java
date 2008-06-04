package Modelo;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class TanqueCombustible extends Autoparte{
   
	private final int OCTANAGE_COMUN = 91;
	private final int OCTANAGE_SUPER = 95;
	private final int OCTANAGE_EXTRA = 98;
	private double capacidadMaxima;
    private double cantidadCombustible;
	private Nafta tipoNafta;
	// costante utilizada para calcular el desgaste al ser utilizado
	private final double factorDeDesgaste = 0.001;
	
	public TanqueCombustible(double precio,double capMax, float vidaUtilInicial){
		super((capMax/10.0),precio, vidaUtilInicial);
		this.capacidadMaxima = capMax;
		this.cantidadCombustible = 0.0;
		
	}
	/**
	 * Este metodo recibe X cantidad de un Tipo de Combustible primero verifica que no rebalse el tanque,
	 * luego hace un promedio para calcular el octanage resultante la cuenta es:  
	 * OctInicial * VolInicial   +  OctanageDeX * X
	 * ------------------------------------------------                   
	 *                  (VolInicial + X)
	 * Dependiendo de que valor toma la mezcla de Naftas se las estandariza.
	 * */
	
	
	public void cargarCombustible(double cuanto,Nafta nafta){

		if(cantidadCombustible + cuanto  > capacidadMaxima){
			cuanto=(capacidadMaxima - cantidadCombustible);
			cantidadCombustible = capacidadMaxima;
		}
		else {
			cantidadCombustible += cuanto;
		}

		if( tipoNafta != null ) {
			if(!estaVacio()){

				int octanageResultante = (int) (  ((tipoNafta.getOctanaje()*cantidadCombustible)	+  (nafta.getOctanaje()*cuanto) )/(cantidadCombustible+cuanto) );

				if(octanageResultante <= (OCTANAGE_COMUN + OCTANAGE_SUPER)/2 ){
					tipoNafta= new Nafta("Comun",OCTANAGE_COMUN);
				}
				else{ 
					if(octanageResultante < (OCTANAGE_SUPER + OCTANAGE_EXTRA)/2){
						tipoNafta= new Nafta("Super",OCTANAGE_SUPER);
					}
					else{
						tipoNafta= new Nafta("Extra",OCTANAGE_EXTRA);
					}
				}
			}
			else{
				tipoNafta = new Nafta(nafta.getNombre(),nafta.getOctanaje());
			}
		}
		else{
			tipoNafta = new Nafta(nafta.getNombre(),nafta.getOctanaje());
		}
	}
    
    public double getPeso(){
    	return ( super.getPeso() + cantidadCombustible );
    }
	
    public Nafta getTipoNafta(){
    	return this.tipoNafta;
    }
    
	public void darCombustible(double cuanto) throws TanqueVacioException {
	 if(!estaVacio()){	
		double segunOctanage = (cuanto*(2.0 - (tipoNafta.getOctanaje()/ 100.0)));
		if(cantidadCombustible > segunOctanage ){
			cantidadCombustible -= segunOctanage;
		}else{
			cantidadCombustible = 0.0;
			throw new TanqueVacioException();
		}
	 }
	 else{
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
