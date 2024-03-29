package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.Juego;


public class ControladorGuardarJuego extends JFrame  implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Juego juego;

	public ControladorGuardarJuego(Juego juego){
		this.juego = juego;
	}

	public void actionPerformed(ActionEvent arg0) {

		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int resultado = selectorArchivo.showSaveDialog(this);
		if( resultado != JFileChooser.CANCEL_OPTION){
			File archivo = selectorArchivo.getSelectedFile();
			try{
				juego.guardar(archivo.toString());
			}
			catch (IOException e){
				JOptionPane.showMessageDialog(null,"Se ha producido un error al guardar el juego.","ERROR" ,JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
