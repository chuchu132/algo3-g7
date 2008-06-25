package Modelo;

import Excepciones.ProblemaTecnicoException;
import org.dom4j.Element;
import org.dom4j.Document;

import org.dom4j.*;
import org.dom4j.io.*;


public class CajaVelocidades extends Autoparte{
	private int cambioActual;
	private int cantidadCambios;
	private double [] relaciones;
	private final double factorDeDesgaste = 0.00001;
	
	
	public CajaVelocidades (int cantidadCambios,double precio, double peso, float vidaUtilInicial){
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
    
	public int getCantidadCambios(){
		return cantidadCambios;
	}
	
	// el desgaste es proporcional al tiempo
	
	
	public void simular(double tiempo) throws ProblemaTecnicoException{
		double desgaste;
		desgaste = (tiempo * factorDeDesgaste);
		gastar (desgaste);
		if (getPorcentajeVidaUtil()<getVidaUtilMinima()){
			   throw new ProblemaTecnicoException();}
	}
	
	public void guardarRelaciones(Element relacionesAux){
		for (double doble : this.relaciones) {
			relacionesAux.addAttribute("double",Double.toString(doble));
		}
	}
	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element cajaVelocidades = document.addElement("cajaVelocidades");
		this.grabar(cajaVelocidades);
		cajaVelocidades.addAttribute("cambioActual",Integer.toString(this.getCambioActual()));
		cajaVelocidades.addAttribute("cantidadDeCambios",Integer.toString(cantidadCambios));
		Element relacionesAux = cajaVelocidades.addElement("relaciones");
		guardarRelaciones(relacionesAux);
		return cajaVelocidades;
	}
	
		
}



