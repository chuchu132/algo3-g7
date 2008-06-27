package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import sun.awt.geom.AreaOp.AddOp;

import controlador.ControladorBotonCambiarAutoparte;
import controlador.ControladorCambiarAutoparte;

import Modelo.Autoparte;
import Modelo.Jugador;


public class VistaCambiarAutoparte extends JDialog implements Observer{

	private final int FILAS_LISTA = 27;
	private final int ALTO_VENTANA = 480;
	private final int ANCHO_VENTANA = 640;
	
	private final int ANCHO_LISTA = 400;
	private Jugador jugador;
	private JList listaAutopartes;
    private JPanel panel;
	
    public VistaCambiarAutoparte(Jugador jugador) {
		this.jugador = jugador;
		Container contenedor = getContentPane();
		
		
		panel = crearPanel();
	    
		contenedor.add(panel);
	    
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setModal(true);
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		setResizable(false);
	    setVisible(true);
	}
	
	String[] getListaAutopartes(){
		ArrayList<Autoparte> listaTemp = jugador.getTaller().getListaDeMisAutopartes();
		String[] listaNombreAutoparte = new String[listaTemp.size()];
		Iterator<Autoparte> it = listaTemp.iterator();
		int i=0;
		while (it.hasNext()) {
			Autoparte parte = (Autoparte) it.next();
		    listaNombreAutoparte[i] = parte.toString();
			i++;
		}
		return listaNombreAutoparte;
	}

	
	
	public void update(Observable o, Object arg) {
		this.getContentPane().removeAll();
		this.getContentPane().add(crearPanel());		
		this.repaint();
		this.show();
	}
	
   private JPanel crearPanel(){
	   JList listaAutopartes;
	   JButton botonCambiar;
	   JPanel panel = new JPanel();
	   
		listaAutopartes = new JList(getListaAutopartes());
		listaAutopartes.setVisibleRowCount(FILAS_LISTA);
		listaAutopartes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaAutopartes.setFixedCellWidth(ANCHO_LISTA);
		
		panel.setLayout(new FlowLayout());
		panel.add(new JScrollPane(listaAutopartes));
		
		botonCambiar =  new JButton("Cambiar");
		botonCambiar.addActionListener(new ControladorCambiarAutoparte(jugador,listaAutopartes,this));
		
		panel.add(botonCambiar);
	  
	   return panel;
   }
}
