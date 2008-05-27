package Modelo;

import Excepciones.MotorFundidoException;
import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class Motor extends Autoparte{
	
	private final int REVOLUCIONES_MINIMAS = 800;
	private final double VIDA_UTIL_MINIMA = 0.2;
	
	private double fuerzaMaxima;
	private int HP;
	private int cilindros;
	private double cubicaje; //en litros cm^3/1000
	private boolean encendido;
	private int revolucionesMax;
	private int revolucionesActuales;
	
	private double fuerzaInstantanea;
	private boolean acelerando;
	private SistemaCombustion sistemaCombustion;
	private CajaVelocidades caja;
	
	public Motor(int HP, int cilindros, double cubicaje, double precio, double peso){
		super(peso,precio);
		this.HP=HP;
		this.cilindros=cilindros;
		this.cubicaje=cubicaje; 
		this.fuerzaMaxima = HP * 25 + cilindros*cubicaje*1000;
		this.encendido=false;
		this.revolucionesMax = (int)(HP * 17 + 2800); 
		this.revolucionesActuales = 0;
		this.fuerzaInstantanea = 0;
		this.acelerando = false;
		this.sistemaCombustion = new SistemaCombustion(0,0,"Comun de Fabrica",0);;
	}

		
	public int getRevolucionesActuales() {
		return revolucionesActuales;
	}

	public double getFuerzaInstantanea() {
		return fuerzaInstantanea;
	}

	public void encender (){
    
		try{
			sistemaCombustion.quemarCombustible(0.1);
			revolucionesActuales = REVOLUCIONES_MINIMAS;
		    encendido= true;
		}
		catch( TanqueVacioException e ){}
		
	    	
	}
	
	public void conectarTanque(TanqueCombustible unTanque){
		sistemaCombustion.conectarTanque(unTanque);
	}
	
	public void conectarCaja(CajaVelocidades unaCaja){
		caja = unaCaja;
	}
	
    public SistemaCombustion getSistemaCombustion(){
		return sistemaCombustion;
	}
	
	public CajaVelocidades cambiarCaja(CajaVelocidades otraCaja){
		CajaVelocidades cajaVieja = caja;
		caja = otraCaja;
		return cajaVieja;
	}
	
    public SistemaCombustion cambiarSitemaCombustion( SistemaCombustion otroSistema){
    	SistemaCombustion sistemaViejo = sistemaCombustion;
    	otroSistema.conectarTanque(sistemaCombustion.desconectarTanque());
    	sistemaCombustion=otroSistema;
    	return sistemaViejo;
    }
	
    public SistemaCombustion desconectarSistemaCombustion(){
    	SistemaCombustion temp = sistemaCombustion;
    	sistemaCombustion = null;
    	return temp;
    }
    
	public void quemarCombustible (double cantidadCombustible) throws TanqueVacioException {
		sistemaCombustion.quemarCombustible(cantidadCombustible);
	}
	
	public void apagar() {
		encendido = false;
	}

	public double obtenerDeltaRevoluciones(double tiempo){
		double relacion = caja.obtenerRelacion();
		if(relacion == 0.0){
			return 0;}
		else{		 
			return tiempo * ((0.75 * revolucionesMax) / (4/relacion) ) ;
		}
	}
	
	public void acelerar (double tiempo){

		   acelerando = true;
		   
		   double deltaRevoluciones = obtenerDeltaRevoluciones(tiempo);
		   if (revolucionesActuales + deltaRevoluciones < revolucionesMax){
			   revolucionesActuales += (int)deltaRevoluciones ;
		   } else {
			   revolucionesActuales = (int) (revolucionesMax); 
		   }

	}
	

	public void desacelerar(double tiempo){
		acelerando = false;
		revolucionesActuales -= obtenerDeltaRevoluciones(tiempo) ;
		if (revolucionesActuales < REVOLUCIONES_MINIMAS) {
			revolucionesActuales = REVOLUCIONES_MINIMAS; }
	}
	
	public double getFuerzaInstantanea (CajaVelocidades caja, double fuerzaRozamiento) {
		if (acelerando == true){
			if ((int)revolucionesActuales >= (int)(0.75 * revolucionesMax)) {
				fuerzaInstantanea = fuerzaRozamiento;
				
			} else {
				fuerzaInstantanea = caja.obtenerRelacion()*fuerzaMaxima* (1 + sistemaCombustion.getPlus());
				
			}
		} else {
			fuerzaInstantanea = 0;
			 
		}

		return fuerzaInstantanea;
	   }
	
	public boolean estaEncendido(){
		return encendido;
	}
	
	// tiempo en segundo
	
    public void simular(double deltaTiempo) throws ProblemaTecnicoException {
    	
    	double consumoInstantaneo = 0;
    	int cambio = caja.getCambioActual();
      
    	if(this.getVidaUtil() < VIDA_UTIL_MINIMA) {
     	   throw new MotorFundidoException();
     	}
    	
    	if (cambio > 0) { 
    		consumoInstantaneo = ((cilindros * cubicaje / 180)* deltaTiempo ) * (1 / caja.obtenerRelacion()); 
    	}
    	else {
    	   consumoInstantaneo = ((cilindros * cubicaje / 180)* deltaTiempo);
    	}
    	// puede lanzar TanqueVacioException
    	this.quemarCombustible(consumoInstantaneo);
       
    	  
     
    	
    }
	
	public String toString(){
		return (" Potencia: " + HP + " Cilindrada: " + (cilindros * cubicaje) + sistemaCombustion.toString() + "RMax: " + revolucionesMax );
	}

	public void embragarSubir(){
		caja.subirCambio();
		revolucionesActuales = (2 * revolucionesActuales / 5);
		if(revolucionesActuales < REVOLUCIONES_MINIMAS){ 
			revolucionesActuales = REVOLUCIONES_MINIMAS;}
	}
    
	public void embragarBajar(){
		caja.bajarCambio();
		if(caja.getCambioActual() != 0){
		revolucionesActuales = (5 * revolucionesActuales / 2);
		if(revolucionesActuales > revolucionesMax){ 
			revolucionesActuales = revolucionesMax;
			this.gastar(0.05);}
        	}
		else{revolucionesActuales = REVOLUCIONES_MINIMAS;}
     	}
	}

    