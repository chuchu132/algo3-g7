package vista;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
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


import controlador.ControladorElegirAuto;

import Modelo.Auto;
import Modelo.Jugador;
import Modelo.Taller;


public class VistaCambiarAuto extends JDialog {
	
	private final int ANCHO_VENTANA = 640;
	private final int ALTO_VENTANA = 480;
	private final int FILAS_LISTA = 23;
	private final int ANCHO_LISTA = 400;
	
	private Jugador jugador;
	private JList listaAutos;
	private JPanel panel;
	private JPanel panelPrincipal;
	private JScrollPane scroll;
	
	public VistaCambiarAuto(Jugador jugador) {
		this.jugador = jugador; 
		
		/* titulo ventana */
		setTitle("Cambio de Auto");
		
		 crearPanel();  
		 add(panelPrincipal,BorderLayout.CENTER);
		
		/* seteos generales ed la ventana */
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setModal(true);
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		setResizable(false);
		
	    setVisible(true);
				
	
	}

		
	   private void crearPanel(){
		   JButton botonCambiar,botonSalir;
		   JPanel panelBotones = new JPanel();
		   JLabel etiqueta = new JLabel("Seleccione un Auto: ");
			
			panel = new JPanel();
		    panelPrincipal = new JPanel();
		    listaAutos = crearLista();
			
			panelPrincipal.setLayout(new BorderLayout());
			panelBotones.setLayout(new FlowLayout());
			
			panelPrincipal.add(etiqueta,BorderLayout.NORTH);
			
			this.scroll = new JScrollPane(listaAutos);
	    	panel.add(scroll,BorderLayout.CENTER);
			
	    	panelPrincipal.add(panel,BorderLayout.CENTER);
	    	
			botonCambiar =  new JButton("Elegir");
			botonCambiar.addActionListener(new ControladorElegirAuto(jugador,listaAutos,this));
			
			botonSalir = new JButton("Salir");
			botonSalir.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								 cerrarVentana();
				}
				
			});
			
			panelBotones.add(botonCambiar);
			panelBotones.add(botonSalir);
			
			panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
		 
		   	   
	   }
	
	private String[] getListaAutos(){
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
	
	 private void cerrarVentana(){
		   this.dispose();
	   }
	
	private JList crearLista(){
		  JList listaAutos;
		  
		   listaAutos = new JList(getListaAutos());
		   listaAutos.setVisibleRowCount(FILAS_LISTA);
		   listaAutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		   listaAutos.setFixedCellWidth(ANCHO_LISTA);
			
		    return listaAutos;
		   
	   }
		
	public void resetearLista(){
		  
		   panel.remove(scroll);
		   listaAutos = crearLista();
		   scroll = new JScrollPane(listaAutos);
		   panel.add(scroll,BorderLayout.CENTER);
		   repaint();
		   show();
		   
		   
	   }
	
	
	public static void main(String[] args) {
		Jugador ale = new Jugador();
		VistaCambiarAuto ventana = new VistaCambiarAuto(ale);
	 
	}
	
	  

	

	
}