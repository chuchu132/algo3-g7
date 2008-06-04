package Modelo;

import java.util.ArrayList;

import Excepciones.TipoNaftaInexistente;

public class FabricaDeNafta  {
	
	public Nafta crearNaftaTipo(int n) throws TipoNaftaInexistente{
		switch (n) {
			case 0: return crearNaftaTipo1();
			case 1: return crearNaftaTipo2();
			case 2: return crearNaftaTipo3();
			case 3: return crearNaftaTipo4();
			default:
				throw new TipoNaftaInexistente();
		}
	}
	
	public ArrayList<Nafta> armarLista(){
		
		ArrayList<Nafta> listaNafta;
		
		Nafta naftaAux;
		
		listaNafta = new ArrayList<Nafta>();
		
		naftaAux = new Nafta(nombreTipo1,octTipo1);
		listaNafta.add(naftaAux);
		
		naftaAux = new Nafta(nombreTipo2,octTipo2);
		listaNafta.add(naftaAux);
		
		naftaAux = new Nafta(nombreTipo3,octTipo3);
		listaNafta.add(naftaAux);
		
		naftaAux = new Nafta (nombreTipo4,octTipo4);
		listaNafta.add(naftaAux);
		
		return listaNafta; 
		
	}
	
	/*=================== NAFTAS ==========================*/
	
	/* Octanaje Nafta 1 */
	private final String nombreTipo1 = "Nafta Tipo 1";
	private final int octTipo1 = 5;
	public Nafta crearNaftaTipo1(){
		return new Nafta(nombreTipo1,octTipo1);
	}
	
	/* Octanaje Nafta 2 */
	private final String nombreTipo2 = "Nafta Tipo 2";
	private final int octTipo2 = 3;
	public Nafta crearNaftaTipo2(){
		return new Nafta(nombreTipo2,octTipo2);
	}
	
	/* Octanaje Nafta 3 */
	private final String nombreTipo3 = "Nafta Tipo 3";
	private final int octTipo3 = 4;
	public Nafta crearNaftaTipo3(){
		return new Nafta(nombreTipo3,octTipo3);
	}
	
	/* Octanaje Nafta 4 */
	private final String nombreTipo4 = "Nafta Tipo 4";
	private final int octTipo4 = 7;
	public Nafta crearNaftaTipo4(){
		return new Nafta(nombreTipo3,octTipo4);
	}

}
