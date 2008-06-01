package Modelo;

import java.util.Observable;
import java.util.Observer;

import Excepciones.MotorFundidoException;
import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class Motor extends Autoparte implements Observer{
	
	private final int REVOLUCIONES_MINIMAS = 800;
	private final double factorDeDesgaste = 0.01;
	
	private final int DESACELERANDO = -1;
	private final int FRENADO = 0;
	private final int ACELERANDO = 1;
	
	
	private double fuerzaMaxima;
	private int HP;
	private int cilindros;
	private double cubicaje; //en litros cm^3/1000
	private boolean encendido;
	private int revolucionesMax;
	private int revolucionesActuales;
	
	private double fuerzaInstantanea;
	private int estado;
	
	
	/** cambioActual y relacionCaja guardan el estado de la caja en todo momento 
	 * y la caja se encarga de actualizarlos cuando cambia de estado. 
	 */
	private int cambioActual = 0;
	private double relacionCaja = 0.0;
		
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
		
	public void quemarCombustible (double cantidadCombustible, SistemaCombustion sistemaCombustion, TanqueCombustible tanque) throws TanqueVacioException {
		sistemaCombustion.quemarCombustible(cantidadCombustible, tanque);
	}
	
	public void apagar() {
		encendido = false;
	}

	public double obtenerDeltaRevoluciones(double tiempo){
	
		if(relacionCaja == 0.0){
			return 0;}
		else{		 
			return tiempo * ((0.75 * revolucionesMax) / (4/relacionCaja) ) ;
		}
	}
	
	public void acelerar (double tiempo){

		     
		   double deltaRevoluciones = obtenerDeltaRevoluciones(tiempo);
		   if (revolucionesActuales + deltaRevoluciones < revolucionesMax){
			   revolucionesActuales += (int)deltaRevoluciones ;
		   } else {
			   revolucionesActuales = (int) (revolucionesMax); 
		   }

	}
	
	public void desacelerar(double tiempo){
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
		
    public void simular(double deltaTiempo, SistemaCombustion sistemaCombustion, TanqueCombustible tanqueCombustible) throws ProblemaTecnicoException {
    	
    	double consumoInstantaneo = 0;
    	double desgaste = deltaTiempo*(revolucionesActuales/revolucionesMax)*factorDeDesgaste;
        gastar(desgaste);
        
    	if(this.getVidaUtil() < getVidaUtilMinima()) {
     	   throw new MotorFundidoException();
     	}
    	
       	
		if(this.estado == ACELERANDO){
			acelerar(deltaTiempo);
		}
		else if (this.estado == DESACELERANDO){
			desacelerar(deltaTiempo);
		}
    	
    	if (cambioActual> 0) { 
    		consumoInstantaneo = ((cilindros * cubicaje / 180)* deltaTiempo ) * (1 / relacionCaja); 
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
		
		revolucionesActuales = (2 * revolucionesActuales / 5);
		if(revolucionesActuales < REVOLUCIONES_MINIMAS){ 
			revolucionesActuales = REVOLUCIONES_MINIMAS;}
	}
    
	public void embragarBajar(){
		
		if(cambioActual != 0){
		revolucionesActuales = (5 * revolucionesActuales / 2);
		if(revolucionesActuales > revolucionesMax){ 
			revolucionesActuales = revolucionesMax;
			this.gastar(0.05);}
        	}
		else{revolucionesActuales = REVOLUCIONES_MINIMAS;}
     	}


	/** este metodo es llamado por notifyObservers() de los obejetos obsevados **/
	
	public void update(Observable o, Object arg) {
		
		if( o instanceof  CajaVelocidades){
			cambioActual = ((CajaVelocidades)o).getCambioActual();
			relacionCaja = ((CajaVelocidades)o).obtenerRelacion();
		}
		
		if( o instanceof Auto){
			this.estado = ((Auto) o).getEstado(); 
		}
	}
	
}

    