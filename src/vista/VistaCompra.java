package vista;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
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

import controlador.ControladorCompraAuto;
import controlador.ControladorCompraAutoparte;
import controlador.ControladorCompraNafta;

import Modelo.Jugador;
import Modelo.Producto;
import Modelo.Vendedor;

public class VistaCompra extends JDialog implements Observer {
	private final int CANTIDAD_PANELES = 3;
	private final int FILAS_LISTA = 5;
	private final int ALTO_VENTANA = 480;
	private final int ANCHO_VENTANA = 640;
	
	private final int ANCHO_LISTA = 200;
	private Jugador jugador;
	private JLabel labelDinero;
	
	public VistaCompra(Jugador jugador){
		this.jugador = jugador;
		Container contenedor = getContentPane();
		JPanel panelDePaneles = new JPanel();
		JPanel panelSuperior = new JPanel();
		JPanel panelInferior = new JPanel();
		
		jugador.addObserver(this);
		labelDinero = new JLabel();
		
		panelDePaneles.setLayout(new BorderLayout());
		panelSuperior.setLayout(new BorderLayout());
		panelInferior.setLayout(new GridLayout(CANTIDAD_PANELES,1));
		
		//opciones ventana principal.
		setLabelPlata(labelDinero);
		panelSuperior.add(labelDinero,BorderLayout.CENTER);
		panelInferior.add(panelAutos());
		panelInferior.add(panelAutopartes());
		panelInferior.add(panelNafta());
		
		panelDePaneles.add(panelSuperior,BorderLayout.NORTH);
		panelDePaneles.add(panelInferior,BorderLayout.CENTER);
		
		contenedor.add(panelDePaneles);
		
	    setTitle("COMPRA");
		setModal(true);
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
	}
	
	
	private  JPanel panelAutos(){
		JList listaAutosVendedor;
		JList listaAutosComprador;
		JButton botonCompraAuto;
		JPanel panelAutos = new JPanel();
		JPanel panelAutosSuperior = new JPanel();
		JPanel panelAutosInferior = new JPanel();
		JLabel etiqueta = new JLabel("  Seleccione un Auto: ");
		
		panelAutos.setLayout(new BorderLayout());
		panelAutosInferior.setLayout(new FlowLayout());
		panelAutosSuperior.setLayout(new BorderLayout());
		
		panelAutosSuperior.add(etiqueta,BorderLayout.WEST);
		
		
		listaAutosVendedor = new JList(getListaDe(Vendedor.AUTOS));
		listaAutosVendedor.setVisibleRowCount(FILAS_LISTA);
		listaAutosVendedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		listaAutosVendedor.setFixedCellWidth(ANCHO_LISTA);
		panelAutosInferior.add(new JScrollPane(listaAutosVendedor));
		
		listaAutosComprador = new JList();
		listaAutosComprador.setVisibleRowCount(FILAS_LISTA);
		listaAutosComprador.setFixedCellWidth(ANCHO_LISTA);
		

		botonCompraAuto = new JButton("Comprar");
		botonCompraAuto.addActionListener(new ControladorCompraAuto(listaAutosVendedor,jugador, listaAutosComprador));
		
		panelAutosInferior.add(botonCompraAuto);
		panelAutosInferior.add(new JScrollPane(listaAutosComprador));
		
		panelAutos.add(panelAutosSuperior,BorderLayout.NORTH);
		panelAutos.add(panelAutosInferior,BorderLayout.SOUTH);
		
		return panelAutos;
		
	}
	
