package Modelo;

import java.util.ArrayList;
import java.util.Observable;

import Excepciones.ProblemaTecnicoException;

public class Carrera extends Observable{
	private Pista pista;
	private ArrayList<Auto> autos;
	private double intervaloTiempo;
	private ArrayList<Double> posiciones;
	
	public Carrera (Pista pista, ArrayList<Auto> autos, double intervaloTiempo) {
		this.pista = pista;
		this.autos = autos;
		this.intervaloTiempo = intervaloTiempo;
	}
	
	public void correr() {
				
		while(!llegoAlguien()) {
			try {
				for(int corredor = 0; corredor < autos.size();corredor++){
					Auto autoTemp = autos.get(corredor);
					autoTemp.simular(intervaloTiempo, pista);
				    double posicionAux = getPosicion(corredor) + autoTemp.getDeltaAvance();
				    posiciones.remove(corredor);
				    posiciones.add(corredor, new Double(posicionAux));
				notifyObservers();
			}
			}
			catch (ProblemaTecnicoException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private double getPosicion(int corredor){
		try{
			return (posiciones.get(corredor)).doubleValue();
		}
		catch (IndexOutOfBoundsException e) {
			return 0.0;
		}
	}	
		
	private boolean llegoAlguien(){
		for(int corredor = 0; corredor < autos.size();corredor++){
			if(getPosicion(corredor) > pista.getLongitud()){
				return true;
		      }
		}
		return false;
	 }
	
	
}
