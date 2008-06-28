package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.ControladorBotonCambiarAuto;
import controlador.ControladorBotonCambiarAutoparte;
import controlador.ControladorBotonCompra;


import Modelo.Jugador;

public class VistaTaller extends JFrame{
	private final int CANTIDAD_BOTONES = 5;
	private final int ANCHO_VENTANA = 640;
	private final int ALTO_VENTANA = 480;
	
	private JButton botonCarrera;
	private JButton botonVerPista;
	private JButton botonElegirAuto;
	private JButton botonCambiarAutoparte;
	private JButton botonComprar;
	private JPanel panelBotones;
	private JLabel labelDinero;
	private JPanel panelDerecho;
	
	
	public VistaTaller (Jugador propietario) {
		
		/* titulo ventana */
		setTitle("TALLER");
		
		/* contenedor de la ventana general */
		Container panel = getContentPane();
		
		/* Botonera izquierda */
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(CANTIDAD_BOTONES,1));
		
		botonComprar = new JButton("Comprar");
		botonComprar.addActionListener(new ControladorBotonCompra(propietario));
		panelBotones.add(botonComprar);
		
		
		botonCambiarAutoparte = new JButton("Cambiar Autoparte");
		botonCambiarAutoparte.addActionListener(new ControladorBotonCambiarAutoparte(propietario));
		panelBotones.add(botonCambiarAutoparte);
		
		botonElegirAuto = new JButton("Elegir Auto");
	//	botonElegirAuto.addActionListener(new ControladorBotonCambiarAuto(propietario));
		panelBotones.add(botonElegirAuto);
		
		botonVerPista = new JButton("Ver Pista");
		panelBotones.add(botonVerPista);
		
		botonCarrera = new JButton("Correr");
		panelBotones.add(botonCarrera);

		/* panel derecho */
		panelDerecho = new JPanel();
		panelDerecho.setLayout(new GridLayout(5,1));

		
		labelDinero = new JLabel();

		labelDinero.setText("PLATA: " + propietario.getPlata() + " Algo$     ");

		labelDinero.setText("Algo$  " + propietario.getPlata()+ "  ");
		labelDinero.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDinero.setVerticalAlignment(SwingConstants.TOP);

		panelDerecho.add(labelDinero);
		
		/* ubicacion de los paneles */
		panel.add(panelBotones,BorderLayout.WEST);
		panel.add(panelDerecho, BorderLayout.EAST);
		
		/* seteos generales ed la ventana */
			setBounds(0, 0, ANCHO_VENTANA,ALTO_VENTANA);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}

		
	
	public static void main(String[] args) {
		Jugador ale = new Jugador();
		VistaTaller ventana = new VistaTaller(ale);
		 
		
	}

}
