package Modelo;

import Excepciones.ProblemaTecnicoException;

public class Carrera {
	private Pista pista;
	private Auto auto;
	private double intervaloTiempo;
	private long posicionAuto = 0;
	
	public Carrera (Pista pista, Auto auto, double intervaloTiempo) {
		this.pista = pista;
		this.auto = auto;
		this.intervaloTiempo = intervaloTiempo;
	}
	
	public void correr() {
		
		
		while(posicionAuto <= pista.getLongitud()) {
			try {
				auto.simular(intervaloTiempo, pista);
				posicionAuto += auto.getDeltaAvance();
				
			} catch (ProblemaTecnicoException e) {
				e.printStackTrace();
			}
		}
	}
	
	public long getPosicionAuto(){
		return posicionAuto;
	}
	
}
