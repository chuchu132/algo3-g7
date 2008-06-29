package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaJuego;
import vista.VistaTaller;

public class ControladorBotonAbandonar implements ActionListener {
     private VistaTaller vista; 
	public ControladorBotonAbandonar(VistaTaller vista){
		
		this.vista = vista;
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		vista.dispose();
		VistaJuego nuevaVista = new VistaJuego();
		
		
	}

}
