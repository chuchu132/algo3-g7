package Modelo;


import java.util.ArrayList;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NumeroAutoparteInexistenteException;


public class Vendedor {

	private ArrayList <Producto> listaProductosAutopartes;
	FabricaDeAutopartes fabricaDeAutopartes;
	
	public Vendedor() {
		fabricaDeAutopartes = new FabricaDeAutopartes();
		listaProductosAutopartes =  fabricaDeAutopartes.armarLista();
	}
			
	public Autoparte SolicitarCompraAutoparte(int numeroProducto, double dineroDisponible) throws NoAlcanzaDineroException {
		
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
	
	
}
