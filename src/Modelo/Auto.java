package Modelo;


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
	
	
	public Auto(Motor motor, CajaVelocidades caja, SistemaCombustion sistemaCombustion, Carroceria carroceria,TanqueCombustible tanque,Rueda rueda){
	       
		   this.motor = motor;
		   this.caja = caja;
		   this.tanque = tanque;
		   this.motor.conectarCaja(caja);
		   this.motor.conectarTanque(tanque);
		   if( sistemaCombustion != null){this.motor.cambiarSitemaCombustion(sistemaCombustion);}
		   this.sistemaCombustion = motor.getSistemaCombustion();
		   this.carroceria = carroceria;
		   this.rueda = rueda;
		   
		   	 
	}
	
    public void encender(){
	   motor.encender();
	}
	
  
  //en seg
	
	public void simular(double tiempo)
	throws ProblemaTecnicoException{ 
		
    	double fuerzaRozamiento = pista.getCoeficienteAgarre() *  rueda.getCoeficienteAgarre() * this.getPesoTotal() * 9.8 ;
    	double fuerzaPositiva = motor.getFuerzaInstantanea(caja, fuerzaRozamiento);
		double fuerzaNeta = 0.0; 
    	
		
		if(velocidad == 0 && aceleracion >= 0)
			fuerzaNeta = fuerzaPositiva;
		else
			fuerzaNeta = fuerzaPositiva - fuerzaRozamiento;
					
		
		aceleracion = (fuerzaNeta/getPesoTotal()) * motor.getVidaUtil();
		velocidad += (aceleracion * tiempo);
		posicion += velocidad * tiempo;
		
		try {
			motor.simular(tiempo); // quema comb en funcion del cambio y el motor
		}
		catch(ProblemaTecnicoException e){throw e; }
		
		if( rueda.getVidaUtil() < 0.2 ){ throw new ProblemaTecnicoException("Neumatico Reventado");}	
	}
    
	public void acelerar(double tiempo){
		motor.acelerar(tiempo);
	}
	
	public void desacelerar(double tiempo ){
		motor.desacelerar(tiempo);
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
	
	public void bajarCambio(){
		motor.embragarBajar();
	}
	
	public String getDetalles(){
		return (" Aceleracion " + aceleracion + " Velocidad " + velocidad + " Posicion " + posicion + " Peso: " + getPesoTotal());
	}
		
	
	public void irAPista(Pista pista){
		this.pista = pista;
	}
   public double getPosicion(){
	   return this.posicion;
   }
   
}
