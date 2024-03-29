package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import controlador.ControladorCarrera;

import Excepciones.ProblemaTecnicoException;

public class Carrera extends Observable implements Runnable{
	private Pista pista;
	private ArrayList<Auto> autos;
	private final double INTERVALO_TIEMPO = 0.01;
	private ArrayList<Double> posiciones;
	private double tiempo=0;
	private double posicionAuto1 = 0;
	private double posicionOponente = 0;
	private int ganador;
	private double apuestaXjugador;
	private double contador=3;
	private ControladorCarrera controlador;
	/** El auto del jugador va siempre en la posicion 0 de los arrays
	 * de esta forma cuando qeremos saber la pos de nuestro auto buscamos el corredor 0.
	 * 
	 */

	public Carrera (Pista pista, ArrayList<Auto> autos,Auto autoJugador, double apuestaXjugador) {
		this.pista = pista;
		this.autos = autos;
		this.posiciones = new ArrayList<Double>();
		this.apuestaXjugador = apuestaXjugador;
		autos.add(0,autoJugador);
		posiciones.add(new Double(0.0));
		for(int i = 1; i<autos.size();i++){
			posiciones.add(i,new Double(0.0));
		}
	}

	public void correr() throws ProblemaTecnicoException{
		double posicion;
		autos.get(0).encender();
		while(!llegoAlguien()) {
			int corredor = 0;
			Iterator< Auto> it = autos.iterator(); 
			while(it.hasNext()){
				Auto autoTemp = it.next();
				cuentaRegresiva();
				if (contador<0){
					try {

						autoTemp.simular(INTERVALO_TIEMPO, pista);
					}
					catch (ProblemaTecnicoException e) {  
						if(corredor == 0){
							throw e; }
						else{
							autos.remove(corredor);
						}
					}

					if (corredor == 0){					
						posicionAuto1 = getPosicion(corredor) + autoTemp.getDeltaAvance();
						posicion = posicionAuto1;
					}
					else {
						posicionOponente = getPosicion(corredor)+ autoTemp.getDeltaAvance();
						posicion = posicionOponente;
					}
					posiciones.remove(corredor);
					posiciones.add(corredor, new Double(posicion));
					System.out.println ("Pos auto " + corredor + " " + posicion);
					corredor++;
					tiempo+=INTERVALO_TIEMPO;
				}

				try{
					Thread.sleep(10);
				}
				catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}
			setChanged();
			notifyObservers();

		}

	}

	public void cuentaRegresiva(){
		contador = contador - 0.01;
	}

	public double getContador(){
		return contador;
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

	public double getPremio(){
		return this.apuestaXjugador*autos.size();
	}

	public Auto ganador(){
		return (Auto) autos.get(ganador);		

	}

	public double getPosJugador (){
		return posicionAuto1;
	}

	public double getPosOponente (){
		return posicionOponente;
	}

	private boolean llegoAlguien(){
		for(int corredor = 0; corredor < autos.size();corredor++){
			if(getPosicion(corredor) > pista.getLongitud()){
				ganador = corredor;
				return true;
			}
		}
		return false;
	}

/**
 * Debido a que la carrera se corre en otro hilo que no es el del programa principal
 * las excepciones no eran atrapadas, entonces tuvimos que poner el ControladorCarrera
 * (el que sabe que hacer cuando se rompe algo o cuando un jugador gana) dentro de la clase 
 * Carrera, que si bien sabemos que esta mal que el modelo sepa o tenga cosas de los controladores, 
 * fue la unica solucion que encontramos al problema.
 * */
	public void run() {
		try
		{
			this.correr();
			controlador.ganador();
		} catch(ProblemaTecnicoException e) 
		{  
			controlador.perder(e);
		}
	}

	public void setControlador(ControladorCarrera controlador) {
		this.controlador = controlador;
	}



}
