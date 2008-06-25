package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;

import Excepciones.NotAutoparteException;
import Modelo.Autoparte;
import Modelo.Jugador;

public class ControladorCambiarAutoparte implements ActionListener {
	Jugador jugador;
	JList listaAutopartes;
	
	public ControladorCambiarAutoparte(Jugador jugador,JList listaAutoprates) {
		this.jugador = jugador;
		this.listaAutopartes = listaAutoprates;
	}

	
	public void actionPerformed(ActionEvent e) {
		ArrayList<Autoparte> lista = jugador.getTaller().getListaDeMisAutopartes();
		int item = listaAutopartes.getSelectedIndex();
		
		if(item >=0 ){
		   try{
			jugador.getTaller().cambiarParte(lista.get(item));
			
		   }
		   catch (NotAutoparteException ex){}
		}
		
		
	}
	

}
