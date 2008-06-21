package vista;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modelo.Auto;
import Modelo.Pista;

import Recursos.SoundCache;
import Recursos.SpriteCache;



public class VistaCarrera extends Canvas {

	private SpriteCache spriteCache;
	private SoundCache soundCache;
	private BufferedImage buffer;
	private ArrayList<Auto> cooredores;
	private Pista pista;
	
public VistaCarrera(ArrayList<Auto> listaCorredores,Pista unaPista){
	soundCache = new SoundCache();	     
	spriteCache = new SpriteCache();
	buffer = new BufferedImage(Escenario.WIDTH,Escenario.HEIGHT, BufferedImage.TYPE_INT_RGB);
	JFrame ventana = new JFrame("Autitos");
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

}

}
