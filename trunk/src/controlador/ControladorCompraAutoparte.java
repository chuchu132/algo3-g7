package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import Excepciones.NoAlcanzaDineroException;
import Excepciones.NoExisteAutoException;
import Modelo.Jugador;
import Modelo.Vendedor;

public class ControladorCompraAutoparte implements ActionListener {
	private Jugador jugador;
	private JList listaAutopartesVendedor;
	private JList listaAutopartesComprador;
	
	public ControladorCompraAutoparte(JList listaAutopartesVendedor, Jugador jugador, JList listaAutopartesComprador) {
		this.jugador = jugador;
		this.listaAutopartesVendedor = listaAutopartesVendedor;
		this.listaAutopartesComprador = listaAutopartesComprador;
		}
	
	
	public void actionPerformed(ActionEvent arg0) {
		int item = listaAutopartesVendedor.getSelectedIndex();
		try {
		
		  if(item>=0){
			jugador.comprarProducto(Vendedor.AUTOPARTES, listaAutopartesVendedor.getSelectedIndex());
			listaAutopartesComprador.setListData(listaAutopartesVendedor.getSelectedValues());
		  }
		 } catch (NoAlcanzaDineroException e) {
			
			JOptionPane.showMessageDialog(null, "NO ALCANZA EL DINERO", "Compra Cancelada", JOptionPane.ERROR_MESSAGE);
		} catch (NoExisteAutoException e2) {
			JOptionPane.showMessageDialog(null,"Ud. no posee un auto.", "Error", JOptionPane.WARNING_MESSAGE);
		}
		  
	
	}

}



