package Modelo;


public class Auto{
	
	private final double  CERO = 0.0;
	private final int PUNTO_MUERTO =0;
	
	private Motor  motor;
	private CajaVelocidades caja;
	private SistemaCombustion sistemaCombustion;
	private Carroceria carroceria;
	private TanqueCombustible tanque;
	private Rueda rueda;
	private Pista pista;
	private boolean acelerando;
	private double posicion;
	private double velocidad;
	private double aceleracion;
	
	
	public Auto(Motor motor, CajaVelocidades caja, SistemaCombustion sistemaCombustion, Carroceria carroceria,TanqueCombustible tanque,Rueda rueda){
	       
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
	
  
  //en seg
	
	public void simular(double tiempo)
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
		posicion += velocidad * tiempo;
		
		try {
			motor.simular(tiempo); // quema comb en funcion del cambio y el motor
		}
		catch(ProblemaTecnicoException e){throw e; }
		
		if( rueda.getVidaUtil() < 0.2 ){
			throw new ProblemaTecnicoException("Neumatico Reventado");}
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
	
	public String getDetalles(){
		return (" Aceleracion " + aceleracion + " Velocidad " + velocidad + " Posicion " + posicion + " Peso: " + getPesoTotal());
	}
		
	
	public void irAPista(Pista pista){
		this.pista = pista;
	}
   public double getPosicion(){
	   return this.posicion;
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
	   temp.darCombustible(temp.cantidadCombustible());
	   this.tanque = otroTanque;
	   return temp;
   }
   
   public Carroceria cambiarCarroceria(Carroceria otraCarroceria){
	   Carroceria temp = this.carroceria;
	   this.carroceria = otraCarroceria;
	   return temp;
   }
   
   public Rueda cambiarRueda(Rueda otraRueda){
	   Rueda temp = this.rueda;
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
   
   
}