	private JPanel panelAutopartes(){
		 JList listaAutoparteVendedor;
		 JList listaAutoparteComprador;
		 JButton botonCompraAutoparte;
		 JPanel panelAutopartes = new JPanel();
		 JPanel panelAutopartesSuperior = new JPanel();
		 JPanel panelAutopartesInferior = new JPanel();
		 JLabel etiqueta = new JLabel("  Seleccione una Autoparte: ");
		
		panelAutopartes.setLayout(new BorderLayout());
		panelAutopartesSuperior.setLayout(new BorderLayout());
		panelAutopartesSuperior.add(etiqueta,BorderLayout.WEST);
		panelAutopartesInferior.setLayout(new FlowLayout());
				
		listaAutoparteVendedor = new JList(getListaDe(Vendedor.AUTOPARTES));
		listaAutoparteVendedor.setVisibleRowCount(FILAS_LISTA);
		listaAutoparteVendedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listaAutoparteVendedor.setFixedCellWidth(ANCHO_LISTA + 80);
		
		
		
		listaAutoparteComprador = new JList();
		listaAutoparteComprador.setVisibleRowCount(FILAS_LISTA);
		listaAutoparteComprador.setFixedCellWidth(ANCHO_LISTA);
		
		botonCompraAutoparte = new JButton("Comprar");
		botonCompraAutoparte.addActionListener(new ControladorCompraAutoparte(listaAutoparteVendedor,jugador, listaAutoparteComprador));
		
		panelAutopartesInferior.add(new JScrollPane(listaAutoparteVendedor));
		panelAutopartesInferior.add(botonCompraAutoparte);
		panelAutopartesInferior.add(new JScrollPane(listaAutoparteComprador));
		panelAutopartes.add(panelAutopartesSuperior,BorderLayout.NORTH);
		panelAutopartes.add(panelAutopartesInferior,BorderLayout.SOUTH);
		
		return panelAutopartes;
	}

	private JPanel panelNafta(){
		JList listaNaftaVendedor;
		JList listaNaftaComprador;
		JButton botonCompraNafta;
		JPanel panelNafta = new JPanel();
		JPanel panelNaftaSuperior = new JPanel();
		JPanel panelnaftaInferior = new JPanel();
		JLabel etiqueta = new JLabel("  Seleccione un Tipo de Nafta: ");
		
		panelNafta.setLayout(new BorderLayout());
		panelNaftaSuperior.setLayout(new BorderLayout());
		panelNaftaSuperior.add(etiqueta,BorderLayout.WEST);
		
		panelnaftaInferior.setLayout(new FlowLayout());
		
		listaNaftaVendedor = new JList(getListaDe(Vendedor.NAFTAS));
		listaNaftaVendedor.setVisibleRowCount(FILAS_LISTA);
		listaNaftaVendedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listaNaftaVendedor.setFixedCellWidth(ANCHO_LISTA);
		panelnaftaInferior.add(new JScrollPane(listaNaftaVendedor));
		
		listaNaftaComprador = new JList();
		listaNaftaComprador.setVisibleRowCount(FILAS_LISTA);
		listaNaftaComprador.setFixedCellWidth(ANCHO_LISTA);
		
		botonCompraNafta = new JButton("Comprar");
		botonCompraNafta.addActionListener(new ControladorCompraNafta(listaNaftaVendedor,jugador, listaNaftaComprador));
		
		panelnaftaInferior.add(botonCompraNafta);
		panelnaftaInferior.add(new JScrollPane(listaNaftaComprador));
		
		panelNafta.add(panelNaftaSuperior,BorderLayout.NORTH);
		panelNafta.add(panelnaftaInferior,BorderLayout.SOUTH);
		
		return panelNafta;
	}
	
	private void  setLabelPlata(JLabel etiquetaDinero){
	
		DecimalFormat formatoPlata = new DecimalFormat("0.00");
		etiquetaDinero.setText("Algo$ " + formatoPlata.format(jugador.getPlata()));
		etiquetaDinero.setFont(new Font("Monospaced",Font.BOLD,30));
		etiquetaDinero.setVerticalAlignment(JLabel.CENTER);
		etiquetaDinero.setHorizontalAlignment(JLabel.CENTER);
				
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



public void update(Observable o, Object arg) {
	setLabelPlata(this.labelDinero);
	
}
	
}
