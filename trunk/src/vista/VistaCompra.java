package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controlador.ControladorCompraAuto;
import controlador.ControladorCompraAutoparte;
import controlador.ControladorCompraNafta;



import Modelo.Jugador;
import Modelo.Producto;
import Modelo.Vendedor;

public class VistaCompra extends JDialog {
	private final int CANTIDAD_PANELES = 3;
	private final int FILAS_LISTA = 5;
	private final int ALTO_VENTANA = 480;
	private final int ANCHO_VENTANA = 640;
	
	private final int ANCHO_LISTA = 200;
	private Jugador jugador;
		 
	public VistaCompra(Jugador jugador){
		this.jugador = jugador;
		Container contenedor = getContentPane();
		JPanel panelDePaneles = new JPanel();
		panelDePaneles.setLayout(new GridLayout(CANTIDAD_PANELES,1));
		
		//opciones ventana principal.
		
		panelDePaneles.add(panelAutos());
		panelDePaneles.add(panelAutopartes());
		panelDePaneles.add(panelNafta());
		
		contenedor.add(panelDePaneles,BorderLayout.CENTER);
		
	    setTitle("COMPRA");
		setModal(true);
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
	}
	
	
	private  JPanel panelAutos(){
		JList listaAutosVendedor;
		JList listaAutosComprador;
		JButton botonCompraAuto;
		JPanel panelAutos = new JPanel();
		panelAutos.setLayout(new FlowLayout());
		
		listaAutosVendedor = new JList(getListaDe(Vendedor.AUTOS));
		listaAutosVendedor.setVisibleRowCount(FILAS_LISTA);
		listaAutosVendedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		listaAutosVendedor.setFixedCellWidth(ANCHO_LISTA);
		panelAutos.add(new JScrollPane(listaAutosVendedor));
		
		listaAutosComprador = new JList();
		listaAutosComprador.setVisibleRowCount(FILAS_LISTA);
		listaAutosComprador.setFixedCellWidth(ANCHO_LISTA);
		

		botonCompraAuto = new JButton("Comprar");
		botonCompraAuto.addActionListener(new ControladorCompraAuto(listaAutosVendedor,jugador, listaAutosComprador));
		
		
		
		panelAutos.add(botonCompraAuto);
		panelAutos.add(new JScrollPane(listaAutosComprador));
		 
		return panelAutos;
		
	}
	
	private JPanel panelAutopartes(){
		 JList listaAutoparteVendedor;
		 JList listaAutoparteComprador;
		 JButton botonCompraAutoparte;
		JPanel panelAutopartes = new JPanel();
		panelAutopartes.setLayout(new FlowLayout());
				
		listaAutoparteVendedor = new JList(getListaDe(Vendedor.AUTOPARTES));
		listaAutoparteVendedor.setVisibleRowCount(FILAS_LISTA);
		listaAutoparteVendedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listaAutoparteVendedor.setFixedCellWidth(ANCHO_LISTA);
		panelAutopartes.add(new JScrollPane(listaAutoparteVendedor));
		
		
		listaAutoparteComprador = new JList();
		listaAutoparteComprador.setVisibleRowCount(FILAS_LISTA);
		listaAutoparteComprador.setFixedCellWidth(ANCHO_LISTA);
		
		botonCompraAutoparte = new JButton("Comprar");
		botonCompraAutoparte.addActionListener(new ControladorCompraAutoparte(listaAutoparteVendedor,jugador, listaAutoparteComprador));
		
		
		panelAutopartes.add(botonCompraAutoparte);
		panelAutopartes.add(new JScrollPane(listaAutoparteComprador));
		
		return panelAutopartes;
	}

	private JPanel panelNafta(){
		JList listaNaftaVendedor;
		JList listaNaftaComprador;
		JButton botonCompraNafta;
		JPanel panelNafta = new JPanel();
		panelNafta.setLayout(new FlowLayout());
				
		listaNaftaVendedor = new JList(getListaDe(Vendedor.NAFTAS));
		listaNaftaVendedor.setVisibleRowCount(FILAS_LISTA);
		listaNaftaVendedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listaNaftaVendedor.setFixedCellWidth(ANCHO_LISTA);
		panelNafta.add(new JScrollPane(listaNaftaVendedor));
		
		listaNaftaComprador = new JList();
		listaNaftaComprador.setVisibleRowCount(FILAS_LISTA);
		listaNaftaComprador.setFixedCellWidth(ANCHO_LISTA);
		
		botonCompraNafta = new JButton("Comprar");
		botonCompraNafta.addActionListener(new ControladorCompraNafta(listaNaftaVendedor,jugador, listaNaftaComprador));
		
		panelNafta.add(botonCompraNafta);
		panelNafta.add(new JScrollPane(listaNaftaComprador));
		
		return panelNafta;
	}
	
	
private String[] getListaDe(int tipoProducto){
	ArrayList<Producto> listaTemp = jugador.solicitarListaDe(tipoProducto);
	String[] listaNombreProducto = new String[listaTemp.size()];
	Iterator<Producto> it = listaTemp.iterator();
	int i=0;
	while (it.hasNext()) {
		Producto producto = (Producto) it.next();
		listaNombreProducto[i]= (producto.getNombre() + " Algo$ " + producto.getPrecio());
		i++;
	}
	return listaNombreProducto;
}
	
}
