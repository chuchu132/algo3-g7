package Modelo;

import java.text.DecimalFormat;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class TanqueCombustible extends Autoparte{
   
	
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

		if( tipoNafta != null ) {
			if(!estaVacio()){

				int octanageResultante = (int) (  ((tipoNafta.getOctanaje()*cantidadCombustible)	+  (nafta.getOctanaje()*cuanto) )/(cantidadCombustible+cuanto) );
				tipoNafta = new Nafta("hibrida",octanageResultante);
			
			}
			else{
				tipoNafta = new Nafta(nafta.getNombre(),nafta.getOctanaje());
			}		
		
		
		if(cantidadCombustible + cuanto  > capacidadMaxima){
			cuanto=(capacidadMaxima - cantidadCombustible);
			cantidadCombustible = capacidadMaxima;
		}
		else {
			cantidadCombustible += cuanto;
		}

		
		}
		else{
		  if(nafta != null){	
			tipoNafta = new Nafta(nafta.getNombre(),nafta.getOctanaje());
			if(cantidadCombustible + cuanto  > capacidadMaxima){
				cuanto=(capacidadMaxima - cantidadCombustible);
				cantidadCombustible = capacidadMaxima;
			}
			else {
				cantidadCombustible += cuanto;
			}
		  }
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
		DecimalFormat porcentage = new DecimalFormat("0.00");
		return ("Tanque Combustible de " + capacidadMaxima + " L " + " Vida Util: " + porcentage.format(100*getPorcentajeVidaUtil())+ " %" );
		
	}
	
	public void vaciarTanque(){
		this.cantidadCombustible = 0;
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
	
	 public Element serialize(){
			Document document = DocumentHelper.createDocument();
			Element tanqueCombustible = document.addElement("tanqueCombustible");
			this.grabar(tanqueCombustible);
			tanqueCombustible.addAttribute("capacidadMaxima",Double.toString(capacidadMaxima));
			tanqueCombustible.addAttribute("cantidadCombustible",Double.toString(this.cantidadCombustible()));
			tanqueCombustible.add(this.getTipoNafta().serialize());
			return tanqueCombustible;
		}
}
