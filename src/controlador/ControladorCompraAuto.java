package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JList;
import javax.swing.JOptionPane;

import Excepciones.NoAlcanzaDineroException;
import Modelo.Jugador;
import Modelo.Vendedor;

public class ControladorCompraAuto extends Observable implements ActionListener {

	private Jugador jugador;
	private JList listaAutosVendedor;
	private JList listaAutosComprador;
	
	public ControladorCompraAuto(JList listaAutosVendedor, Jugador jugador, JList listaAutosComprador) {
		this.jugador = jugador;
		this.listaAutosVendedor = listaAutosVendedor;
		this.listaAutosComprador = listaAutosComprador;
		}
	
	
	public void actionPerformed(ActionEvent arg0) {
		int item = listaAutosVendedor.getSelectedIndex();
		try {
		
		  if(item>=0){
			jugador.comprarProducto(Vendedor.AUTOS, item);
			listaAutosComprador.setListData(listaAutosVendedor.getSelectedValues());
			setChanged();
			notifyObservers();
	     	}
		} catch (NoAlcanzaDineroException e) {
			
			JOptionPane.showMessageDialog(null, "NO ALCANZA EL DINERO", "Compra Cancelada", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
