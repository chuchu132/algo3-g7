package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaCambiarAuto;

import Modelo.Jugador;



public class ControladorBotonElegirAuto implements ActionListener {
	private Jugador jugador;

	public ControladorBotonElegirAuto(Jugador jugador){
		this.jugador = jugador;
	}

	public void actionPerformed(ActionEvent e) {
		new VistaCambiarAuto(jugador);

	}

}
