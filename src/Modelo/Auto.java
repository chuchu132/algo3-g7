package Modelo;

import java.util.Iterator;
import java.util.Observable;

import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

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
		this.sistemaCombustion = sistemaCombustion;
		this.carroceria = carroceria;
		this.rueda = rueda;
		this.estado = FRENADO;

	}

	public Auto(Element elemAuto) {
		Iterator it = elemAuto.elementIterator();

		Element elemMotor = (Element)it.next();
		Element elemCaja = (Element) it.next();
		Element elemSistemaCombustion = (Element) it.next();
		Element elemCarroceria = (Element) it.next();
		Element elemTanque = (Element) it.next();
		Element elemRueda = (Element) it.next();

		this.motor = new Motor(elemMotor);
		this.addObserver(motor);
		if (elemCaja.getName().equals("cajaVelocidades"))
		{
			this.caja = new CajaVelocidades(elemCaja);
		}
		else{
			this.caja = new CajaAutomatica(elemCaja);
			motor.addObserver((CajaAutomatica)this.caja);
		}
		this.sistemaCombustion = new SistemaCombustion(elemSistemaCombustion);
		this.carroceria = new Carroceria(elemCarroceria);
		this.tanque = new TanqueCombustible (elemTanque);
		this.rueda = new TipoRueda(elemRueda);

		this.estado = FRENADO;
	}

	public void encender(){
		motor.encender(sistemaCombustion, tanque);
		caja.setCambio(0);
		velocidad = 0;
		deltaAvance = 0;
		aceleracion = 0;
	}

	private double getFuerzaNeta(Pista pista){
		double fuerzaRozamiento = pista.getCoeficienteAgarre() *  rueda.getCoeficienteAgarre() * this.getPesoTotal() * 9.8 ;
		double fuerzaPositiva = motor.getFuerzaInstantanea(caja, fuerzaRozamiento, sistemaCombustion);
		double fuerzaNeta = (velocidad == CERO && aceleracion > CERO)? fuerzaPositiva : fuerzaPositiva - fuerzaRozamiento; 
		return fuerzaNeta;
	}

	public void simular(double tiempo, Pista pista)
	throws ProblemaTecnicoException{ 


		aceleracion = (getFuerzaNeta(pista)/getPesoTotal()) * (motor.getVidaUtil()/motor.getVidaUtilInicial());

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

	public double getPorcentageVidaUtilMotor(){
		return motor.getPorcentajeVidaUtil();
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
		return "Auto: " + carroceria.getModelo() + ". Vida Útil Motor: " + getPorcentageVidaUtilMotor()*100 + "%"; 
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

	public Element serialize(){
		Document document = DocumentHelper.createDocument();
		Element auto = document.addElement("auto");
		auto.add(this.motor.serialize());
		if (caja instanceof CajaAutomatica)
			auto.add(((CajaAutomatica) this.caja).serialize());
		else auto.add(this.caja.serialize());
		auto.add(this.sistemaCombustion.serialize());
		auto.add(this.carroceria.serialize());
		auto.add(this.tanque.serialize());
		auto.add(this.rueda.serialize());
		return auto;

	}

	
	/* los siguientes metodos seran usados para probar grabar y cargar, no seran
	 * utilizados durante el juego
	 */
	
	public Motor getMotor(){
		return this.motor;
	}
	
	public CajaVelocidades getCaja(){
		return this.caja;
	}
	
	public SistemaCombustion getSistemaCombustion(){
		return this.sistemaCombustion;
	}
	
	public Carroceria getCarroceria(){
		return this.carroceria;
	}
	
	public TanqueCombustible getTanque(){
		return this.tanque;
	}
	
	public TipoRueda getRueda(){
		return this.rueda;
	}
	
	
	


}
