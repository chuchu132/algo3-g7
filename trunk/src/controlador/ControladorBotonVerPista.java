package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaCompra;
import vista.VistaVerPista;

import Modelo.Jugador;

public class ControladorBotonVerPista implements ActionListener{

	private Jugador jugador;
	
	public ControladorBotonVerPista(Jugador jugador) {
		this.jugador = jugador;
		}
	
	public void actionPerformed(ActionEvent arg0) {
		VistaVerPista ventanaVerPista = new VistaVerPista(jugador);	
		
	}

}
