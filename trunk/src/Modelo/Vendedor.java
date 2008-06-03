package Modelo;


import java.util.ArrayList;
import java.util.SortedMap;

import Excepciones.NoAlcanzaDineroException;
import Excepciones.NumeroAutoparteInexistente;


public class Vendedor {

	private ArrayList <Producto> listaAutopartes;
	
	
	public Vendedor() throws SecurityException, NoSuchMethodException, ClassNotFoundException{
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
			
	private void armarLista() throws SecurityException, NoSuchMethodException, ClassNotFoundException {	
		
		Producto pAux;

		//Class<FabricaDeAutopartes> fabrica = (Class<FabricaDeAutopartes>) Class.forName("FabricaDeAutopartes");

		
		/*carrocería tipo 1*/
		//mAux = fabrica.getMethod("crearCarroceriaTipo1", null);
		pAux = new Producto(0,"Carrocería","Carrocería 1", "copada", 2700);
		listaAutopartes.add(pAux);
		
		/*carrocería tipo 2*/
		//mAux = fabrica.getMethod("crearCarroceriaTipo2", null);
		pAux = new Producto(1,"Carrocería","Carrocería 2", "mas copada", 3000);
		listaAutopartes.add(pAux);
		
		/*Ruedas tipo 1*/
		//mAux = fabrica.getMethod("crearTipoRuedaTipo1", null);
		pAux = new Producto(2,"Ruedas","Ruedas 1", "no resbalan", 500);
		listaAutopartes.add(pAux);
	}
	
	
}
