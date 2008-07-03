package controlador;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


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
	private Juego juego;

	public ControladorCarrera(Juego juego, VistaTaller vista) {
		this.juego = juego;
		this.soundCache= new SoundCache();
		this.spriteCache = new SpriteCache();
		this.apuesta = 0.0;
		this.jugador = juego.getJugador();
		this.vista = vista;
	}

	/**
	 * El metodo actionPerformed(ActionEvent arg0) es llamado por el clic
	 * en el boton de la vista taller y se encarga de ir llamando a los distintos 
	 * metodos que controlan la carrera.
	 * 
	 * La secuencia es: apostar-----> Correr---> Ganador---->Cobrar---> Salir
	 *                          \            \ 
	 *                            --> Salir    --> Perdedor-----> Salir
	 * 
	 * */

	public void actionPerformed(ActionEvent arg0) {
		miAuto	= jugador.getTaller().getAutoActual();
		vista.dispose();

		if(miAuto != null){

			if(apostar()){

				ArrayList<Auto> competidores = new ArrayList<Auto>();
				competidores.add(new AutoPc());
				ControladorAuto controladorAuto = new ControladorAuto(miAuto); 
				picada = new Carrera(juego.getPista(),competidores,miAuto,apuesta);

				vistacarrera= new VistaCarrera(controladorAuto,this,picada,juego.getPista());

				Thread hiloCarrera = new Thread(picada);
				picada.setControlador(this);
				hiloCarrera.start();

			}
			else{
				JOptionPane.showMessageDialog(null,"La carrera fue suspendida.",null,JOptionPane.INFORMATION_MESSAGE);
				new VistaTaller(juego);
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Para correr necesitas un auto.","SIN AUTO",JOptionPane.ERROR_MESSAGE);
			new VistaTaller(juego);
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
		juego.generarPista();
		new VistaTaller(juego);	
	}

	public void perder(Exception ex)
	{
		if (ex instanceof MotorFundidoException){ lanzarCartel("Se fundio el motor.");}
		else if (ex instanceof RuedaRotaException){ lanzarCartel("Se rompio una rueda.");}
		else if (ex instanceof TanqueVacioException){ lanzarCartel("No tiene combustible.");}
		else if (ex instanceof ProblemaTecnicoException) {lanzarCartel("Desperfecto Mecanico no Identificado.");}
		vistacarrera.eliminarVentana();
		juego.generarPista();
		new VistaTaller(juego);	

	}
	private void lanzarCartel(String problema){
		JOptionPane.showMessageDialog(null,"Debes abandonar la carrera. Problema: " + problema,"PROBLEMA TECNICO",JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Este metodo se encarga de cobrar la apuesta al jugador. Si el jugador cuenta con dinero
	 * se le toma la apuesta y puede correr sino vuelve al taller. 
	 * */

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
