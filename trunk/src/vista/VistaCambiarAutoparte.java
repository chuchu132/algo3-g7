package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controlador.ControladorCambiarAutoparte;

import Modelo.Autoparte;
import Modelo.Jugador;


public class VistaCambiarAutoparte extends JDialog {

	private final int FILAS_LISTA = 23;
	private final int ALTO_VENTANA = 480;
	private final int ANCHO_VENTANA = 640;
	
	private final int ANCHO_LISTA = 400;
	private Jugador jugador;
	private JList listaAutopartes;
    private JPanel panelPrincipal;
	private JPanel panel;
    private JScrollPane scroll;
	
    public VistaCambiarAutoparte(Jugador jugador) {
		this.jugador = jugador;
		panelPrincipal= new JPanel();
		
		setTitle("CAMBIAR AUTOPARTE");
		
		crearPanel();
	    add(panelPrincipal,BorderLayout.CENTER);	    
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

   private void cerrarVentana(){
	   this.dispose();
   }	
	
   private void crearPanel(){
	   JButton botonCambiar,botonSalir;
	   JPanel panelBotones = new JPanel();
	   JLabel etiqueta = new JLabel("Seleccione una Autoparte: ");
		
		panel = new JPanel();
	   
	    listaAutopartes = crearLista();
		
		panelPrincipal.setLayout(new BorderLayout());
		panelBotones.setLayout(new FlowLayout());
		
		panelPrincipal.add(etiqueta,BorderLayout.NORTH);
		
		this.scroll = new JScrollPane(listaAutopartes);
    	panel.add(scroll,BorderLayout.CENTER);
		
    	panelPrincipal.add(panel,BorderLayout.CENTER);
    	
		botonCambiar =  new JButton("Cambiar");
		botonCambiar.addActionListener(new ControladorCambiarAutoparte(jugador,listaAutopartes,this));
		
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
   
   private JList crearLista(){
	  JList listaAutopartes;
	  
	   listaAutopartes = new JList(getListaAutopartes());
	   listaAutopartes.setVisibleRowCount(FILAS_LISTA);
	   listaAutopartes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	   listaAutopartes.setFixedCellWidth(ANCHO_LISTA);
		
	    return listaAutopartes;
	   
   }
   
   public void resetearLista(){
	  
	   panel.remove(scroll);
	   listaAutopartes = crearLista();
	   scroll = new JScrollPane(listaAutopartes);
	   panel.add(scroll,BorderLayout.CENTER);
	   repaint();
	   show();
	   
	   
   }
   
}
