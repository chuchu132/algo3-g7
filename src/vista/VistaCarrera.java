package vista;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controlador.ControladorAuto;

import Modelo.Auto;
import Modelo.Carrera;
import Modelo.Pista;

import Recursos.SoundCache;




public class VistaCarrera extends Canvas implements Observer{
    private VistaPista vistaPista;
    private VistaAutoEnPista vistaAutoPrincipal;
    private VistaAutoEnPista vistaAutoSecundario;
   
    private SoundCache soundCache;
	private BufferedImage buffer;
	private Auto principal,secundario;
 
	
	
public VistaCarrera(ControladorAuto controlador1, ControladorAuto controlador2,Pista unaPista, Escenario escenario,Carrera picada){
	
	principal = controlador1.getAutoControlado();
	secundario = controlador2.getAutoControlado();
	vistaAutoPrincipal = new VistaAutoEnPista(escenario,principal);
	vistaAutoSecundario = new VistaAutoEnPista(escenario,secundario);
	vistaPista = new VistaPista(escenario,unaPista);
	soundCache = new SoundCache();	     
	
	
	buffer = new BufferedImage(Escenario.WIDTH,Escenario.HEIGHT, BufferedImage.TYPE_INT_RGB);
	JFrame ventana = new JFrame("CARRERA");
	
	crearMenu(ventana);	
	
	JPanel panel = (JPanel) ventana.getContentPane();
	setBounds(0,0,Escenario.WIDTH,Escenario.HEIGHT);
	panel.setPreferredSize(new Dimension(Escenario.WIDTH,Escenario.HEIGHT));
	panel.setLayout(null);
	panel.add(this);
	
	ventana.setBounds(0,0,Escenario.WIDTH,Escenario.HEIGHT);
	ventana.setVisible(true);
	ventana.addWindowListener( new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	});
    
	ventana.setResizable(false);
	setFocusable(true);
    addKeyListener(controlador1);

    mostrarCarrera();
	
}

private void pintarCarrera(){
	Graphics g = buffer.getGraphics();
	vistaPista.setVelocidadFotograma((int)Math.round(principal.getVelocidad()));
    vistaPista.pintar(g);
    vistaAutoPrincipal.pintar(g);
    	
}

@Override
public void update(Observable arg0, Object arg1) {
	mostrarCarrera();
	
}

public void paint(Graphics g) {
    g.drawImage(buffer,0,0,this);
	     }

public void mostrarCarrera(){
	pintarCarrera();
	paint(getGraphics());
	
}

private void crearMenu(JFrame ventana){
	
	JMenuItem elementoVer = new JMenuItem("Opciones Vista");
    elementoVer.setMnemonic('V');
    elementoVer.addActionListener(
    		new ActionListener(){
    			public void actionPerformed(ActionEvent evento){
    			VistaOpcionesAutoEnCarrera	vistaOpcionesAutoPrincipal = new VistaOpcionesAutoEnCarrera(vistaAutoPrincipal);
    			}
    		});
	
    JMenuBar barra = new JMenuBar();
	ventana.setJMenuBar(barra);  
	barra.add(elementoVer);
	
}

}
