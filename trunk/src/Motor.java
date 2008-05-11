public class Motor extends Autoparte{
	
	private double fuerzaMaxima;
	private int HP;
	private int cilindros;
	private double cubicaje; //en litros cm^3/1000
	private boolean encendido;
	private int revolucionesMax;
	private int revolucionesActuales;
	
	private double fuerzaInstantanea;
	private boolean acelerando;
	private SistemaCombustion sistemaC;
	private CajaVelocidades caja;
	
	public Motor(int HP, int cilindros, double cubicaje, double precio, double peso){
		super(peso,precio,1);
		this.HP=HP;
		this.cilindros=cilindros;
		this.cubicaje=cubicaje; 
		this.fuerzaMaxima = HP * 250 + cilindros*cubicaje*1000;
		this.encendido=false;
		this.revolucionesMax = (int)(HP * 17 + 2800); // son los numeros para q a 800 hp = alrededor de 16000 y a 200hp alrededor d 6000
		this.revolucionesActuales = 0;
		this.fuerzaInstantanea = 0;
		this.acelerando = false;
		this.sistemaC = new SistemaCombustion(0,0,"Comun de Fabrica",0);;
	}

	
	
	public void encender (){
		encendido = sistemaC.quemarCombustible(0.1);
	    if(encendido){revolucionesActuales = 800; }	
	}
	
	public void conectarTanque(TanqueCombustible unTanque){
		sistemaC.conectarTanque(unTanque);
	}
	
	public void conectarCaja(CajaVelocidades unaCaja){
		caja = unaCaja;
	}
	
    public SistemaCombustion getSistemaCombustion(){
		return sistemaC;
	}
	
	public CajaVelocidades cambiarCaja(CajaVelocidades otraCaja){
		CajaVelocidades cajaVieja = caja;
		caja = otraCaja;
		return cajaVieja;
	}
	
    public SistemaCombustion cambiarSitemaCombustion( SistemaCombustion otroSistema){
    	SistemaCombustion sistemaViejo = sistemaC;
    	otroSistema.conectarTanque(sistemaC.desconectarTanque());
    	sistemaC=otroSistema;
    	return sistemaViejo;
    }
	
	public void quemarCombustible (double cantidadCombustible) {
		if(!sistemaC.quemarCombustible(cantidadCombustible)) apagar();
	}

	public void apagar() {
		encendido = false;
	}

	public double obtenerDeltaRevoluciones(double tiempo){
		
		 return ( 4*tiempo * caja.obtenerRelacion()*HP*cubicaje); 
	
	}
	
	public void acelerar (double tiempo){

		   acelerando = true;
		   double deltaRevoluciones = obtenerDeltaRevoluciones(tiempo);
		   if (revolucionesActuales + deltaRevoluciones < revolucionesMax){
		   revolucionesActuales += deltaRevoluciones ;}
		   else{ revolucionesActuales = revolucionesMax; }

	}
	

	public void desacelerar(double tiempo){
		acelerando = false;
		revolucionesActuales -= obtenerDeltaRevoluciones(tiempo) ;
		if (revolucionesActuales < 800) {revolucionesActuales = 800; }

	}
	
	public double getFuerzaInstantanea (CajaVelocidades caja, double fuerzaRozamiento, double velocidadInstantanea) {
		if (acelerando == true){
			if (revolucionesActuales >= 3/4*revolucionesMax)
			{fuerzaInstantanea = fuerzaRozamiento;
			return fuerzaRozamiento;}
		}
		else if (revolucionesActuales < 3/4*revolucionesMax)
			fuerzaInstantanea = caja.obtenerRelacion()*fuerzaMaxima;
		else
			fuerzaInstantanea = 0;
		
		return (fuerzaInstantanea * (1 + sistemaC.getPlus()) );
	}
	
	public boolean estaEncendido(){
		return encendido;
	}
	
	// tiempo en segundo
	
    public void simular(double deltaTiempo)
    throws ProblemaTecnicoException{
    	
      double consumoInstantaneo = 0;
      int cambio= caja.getCambioActual();
      
      if (cambio>0) { 
         consumoInstantaneo =  (  (cilindros * cubicaje / 3600)* deltaTiempo )* (1 / caja.obtenerRelacion());  ; 
      }
       else{
    	   consumoInstantaneo = (  (cilindros * cubicaje / 3600)* deltaTiempo );
    	   }
       this.quemarCombustible(consumoInstantaneo);
       
     if(!encendido){ throw new ProblemaTecnicoException("Sin Combustible");}
     if(this.getVidaUtil() < 0.2){throw new ProblemaTecnicoException("Motor Fundido");}
     }
	
	public String getDetalles(){
		return (" Potencia: " + HP + " Cilindrada: " + (cilindros * cubicaje) + sistemaC.getDetalles() + "RMax: " + revolucionesMax + " Ract: " + revolucionesActuales);
	}

	public void embragarSubir(){
		caja.subirCambio();
		revolucionesActuales = (2 * revolucionesActuales / 5);
		if(revolucionesActuales < 800){ revolucionesActuales = 800;}
	}
    
	public void embragarBajar(){
		caja.bajarCambio();
		revolucionesActuales = (5 * revolucionesActuales / 2);
		if(revolucionesActuales > revolucionesMax){ 
			revolucionesActuales = revolucionesMax;
			this.gastar(0.05);}
	}
}  