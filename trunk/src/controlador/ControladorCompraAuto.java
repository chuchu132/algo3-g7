package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import Excepciones.NoAlcanzaDineroException;
import Modelo.Jugador;
import Modelo.Vendedor;

public class ControladorCompraAuto implements ActionListener {

	private Jugador jugador;
	private JList listaAutosVendedor;
	private JList listaAutosComprador;
	
	public ControladorCompraAuto(JList listaAutosVendedor, Jugador jugador, JList listaAutosComprador) {
		this.jugador = jugador;
		this.listaAutosVendedor = listaAutosVendedor;
		this.listaAutosComprador = listaAutosComprador;
		}
	
	
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			jugador.comprarProducto(Vendedor.AUTOS, listaAutosVendedor.getSelectedIndex());
			listaAutosComprador.setListData(listaAutosVendedor.getSelectedValues());
		} catch (NoAlcanzaDineroException e) {
			
			JOptionPane.showMessageDialog(null, "NO ALCANZA EL DINERO", "Compra Cancelada", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
