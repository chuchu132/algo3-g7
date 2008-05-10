
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
}
