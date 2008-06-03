package Modelo;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NotAutoparteException;

//import java.util.ArrayList;

public class Jugador {
	
	//constantes
	private final long plataInicial = 10000;
	
	//atributos
	
	private Taller taller;
	private long plata;
	private Vendedor miVendedor;
	
	//contructor
	
	public Jugador () {
		taller = new Taller();
		plata = plataInicial;
		miVendedor = new Vendedor();
	}
	
	
	public void pedirMostrarListaAutopartes () {
		miVendedor.mostrarListaAutopartes();	
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
