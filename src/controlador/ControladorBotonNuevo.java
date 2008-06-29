package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Jugador;

import vista.VistaJuego;
import vista.VistaTaller;

public class ControladorBotonNuevo implements ActionListener {
   VistaJuego vista;
   
	public ControladorBotonNuevo(VistaJuego vista){
	  this.vista = vista;	 
		
	}
	public void actionPerformed(ActionEvent e) {
		Jugador unJugador = new Jugador();
		vista.dispose();
		VistaTaller verTaller = new VistaTaller(unJugador); 
	}

}
