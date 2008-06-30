package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.dom4j.DocumentException;
import org.xmlpull.v1.XmlPullParserException;

import vista.VistaJuego;
import vista.VistaTaller;
import Modelo.Juego;

public class ControladorCargarJuego extends JFrame implements ActionListener {
	VistaJuego vista;
	   
	public  ControladorCargarJuego(VistaJuego vista){
	  this.vista = vista;	 
		
	}
	public void actionPerformed(ActionEvent e) {
		Juego unJuego = new Juego();
		vista.dispose();
		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int resultado = selectorArchivo.showSaveDialog(this);
		if( resultado == JFileChooser.CANCEL_OPTION){
			vista = new VistaJuego();
		} 
		else
		{  
			File archivo = selectorArchivo.getSelectedFile();
					
		
			try {
				unJuego.cargar(archivo.toString());
			} catch (IOException e1) {
				
			} catch (DocumentException e1) {
				
			} catch (XmlPullParserException e1) {
				
			}
	    
			
		    VistaTaller verTaller = new VistaTaller(unJuego.getJugador());
		}
	}
	private void lanzarCartel(){
		
	}
}
