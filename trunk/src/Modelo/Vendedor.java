package Modelo;


import java.util.ArrayList;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.ProductoInexistenteException;



public class Vendedor {

	private ArrayList <Producto> listaDeProductos[];
	private FabricaDeProductosVendibles[] fabricaDe; 
	
	public static final int AUTOPARTES = 0;
	public static  final int AUTOS = 1;
	public static final int NAFTAS = 2;
	private int CANTIDAD_FABRICAS = 3;
		
	public Vendedor() {
		fabricaDe= new FabricaDeProductosVendibles[CANTIDAD_FABRICAS];
		listaDeProductos = new  ArrayList[CANTIDAD_FABRICAS];
		fabricaDe[AUTOPARTES] = new FabricaDeAutopartes();
		fabricaDe[AUTOS] = new FabricaDeAutos();
		fabricaDe[NAFTAS] = new FabricaDeNafta();
		
		for(int i=0; i< CANTIDAD_FABRICAS; i++)
			listaDeProductos[i] = fabricaDe[i].armarLista();
	
	}
	
	public ArrayList<Producto> getListaDeProductosDe(int tipoProducto) {
		return listaDeProductos[tipoProducto];
	}
	
	public Vendible solicitarProducto(int numero, int tipoProducto, Jugador unJugador) throws NoAlcanzaDineroException {
		Producto pAux = listaDeProductos[tipoProducto].get(numero);
		Vendible vAux = null;
		double precioProducto;
		
		precioProducto = pAux.getPrecio();
		if (tipoProducto == NAFTAS) 
			precioProducto *= unJugador.getLitrosParaCargar();
		
		try {
			unJugador.restarDinero(precioProducto);
				
			vAux = fabricaDe[tipoProducto].crearProductoNumero(numero);
		} catch (ProductoInexistenteException e) {
			 unJugador.sumarDinero(precioProducto);
		}
		
		return vAux; 
		
	}
	

}
