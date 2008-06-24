package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

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
		
		jugador.comprarProducto(Vendedor.AUTOS, listaAutosVendedor.getSelectedIndex());
		listaAutosComprador.setListData(listaAutosVendedor.getSelectedValues());
		
	
	}

}
