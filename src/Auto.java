
public class Auto{
	
	
	private Motor  motor;
	private CajaVelocidades caja;
	private SistemaCombustion sistemaCombustion;
	private Carroceria carroceria;
	private TanqueCombustible tanque;
	private Rueda rueda;
	private Pista pista;
	
	private double posicion;
	private double velocidad;
	private double aceleracion;
	
	
	
    
	public void encender(){
	   motor.encender();
	}
	
	public void simular(double tiempo) { //en seg
		
		double fuerzaRozamiento = pista.getCoeficienteAgarre() * rueda.getCoeficienteAgarre() * this.getPesoTotal(); 
		
		aceleracion = this.getPesoTotal() * motor.getFuerzaInstantanea(caja, fuerzaRozamiento, velocidad);
		velocidad += aceleracion * tiempo;
		posicion += velocidad * tiempo;
		
		motor.simular(tiempo); // quema comb en funcion del cambio y el motor
	}
    
	public void acelerar(){
		
	}
	private double getPesoTotal() {
		double pesoTemp= ( motor.getPeso() + caja.getPeso() + tanque.getPeso()+ carroceria.getPeso() + sistemaCombustion.getPeso() + (4*rueda.getPeso()) );
		
		return pesoTemp;
	}
	
	
	
	

}
