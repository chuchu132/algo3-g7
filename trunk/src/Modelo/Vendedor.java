package Modelo;


import java.util.ArrayList;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NumeroAutoparteInexistente;


public class Vendedor {

	private ArrayList <Producto> listaAutopartes;
	
	
	public Vendedor() {
		armarLista();
	}
		
	public ArrayList <Producto> solicitarListaDeAutopartes() {
		return listaAutopartes;
	}
	
	public Autoparte SolicitarCompraAutoparte(int numeroProducto, double dineroDisponible) throws NoAlcanzaDineroException {
		FabricaDeAutopartes fabricaAutopartes = null;
		
		Producto pAux;
		pAux = (Producto)listaAutopartes.get(numeroProducto);
		
		if(dineroDisponible >= pAux.getPrecio()) {
			
			dineroDisponible -= pAux.getPrecio();
			fabricaAutopartes = new FabricaDeAutopartes();
			
			try {
				return fabricaAutopartes.crearAutoparteNumero(numeroProducto);
			} catch (NumeroAutoparteInexistente e) {
				e.printStackTrace();
			}
		} else
			throw new NoAlcanzaDineroException();
		
		return null;
	}
			
	private void armarLista(){	
		
		Producto pAux;

		pAux = new Producto(0,"Carrocería","Carrocería 1", "copada", 2700);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(1,"Carrocería","Carrocería 2", "mas copada", 3000);
		listaAutopartes.add(pAux);
		

		pAux = new Producto(2,"Ruedas","Ruedas 1", "no resbalan", 500);
		listaAutopartes.add(pAux);
	}
	
	
}
