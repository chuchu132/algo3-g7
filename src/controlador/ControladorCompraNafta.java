package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import Excepciones.NoAlcanzaDineroException;
import Excepciones.NoExisteAutoException;
import Modelo.Jugador;
import Modelo.Vendedor;

public class ControladorCompraNafta implements ActionListener{
	private Jugador jugador;
	private JList listaNaftasVendedor;
	private JList listaNaftasComprador;
	
	public  ControladorCompraNafta(JList listaNaftasVendedor, Jugador jugador, JList listaNaftasComprador) {
		this.jugador = jugador;
		this.listaNaftasVendedor = listaNaftasVendedor;
		this.listaNaftasComprador = listaNaftasComprador;
		}
	
	

public void actionPerformed(ActionEvent arg0) {
	String valorTemp;
	if(jugador.getTaller().getAutoActual() != null){
			int item = listaNaftasVendedor.getSelectedIndex();
			if( item>=0){
				valorTemp=JOptionPane.showInputDialog(null,"Cantidad de litros:","Comprar Nafta",JOptionPane.QUESTION_MESSAGE);
			
			
				if(valorTemp!= null){
					try{
						jugador.setLitrosParaCargar(Double.parseDouble(valorTemp));
						jugador.comprarProducto(Vendedor.NAFTAS, listaNaftasVendedor.getSelectedIndex());
						listaNaftasComprador.setListData(listaNaftasVendedor.getSelectedValues());
					} 
					catch (NoAlcanzaDineroException e) {
						JOptionPane.showMessageDialog(null, "NO ALCANZA EL DINERO", "Compra Cancelada", JOptionPane.ERROR_MESSAGE);
						}
					catch (NumberFormatException e2){
						JOptionPane.showMessageDialog(null, "El valor ingresado no es un numero.","ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
						actionPerformed(arg0);} 
				}
		 }
	}
	else {
		JOptionPane.showMessageDialog(null,"Ud. no posee un auto.", "Error", JOptionPane.WARNING_MESSAGE);
	}
}


}


