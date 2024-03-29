package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


//import controlador.ControladorBotonCambiarAuto;
import controlador.ControladorBotonAbandonar;
import controlador.ControladorBotonCambiarAutoparte;
import controlador.ControladorBotonCompra;
import controlador.ControladorBotonElegirAuto;
import controlador.ControladorBotonVerPista;
import controlador.ControladorCarrera;
import controlador.ControladorGuardarJuego;


import Modelo.Juego;
import Modelo.Jugador;
import Recursos.SpriteCache;

public class VistaTaller extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
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

	public VistaTaller (Juego juego) {

		ControladorVistaTaller controladorVistaTaller = new ControladorVistaTaller();

		propietario =  juego.getJugador();
		propietario.addObserver(controladorVistaTaller);
		propietario.getTaller().addObserver(controladorVistaTaller);
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
		botonVerPista.addActionListener (new ControladorBotonVerPista (juego));
		panelBotones.add(botonVerPista);

		botonCarrera = new JButton("Correr");
		panelBotones.add(botonCarrera);
		botonCarrera.addActionListener(new ControladorCarrera(juego,this));
		botonGuardar = new JButton("Guardar Juego");
		panelBotones.add(botonGuardar);
		botonGuardar.addActionListener(new ControladorGuardarJuego(juego));
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
		chequearGameOver();
	}

	private void chequearGameOver(){
		if(propietario.getPlata() < 50.0){
			JOptionPane.showMessageDialog(null, "No te alcanza la plata para seguir corriendo.", "FIN DEL JUEGO", JOptionPane.ERROR_MESSAGE);
			new VistaJuego();
			this.dispose();
		}
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
