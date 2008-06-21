package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Recursos.SpriteCache;



public class VistaAuto {

protected int x,y;
protected int width, height;
protected String[] spriteNames;

protected int currentFrame;

protected int frameSpeed;
protected int t;

protected Escenario escenario;
protected SpriteCache spriteCache;
 
public  VistaAuto(Escenario escenario) {
	         this.escenario = escenario;
	         spriteCache = escenario.getSpriteCache();
	         currentFrame = 0;
	         frameSpeed = 1;
	         t=0; 
}
	       
	       public void paint(Graphics g){
	         g.drawImage( spriteCache.getSprite(spriteNames[currentFrame]), x,y, escenario );
	       }
	       
	       public int getX()  { return x; }
	       public void setX(int i) { x = i; }
	     
	       public int getY() { return y; }
	       public void setY(int i) { y = i; }
	       
	       public int getFrameSpeed() {return frameSpeed;  }
	       public void setFrameSpeed(int i) {frameSpeed = i; }
	       
	       public void setSpriteNames(String[] names) { 
	         spriteNames = names;
	         height =0;
	         width =0;
	         for (int i = 0; i < names.length; i++ ) {
	         BufferedImage image = spriteCache.getSprite(spriteNames[i]);
	         height = image.getHeight();
	         width = image.getWidth();
	       }     
	      }
public int getHeight() { return height; }
public int getWidth() { return width; }
public void setHeight(int i) {height = i; }
public void setWidth(int i) { width = i;  }
public void act() {
  t++;
 if (t % frameSpeed == 0){
   t=0;
   currentFrame = (currentFrame + 1) % spriteNames.length;
  }
}
}


