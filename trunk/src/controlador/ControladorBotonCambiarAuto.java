package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaCambiarAuto;
import Modelo.Jugador;

public class ControladorBotonCambiarAuto implements ActionListener {
    private Jugador jugador;
    
	 public ControladorBotonCambiarAuto(Jugador jugador){
		 this.jugador = jugador;
		 
	 }

	public void actionPerformed(ActionEvent arg0) {
		
		VistaCambiarAuto ventana = new VistaCambiarAuto(jugador); 
		
	}

}
