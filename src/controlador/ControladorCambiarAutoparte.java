package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JList;

import vista.VistaCambiarAutoparte;

import Excepciones.NotAutoparteException;
import Modelo.Autoparte;
import Modelo.Jugador;

public class ControladorCambiarAutoparte  extends Observable  implements ActionListener {
	Jugador jugador;
	JList listaAutopartes;
	
	public ControladorCambiarAutoparte(Jugador jugador,JList listaAutoprates,VistaCambiarAutoparte vista) {
		this.jugador = jugador;
		this.listaAutopartes = listaAutoprates;
		this.addObserver(vista);
	}

	
	public void actionPerformed(ActionEvent e) {
		
		int item = listaAutopartes.getSelectedIndex();
		
		if(item >=0 ){
		   try{
			jugador.getTaller().cambiarParte(item);
			setChanged();
			notifyObservers();
		   }
		   catch (NotAutoparteException ex){
			   ex.printStackTrace();
		   }
		}
		
		
	}
	

}
