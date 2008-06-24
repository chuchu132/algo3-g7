package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaCompra;

import Modelo.Jugador;

public class ControladorCompra implements ActionListener{

	private Jugador jugador;
	
	public ControladorCompra(Jugador jugador) {
		this.jugador = jugador;
		}
	
	public void actionPerformed(ActionEvent arg0) {
		VistaCompra ventanaCompra = new VistaCompra(jugador);	
		
	}

}
