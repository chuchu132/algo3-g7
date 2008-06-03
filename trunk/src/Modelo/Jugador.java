package Modelo;

import Excepciones.NoAlcanzaDineroException;
import Excepciones.NotAutoparteException;

//import java.util.ArrayList;

public class Jugador {

	private final long plataInicial = 10000;
	
	private Taller taller;
	private long plata;
	
	
	public Jugador () {
		taller = new Taller();
		plata = plataInicial;
	}
	
	
	public void pedirMostrarLista () {
		
	}
	
	public void ComprarAutoparte(int numeroAutoparte){
		
	}
	
	
	/*
	public void ComprarAutoparte(int numeroAutoparte) {
		
		Vendedor miVendedor = null;
		
		try {
			miVendedor = new Vendedor();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			taller.agregarAutoparte((Autoparte)miVendedor.SolicitarCompraAutoparte(numeroAutoparte, plata));
		} catch (NotAutoparteException e) {
			e.printStackTrace();
		} catch (NoAlcanzaDineroException e) {
			e.printStackTrace();
		}
		
		
	}
*/
	
	
	
	
}
