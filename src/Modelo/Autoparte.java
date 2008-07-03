package Modelo;

import java.util.Observable;


import org.dom4j.Attribute;
import org.dom4j.Element;


public abstract class Autoparte extends Observable implements Vendible {
	private double peso;
	private double precio;
	private float vidaUtil;
	private float vidaUtilInicial;
	private final double porcentajeVidaUtilMinima = 0.2; //20% --> pongo 0.2

	public Autoparte(double peso, double precio, float vidaUtilInicial){
		this.peso = peso;
		this.precio = precio;
		this.vidaUtil = this.vidaUtilInicial = vidaUtilInicial;

	}

	public Autoparte (Element elemAutoparte){
		Attribute atrPeso = elemAutoparte.attribute(0);
		Attribute atrPrecio = elemAutoparte.attribute(1);
		Attribute atrVidaUtil = elemAutoparte.attribute(2);
		Attribute atrVidaUtilInicial = elemAutoparte.attribute(3);

		this.peso = (Double.parseDouble(atrPeso.getValue()));
		this.precio = (Double.parseDouble(atrPrecio.getValue()));
		this.vidaUtil = (Float.parseFloat(atrVidaUtil.getValue()));
		this.vidaUtilInicial = (Float.parseFloat(atrVidaUtilInicial.getValue()));		

	}


	public double getPorcentajeVidaUtil() {
		return (vidaUtil)/vidaUtilInicial;
	}

	public double getVidaUtilMinima(){
		return porcentajeVidaUtilMinima;
	}

	public String toString(){
		return ( " Precio: Algo$ " + precio + " , Vida Util: " + (getPorcentajeVidaUtil() * 100 ) + " % ");
	}

	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getVidaUtil() {
		return vidaUtil;
	}

	public double getVidaUtilInicial(){
		return vidaUtilInicial;
	}

	public void gastar(double desgaste){
		if(vidaUtil - desgaste >= 0)
			vidaUtil -= desgaste;
		else
			vidaUtil = 0;
	}

	public double getPeso(){
		return this.peso;
	}

	public void grabar(Element autoParte){
		autoParte.addAttribute("peso",Double.toString(this.getPeso()));
		autoParte.addAttribute("precio",Double.toString(this.getPrecio()));
		autoParte.addAttribute("vidaUtil",Float.toString(vidaUtil));
		autoParte.addAttribute("vidaUtilInicial",Float.toString(vidaUtilInicial));
	}



}
