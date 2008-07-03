package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.dom4j.DocumentException;
import org.xmlpull.v1.XmlPullParserException;

import vista.VistaJuego;
import vista.VistaTaller;
import Modelo.Juego;

public class ControladorCargarJuego extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private VistaJuego vista;

	public  ControladorCargarJuego(VistaJuego vista){
		this.vista = vista;	 

	}
	public void actionPerformed(ActionEvent e) {

		vista.dispose();
		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setApproveButtonText("Cargar");
		selectorArchivo.setDialogTitle("Cargar  Juego");
		selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int resultado = selectorArchivo.showSaveDialog(this);

		if( resultado == JFileChooser.CANCEL_OPTION){
			vista = new VistaJuego();
		} 
		else
		{  
			File archivo = selectorArchivo.getSelectedFile();


			try {
				Juego juego = new Juego();
				juego.cargar(archivo.toString());
				new VistaTaller(juego);
			} catch (IOException e1) {
				avisarYVolver();
			} catch (DocumentException e1) {
				avisarYVolver();
			} catch (XmlPullParserException e1) {
				avisarYVolver();
			}		    
		}
	}
	private void avisarYVolver(){
		JOptionPane.showMessageDialog(null, "Se ha producido un error al cargar el archivo.", "ERROR", JOptionPane.ERROR_MESSAGE);
		new VistaJuego(); 
	}
}
