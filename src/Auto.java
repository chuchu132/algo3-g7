
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
	
	
	public Auto(Motor motor, CajaVelocidades caja,SistemaCombustion sistemaCombustion,Carroceria carroceria,TanqueCombustible tanque,Rueda rueda){
	       
		   this.motor = motor;
		   this.caja = caja;
		   this.tanque = tanque;
		   this.motor.conectarCaja(caja);
		   this.motor.conectarTanque(tanque);
		   if( sistemaCombustion != null){this.motor.cambiarSitemaCombustion(sistemaCombustion);}
		   else { this.sistemaCombustion = new SistemaCombustion(0,0,"Comun de Fabrica",0);}
		   this.sistemaCombustion.conectarTanque(tanque);
		   this.carroceria = carroceria;
		   this.rueda = rueda;
		   	 
	}
	
    public void encender(){
	   motor.encender();
	}
	
  
  //en seg
	
	public void simular(double tiempo) { 
		
    	double fuerzaRozamiento = pista.getCoeficienteAgarre() *  rueda.getCoeficienteAgarre() * this.getPesoTotal(); 
		
		aceleracion = (this.getPesoTotal() * motor.getFuerzaInstantanea(caja, fuerzaRozamiento, velocidad))* motor.getVidaUtil();
		velocidad += (aceleracion * tiempo)* (1 + carroceria.getPlusVelocidad());
		posicion += velocidad * tiempo;
		
		motor.simular(tiempo); // quema comb en funcion del cambio y el motor
	}
    
	public void acelerar(int intervaloTiempo){
		motor.acelerar(intervaloTiempo);
	}
	
	public void desacelerar(int intervaloTiempo ){
		motor.desacelerar(intervaloTiempo);
	}
	
	public void cargarCombustible(double litros, int octanage){
		tanque.cargarCombustible(litros,octanage);
	}
	
	public double getPesoTotal() {
		return ( motor.getPeso() + caja.getPeso() + tanque.getPeso()+ carroceria.getPeso() + sistemaCombustion.getPeso() + (4*rueda.getPeso()) );
	
	}
	
	public void subirCambio (){
		motor.embragarSubir();
	}
	
	public String getDetalles(){
		return (" Aceleracion " + aceleracion + " Velocidad " + velocidad + " Posicion " + posicion);
	}
	
	public void irAPista(Pista pista){
		this.pista = pista;
	}

}
