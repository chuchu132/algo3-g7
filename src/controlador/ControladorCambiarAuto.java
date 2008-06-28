package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JList;

import vista.VistaCambiarAuto;
import Modelo.Jugador;

public class ControladorCambiarAuto extends Observable implements ActionListener {

	Jugador jugador;
	JList listaAutopartes;
	
	public ControladorCambiarAuto(Jugador jugador,JList listaAutoprates,VistaCambiarAuto vista) {
		this.jugador = jugador;
		this.listaAutopartes = listaAutoprates;

	}

	
	public void actionPerformed(ActionEvent e) {
		
		int item = listaAutopartes.getSelectedIndex();
		
		if(item >=0 ){
				jugador.getTaller().elegirAuto(item);
				setChanged();
				notifyObservers();
		}
		
		
	}

}
