
public class Auto{
	
	private double posicion;
	private double velocidad;
	private Motor  motor;
	private CajaVelocidades caja;
	private SistemaCombustion sistemaCombustion;
	private Carroceria carroceria;
	private TanqueCombustible tanque;
	private Rueda rueda;
	
    
	public void encender(){
	   motor.encender(tanque);
   }
	
	
	

}
