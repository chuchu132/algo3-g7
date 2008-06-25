package Modelo;

import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Excepciones.MotorFundidoException;
import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

public class Motor extends Autoparte implements Observer{
	public final static double COEF_BAJAR_CAMBIO = 2.5;
	public final static double COEF_SUBIR_CAMBIO = 0.4;
	public final static int REVOLUCIONES_MINIMAS = 800; //es static porque es comun para todos los motores
	private final double factorDeDesgaste = 0.001;
	
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
	/** Estado va public para poder cambiarlo en los test sin tener que crear un auto.
	 *  Pero en realidad, cuando el auto cambia su comportamiento le avisa al motor que lo esta  "mirando"
	 *  y este cambia de estado. */
	public int estado; 
	
	
	/** cambioActual y relacionCaja guardan el estado de la caja en todo momento 
	 * y la caja se encarga de avisarle al motor cuando cambia de estado. 
	 */
	private int cambioActual = 0;
	private double relacionCaja = 0.0;
		
	public Motor(int HP, int cilindros, double cubicaje, double precio, double peso, float vidaUtilInicial){
		super(peso,precio, vidaUtilInicial);
		this.HP=HP;
		this.cilindros=cilindros;
		this.cubicaje=cubicaje; 
		this.fuerzaMaxima = HP * 50 + cilindros*cubicaje*1000;
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
	
	public void setRevolucionesActuales(int revoluciones){
		this.revolucionesActuales = revoluciones;
	}

	public int getRevolucionesMaximas(){
		return revolucionesMax;
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
			return tiempo * (0.75 * revolucionesMax);}
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
		setChanged();
		this.notifyObservers();

	}
	
	public void desacelerar(double tiempo){
		revolucionesActuales -= obtenerDeltaRevoluciones(tiempo) ;
		if (revolucionesActuales < REVOLUCIONES_MINIMAS) {
			revolucionesActuales = REVOLUCIONES_MINIMAS; 
			}
		setChanged();
		this.notifyObservers();
	}
	
	public double getFuerzaInstantanea (CajaVelocidades caja, double fuerzaRozamiento, SistemaCombustion sistemaCombustion) {
		if (estado == ACELERANDO){
			if ((int)revolucionesActuales >= (int)(0.90 * revolucionesMax)) {
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
    	// si no hay combustible apaga el motor y lanza la excepcion para avisar que hubo un problema
    	try{
    	sistemaCombustion.quemarCombustible(consumoInstantaneo, tanqueCombustible);
    	}
    	catch(TanqueVacioException e){
    		this.apagar();
    		throw e;
    	}
     	
    }
	
	public String toString(){
		DecimalFormat porcentage = new DecimalFormat("0.00");
		return ("Motor:  Potencia: " + HP + " Cilindrada: " + (cilindros * cubicaje) + "Vida Util: " + porcentage.format(getPorcentajeVidaUtil())+ " %" );
	}

	public void embragarSubir(){
		
		revolucionesActuales = (int)( revolucionesActuales * COEF_SUBIR_CAMBIO);
		if(revolucionesActuales < REVOLUCIONES_MINIMAS){ 
			revolucionesActuales = REVOLUCIONES_MINIMAS;}
	}
    
	public void embragarBajar(){
		
		if(cambioActual != 0){
		revolucionesActuales = (int)(revolucionesActuales * COEF_BAJAR_CAMBIO);
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
	
	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element motor = document.addElement("motor");
		this.grabar(motor);
		motor.addAttribute("fuerzaMaxima",Double.toString(fuerzaMaxima));
		motor.addAttribute("HP",Integer.toString(HP));
		motor.addAttribute("cilindros",Integer.toString(cilindros));
		motor.addAttribute("cubicaje",Double.toString(cubicaje));
		motor.addAttribute("encendido",Boolean.toString(this.estaEncendido()));
		motor.addAttribute("revolucionesMax",Integer.toString(this.getRevolucionesMaximas()));
		motor.addAttribute("revolucionesActuales",Integer.toString(this.getRevolucionesActuales()));
		motor.addAttribute("fuerzaInstantanea",Double.toString(this.getFuerzaInstantanea()));
		motor.addAttribute("estado",Integer.toString(estado));
		return motor;
	}
}

    