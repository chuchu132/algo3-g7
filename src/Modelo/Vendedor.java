package Modelo;



import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.SortedMap;

import Excepciones.NoAlcanzaDineroException;




public class Vendedor {

	private ArrayList <Producto> listaAutopartes;
	
	
	public Vendedor() throws SecurityException, NoSuchMethodException, ClassNotFoundException{
		armarLista();
	}
		
	public ArrayList <Producto> entregarListaDeAutos() {
		return listaAutopartes;
	}
	
	
	public Object SolicitarCompraAutoparte(int numeroProducto, double dineroDisponible) throws NoAlcanzaDineroException {
		Producto pAux;
		pAux = (Producto)listaAutopartes.get(numeroProducto);
		
		if(dineroDisponible >= pAux.getPrecio()) {
			dineroDisponible -= pAux.getPrecio();
			return pAux.construir();
		} else
			throw new NoAlcanzaDineroException();
	}
			
	private void armarLista() throws SecurityException, NoSuchMethodException, ClassNotFoundException {	
		
		Producto pAux;
		Method mAux;

		Class<FabricaDeAutopartes> fabrica = (Class<FabricaDeAutopartes>) Class.forName("FabricaDeAutopartes");

		
		/*carrocería tipo 1*/
		mAux = fabrica.getMethod("crearCarroceriaTipo1", null);
		pAux = new Producto(0,"Carrocería","Carrocería 1", "copada", 2700, mAux);
		listaAutopartes.add(pAux);
		
		/*carrocería tipo 2*/
		mAux = fabrica.getMethod("crearCarroceriaTipo2", null);
		pAux = new Producto(1,"Carrocería","Carrocería 2", "mas copada", 3000, mAux);
		listaAutopartes.add(pAux);
		
		/*Ruedas tipo 1*/
		mAux = fabrica.getMethod("crearTipoRuedaTipo1", null);
		pAux = new Producto(2,"Ruedas","Ruedas 1", "no resbalan", 500, mAux);
		listaAutopartes.add(pAux);
	}
	
	
}
