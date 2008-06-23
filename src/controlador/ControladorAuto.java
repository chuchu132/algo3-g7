package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modelo.Auto;

public class ControladorAuto implements KeyListener{
   /**
    * Up: apretada acelera suelta desacelera
    * Right: subeCambio
    * Left: bajaCambio
    * */
	private Auto auto;
    
	public ControladorAuto(Auto auto){
	this.auto = auto;	
	}
	
	public Auto getAutoControlado(){
		return auto;
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP : auto.acelerar(); break;
		case KeyEvent.VK_RIGHT : auto.subirCambio(); break;
		case KeyEvent.VK_LEFT : auto.bajarCambio(); break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP : auto.desacelerar(); break;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyCode()) {
		//case KeyEvent.VK_RIGHT : auto.subirCambio(); break;
		//case KeyEvent.VK_LEFT : auto.bajarCambio(); break;
		}
	}
	
	

}
