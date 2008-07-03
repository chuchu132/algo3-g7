package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaCompra;

import Modelo.Jugador;

public class ControladorBotonCompra implements ActionListener{

	private Jugador jugador;

	public ControladorBotonCompra(Jugador jugador) {
		this.jugador = jugador;
	}

	public void actionPerformed(ActionEvent arg0) {
		new VistaCompra(jugador);	

	}

}
