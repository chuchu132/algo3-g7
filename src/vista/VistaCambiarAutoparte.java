package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Modelo.Autoparte;
import Modelo.Jugador;


public class VistaCambiarAutoparte extends JDialog{

	private final int FILAS_LISTA = 20;
	private final int ALTO_VENTANA = 480;
	private final int ANCHO_VENTANA = 640;
	
	private final int ANCHO_LISTA = 400;
	private Jugador jugador;
		 
	public VistaCambiarAutoparte(Jugador jugador) {
		this.jugador = jugador;
		Container contenedor = getContentPane();
		JPanel panel = new JPanel();
		JList listaAutopartes;
		JButton botonCambiar;
		
		panel.setLayout(new FlowLayout());
		listaAutopartes = new JList(getListaAutopartes());
		listaAutopartes.setVisibleRowCount(FILAS_LISTA);
		listaAutopartes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaAutopartes.setFixedCellWidth(ANCHO_LISTA);
		
		botonCambiar =  new JButton("Cambiar");
		panel.add(botonCambiar);
		panel.add(new JScrollPane(listaAutopartes));
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
}
