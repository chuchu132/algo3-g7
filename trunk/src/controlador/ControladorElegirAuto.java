package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import vista.VistaCambiarAuto;
import vista.VistaCambiarAutoparte;
import Modelo.Jugador;

public class ControladorElegirAuto implements ActionListener{

	private Jugador jugador;
	private JList listaAutos;
	private VistaCambiarAuto vista;
	
	public ControladorElegirAuto(Jugador jugador,JList listaAutos,VistaCambiarAuto vista){
		this.jugador = jugador;
		this.listaAutos = listaAutos;
		this.vista=vista;
	}
	public void actionPerformed(ActionEvent e) {
	
		
	}
	

}
