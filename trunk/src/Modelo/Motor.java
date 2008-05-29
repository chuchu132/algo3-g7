package Modelo;

import Excepciones.MotorFundidoException;
import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class Motor extends Autoparte{
	
	private final int REVOLUCIONES_MINIMAS = 800;
	private final double factorDeDesgaste = 0.01;
	
	private final int DESACELERANDO = -1;
	private final int FRENADO = 0;
	private final int ACELERANDO = 1;
	private final int MOTOR_FUNDIDO = -2;
	
	private double fuerzaMaxima;
	private int HP;
	private int cilindros;
	private double cubicaje; //en litros cm^3/1000
	private boolean encendido;
	private int revolucionesMax;
	private int revolucionesActuales;
	
	private double fuerzaInstantanea;
	private int estado;
	//private SistemaCombustion sistemaCombustion;
	private CajaVelocidades caja;
	
	public Motor(int HP, int cilindros, double cubicaje, double precio, double peso, long vidaUtilInicial){
		super(peso,precio, vidaUtilInicial);
		this.HP=HP;
		this.cilindros=cilindros;
		this.cubicaje=cubicaje; 
		this.fuerzaMaxima = HP * 25 + cilindros*cubicaje*1000;
		this.encendido=false;
		this.revolucionesMax = (int)(HP * 17 + 2800); 
		this.revolucionesActuales = 0;
		this.fuerzaInstantanea = 0;
		this.estado = FRENADO;
		//this.sistemaCombustion = new SistemaCombustion(0,0,"Comun de Fabrica",0,5);;
	}
		
	public int getRevolucionesActuales() {
		return revolucionesActuales;
	}

	public double getFuerzaInstantanea() {
		return fuerzaInstantanea;
	}

	public void encender (SistemaCombustion sistemaCombustion, TanqueCombustible tanque){
    
		try{
			sistemaCombustion.quemarCombustible(0.1, tanque);
			revolucionesActuales = REVOLUCIONES_MINIMAS;
		    encendido= true;
		}
		catch( TanqueVacioException e ){}
		
	    	
	}
		
	public void conectarCaja(CajaVelocidades unaCaja){
		caja = unaCaja;
	}
	
	public CajaVelocidades cambiarCaja(CajaVelocidades otraCaja){
		CajaVelocidades cajaVieja = caja;
		caja = otraCaja;
		return cajaVieja;
	}
	    
	public void quemarCombustible (double cantidadCombustible, SistemaCombustion sistemaCombustion, TanqueCombustible tanque) throws TanqueVacioException {
		sistemaCombustion.quemarCombustible(cantidadCombustible, tanque);
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

		   estado = ACELERANDO;
		   
		   double deltaRevoluciones = obtenerDeltaRevoluciones(tiempo);
		   if (revolucionesActuales + deltaRevoluciones < revolucionesMax){
			   revolucionesActuales += (int)deltaRevoluciones ;
		   } else {
			   revolucionesActuales = (int) (revolucionesMax); 
		   }

	}
	
	public void desacelerar(double tiempo){
		estado = DESACELERANDO;
		revolucionesActuales -= obtenerDeltaRevoluciones(tiempo) ;
		if (revolucionesActuales < REVOLUCIONES_MINIMAS) {
			revolucionesActuales = REVOLUCIONES_MINIMAS; }
	}
	
	public double getFuerzaInstantanea (CajaVelocidades caja, double fuerzaRozamiento, SistemaCombustion sistemaCombustion) {
		if (estado == ACELERANDO){
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
		
    public void simular(double deltaTiempo, SistemaCombustion sistemaCombustion, TanqueCombustible tanqueCombustible, int estado) throws ProblemaTecnicoException {
    	
    	double consumoInstantaneo = 0;
    	int cambio = caja.getCambioActual();
        double desgaste = deltaTiempo*(revolucionesActuales/revolucionesMax)*factorDeDesgaste;
        gastar(desgaste);
        
    	if(this.getVidaUtil() < getVidaUtilMinima()) {
     	   throw new MotorFundidoException();
     	}
    	
    	this.estado = estado;
    	
		if(this.estado == ACELERANDO){
			acelerar(deltaTiempo);
		}
		else if (this.estado == DESACELERANDO){
			desacelerar(deltaTiempo);
		}
    	
    	if (cambio > 0) { 
    		consumoInstantaneo = ((cilindros * cubicaje / 180)* deltaTiempo ) * (1 / caja.obtenerRelacion()); 
    	}
    	else {
    	   consumoInstantaneo = ((cilindros * cubicaje / 180)* deltaTiempo);
    	}
    	// puede lanzar TanqueVacioException
    	sistemaCombustion.quemarCombustible(consumoInstantaneo, tanqueCombustible);
     	
    }
	
	public String toString(){
		return (" Potencia: " + HP + " Cilindrada: " + (cilindros * cubicaje) + "RMax: " + revolucionesMax );
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

    