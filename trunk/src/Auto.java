
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
	   motor.encender(tanque);
	}
	
	public void simular(double tiempo) { //en mm seg
		
		double fuerzaRozamiento = pista.getCoeficienteAgarre() * rueda.getCoeficienteAgarre() * this.getPesoTotal(); 
		
		aceleracion = this.getPesoTotal() * motor.getFuerzaInstantanea(caja, fuerzaRozamiento, velocidad);
		velocidad += aceleracion * tiempo;
		posicion += velocidad * tiempo;
		
	}

	private double getPesoTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	

}
