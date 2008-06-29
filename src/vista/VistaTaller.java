package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//import controlador.ControladorBotonCambiarAuto;
import controlador.ControladorBotonAbandonar;
import controlador.ControladorBotonCambiarAutoparte;
import controlador.ControladorBotonCompra;
import controlador.ControladorBotonElegirAuto;


import Modelo.Jugador;
import Recursos.SpriteCache;

public class VistaTaller extends JFrame{
	private final int CANTIDAD_BOTONES = 7;
	private final int ANCHO_VENTANA = 640;
	private final int ALTO_VENTANA = 480;
	
	private JButton botonGuardar;
	private JButton botonAbandonar;
	private JButton botonCarrera;
	private JButton botonVerPista;
	private JButton botonElegirAuto;
	private JButton botonCambiarAutoparte;
	private JButton botonComprar;
	private JPanel panelBotones;
	private JPanel panelDerecho;
	private JLabel imagenAuto ;
	private Jugador propietario;
	private boolean yaCorrio = false;
	
	public VistaTaller (Jugador propietario) {
		
		ControladorVistaTaller controladorVistaTaller = new ControladorVistaTaller();
		
		this.propietario = propietario;
		this.propietario.addObserver(controladorVistaTaller);
		this.propietario.getTaller().addObserver(controladorVistaTaller);
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
		botonElegirAuto.addActionListener(new ControladorBotonElegirAuto(propietario));
		panelBotones.add(botonElegirAuto);
		
		botonVerPista = new JButton("Ver Pista");
		panelBotones.add(botonVerPista);
		
		botonCarrera = new JButton("Correr");
		panelBotones.add(botonCarrera);
		
		botonGuardar = new JButton("Guardar Juego");
		panelBotones.add(botonGuardar);
		
		botonAbandonar = new JButton("Abandonar Juego");
		panelBotones.add(botonAbandonar);
		botonAbandonar.addActionListener(new ControladorBotonAbandonar(this));

		/* panel derecho */
		panelDerecho = new JPanel();
		panelDerecho.setLayout(new BorderLayout());
		
		imagenAuto = new JLabel();
		panelDerecho.add(imagenAuto,BorderLayout.CENTER);
		panelDerecho.setBackground(Color.WHITE);
		setImagenAuto();		
		/* ubicacion de los paneles */
		panel.add(panelBotones,BorderLayout.WEST);
		panel.add(panelDerecho, BorderLayout.CENTER);
		
		/* seteos generales ed la ventana */
			setBounds(0, 0, ANCHO_VENTANA,ALTO_VENTANA);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}
	
	public void yaCorrio(boolean yaCorrio){
		
		this.yaCorrio = yaCorrio;
	}
	
	public void setImagenAuto(){
		SpriteCache cargadorImagen = new SpriteCache();
		ImageIcon imagen;
		try{
		String ruta = propietario.getTaller().getAutoActual().getModelo();
		imagen = new ImageIcon(cargadorImagen.getSprite(ruta + "/fondo.jpg"));
		imagenAuto.setIcon(imagen);
		}
		catch( NullPointerException e){
			imagen = new ImageIcon((Image) cargadorImagen.getSprite("nada.jpg"));
			imagenAuto.setIcon(imagen);
		}
		
		
	}
	
	private class ControladorVistaTaller implements Observer {

		
		public void update(Observable arg0, Object arg1) {
			setImagenAuto();

		}

	}
	
		
	
}
