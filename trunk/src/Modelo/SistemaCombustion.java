package Modelo;

import Excepciones.TanqueVacioException;

public class SistemaCombustion extends Autoparte{
    private TanqueCombustible tanque=null; 
	private String tipo;
	private double plusPotencia; 
	/*
	 * ej: si vale 0.1 a la potencia del motor la multiplicamos x 1,1
	 * y obtenemos un 10% mas de potencia
	 */ 
   public SistemaCombustion(double precio, double peso, String tipo, double plus){
	   super(peso,precio);
	   this.tipo = tipo;
	   this.plusPotencia = plus;
   }
	
   public void conectarTanque( TanqueCombustible unTanque){
	   tanque= unTanque;
   }
  
   public TanqueCombustible desconectarTanque(){
	   TanqueCombustible tanqueTemp= tanque;
	   tanque = null;
	   return tanqueTemp;
   }
	
   public boolean tieneCombustible(){
	   return !tanque.estaVacio();
   }
   
   public void quemarCombustible(double cantidadCombustible)throws TanqueVacioException{
	   tanque.darCombustible(cantidadCombustible);
	  }
   
   public String toString() {
	   return( " Sistema de Combustion: " + tipo + " Plus de Potencia: "+ plusPotencia + super.toString());
	}
   public double getPlus(){
	   return plusPotencia;
   }
   
   public void simular(double tiempo) {
		//CODIFICAR
	}
}
