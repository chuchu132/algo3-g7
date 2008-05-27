package Modelo;

import Excepciones.ProblemaTecnicoException;

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
		long posicionAuto = 0;
		
		while(posicionAuto <= pista.getLongitud()) {
			try {
				auto.simular(intervaloTiempo);
				posicionAuto += auto.getDeltaAvance();
				dibujarAuto(posicionAuto);
			} catch (ProblemaTecnicoException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void dibujarAuto(double posicion) {
		
	}
	
}
