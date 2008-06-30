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
import Modelo.Juego;
import Modelo.Pista;

import Recursos.SoundCache;




public class VistaCarrera extends Canvas implements Observer{
    private VistaPista vistaPista;
    private VistaAutoEnPista vistaAutoPrincipal;
      
    private SoundCache soundCache;
	private BufferedImage buffer;
	private Auto principal;
 
	
	
public VistaCarrera(ControladorAuto controlador1,Escenario escenario,Carrera picada){
	
	principal = controlador1.getAutoControlado();
	vistaAutoPrincipal = new VistaAutoEnPista(escenario,principal, picada);
	vistaPista = new VistaPista(escenario,Juego.getPista());
	soundCache = new SoundCache();	     
	picada.addObserver(this);
	principal.addObserver(this);
	
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
