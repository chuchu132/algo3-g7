package controlador;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Excepciones.NoAlcanzaDineroException;
import Modelo.Auto;
import Modelo.AutoPc;
import Modelo.Carrera;
import Modelo.Juego;
import Modelo.Jugador;
import Recursos.SoundCache;
import Recursos.SpriteCache;

import vista.Escenario;
import vista.VistaCarrera;
import vista.VistaTaller;

public class ControladorCarrera implements ActionListener, Escenario {
	
	private SoundCache soundCache;
	private SpriteCache spriteCache;
	private Jugador jugador;
	private VistaTaller vista;
    private double apuesta;
	
	public ControladorCarrera(Jugador jugador, VistaTaller vista) {
		this.soundCache= new SoundCache();
		this.spriteCache = new SpriteCache();
		this.apuesta = 0.0;
		this.jugador = jugador;
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent arg0) {
		Auto miAuto	= jugador.getTaller().getAutoActual();
		//vista.dispose();
	  if(miAuto != null){	
		if(apostar()){
				ArrayList<Auto> competidores = new ArrayList<Auto>();
				competidores.add(new AutoPc());
				Carrera picada = new Carrera(Juego.getPista(),competidores,miAuto,apuesta);
				VistaCarrera 
				if(picada.ganador() == miAuto){
					jugador.sumarDinero(picada.getPremio());
				}
				
				
			}
		else{
			JOptionPane.showMessageDialog(null,"La carrera fue suspendida.",null,JOptionPane.INFORMATION_MESSAGE);
		}
	  }
	  else{
		  JOptionPane.showMessageDialog(null,"Para correr necesitas un auto.","SIN AUTO",JOptionPane.ERROR_MESSAGE);
	  }
	}
	
	private boolean apostar(){
		double monto; 
		String temp =	JOptionPane.showInputDialog(new JLabel("Tienes Algo$ "+jugador.getPlata()), "Monto minimo Algo$ 50.0 :", "Ingrese su Apuesta",JOptionPane.QUESTION_MESSAGE);
		 if(temp!=null){
			try{
			 monto = Double.valueOf(temp);
			 	if(monto < 50.0){
			 		JOptionPane.showMessageDialog(null,"La apuesta minima es de Algo$ 50.0",null,JOptionPane.ERROR_MESSAGE);
			 		return apostar();
			 	}
			 	else{
			 		try{
				 		 jugador.restarDinero(monto);
				 		 this.apuesta = monto;
				 		 return true;
				 		}
			 			catch (NoAlcanzaDineroException ex){
			 				JOptionPane.showMessageDialog(null,"Ud. no posee tanto dinero.",null,JOptionPane.ERROR_MESSAGE);
			 				return apostar();
			 			}
			 		
			 		}	
				}	
				catch (NumberFormatException ex2){
					JOptionPane.showMessageDialog(null, "El valor ingresado no es un numero.","ERROR DE FORMATO", JOptionPane.ERROR_MESSAGE);
					return apostar();
					}
			 }
		 else {
			 return false;
		 	}
	}


	public SoundCache getSoundCache() {
		return soundCache;
	}


	public SpriteCache getSpriteCache() {
		return spriteCache;
	}

	
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
				return false;
	}
		 	
	
}
