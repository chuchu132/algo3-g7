package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import Excepciones.ProblemaTecnicoException;

public class Carrera extends Observable{
	private Pista pista;
	private ArrayList<Auto> autos;
	private double intervaloTiempo;
	private ArrayList<Double> posiciones;
	
	/** El auto del jugador va siempre en la posicion 0 de los arrays
	 * de esta forma cuando qeremos saber la pos de nuestro auto buscamos el corredor 0.
	 * 
	 * @param pista
	 * @param autos
	 * @param autoJugador
	 * @param intervaloTiempo
	 */
	
	public Carrera (Pista pista, ArrayList<Auto> autos,Auto autoJugador, double intervaloTiempo) {
		this.pista = pista;
		this.autos = autos;
		this.intervaloTiempo = intervaloTiempo;
		this.posiciones = new ArrayList<Double>();
		autos.add(0,autoJugador);
		posiciones.add(new Double(0.0));
		for(int i = 1; i<autos.size();i++){
			posiciones.add(i,new Double(0.0));
			}
	}
	
	public synchronized void correr() {
	    float tiempo=0; 			
		while(!llegoAlguien()) {
			int corredor = 0;
			Iterator< Auto> it = autos.iterator(); 
				while(it.hasNext()){
					Auto autoTemp = it.next();
				   
					try {
					
					autoTemp.simular(intervaloTiempo, pista);
					}
					catch (ProblemaTecnicoException e) {
						                                  e.printStackTrace();
						                                  autos.remove(corredor);// ver q pasa si el auto q explota es el del jugador
					                                      } 
										
					double posicionAux = getPosicion(corredor) + autoTemp.getDeltaAvance();
				    posiciones.remove(corredor);
				    posiciones.add(corredor, new Double(posicionAux));
			     	System.out.println("Auto "+ corredor + " " + autoTemp.toString());
			     	System.out.println(" POS:" + posicionAux + " tiempo: " + tiempo);
			     	corredor++;
			    }
				try{
				Thread.sleep(10);
				}
				catch (InterruptedException e) {				
					e.printStackTrace();
				}
				setChanged();
				notifyObservers();
				tiempo+=intervaloTiempo;
		}
	}
	
	
	private double getPosicion(int corredor){
		try{
			return (posiciones.get(corredor)).doubleValue();
		}
		catch (Exception e) {
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
