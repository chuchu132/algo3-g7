package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.ControladorCompra;

import Modelo.Jugador;

public class VistaTaller extends JFrame{
	private final int CANTIDAD_BOTONES = 5;
	private final int ANCHO_VENTANA = 640;
	private final int ALTO_VENTANA = 480;
	
	private JButton botonCarrera;
	private JButton botonVerPista;
	private JButton botonCambiarAuto;
	private JButton botonCambiarAutoparte;
	private JButton botonComprar;
	private JPanel panelBotones;
	private Jugador propietario;
	
	public VistaTaller (Jugador propietario) {
		this.propietario = propietario;
		
		setTitle("TALLER");
		Container panel = getContentPane();
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(CANTIDAD_BOTONES,1));
		botonComprar = new JButton("Comprar");
		botonComprar.addActionListener(new ControladorCompra(propietario));
		
		panelBotones.add(botonComprar);
		
		
		botonCambiarAutoparte = new JButton("Cambiar Autoparte");
		botonCambiarAutoparte.setMnemonic('C');
		panelBotones.add(botonCambiarAutoparte);
		
		botonCambiarAuto = new JButton("Cambiar Auto");
		panelBotones.add(botonCambiarAuto);
		
		botonVerPista = new JButton("Ver Pista");
		panelBotones.add(botonVerPista);
		
		botonCarrera = new JButton(" Correr");
		panelBotones.add(botonCarrera);
		
		panel.add(panelBotones,BorderLayout.WEST);
		
			setBounds(0, 0, ANCHO_VENTANA,ALTO_VENTANA);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		Jugador ale = new Jugador();
		VistaTaller ventana = new VistaTaller(ale);
		
	}

}
