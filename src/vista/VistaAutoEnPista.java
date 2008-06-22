package vista;

import java.awt.Graphics;


import Modelo.Auto;
import Recursos.SpriteCache;



public class VistaAutoEnPista {

private int x,y;
private String[] spriteNames;
private int fotogramaActual;
private final int MANO_DERECHA = 430;
private final int MANO_IZQUIERDA =240;
private final int ABAJO = 450;
private Auto auto;
private Escenario escenario;
private SpriteCache spriteCache;
 
public  VistaAutoEnPista(Escenario escenario, Auto auto) {
	         this.escenario = escenario;
	         this.auto= auto;
	         spriteCache = escenario.getSpriteCache();
	         fotogramaActual = 0;
	         String modelo = auto.getModelo();
	         spriteNames = new String[]{modelo + "/acelerando.jpg",modelo + "/frenando.jpg"};
	        }	        
	    
	       
	       public void pintar(Graphics g){
	    	   if(auto.getEstado() == 1){
	    		   fotogramaActual = 0 ;
	    		}
	    	   else{
	    		   fotogramaActual = 0;
	    	   }
	         g.drawImage( spriteCache.getSprite(spriteNames[fotogramaActual]), MANO_DERECHA ,ABAJO, escenario );
	         
	       }
	       
	       public int getX()  { return x; }
	       public void setX(int i) { x = i; }
	     
	       public int getY() { return y; }
	       public void setY(int i) { y = i; }
	       
}


