package Modelo;

import java.text.DecimalFormat;


import Excepciones.ProblemaTecnicoException;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Document;

import org.dom4j.*;



public class CajaVelocidades extends Autoparte{
	private int cambioActual;
	private int cantidadCambios;
	private double [] relaciones;
	private final double factorDeDesgaste = 20;
	
	
	public CajaVelocidades (int cantidadCambios,double precio, double peso, float vidaUtilInicial){
		super(peso,precio, vidaUtilInicial);
		cambioActual = 0;
		this.cantidadCambios = cantidadCambios;
		relaciones = new double [cantidadCambios + 1];
		
		relaciones[0] = 0;
		for(int i = 1; i < (cantidadCambios + 1); i++){ 
			relaciones[i] = ((double)(cantidadCambios+1-i)/cantidadCambios );} 
	}

	public CajaVelocidades(Element elemCaja) {
		super(elemCaja);
		this.cambioActual = 0;
		Attribute atrCantidadDeCambios = elemCaja.attribute(4);
		this.cantidadCambios = (Integer.parseInt(atrCantidadDeCambios.getValue()));
		relaciones = new double [cantidadCambios + 1];
		
		
		relaciones[0] = 0;
		for(int i = 1; i < (cantidadCambios + 1); i++){ 
			relaciones[i] = ((double)(cantidadCambios+1-i)/cantidadCambios );
			}

	}

	public String toString(){
		DecimalFormat porcentage = new DecimalFormat("0.00");
		return ("Caja " + cantidadCambios + " Velocidades." + " Vida Util: " + porcentage.format(100*super.getPorcentajeVidaUtil()) + " %.");
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
	
	
	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element cajaVelocidades = document.addElement("CajaVelocidades");
		this.grabar(cajaVelocidades);
		cajaVelocidades.addAttribute("cantidadDeCambios",Integer.toString(this.getCantidadCambios()));
		return cajaVelocidades;
	}

	
		
}



