package Modelo;

import java.util.ArrayList;

import Excepciones.ProductoInexistenteException;
import Excepciones.TipoNaftaInexistenteException;

public class FabricaDeNafta extends FabricaDeProductosVendibles {
	

	public ArrayList<Producto> armarLista(){
		
		ArrayList<Producto> listaNafta;
		
		Producto pAux;
		
		listaNafta = new ArrayList<Producto>();
		
		pAux = new Producto(0,tipoNafta,nombreTipo1,descripcionTipo1,precioTipo1);
		listaNafta.add(pAux);
		
		pAux = new Producto(1,tipoNafta,nombreTipo2,descripcionTipo2,precioTipo2);
		listaNafta.add(pAux);
		
		pAux = new Producto(2,tipoNafta,nombreTipo3,descripcionTipo3,precioTipo3);
		listaNafta.add(pAux);
	
		return listaNafta; 
		
	}
	
	public Vendible crearProductoNumero(int n)
			throws ProductoInexistenteException {

		switch (n) {
			case 0: return crearNaftaTipo1();
			case 1: return crearNaftaTipo2();
			case 2: return crearNaftaTipo3();
			default:
				throw new ProductoInexistenteException();
		}		
	}

	
	
	/*=================== NAFTAS ==========================*/
	private final String tipoNafta = "Nafta";
	/* Octanaje Nafta 1 */
	private final String nombreTipo1 = "Comun";
	private final int octTipo1 = 91;
	private final double precioTipo1 = 3;
	private final String descripcionTipo1 = "la nafta de más bajo octanaje";
	
	private Nafta crearNaftaTipo1(){
		return new Nafta(nombreTipo1,octTipo1,precioTipo1);
	}
	
	/* Octanaje Nafta 2 */
	private final String nombreTipo2 = "Super";
	private final int octTipo2 = 95;
	private final double precioTipo2 = 3.5;
	private final String descripcionTipo2 = "un buen combustible";
	
	private Nafta crearNaftaTipo2(){
		return new Nafta(nombreTipo2,octTipo2,precioTipo2);
	}
	
	/* Octanaje Nafta 3 */
	private final String nombreTipo3 = "Extra";
	private final int octTipo3 = 98;
	private final double precioTipo3 = 5;
	private final String descripcionTipo3 ="la nafta de mejor octanaje";
	
	private Nafta crearNaftaTipo3(){
		return new Nafta(nombreTipo3,octTipo3,precioTipo3);
	}



	

}
