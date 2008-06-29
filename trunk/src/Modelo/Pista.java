package Modelo;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Pista {
   private double coeficienteAgarre;
   private double longitud;
   
   public Pista(double longitud, double coeficiente){
	   this.coeficienteAgarre = coeficiente;
	   this.longitud = longitud;
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
public Pista deserialize(Element elemPista) {
	// TODO Auto-generated method stub
	return null;
}
}
