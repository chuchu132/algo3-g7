package Modelo;

import java.text.DecimalFormat;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Attribute;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class SistemaCombustion extends Autoparte{
	private String tipo;
	private double plusPotencia; 
	private final double factorDeDesgaste = 10;
	/*
	 * ej: si vale 0.1 a la potencia del motor la multiplicamos x 1,1
	 * y obtenemos un 10% mas de potencia
	 */ 
	public SistemaCombustion(double precio, double peso, String tipo, double plus, float vidaUtilInicial){
		super(peso,precio, vidaUtilInicial);
		this.tipo = tipo;
		this.plusPotencia = plus;
	}


	public SistemaCombustion(Element elemSistemaCombustion) {
		super(elemSistemaCombustion);
		Attribute atrTipo = elemSistemaCombustion.attribute(4);
		Attribute atrPlusPotencia = elemSistemaCombustion.attribute(5);

		this.tipo = (String) atrTipo.getValue();
		this.plusPotencia = Double.parseDouble(atrPlusPotencia.getValue());
	}


	public void quemarCombustible(double cantidadCombustible, TanqueCombustible tanque)throws TanqueVacioException{
		tanque.darCombustible(cantidadCombustible);
	}

	public String toString() {
		DecimalFormat porcentage = new DecimalFormat("0.00");
		return( " Sist. de Combustion: " + tipo + " Plus de Potencia: "+ plusPotencia +" Vida Util: " + porcentage.format(100*getPorcentajeVidaUtil())+ " %" );
	}

	public double getPlus(){
		return plusPotencia;
	}

	public void simular(double tiempo) throws ProblemaTecnicoException{
		double desgaste = tiempo*factorDeDesgaste;
		gastar(desgaste);
		if (getPorcentajeVidaUtil()<getVidaUtilMinima()){
			throw new ProblemaTecnicoException();}
	}

	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element sistemaCombustion = document.addElement("sistemaCombustion");
		this.grabar(sistemaCombustion);
		sistemaCombustion.addAttribute("tipo",tipo);
		sistemaCombustion.addAttribute("plusPotencia",Double.toString(this.getPlus()));
		return sistemaCombustion;
	}
	/* el metodo getTipo no se usa en el juego, pero es necesarios para la prueba
	 * de guardar y cargar
	 * */
	   
	public String getTipo(){
		   return tipo;
	   }	
}

