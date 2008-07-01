package Modelo;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Attribute;

public class Pista {
   private double coeficienteAgarre;
   private double longitud;
   
   public Pista(double longitud, double coeficiente){
	   this.coeficienteAgarre = coeficiente;
	   this.longitud = longitud;
   }
   
   public Pista(){
	   double temp = (1 - Math.random());
	   this.coeficienteAgarre = temp;
	   this.longitud = (1000 * (temp + 0.1)); //longitud entre 100 y 1100 metros.
	   
   }
   
   public Pista(Element elemPista){
	  Attribute atrCoef = elemPista.attribute(0);
	  Attribute atrLongitud = elemPista.attribute(1);
	  
	  coeficienteAgarre = Double.parseDouble(atrCoef.getValue());
	  longitud = Double.parseDouble(atrLongitud.getValue());
		    
   }
   public double getCoeficienteAgarre() {
		
		return coeficienteAgarre;
	}
   public double getLongitud(){
	
	   return longitud;
   }
   
   public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element pista = document.addElement("pista");
		pista.addAttribute("coeficienteAgarre",Double.toString(this.getCoeficienteAgarre()));
		pista.addAttribute("longitud",Double.toString(this.getLongitud()));
		return pista;
		
   }
  /* 
   public void deserialize(Element elemPista) {
	  
	  Attribute atrCoef = elemPista.attribute(0);
	  Attribute atrLongitud = elemPista.attribute(1);
	  
	  coeficienteAgarre = Double.parseDouble(atrCoef.getValue());
	  longitud = Double.parseDouble(atrLongitud.getValue());
	  
   }
   */
   
}
