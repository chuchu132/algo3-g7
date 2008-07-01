package Modelo;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Attribute;

import Excepciones.ProblemaTecnicoException;

public class Carroceria extends Autoparte{
	 private String modelo;
	 private int color;
	 private double plusVelocidad;
	 private final double factorDeDesgaste = 10; 
	 
	public Carroceria(double precio,double peso, String modelo, int color, double plus, long vidaUtilInicial ){
		super(peso,precio, vidaUtilInicial);
		this.modelo= modelo;
		this.color= color;
		this.plusVelocidad= plus;
		
	}
	
	
	public Carroceria(Element elemCarroceria) {
		super(elemCarroceria);
		Attribute atrModelo = elemCarroceria.attribute(4);
		Attribute atrColor = elemCarroceria.attribute(5);
		Attribute atrPlus = elemCarroceria.attribute(6);
		
		this.modelo = atrModelo.getValue();
		this.color = Integer.parseInt(atrColor.getValue());
		this.plusVelocidad = Double.parseDouble(atrPlus.getValue());		
		
	}


	public String toString(){
	  DecimalFormat porcentage = new DecimalFormat("0.00");
		return ("Carroceria: " + modelo + " Vida Util: " + porcentage.format(100*getPorcentajeVidaUtil())+ " %");
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public double getPlusVelocidad() {
		return (plusVelocidad * super.getPorcentajeVidaUtil());
	}
	public String getModelo(){
		return modelo;
	}
	
	public void simular(double tiempo) throws ProblemaTecnicoException{
		double desgaste = tiempo * factorDeDesgaste;
		gastar(desgaste);
		if (getPorcentajeVidaUtil()<getVidaUtilMinima()){
			throw new ProblemaTecnicoException();}
		}
	
	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element carroceria = document.addElement("carroceria");
		this.grabar(carroceria);
		carroceria.addAttribute("modelo",modelo);
		carroceria.addAttribute("color",Integer.toString(this.getColor()));
		carroceria.addAttribute("plusVelocidad",Double.toString(this.getPlusVelocidad()));
		return carroceria;
	}

			
}
