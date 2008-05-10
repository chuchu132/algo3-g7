public class Motor extends Autoparte{
	
	final private int CTE = 1;
	private double fuerzaMaxima;
	private int HP;
	private int cilindros;
	private double cubicaje;
	private double temperatura;
	private boolean encendido;
	private int revolucionesMax;
	private int revolucionesActuales;
	private double fuerzaInstantanea;
	private boolean acelerando;
	
	private SistemaCombustion sistemaC;
	
	public Motor(int HP, int cilindros, double cubicaje, double precio, double peso){
		super(peso,precio,1);
		this.HP=HP;
		this.cilindros=cilindros;
		this.cubicaje=cubicaje;
		this.fuerzaMaxima = HP * 250 + cilindros*cubicaje;
		this.temperatura = 0;
		this.encendido=false;
		this.revolucionesMax = (int)(HP * 17.647 + 2882.4);
		this.fuerzaInstantanea = 0;
		this.acelerando = false;
		this.sistemaC = new SistemaCombustion(0,0,"Comun",0);	
	}

	public void encender (){
		encendido = sistemaC.quemarCombustible(0.1);
		}
	
	public void conectarTanque(TanqueCombustible unTanque){
		sistemaC.conectarTanque(unTanque);
	}
    public void	cambiarSitemaCombustion( SistemaCombustion otroSistema){
    	otroSistema.conectarTanque(sistemaC.desconectarTanque());
    	sistemaC=otroSistema;
    }
	
	public void quemarCombustible (double cantidadCombustible) {
		if(!sistemaC.quemarCombustible(cantidadCombustible)) apagar();
	}

	private void apagar() {
		encendido = false;
	}

	public void acelerar (CajaVelocidades caja, int intervaloTiempo){
		int deltaRevoluciones;
		int cambioActual;
		acelerando = true;
		cambioActual = caja.getCambioActual();
		deltaRevoluciones = revolucionesMax * intervaloTiempo / 1000 *  CTE; 
		if (revolucionesActuales + deltaRevoluciones < revolucionesMax)
			revolucionesActuales += deltaRevoluciones;
	}
	
	public void desacelerar(int decrementoRevoluciones){
		acelerando = false;
		revolucionesActuales -= decrementoRevoluciones; 
		
	}
	
	public double getFuerzaInstantanea (CajaVelocidades caja, double fuerzaRozamiento, double velocidadInstantanea) {
		if (acelerando == true){
			if (revolucionesActuales >= 3/4*revolucionesMax)
			fuerzaInstantanea = fuerzaRozamiento;
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
	

	public String getDetalles(){
		return (" Potencia: " + HP + "Cilindrada: " + (cilindros * cubicaje) + "Sistema Combustion: " + sistemaC.getDetalles() );
	}
}

	
