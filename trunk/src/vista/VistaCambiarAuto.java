package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Modelo.Auto;
import Modelo.Jugador;
import Modelo.Producto;
import Modelo.Taller;
import Modelo.Vendedor;

public class VistaCambiarAuto extends JFrame{
	
	private final int ANCHO_VENTANA = 640;
	private final int ALTO_VENTANA = 480;
	private final int FILAS_LISTA = 5;
	private final int ANCHO_LISTA = 200;
	
	private Jugador jugador;
	private JList listaAutos;
	
	
	public VistaCambiarAuto(Jugador jugador) {
		this.jugador = jugador; 
		
		/* titulo ventana */
		setTitle("Cambio de Auto");
		
		/* contenedor de la ventana general y seteo*/
		Container panel = getContentPane();
		panel.setLayout(new GridLayout(1,2));
		
		/* Lista */
		listaAutos = new JList(getListaDeAutos());
		listaAutos.setVisibleRowCount(FILAS_LISTA);
		listaAutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaAutos.setFixedCellWidth(ANCHO_LISTA);
		panel.add(new JScrollPane(listaAutos), BorderLayout.CENTER);
		
		
		/* seteos generales ed la ventana */
		setBounds(0, 0, ANCHO_VENTANA,ALTO_VENTANA);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	private String[] getListaDeAutos(){
		Taller taller = jugador.getTaller();
		
		ArrayList <Auto> listaTemp = taller.getListaDeMisAutos();
		
		String[] listaNombreAuto = new String[listaTemp.size()];
		Iterator<Auto> it = listaTemp.iterator();
		
		int i=0;
		while (it.hasNext()) {
			Auto autoAux = (Auto) it.next();
			listaNombreAuto[i]= (autoAux.toString());
			i++;
		}
		return listaNombreAuto;
	}
	

	public static void main(String[] args) {
		Jugador ale = new Jugador();
		VistaCambiarAuto ventana = new VistaCambiarAuto(ale);
	 
	}
	
	

	
}