package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Modelo.Auto;
import Recursos.SpriteCache;



public class VistaAutoEnPista {

protected int x,y;

protected String[] spriteNames;

protected int currentFrame;

protected int frameSpeed;
protected int t;
protected Auto auto;
protected Escenario escenario;
protected SpriteCache spriteCache;
 
public  VistaAutoEnPista(Escenario escenario, Auto auto) {
	         this.escenario = escenario;
	         spriteCache = escenario.getSpriteCache();
	         currentFrame = 0;
	        }	        
	    
	       
	       public void paint(Graphics g){
	         g.drawImage( spriteCache.getSprite(spriteNames[currentFrame]), x,y, escenario );
	       }
	       
	       public int getX()  { return x; }
	       public void setX(int i) { x = i; }
	     
	       public int getY() { return y; }
	       public void setY(int i) { y = i; }
	       
	       	       
	       public void setSpriteNames(String[] names) { 
	         spriteNames = names;
	        
	         for (int i = 0; i < names.length; i++ ) {
	         BufferedImage image = spriteCache.getSprite(spriteNames[i]);
	         
	       }     
	      }

}


