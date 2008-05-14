package Modelo;

public class Carrera {
	private Pista pista;
	private Auto auto;
	private double intervaloTiempo;
	
	
	public Carrera (Pista pista, Auto auto, double intervaloTiempo) {
		this.pista = pista;
		this.auto = auto;
		this.intervaloTiempo = intervaloTiempo;
	}
	
	public void correr() {
		
		while(auto.getPosicion() <= pista.getLongitud()) {
			try {
				auto.simular(intervaloTiempo);
			} catch (ProblemaTecnicoException e) {
				e.printStackTrace();
			}
		}
	}
}
