package Modelo;


import java.util.ArrayList;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NumeroAutoInexistenteException;
import Excepciones.NumeroAutoparteInexistenteException;


public class Vendedor {

	private ArrayList <Producto> listaProductosAutopartes;
	private ArrayList <Producto> listaProductosAutos;
	
	FabricaDeAutopartes fabricaDeAutopartes;
	FabricaDeAutos fabricaDeAutos;
	
	public Vendedor() {
		fabricaDeAutopartes = new FabricaDeAutopartes();
		listaProductosAutopartes =  fabricaDeAutopartes.armarLista();
		
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
		//acá utilizaría alguna función de la Vista
		System.out.println(listaProductosAutopartes.toString());		
	}

	public void mostrarListaAutos() {
		//acá utilizaría alguna función de la Vista
		System.out.println(listaProductosAutos.toString());
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
