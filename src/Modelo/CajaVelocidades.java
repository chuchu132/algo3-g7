package Modelo;

import Excepciones.ProblemaTecnicoException;

public class CajaVelocidades extends Autoparte{
	private int cambioActual;
	private int cantidadCambios;
	private double [] relaciones;
	private double factorDeDesgaste = 0.1; 
	
	
	public CajaVelocidades (int cantidadCambios,double precio, double peso, long vidaUtilInicial){
		super(peso,precio, vidaUtilInicial);
		cambioActual = 0;
		this.cantidadCambios = cantidadCambios;
		relaciones = new double [cantidadCambios + 1];
		
		relaciones[0] = 0;
		for(int i = 1; i < (cantidadCambios + 1); i++){ 
			relaciones[i] = ((double)(cantidadCambios+1-i)/cantidadCambios );} 
	}

		
	public void subirCambio () {
		if(cambioActual < cantidadCambios)
			cambioActual++;
		 }
	
	public void bajarCambio (){
		if(cambioActual > 0)
				cambioActual--;
	}
	

	public int getCambioActual (){
		return cambioActual;
	}
	
				
	public double obtenerRelacion() {
		return relaciones[cambioActual];
	}


	public void setCambio(int numeroCambio) {
		cambioActual = numeroCambio;		
	}

	//ver si es necesario recibir tiempo aqui
	
	/*
	
	public void simular(double tiempo, double revolucionesActuales) throws ProblemaTecnicoException{
		double desgaste;
		desgaste = (tiempo * (factorDeDesgaste/revolucionesActuales));
		gastar (desgaste);
		if (getPorcentajeVidaUtil()<getVidaUtilMinima()){
			   throw new ProblemaTecnicoException();}
	}
*/
	
	public void simular (double tiempo){};
}



