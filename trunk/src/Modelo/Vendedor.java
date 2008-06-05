package Modelo;


import java.util.ArrayList;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NumeroAutoInexistenteException;
import Excepciones.NumeroAutoparteInexistenteException;
import Excepciones.TipoNaftaInexistenteException;


public class Vendedor {

	private ArrayList <Producto> listaProductosAutopartes;
	private ArrayList <Producto> listaProductosAutos;
	private ArrayList <Producto> listaNafta;
	
	FabricaDeAutopartes fabricaDeAutopartes;
	FabricaDeAutos fabricaDeAutos;
	FabricaDeNafta fabricaDeNafta;
	
	public Vendedor() {
		fabricaDeAutopartes = new FabricaDeAutopartes();
		listaProductosAutopartes =  fabricaDeAutopartes.armarLista();
		
		fabricaDeNafta = new FabricaDeNafta();
		listaNafta = fabricaDeNafta.armarLista();
		
		fabricaDeAutos = new FabricaDeAutos();
		listaProductosAutos =  fabricaDeAutos.armarLista();
	}
			
	public Autoparte SolicitarCompraAutoparte(int numeroProducto, long dineroDisponible) throws NoAlcanzaDineroException {
		
		Autoparte AutoparteAux;
	
		Producto pAux;
		pAux = (Producto)listaProductosAutopartes.get(numeroProducto);
		
		if(dineroDisponible >= pAux.getPrecio()) {
			
			dineroDisponible -= pAux.getPrecio();

			try {
				AutoparteAux = fabricaDeAutopartes.crearAutoparteNumero(numeroProducto);
				return AutoparteAux; 
				
			} catch (NumeroAutoparteInexistenteException e) {
				e.printStackTrace();
			}
		} else
			throw new NoAlcanzaDineroException();
		
		return null;
	}


	public void mostrarListaAutopartes() {
		//aca utilizaria alguna funcion de la Vista
		System.out.println(listaProductosAutopartes.toString());		
	}

	public void mostrarListaAutos() {
		//aca utilizaria alguna funcion de la Vista
		System.out.println(listaProductosAutos.toString());
	}
	
	public void mostrarListaNafta() {
		//aca utilizaria alguna funcion de la Vista
		System.out.println(listaNafta.toString());
	}
	
	public Nafta SolicitarCompraNafta(int numeroNafta,double litros, long dineroDisponible) throws NoAlcanzaDineroException {
		Nafta naftaAux;
		
		Producto pAux;
		
		pAux = listaNafta.get(numeroNafta);
		
		if (dineroDisponible >= (litros * pAux.getPrecio())) {
			
			dineroDisponible -= ( litros *pAux.getPrecio());
			
			try {
				naftaAux = fabricaDeNafta.crearNaftaTipo(numeroNafta);
				return naftaAux;
			} catch (TipoNaftaInexistenteException e){
				e.printStackTrace();
			}
		} else 
			throw new NoAlcanzaDineroException();
		return null;
		
	}
	
	public Auto SolicitarCompraAuto(int numeroAuto, long dineroDisponible) throws NoAlcanzaDineroException {
		Auto autoAux;
		
		Producto pAux;
		pAux = (Producto)listaProductosAutos.get(numeroAuto);
		
		if(dineroDisponible >= pAux.getPrecio()) {
			
			dineroDisponible -= pAux.getPrecio();

			try {
				autoAux = fabricaDeAutos.crearAutoNumero(numeroAuto);
				return autoAux; 
				
			} catch (NumeroAutoInexistenteException e) {
				e.printStackTrace();
			}
		} else
			throw new NoAlcanzaDineroException();
		return null;
	}
	
	
}
