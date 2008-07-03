package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaVerPista;
import Modelo.Juego;


public class ControladorBotonVerPista implements ActionListener{

	private Juego juego;

	public ControladorBotonVerPista(Juego juego) {
		this.juego = juego;
	}

	public void actionPerformed(ActionEvent arg0) {
		new VistaVerPista(juego);	

	}

}
