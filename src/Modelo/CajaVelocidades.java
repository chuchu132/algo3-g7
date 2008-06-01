package Modelo;

import Excepciones.ProblemaTecnicoException;

public class CajaVelocidades extends Autoparte{
	private int cambioActual;
	private int cantidadCambios;
	private double [] relaciones;
	private final double factorDeDesgaste = 0.1;
	
	
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
		if(cambioActual < cantidadCambios){
			cambioActual++;
			setChanged();
	        notifyObservers();}	 
	}
	
	public void bajarCambio (){
		if(cambioActual > 0){
		cambioActual--;
		setChanged();
	    notifyObservers();}
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

	
	// el desgaste es proporcional al tiempo
	
	
	public void simular(double tiempo) throws ProblemaTecnicoException{
		double desgaste;
		desgaste = (tiempo * factorDeDesgaste);
		gastar (desgaste);
		if (getPorcentajeVidaUtil()<getVidaUtilMinima()){
			   throw new ProblemaTecnicoException();}
	}

	
	
}



