package Modelo;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NotAutoException;
import Excepciones.NotAutoparteException;

//import java.util.ArrayList;

public class Jugador {
	
	//constantes
	private final long plataInicial = 10000;
	
	//atributos
	
	private Taller miTaller;
	private long miPlata;
	private Vendedor miVendedor;
	
	//contructor
	
	public Jugador () {
		miTaller = new Taller();
		miPlata = plataInicial;
		miVendedor = new Vendedor();
	}
	
	
	public void pedirMostrarListaAutopartes () {
		miVendedor.mostrarListaAutopartes();	
	}
	
	public void pedirMostrarListaAutos () {
		miVendedor.mostrarListaAutos();	
	}
	
	public void ComprarAutoparte(int numeroAutoparte){
		try {
			try {
				miTaller.agregarAutoparte(miVendedor.SolicitarCompraAutoparte(numeroAutoparte, miPlata));
			} catch (NotAutoparteException e) {
				e.printStackTrace();
			}
		} catch (NoAlcanzaDineroException e) {
			e.printStackTrace(); //Mensaje por la Vista que no alcanza la plata
		}
	}
	
	public void ComprarAuto(int numeroAuto){
		try {
			try {
				miTaller.agregarAuto(miVendedor.SolicitarCompraAuto(numeroAuto, miPlata));
			} catch (NotAutoException e) {
				e.printStackTrace();
			}
		} catch (NoAlcanzaDineroException e) {
			e.printStackTrace(); //Mensaje por la Vista que no alcanza la plata
		}
	}
	
	public void listarMisAutopartes() {
		miTaller.listarMisAutopartes();
	}
	
		
}
