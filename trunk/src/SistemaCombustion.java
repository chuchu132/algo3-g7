
public class SistemaCombustion extends Autoparte{
    private TanqueCombustible tanque=null; 
	private String tipo;
	private double plusPotencia; 
	/*
	 * ej: si vale 0.1 a la potencia del motor la multiplicamos x 1,1
	 * y ontenemos un 10% mas de potencia
	 */ 
   public SistemaCombustion(double precio, double peso, String tipo, double plus){
	   super(precio,peso,1);
	   this.tipo = tipo;
	   this.plusPotencia = plus;
   }
	
   public void conectarTanque( TanqueCombustible unTanque){
	   tanque= unTanque;
   }
  
   public TanqueCombustible desconectarTanque(){
	   TanqueCombustible tanqueTemp= tanque;
	   tanque = null;
	   return tanqueTemp;
   }
	
   public boolean tieneCombustible(){
	   return !tanque.estaVacio();
   }
   
   public boolean quemarCombustible(double cantidadCombustible){
	   tanque.darCombustible(cantidadCombustible);
	    if(tanque.estaVacio()){	return false;}
	    else return true;
   }
   
   public String getDetalles() {
	   return( " Sistema de Combustion: " + tipo + " Plus de Potencia: "+ plusPotencia + super.getDetalles());
	}
   public double getPlus(){
	   return plusPotencia;
   }
}
