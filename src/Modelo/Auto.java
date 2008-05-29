package Modelo;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;


public class Auto{
	
	private final double  CERO = 0.0;
	private final int PUNTO_MUERTO =0;
	
	private final int DESACELERANDO = -1;
	private final int FRENADO = 0;
	private final int ACELERANDO = 1;
	private final int MOTOR_FUNDIDO = -2;
	
	
	private Motor  motor;
	private CajaVelocidades caja;
	private SistemaCombustion sistemaCombustion;
	private Carroceria carroceria;
	private TanqueCombustible tanque;
	private TipoRueda rueda;

	private int estado;
	private double deltaAvance;
	private double velocidad;
	private double aceleracion;
	
	
	public Auto(Motor motor, CajaVelocidades caja, SistemaCombustion sistemaCombustion, Carroceria carroceria,TanqueCombustible tanque,TipoRueda rueda){
	       
		   this.motor = motor;
		   this.caja = caja;
		   this.tanque = tanque;
		   this.motor.conectarCaja(caja);
		   this.conectarTanque(tanque);
		   if( sistemaCombustion != null){
			   this.cambiarSitemaCombustion(sistemaCombustion);}
		   this.sistemaCombustion = sistemaCombustion;
		   this.carroceria = carroceria;
		   this.rueda = rueda;
		   this.estado = FRENADO;
		   	 
	}
	
    public void encender(){
	   motor.encender(sistemaCombustion, tanque);
	}
		
	public void simular(double tiempo, Pista pista)
	throws ProblemaTecnicoException{ 
		
    	double fuerzaRozamiento = pista.getCoeficienteAgarre() *  rueda.getCoeficienteAgarre() * this.getPesoTotal() * 9.8 ;
    	double fuerzaPositiva = motor.getFuerzaInstantanea(caja, fuerzaRozamiento, sistemaCombustion);
		double fuerzaNeta = (velocidad == CERO && aceleracion > CERO)? fuerzaPositiva : fuerzaPositiva - fuerzaRozamiento; 
    						
		
		aceleracion = (fuerzaNeta/getPesoTotal()) * motor.getVidaUtil();
		
		if(velocidad + aceleracion * tiempo >= CERO){
			velocidad += (aceleracion * tiempo);}
		else {
			velocidad = CERO;
			aceleracion = CERO;
			this.estado = FRENADO;
		}
		
		deltaAvance = velocidad * tiempo;
		
		motor.simular(tiempo, sistemaCombustion, tanque, estado); // quema comb en funcion del cambio y el motor
		caja.simular (tiempo);
		carroceria.simular (tiempo);
		sistemaCombustion.simular(tiempo);
		tanque.simular(tiempo);
		rueda.simular(tiempo);
				
		
	}
    
	public void acelerar(){
		this.estado = ACELERANDO;
	}
	
	public void desacelerar(){
		this.estado = DESACELERANDO;
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
	   otroMotor.conectarCaja(temp.cambiarCaja(null));
	   this.motor = otroMotor;
	   return temp;
	}
   
	public CajaVelocidades cambiarCaja(CajaVelocidades otraCaja){
	   CajaVelocidades temp = motor.cambiarCaja(otraCaja);
	   return temp;
	}
   
	public TanqueCombustible cambiarTanque(TanqueCombustible otroTanque){
		TanqueCombustible temp = this.desconectarTanque();
		this.conectarTanque(otroTanque);
		otroTanque.cargarCombustible(temp.cantidadCombustible(), temp.getOctanage());
		try {
			temp.darCombustible(temp.cantidadCombustible());
		}
		catch(TanqueVacioException e){};
	   
		this.tanque = otroTanque;
		return temp;
	}

   	public void conectarTanque(TanqueCombustible unTanque){
		tanque= unTanque;
	}

	public TanqueCombustible desconectarTanque(){
	   TanqueCombustible tanqueTemp= tanque;
	   tanque = null;
	   return tanqueTemp;
	}
	
    public SistemaCombustion cambiarSitemaCombustion( SistemaCombustion otroSistema){
    	SistemaCombustion viejoSistemaCombustion = this.sistemaCombustion;
    	sistemaCombustion=otroSistema;
    	return viejoSistemaCombustion;
    }
	
    public SistemaCombustion desconectarSistemaCombustion(){
    	SistemaCombustion temp = sistemaCombustion;
    	sistemaCombustion = null;
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
