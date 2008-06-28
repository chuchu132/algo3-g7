package Modelo;


import java.util.ArrayList;
import java.util.Observable;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Excepciones.NoAlcanzaDineroException;
import Excepciones.NoExisteAutoException;
import Excepciones.NotAutoException;
import Excepciones.NotAutoparteException;


//import java.util.ArrayList;

public class Jugador extends Observable{
	
	//constantes
	private final long plataInicial = 100000;
	
	private final int AUTOPARTES = 0;
	private final int AUTOS = 1;
	private final int NAFTAS = 2;
	private int CANTIDAD_FABRICAS = 3;
		
	
	//atributos
	
	private Taller miTaller;
	private long miPlata;
	private double litrosParaCargar;
	
	//contructor
	
	public Jugador () {
		miTaller = new Taller();
		miPlata = plataInicial;
	}
	
	public double getLitrosParaCargar() {
		return litrosParaCargar;
	}

	//lo llamaria el controlador
	public void setLitrosParaCargar(double litrosParaCargar) {
		this.litrosParaCargar = litrosParaCargar;
	}

	public void  comprarProducto (int tipoProducto, int numero) throws NoAlcanzaDineroException, NoExisteAutoException {
		Vendedor miVendedor = new Vendedor();
		Vendible vAux;
		
		vAux = miVendedor.solicitarProducto(numero, tipoProducto, this);
		
		switch (tipoProducto) {
		case(AUTOPARTES):
			try {
					miTaller.agregarAutoparte((Autoparte) vAux);
				} catch (NotAutoparteException e) {
					e.printStackTrace();
				}break;
		case(AUTOS):
			try {
					miTaller.agregarAuto((Auto)vAux);
				} catch (NotAutoException e) {
					e.printStackTrace();
				}break;
		case(NAFTAS): 
			miTaller.cargarNaftaAlAutoActual(litrosParaCargar, (Nafta)vAux);break;
			
		}
		
		
	}
	
	public void vaciarTanque(){
		miTaller.vaciarTanqueAutoActual();
	}
	
	public void restarDinero(double precio) throws NoAlcanzaDineroException {
		if(miPlata - precio >= 0){
			miPlata -= precio;
		   setChanged();
	       notifyObservers();
	       }
		else
			throw new NoAlcanzaDineroException();
	}
	
	
	public Taller getTaller(){
		return miTaller;
	}
	
	public long getPlata() {
		return miPlata;
	}
	
   public ArrayList<Producto> solicitarListaDe(int tipoProducto){
	   Vendedor vendedor = new Vendedor();
	   return vendedor.getListaDeProductosDe(tipoProducto);
	   
   }	
   
   public Element serialize(){
	   Document document = DocumentHelper.createDocument();
	   Element jugador = document.addElement("jugador");
	   jugador.add(this.getTaller().serialize());
	   jugador.addAttribute("miPlata",Long.toString(this.miPlata));
	   return jugador;
	   
   }
}
