package Modelo;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;


public class Auto{
	
	private final double  CERO = 0.0;
	private final int PUNTO_MUERTO =0;
	
	private Motor  motor;
	private CajaVelocidades caja;
	private SistemaCombustion sistemaCombustion;
	private Carroceria carroceria;
	private TanqueCombustible tanque;
	private TipoRueda rueda;

	private boolean acelerando;
	private double deltaAvance;
	private double velocidad;
	private double aceleracion;
	
	
	public Auto(Motor motor, CajaVelocidades caja, SistemaCombustion sistemaCombustion, Carroceria carroceria,TanqueCombustible tanque,TipoRueda rueda){
	       
		   this.motor = motor;
		   this.caja = caja;
		   this.tanque = tanque;
		   this.motor.conectarCaja(caja);
		   this.motor.conectarTanque(tanque);
		   if( sistemaCombustion != null){
			   this.motor.cambiarSitemaCombustion(sistemaCombustion);}
		   this.sistemaCombustion = motor.getSistemaCombustion();
		   this.carroceria = carroceria;
		   this.rueda = rueda;
		   this.acelerando = false;
		   	 
	}
	
    public void encender(){
	   motor.encender();
	}
		
	public void simular(double tiempo, Pista pista)
	throws ProblemaTecnicoException{ 
		
		
		if(this.acelerando){
			motor.acelerar(tiempo);}
		else{motor.desacelerar(tiempo);}
		
    	double fuerzaRozamiento = pista.getCoeficienteAgarre() *  rueda.getCoeficienteAgarre() * this.getPesoTotal() * 9.8 ;
    	double fuerzaPositiva = motor.getFuerzaInstantanea(caja, fuerzaRozamiento);
		double fuerzaNeta = (velocidad == CERO && aceleracion > CERO)? fuerzaPositiva : fuerzaPositiva - fuerzaRozamiento; 
    						
		
		aceleracion = (fuerzaNeta/getPesoTotal()) * motor.getVidaUtil();
		
		if(velocidad + aceleracion * tiempo >= CERO){
			velocidad += (aceleracion * tiempo);}
		else {
			velocidad = CERO;
			aceleracion = CERO;
		}
		deltaAvance = velocidad * tiempo;
		
		try {
			motor.simular(tiempo); // quema comb en funcion del cambio y el motor
		}
		catch(ProblemaTecnicoException e){throw e; }
		
		// rueda.simular() 
		if( rueda.getVidaUtil() < 0.2 ){
			throw new ProblemaTecnicoException();}
	}
    
	public void acelerar(){
		this.acelerando= true;
	}
	
	public void desacelerar(){
		acelerando = false;
		if(velocidad == CERO)
			caja.setCambio( PUNTO_MUERTO );
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
	
	public String toString(){
		return (" Aceleracion " + aceleracion + " Velocidad " + velocidad + " Avance " + deltaAvance + " Peso: " + getPesoTotal());
	}
	   
	public Motor cambiarMotor(Motor otroMotor){
	   Motor temp = this.motor;
	   otroMotor.cambiarSitemaCombustion(temp.desconectarSistemaCombustion());
	   otroMotor.conectarCaja(temp.cambiarCaja(null));
	   this.motor = otroMotor;
	   return temp;
	}
   
	public CajaVelocidades cambiarCaja(CajaVelocidades otraCaja){
	   CajaVelocidades temp = motor.cambiarCaja(otraCaja);
	   return temp;
	}
   
	public TanqueCombustible cambiarTanque(TanqueCombustible otroTanque){
	   TanqueCombustible temp = sistemaCombustion.desconectarTanque();
	   motor.conectarTanque(otroTanque);
	   otroTanque.cargarCombustible(temp.cantidadCombustible(), temp.getOctanage());
	  try{
	   temp.darCombustible(temp.cantidadCombustible());
	 }
	  catch(TanqueVacioException e){};
	   
	  this.tanque = otroTanque;
	   return temp;
   }
   
	public Carroceria cambiarCarroceria(Carroceria otraCarroceria){
	   Carroceria temp = this.carroceria;
	   this.carroceria = otraCarroceria;
	   return temp;
	}
   
	public TipoRueda cambiarRueda(TipoRueda otraRueda){
	   TipoRueda temp = this.rueda;
	   this.rueda = otraRueda;
	   return temp;
	}
	
	public boolean estaCompleto(){
	   if( motor != null &&  caja != null && tanque != null && sistemaCombustion != null && carroceria != null &&  rueda != null){
		   return true;}
	   else {return false;}
	   
	}
   
	public boolean estaEncendido(){
	   return motor.estaEncendido();
	}
   
	public double cantidadCombustible(){
	   return tanque.cantidadCombustible();	   
	}
	   
	public int getRevoluciones(){
		return motor.getRevolucionesActuales();
	}
   
   public double getPrecio(){
	   return (motor.getPrecio() + caja.getPrecio() + tanque.getPrecio() + sistemaCombustion.getPrecio() + carroceria.getPrecio() + (4* rueda.getPrecio()));
   }

   public double getVelocidad() {
		return velocidad;
	}   
   
   public double getAceleracion() {
	   return aceleracion;
   }

   public double getDeltaAvance() {
	   return deltaAvance;
   }
   
   
}
