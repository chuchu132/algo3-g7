package Modelo;

import java.util.ArrayList;

public class Taller {
	
	ArrayList<Auto> misAutos;
	ArrayList<Autoparte> misAutopartes;
	Auto autoActual;
	
	public Taller(){
		autoActual = null;
		misAutopartes = new ArrayList<Autoparte>();
		misAutos = new ArrayList<Auto>();
	}
	
	public void elegirAuto(int auto){
		try{
			autoActual = misAutos.get(auto);
		}
		catch (IndexOutOfBoundsException e){//MUY PRONTO
			autoActual = null;
			}
	}
}
