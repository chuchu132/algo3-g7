package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controlador.ControladorCambiarAuto;
import controlador.ControladorCambiarAutoparte;

import Modelo.Auto;
import Modelo.Jugador;
import Modelo.Producto;
import Modelo.Taller;
import Modelo.Vendedor;

public class VistaCambiarAuto extends JDialog implements Observer{
	
	private final int ANCHO_VENTANA = 640;
	private final int ALTO_VENTANA = 480;
	private final int FILAS_LISTA = 5;
	private final int ANCHO_LISTA = 200;
	
	private Jugador jugador;
	//private JList listaAutos;
	private JPanel panel;
	
	
	public VistaCambiarAuto(Jugador jugador) {
		this.jugador = jugador; 
		
		/* titulo ventana */
		setTitle("Cambio de Auto");
		
		/* contenedor de la ventana general y seteo*/
		Container contenedor = getContentPane();
		panel = crearPanel();
	    
		contenedor.add(panel);
		
		/* seteos generales ed la ventana */
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setModal(true);
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		setResizable(false);
		
	    setVisible(true);
				
		
		

	}

	private JPanel crearPanel(){
		
		JList listaAutos;
		JButton botonCambiar,botonSalir;
		JPanel panel = new JPanel();
		JPanel panelBotones = new JPanel();
		JLabel etiqueta = new JLabel("Seleccione un Auto: ");
		
		/* Lista */
		listaAutos = new JList(getListaDeAutos());
		listaAutos.setVisibleRowCount(FILAS_LISTA);
		listaAutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaAutos.setFixedCellWidth(ANCHO_LISTA);
		
		panel.setLayout(new BorderLayout());
		panelBotones.setLayout(new FlowLayout());
		
		panel.add(etiqueta,BorderLayout.NORTH);
		panel.add(new JScrollPane(listaAutos),BorderLayout.CENTER);
		
		botonCambiar =  new JButton("Cambiar");
		botonCambiar.addActionListener(new ControladorCambiarAuto(jugador,listaAutos,this));
		
		botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							 cerrarVentana();
			}
			
		});
		
		panelBotones.add(botonCambiar);
		panelBotones.add(botonSalir);
		
		panel.add(panelBotones, BorderLayout.SOUTH);
	  
	   return panel;
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
	
	   private void cerrarVentana(){
		   this.dispose();
	   }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
}