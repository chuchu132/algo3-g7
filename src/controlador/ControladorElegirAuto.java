package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import vista.VistaCambiarAuto;
import vista.VistaCambiarAutoparte;
import Excepciones.NoExisteAutoException;
import Excepciones.NotAutoparteException;
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
		
		int item = listaAutos.getSelectedIndex();
		
		if(item >=0 ){
			   try{
				jugador.getTaller().elegirAuto(item);
				vista.resetearLista();			
			   }
			   catch (NoExisteAutoException ex2) {
				 
				   JOptionPane.showMessageDialog(null,"Ud. no posee un auto.", "Error", JOptionPane.WARNING_MESSAGE);
			}
			   
			}
	
		
	}
	

}
