package Modelo;

import java.util.Observable;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;


public class Auto extends Observable implements Vendible{

	private final double  CERO = 0.0;
	private final int PUNTO_MUERTO =0;

	private final int DESACELERANDO = -1;
	private final int FRENADO = 0;
	private final int ACELERANDO = 1;



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
		this.addObserver(motor);
		this.caja = caja;
		if(caja instanceof CajaAutomatica){
			motor.addObserver((CajaAutomatica)this.caja);
		}
		this.tanque = tanque;
		caja.addObserver(this.motor);
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

	private double getFuerzaNeta(Pista pista){
		double fuerzaRozamiento = pista.getCoeficienteAgarre() *  rueda.getCoeficienteAgarre() * this.getPesoTotal() * 9.8 ;
		double fuerzaPositiva = motor.getFuerzaInstantanea(caja, fuerzaRozamiento, sistemaCombustion);
		double fuerzaNeta = (velocidad == CERO && aceleracion > CERO)? fuerzaPositiva : fuerzaPositiva - fuerzaRozamiento; 
		return fuerzaNeta;
	}

	public void simular(double tiempo, Pista pista)
	throws ProblemaTecnicoException{ 


		aceleracion = (getFuerzaNeta(pista)/getPesoTotal()) * motor.getVidaUtil();

		if(velocidad + aceleracion * tiempo >= CERO){
			velocidad += (aceleracion * tiempo);}
		else {
			velocidad = CERO;
			aceleracion = CERO;
			this.estado = FRENADO;
		}

		deltaAvance = velocidad * tiempo;

		motor.simular(tiempo, sistemaCombustion, tanque); // quema comb en funcion del cambio y el motor
		caja.simular (tiempo);
		carroceria.simular (tiempo);
		sistemaCombustion.simular(tiempo);
		tanque.simular(tiempo);
		rueda.simular(tiempo);


	}

	public void acelerar(){
		this.estado = ACELERANDO;
		setChanged();
		notifyObservers();

	}

	public void desacelerar(){
		this.estado = DESACELERANDO;
		if(velocidad == CERO)
			caja.setCambio( PUNTO_MUERTO );
		setChanged();
		notifyObservers();
	}

	public void cargarCombustible(double litros, Nafta nafta){
		tanque.cargarCombustible(litros,nafta);
	}
	
	public void vaciarTanque(){
		tanque.vaciarTanque();
	}
	
	public double getPesoTotal() {
		return ( motor.getPeso() + caja.getPeso() + tanque.getPeso()+ carroceria.getPeso() + sistemaCombustion.getPeso() + (4*rueda.getPeso()) );

	}
	
	public double getPorcentageVidaUtilGeneral(){
		return ( ( motor.getPorcentajeVidaUtil() + caja.getPorcentajeVidaUtil() + tanque.getPorcentajeVidaUtil()+ carroceria.getPorcentajeVidaUtil() + sistemaCombustion.getPorcentajeVidaUtil() + (4*rueda.getPorcentajeVidaUtil() ) ) / 9 );
		
	}

	public void subirCambio (){
		if (!(caja instanceof  CajaAutomatica)) {
			caja.subirCambio();
			motor.embragarSubir();
		}
	}

	public void bajarCambio(){
		if (!(caja instanceof  CajaAutomatica)) {
			caja.bajarCambio();
			motor.embragarBajar();
		}
	}

	public String toString(){
		return (" Aceleracion " + aceleracion + " Velocidad " + velocidad + " Avance " + deltaAvance + " Peso: " + getPesoTotal());
	}

	public Motor cambiarMotor(Motor otroMotor){
		Motor temp = this.motor;
		caja.deleteObserver(motor);
		caja.addObserver(otroMotor);
		if(caja instanceof CajaAutomatica){
			otroMotor.addObserver((CajaAutomatica)this.caja);
		}
		this.motor = otroMotor;
		return temp;
	}

	public CajaVelocidades cambiarCaja(CajaVelocidades otraCaja){
		CajaVelocidades temp = this.caja;
		caja.deleteObservers();
		otraCaja.addObserver(motor);
		if(otraCaja instanceof CajaAutomatica){
			this.motor.addObserver((CajaAutomatica)otraCaja);
		}
		this.caja = otraCaja;

		return temp;
	}

	public TanqueCombustible cambiarTanque(TanqueCombustible otroTanque){
		TanqueCombustible temp = this.desconectarTanque();
		this.conectarTanque(otroTanque);
		otroTanque.cargarCombustible(temp.cantidadCombustible(), temp.getTipoNafta());
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

	public int getEstado() {
		return estado;
	}
	public String getModelo(){
		return carroceria.getModelo();
	}
	
	public int getCambioActual(){
		return caja.getCambioActual();
	}
}
