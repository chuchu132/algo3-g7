package Modelo;

import java.util.ArrayList;

import Excepciones.TipoNaftaInexistenteException;

public class FabricaDeNafta  {
	
	public Nafta crearNaftaTipo(int n) throws TipoNaftaInexistenteException{
		switch (n) {
			case 0: return crearNaftaTipo1();
			case 1: return crearNaftaTipo2();
			case 2: return crearNaftaTipo3();
			default:
				throw new TipoNaftaInexistenteException();
		}
	}
	
	public ArrayList<Nafta> armarLista(){
		
		ArrayList<Nafta> listaNafta;
		
		Nafta naftaAux;
		
		listaNafta = new ArrayList<Nafta>();
		
		naftaAux = new Nafta(nombreTipo1,octTipo1,precioTipo1);
		listaNafta.add(naftaAux);
		
		naftaAux = new Nafta(nombreTipo2,octTipo2,precioTipo2);
		listaNafta.add(naftaAux);
		
		naftaAux = new Nafta(nombreTipo3,octTipo3,precioTipo3);
		listaNafta.add(naftaAux);
	
		return listaNafta; 
		
	}
	
	/*=================== NAFTAS ==========================*/
	
	/* Octanaje Nafta 1 */
	private final String nombreTipo1 = "Comun";
	private final int octTipo1 = 91;
	private final double precioTipo1 = 3;
	public Nafta crearNaftaTipo1(){
		return new Nafta(nombreTipo1,octTipo1,precioTipo1);
	}
	
	/* Octanaje Nafta 2 */
	private final String nombreTipo2 = "Super";
	private final int octTipo2 = 95;
	private final double precioTipo2 = 3.5;
	public Nafta crearNaftaTipo2(){
		return new Nafta(nombreTipo2,octTipo2,precioTipo2);
	}
	
	/* Octanaje Nafta 3 */
	private final String nombreTipo3 = "Extra";
	private final int octTipo3 = 98;
	private final double precioTipo3 = 5;
	public Nafta crearNaftaTipo3(){
		return new Nafta(nombreTipo3,octTipo3,precioTipo3);
	}
	

}
