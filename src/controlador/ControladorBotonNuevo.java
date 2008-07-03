package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Juego;
import vista.VistaJuego;
import vista.VistaTaller;

public class ControladorBotonNuevo implements ActionListener {
	private  VistaJuego vista;

	public ControladorBotonNuevo(VistaJuego vista){
		this.vista = vista;	 

	}
	public void actionPerformed(ActionEvent e) {
		vista.dispose();
		new VistaTaller(new Juego()); 
	}

}
