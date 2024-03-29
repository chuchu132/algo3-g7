package vista;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controlador.ControladorAuto;

import Modelo.Auto;
import Modelo.Carrera;
import Modelo.Pista;



public class VistaCarrera extends Canvas implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	private VistaPista vistaPista;
	private VistaAutoEnPista vistaAutoPrincipal;
	private JDialog ventana;  
	private BufferedImage buffer;
	private Auto principal;



	public VistaCarrera(ControladorAuto controlador1,Escenario escenario,Carrera picada,Pista pista){

		principal = controlador1.getAutoControlado();
		vistaAutoPrincipal = new VistaAutoEnPista(escenario,principal, picada,pista);
		vistaPista = new VistaPista(escenario,pista);
		picada.addObserver(this);
		principal.addObserver(this);

		buffer = new BufferedImage(Escenario.WIDTH,Escenario.HEIGHT, BufferedImage.TYPE_INT_RGB);
		ventana = new JDialog();
		ventana.setTitle("CARRERA");
		crearMenu(ventana);	

		JPanel panel = (JPanel) ventana.getContentPane();
		setBounds(0,0,Escenario.WIDTH,Escenario.HEIGHT);
		panel.setPreferredSize(new Dimension(Escenario.WIDTH,Escenario.HEIGHT));
		panel.setLayout(null);
		panel.add(this);

		ventana.setBounds(0,0,Escenario.WIDTH,Escenario.HEIGHT);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		ventana.setResizable(false);
		ventana.setModal(true);
		addKeyListener(controlador1);

		mostrarCarrera();
		setFocusable(true);
	}

	private void pintarCarrera(){
		Graphics g = buffer.getGraphics();
		double vel = principal.getVelocidad()*3.6;
		if (vel==0)
			vistaPista.setVelocidadFotograma(0);
		if ((vel < 20)&&(vel>0))
			vistaPista.setVelocidadFotograma(8);
		if (( vel > 20)&&(vel<40))
			vistaPista.setVelocidadFotograma(6);
		if (( vel > 40)&&(vel<90))
			vistaPista.setVelocidadFotograma(4);
		if ((vel > 90)&& (vel<120))
			vistaPista.setVelocidadFotograma(3);
		if (vel>120)
			vistaPista.setVelocidadFotograma(2);


		vistaPista.pintar(g);
		vistaAutoPrincipal.pintar(g);


	}


	public void update(Observable arg0, Object arg1) {
		mostrarCarrera();

	}

	public synchronized void paint(Graphics g) {
		g.drawImage(buffer,0,0,this);
	}

	public void mostrarCarrera(){
		pintarCarrera();
		paint(getGraphics());

	}

	private void crearMenu(JDialog ventana){

		JMenuItem elementoVer = new JMenuItem("Opciones Vista");
		elementoVer.setMnemonic('V');
		elementoVer.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evento){
						new VistaOpcionesAutoEnCarrera(vistaAutoPrincipal);
					}
				});

		JMenuBar barra = new JMenuBar();
		ventana.setJMenuBar(barra);  
		barra.add(elementoVer);

	}

	public void eliminarVentana(){
		ventana.dispose();
	}

}
