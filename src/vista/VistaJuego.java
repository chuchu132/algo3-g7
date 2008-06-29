package vista;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Jugador;
import Recursos.SpriteCache;

public class VistaJuego extends JFrame{
	private final int CANTIDAD_BOTONES = 3;
	private final int ANCHO_VENTANA = 640;
	private final int ALTO_VENTANA = 480;
	
	private JButton botonNuevo;
	private JButton botonCargar;
	private JButton botonSalir;
	private JPanel panelBotones;
	private JPanel panelPrincipal;
	private JLabel imagenFondo ;
	
	
	public VistaJuego(){
		panelBotones = new JPanel();
		panelPrincipal = new JPanel();
		imagenFondo = new JLabel();
		setImagenFondo();
		
		botonNuevo = new JButton("Juego Nuevo");
		
		botonCargar = new JButton("Cargar Juego");
		
		botonSalir = new JButton("Salir");
		

		
		panelBotones.add(botonNuevo);
		panelBotones.add(botonCargar);
		panelBotones.add(botonSalir);
		
		panelPrincipal.setLayout(new BorderLayout());
		
		panelPrincipal.add(panelBotones,BorderLayout.SOUTH);
		panelPrincipal.add(imagenFondo,BorderLayout.NORTH);
		
		getContentPane().add(panelPrincipal,BorderLayout.CENTER);
		
		setTitle("Car Tunning Experience '08");
		
		setBounds(0, 0, ANCHO_VENTANA,ALTO_VENTANA);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

private void setImagenFondo(){
	SpriteCache cargadorImagen = new SpriteCache();
	ImageIcon imagen;
	imagen = new ImageIcon(cargadorImagen.getSprite("main.jpg"));
	imagenFondo.setIcon(imagen);
}

}
