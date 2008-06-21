package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Modelo.Auto;
import Recursos.SpriteCache;



public class VistaAutoEnPista {

protected int x,y;

protected String[] spriteNames;

protected int fotogramaActual;


protected Auto auto;
protected Escenario escenario;
protected SpriteCache spriteCache;
 
public  VistaAutoEnPista(Escenario escenario, Auto auto) {
	         this.escenario = escenario;
	         spriteCache = escenario.getSpriteCache();
	         fotogramaActual = 0;
	         String modelo = auto.getModelo();
	         spriteNames[0] = ( modelo + "/acelerando.jpg");
	         spriteNames[1] = ( modelo + "/frenando.jpg");
	        }	        
	    
	       
	       public void paint(Graphics g){
	    	   if(auto.getEstado() == 1){
	    		   fotogramaActual = 1 ;
	    		}
	    	   else{
	    		   fotogramaActual = 0;
	    	   }
	         g.drawImage( spriteCache.getSprite(spriteNames[fotogramaActual]), x,y, escenario );
	       }
	       
	       public int getX()  { return x; }
	       public void setX(int i) { x = i; }
	     
	       public int getY() { return y; }
	       public void setY(int i) { y = i; }
	       
}


