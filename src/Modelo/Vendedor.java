package Modelo;


import java.util.ArrayList;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NumeroAutoInexistenteException;
import Excepciones.NumeroAutoparteInexistenteException;
import Excepciones.ProductoInexistenteException;
import Excepciones.TipoNaftaInexistenteException;


public class Vendedor {

	private ArrayList <Producto> listaDeProductos[];
	private FabricaDeProductosVendibles[] fabricaDe; 
	
	private final int AUTOPARTES = 0;
	private final int AUTOS = 1;
	private final int NAFTAS = 2;
	private int CANTIDAD_FABRICAS = 3;
		
	public Vendedor() {
		fabricaDe[AUTOPARTES] = new FabricaDeAutopartes();
		fabricaDe[AUTOS] = new FabricaDeAutos();
		fabricaDe[NAFTAS] = new FabricaDeNafta();
		
		for(int i=0; i< CANTIDAD_FABRICAS; i++)
			listaDeProductos[i] = fabricaDe[i].armarLista();
	
	}
	
	public ArrayList<Producto> getListaDeProductosDe(int tipoProducto) {
		return listaDeProductos[tipoProducto];
	}
	
	public Vendible solicitarProducto(int numero, int tipoProducto, Jugador unJugador) {
		Producto pAux = listaDeProductos[tipoProducto].get(numero);
		Vendible vAux = null;
		
		try {
			unJugador.restarDinero(pAux.getPrecio());
		} catch (NoAlcanzaDineroException e) {
			e.printStackTrace();
		}
		
		try {
			vAux = fabricaDe[tipoProducto].crearProductoNumero(numero);
		} catch (ProductoInexistenteException e) {
			e.printStackTrace();
		}
		return vAux; //acá llega solo cuando el try funco?? sino va al catch?
		
	}
	

}
