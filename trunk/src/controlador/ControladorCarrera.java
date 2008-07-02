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


import Excepciones.MotorFundidoException;
import Excepciones.NoAlcanzaDineroException;
import Excepciones.ProblemaTecnicoException;
import Excepciones.RuedaRotaException;
import Excepciones.TanqueVacioException;
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
	private VistaCarrera vistacarrera;
	private Carrera picada;
	private Auto miAuto;
    private double apuesta;
	
	public ControladorCarrera(Jugador jugador, VistaTaller vista) {
		this.soundCache= new SoundCache();
		this.spriteCache = new SpriteCache();
		this.apuesta = 0.0;
		this.jugador = jugador;
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent arg0) {
		miAuto	= jugador.getTaller().getAutoActual();
		vista.dispose();
		
	  if(miAuto != null){
		
		if(apostar()){
			
				ArrayList<Auto> competidores = new ArrayList<Auto>();
				competidores.add(new AutoPc());
				ControladorAuto controladorAuto = new ControladorAuto(miAuto); 
				picada = new Carrera(Juego.getPista(),competidores,miAuto,apuesta);
				
				vistacarrera= new VistaCarrera(controladorAuto,this,picada);
				try{
					Thread hiloCarrera = new Thread(picada);
					picada.setControlador(this);
					hiloCarrera.start();
					/**
					 * Esto en teoria anda.. pero en la practica no :(
					 *  lo q hace es armar toda la carrera crea r la vista
					 *  q se va a actualizar cada vez q simulen todos los autos
					 *  y en otro hilo // se esta corriendo la carrera (se esta ejecutando el ciclo de simular)
					 *  
					 *  Lo problemas q encontre y no pude resolver:
					 *  1) La pantalla qda mal no escucha teclado ni se pinta bien.
					 *  2) Las excepciones q tira picada.run() (Heredan todas de RuntimeException xq sino no podia usar la interfaz  Runnable)
					 *    no las cachan los catchs.. es como q explotan en otro lado y no se enteran.
					 * */
					/*
					while(hiloCarrera.isAlive()){
						
					}
				   vistaCarrera.eliminarVentana();

					if(picada.ganador() == miAuto){
						double premio = picada.getPremio();
						jugador.sumarDinero(premio);
						JOptionPane.showMessageDialog(null, "Felicitaciones ganaste: Algo$ " + premio, "GANADOR",JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Esta vez perdiste, volve a intentarlo.", "PERDEDOR",JOptionPane.ERROR_MESSAGE);
					}*/
				}
				catch (MotorFundidoException mex){ lanzarCartel("Se fundio el motor.");}
				catch (RuedaRotaException rrex){ lanzarCartel("Se rompio una rueda.");}
				catch (TanqueVacioException tvex){ lanzarCartel("No tiene combustible.");}
				catch (ProblemaTecnicoException ptex) {lanzarCartel("Desperfecto Mecanico no Identificado.");}
			}
		else{
			JOptionPane.showMessageDialog(null,"La carrera fue suspendida.",null,JOptionPane.INFORMATION_MESSAGE);
			VistaTaller vistaTaller = new VistaTaller(jugador);
		}
	  }
	  else{
		  JOptionPane.showMessageDialog(null,"Para correr necesitas un auto.","SIN AUTO",JOptionPane.ERROR_MESSAGE);
		  VistaTaller vistaTaller = new VistaTaller(jugador);
	  }
	  
	}
	
	public void ganador()
	{
		vistacarrera.eliminarVentana();

		if(picada.ganador() == miAuto){
			double premio = picada.getPremio();
			jugador.sumarDinero(premio);
			JOptionPane.showMessageDialog(null, "Felicitaciones ganaste: Algo$ " + premio, "GANADOR",JOptionPane.INFORMATION_MESSAGE);
			
		}
		else{
			JOptionPane.showMessageDialog(null, "Esta vez perdiste, volve a intentarlo.", "PERDEDOR",JOptionPane.ERROR_MESSAGE);
		}
		Juego.generarPista();
		VistaTaller vistaTaller = new VistaTaller(jugador);	
	}
	
	public void perder(Exception ex)
	{
		if (ex instanceof MotorFundidoException){ lanzarCartel("Se fundio el motor.");}
		else if (ex instanceof RuedaRotaException){ lanzarCartel("Se rompio una rueda.");}
		else if (ex instanceof TanqueVacioException){ lanzarCartel("No tiene combustible.");}
		else if (ex instanceof ProblemaTecnicoException) {lanzarCartel("Desperfecto Mecanico no Identificado.");}
		vistacarrera.eliminarVentana();
		Juego.generarPista();
		VistaTaller vistaTaller = new VistaTaller(jugador);	

	}
	private void lanzarCartel(String problema){
		JOptionPane.showMessageDialog(null,"Debes abandonar la carrera. Problema: " + problema,"PROBLEMA TECNICO",JOptionPane.ERROR_MESSAGE);
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


	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
	
		return false;
	}

	
	
		 	
	
}
