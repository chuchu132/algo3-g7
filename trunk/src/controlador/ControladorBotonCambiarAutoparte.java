package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaCambiarAutoparte;

import Modelo.Jugador;

public class ControladorBotonCambiarAutoparte implements ActionListener {
     private Jugador jugador;
  
	 public ControladorBotonCambiarAutoparte(Jugador jugador){
		 this.jugador = jugador;
	 }

	public void actionPerformed(ActionEvent arg0) {
		
		VistaCambiarAutoparte ventana = new VistaCambiarAutoparte(jugador); 
		
	}

}
