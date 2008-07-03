package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaCompra;
import vista.VistaVerPista;

import Modelo.Juego;
import Modelo.Jugador;

public class ControladorBotonVerPista implements ActionListener{

	private Juego juego;
	
	public ControladorBotonVerPista(Juego juego) {
		this.juego = juego;
		}
	
	public void actionPerformed(ActionEvent arg0) {
		VistaVerPista ventanaVerPista = new VistaVerPista(juego);	
		
	}

}
