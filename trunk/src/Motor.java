public class Motor extends Autoparte{
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
		
	public Motor (int HP, int cilindros, double cubicaje, double temperaturaAmbiente){
		this.HP=HP;
		this.cilindros=cilindros;
		this.cubicaje=cubicaje;
		this.fuerzaMaxima = HP * 250 + cilindros*cubicaje;
		this.temperatura = temperaturaAmbiente;
		this.encendido=false;
		this.revolucionesMax = (int)(HP * 17.647 + 2882.4);
		this.fuerzaInstantanea = 0;
		this.acelerando = false;
	}

	public void encender (TanqueCombustible tanque){
		if (tanque.getCombustible() > 2.0)
			encendido = true;
		//aca va una excepcion
	}
	
	public void quemarCombustible (TanqueCombustible tanque, double cantidadCombustible) {
		if(tanque.getCombustible() >= cantidadCombustible)
			tanque.quemarCombustible(cantidadCombustible);
		else
			apagar();
	}

	private void apagar() {
		encendido = false;
	}

	private void acelerar (CajaVelocidades caja, long intervaloTiempo){
		int deltaRevoluciones;
		int cambioActual;
		acelerando = true;
		cambioActual = caja.getCambioActual();
		deltaRevoluciones = revolucionesMax * intervaloTiempo / 1000 *  CTE //despues se define cual es
		if (revolucionesActuales + deltaRevoluciones < revolucionesMax)
			revolucionesActuales += deltaRevoluciones;
	}
	
	private void desacelerar(int decrementoRevoluciones){
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
			fuerzaInstantanea = 0.0;
		
		return fuerzaInstantanea;
	}
	
	

}

	
