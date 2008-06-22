package vista;

import java.awt.Graphics;

import Modelo.Pista;
import Recursos.SpriteCache;

public class VistaPista {
	private final int FOTOGRAMAS = 3;
	private String[] spriteNames;
	private int fotogramaActual;
	private Escenario escenario;
	private SpriteCache spriteCache;
	private int  velocidadFotograma;
	private int t;
	
	
	 public VistaPista(Escenario escenario, Pista pista){
		 String dirFondo;
		 
		 this.escenario = escenario;
         spriteCache = escenario.getSpriteCache();
         fotogramaActual = 0;
         velocidadFotograma = 1;
         t=0;
         spriteNames = new String[FOTOGRAMAS];
		 if(pista.getCoeficienteAgarre() < 0.33){
				dirFondo = "nieve/";
				}
			else{
				if(pista.getCoeficienteAgarre()< 0.66){
					dirFondo = "lluvia/";
				}
				else{
					dirFondo = "despejado/";
				}
			}
		 for(int i=0; i < FOTOGRAMAS; i++){
			spriteNames[i] = (dirFondo + "pista" + i + ".jpg"); 
		 }
		 
	 }
	 /**
	  * revisar no se bien como funciona
	  * 
	  * */
	 private void actualizarFotograma(){
		    t++;
		if(velocidadFotograma !=0){
		    if (t % velocidadFotograma == 0){
			    t=0;
			    fotogramaActual = (fotogramaActual + 1) % spriteNames.length;
		       }
		}
		
	}
	 
	 
	 public void setVelocidadFotograma(int vel){
		 velocidadFotograma = vel;		 
	 }
	 
	 public void pintar(Graphics g){
		 actualizarFotograma();
		 g.drawImage( spriteCache.getSprite(spriteNames[fotogramaActual]),0,0, escenario );
	 }
	 

}
