package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import Excepciones.ProblemaTecnicoException;

public class Carrera extends Observable{
	private Pista pista;
	private ArrayList<Auto> autos;
	private final double INTERVALO_TIEMPO = 0.01;
	private ArrayList<Double> posiciones;
	private double tiempo=0;
	private double posicion = 0;
	/** El auto del jugador va siempre en la posicion 0 de los arrays
	 * de esta forma cuando qeremos saber la pos de nuestro auto buscamos el corredor 0.
	 * 
	 * @param pista
	 * @param autos
	 * @param autoJugador
	 * @param INTERVALO_TIEMPO
	 */
	
	public Carrera (Pista pista, ArrayList<Auto> autos,Auto autoJugador, double Premio) {
		this.pista = pista;
		this.autos = autos;
		this.posiciones = new ArrayList<Double>();
		autos.add(0,autoJugador);
		posiciones.add(new Double(0.0));
		for(int i = 1; i<autos.size();i++){
			posiciones.add(i,new Double(0.0));
			}
	}
	
	public synchronized void correr() {
	    
		while(!llegoAlguien()) {
			int corredor = 0;
			Iterator< Auto> it = autos.iterator(); 
				while(it.hasNext()){
					Auto autoTemp = it.next();
				   
					try {
					
					autoTemp.simular(INTERVALO_TIEMPO, pista);
					}
					catch (ProblemaTecnicoException e) {
						                                  e.printStackTrace();
						                                  autos.remove(corredor);// ver q pasa si el auto q explota es el del jugador
					                                      } 
										
					posicion = getPosicion(corredor) + autoTemp.getDeltaAvance();
				    posiciones.remove(corredor);
				    posiciones.add(corredor, new Double(posicion));
				    System.out.println ("Pos auto " + corredor + " " + posicion);
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
				tiempo+=INTERVALO_TIEMPO;
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
	
	public double getTiempo(){
		return tiempo;
	}
	
	public double getPos (){
		return posicion;
	}
		
	private boolean llegoAlguien(){
		for(int corredor = 0; corredor < autos.size();corredor++){
			if(getPosicion(corredor) > pista.getLongitud()){
				return true;
		      }
		}
		return false;
	 }
	
	private int numeroDelGanador(){
		
	Iterator<Double> it = posiciones.iterator();
	double max = posiciones.get(0).doubleValue();
	int posicionMax = 0;
	int i= 0;
	while(it.hasNext()){
		i++;
		double temp =getPosicion(i);
		if( temp > max){
			max = temp;
			posicionMax = i;
		}
	}
	return posicionMax;
	}
	
	
}
