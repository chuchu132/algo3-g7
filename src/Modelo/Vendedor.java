package Modelo;


import java.util.ArrayList;


import Excepciones.NoAlcanzaDineroException;
import Excepciones.NumeroAutoparteInexistente;


public class Vendedor {

	private ArrayList <Producto> listaAutopartes;

	
	public Vendedor() {
		armarLista();
	}
			
	public Autoparte SolicitarCompraAutoparte(int numeroProducto, double dineroDisponible) throws NoAlcanzaDineroException {
		FabricaDeAutopartes fabricaAutopartes = null;
		Autoparte AutoparteAux;
		
		Producto pAux;
		pAux = (Producto)listaAutopartes.get(numeroProducto);
		
		if(dineroDisponible >= pAux.getPrecio()) {
			
			dineroDisponible -= pAux.getPrecio();
			fabricaAutopartes = new FabricaDeAutopartes();
			
			try {
				AutoparteAux = fabricaAutopartes.crearAutoparteNumero(numeroProducto);
				return AutoparteAux; 
				
			} catch (NumeroAutoparteInexistente e) {
				e.printStackTrace();
			}
		} else
			throw new NoAlcanzaDineroException();
		
		return null;
	}
			
	private void armarLista(){	
		
		Producto pAux;
		
		listaAutopartes = new ArrayList<Producto>();
		
		pAux = new Producto(0,"Carrocería","Carrocería 1", "copada", 2700);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(1,"Carrocería","Carrocería 2", "mas copada", 3000);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(2,"Ruedas","Ruedas 1", "no resbalan", 500);
		listaAutopartes.add(pAux);
	}

	public void mostrarListaAutopartes() {
		//acá utilizaría alguna función de la Vista
		System.out.println(listaAutopartes.toString());		
	}
	
	
}
