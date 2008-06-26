package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

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

	public VistaCambiarAutoparte(Jugador jugador) {
		this.jugador = jugador;
		Container contenedor = getContentPane();
		JPanel panel = new JPanel();
		JButton botonCambiar;
				
		listaAutopartes = new JList(getListaAutopartes());
		listaAutopartes.setVisibleRowCount(FILAS_LISTA);
		listaAutopartes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaAutopartes.setFixedCellWidth(ANCHO_LISTA);
		
		panel.setLayout(new FlowLayout());
		panel.add(new JScrollPane(listaAutopartes));
		
		botonCambiar =  new JButton("Cambiar");
		botonCambiar.addActionListener(new ControladorCambiarAutoparte(jugador,listaAutopartes,this));
		
		panel.add(botonCambiar);
	    
		contenedor.add(panel);
	    
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setModal(true);
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		setResizable(true);
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
		listaAutopartes = new JList(getListaAutopartes());		
	}
}
