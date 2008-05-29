package Modelo;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class SistemaCombustion extends Autoparte{
	private String tipo;
	private double plusPotencia; 
	private final double factorDeDesgaste = 0.5;
	/*
	 * ej: si vale 0.1 a la potencia del motor la multiplicamos x 1,1
	 * y obtenemos un 10% mas de potencia
	 */ 
   public SistemaCombustion(double precio, double peso, String tipo, double plus, long vidaUtilInicial){
	   super(peso,precio, vidaUtilInicial);
	   this.tipo = tipo;
	   this.plusPotencia = plus;
   }

   

   public boolean tieneCombustible(TanqueCombustible tanque){
	   return !tanque.estaVacio();
   }
   
   public void quemarCombustible(double cantidadCombustible, TanqueCombustible tanque)throws TanqueVacioException{
	   tanque.darCombustible(cantidadCombustible);
	  }
   
   public String toString() {
	   return( " Sistema de Combustion: " + tipo + " Plus de Potencia: "+ plusPotencia + super.toString());
	}
   
   public double getPlus(){
	   return plusPotencia;
   }
   
   public void simular(double tiempo) throws ProblemaTecnicoException{
	   double desgaste = tiempo/(getPorcentajeVidaUtil()-factorDeDesgaste);
	   gastar(desgaste);
	   if (getPorcentajeVidaUtil()<getVidaUtilMinima()){
		   throw new ProblemaTecnicoException();}
	}
}
