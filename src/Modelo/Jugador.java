package Modelo;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Attribute;

import Excepciones.NoAlcanzaDineroException;
import Excepciones.NotAutoException;
import Excepciones.NotAutoparteException;



public class Jugador extends Observable{
	
	//constantes
	private final long plataInicial = 100000;
	
	private final int AUTOPARTES = 0;
	private final int AUTOS = 1;
	private final int NAFTAS = 2;
	private int CANTIDAD_FABRICAS = 3;
		
	
	//atributos
	
	private Taller miTaller;
	private double miPlata;
	private double litrosParaCargar;
	
	//contructor
	
	public Jugador () {
		miTaller = new Taller();
		miPlata = plataInicial;
	}
	
	public Jugador(Element elemJugador){
		Iterator it = elemJugador.elementIterator();
		
		Element elemTaller = (Element) it.next();
		Attribute atrPlata = (Attribute) elemJugador.attribute(0);
		
		miTaller = new Taller(elemTaller);
		miPlata = (Double.parseDouble( atrPlata.getValue()));
		}

	
	public double getLitrosParaCargar() {
		return litrosParaCargar;
	}

	public void setLitrosParaCargar(double litrosParaCargar) {
		this.litrosParaCargar = litrosParaCargar;
	}

	public void  comprarProducto (int tipoProducto, int numero) throws NoAlcanzaDineroException{
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
		setChanged();
	    notifyObservers();
		
		
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
	
	public void sumarDinero(double dinero){
	 if(dinero>=0.0){	
		miPlata += dinero;
		setChanged();
	    notifyObservers();
	 }
	}
	 
	public Taller getTaller(){
		return miTaller;
	}
	
	//este setter se usa en un test
	public void setPlata(long plata) {
		miPlata = plata;
	}
	
	public double getPlata() {
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
	   jugador.addAttribute("miPlata",Double.toString(this.miPlata));
	   return jugador;
	   
   }

}
