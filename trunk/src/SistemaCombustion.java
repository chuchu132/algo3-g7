
public class SistemaCombustion extends Autoparte{
     
	private String tipo;
	private double plusPotencia; 
	/*
	 * ej: si vale 0.1 a la potencia del motor la multiplicamos x 1,1
	 * y ontenemos un 10% mas de potencia
	 */ 
   public String getDetalles() {
	   return( "Sistema de Combustion: " + tipo + "Plus de Potencia: "+ plusPotencia + super.getDetalles());
	}
   public double getPlus(){
	   return plusPotencia;
   }
}
