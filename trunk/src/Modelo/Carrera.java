package Modelo;

import java.util.Observable;

import Excepciones.ProblemaTecnicoException;

public class Carrera extends Observable{
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
				notifyObservers();
			} catch (ProblemaTecnicoException e) {
				e.printStackTrace();
			}
		}
	}
	
	public long getPosicionAuto(){
		return posicionAuto;
	}
	
}
