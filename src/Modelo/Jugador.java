package Modelo;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NotAutoException;
import Excepciones.NotAutoparteException;
import Excepciones.TipoNaftaInexistenteException;

//import java.util.ArrayList;

public class Jugador {
	
	//constantes
	private final long plataInicial = 10000;
	
	private final int AUTOPARTES = 0;
	private final int AUTOS = 1;
	private final int NAFTAS = 2;
	private int CANTIDAD_FABRICAS = 3;
		
	
	//atributos
	
	private Taller miTaller;
	private long miPlata;
	
	//contructor
	
	public Jugador () {
		miTaller = new Taller();
		miPlata = plataInicial;
	}
		
	public void  comprarProducto (int tipoProducto, int numero) {
		Vendedor miVendedor = new Vendedor();
		Vendible vAux;
		
		vAux = miVendedor.solicitarProducto(numero, tipoProducto, this);
		
		switch (tipoProducto) {
		case(AUTOPARTES):
			try {
					miTaller.agregarAutoparte((Autoparte) vAux);
				} catch (NotAutoparteException e) {
					e.printStackTrace();
				}
		case(AUTOS):
			try {
					miTaller.agregarAuto((Auto)vAux);
				} catch (NotAutoException e) {
					e.printStackTrace();
				}
		case(NAFTAS): //ver aca el tema de los ltros
			miTaller.cargarNaftaAlAutoActual(3, (Nafta)vAux);
			
		}
	}

	public void restarDinero(double precio) throws NoAlcanzaDineroException {
		if(miPlata - precio >= 0)
			miPlata -= precio;
		else
			throw new NoAlcanzaDineroException();
	}
	
		
}
