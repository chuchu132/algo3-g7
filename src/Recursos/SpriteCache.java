package Recursos;

import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SpriteCache extends ResourceCache{
	
	
	protected Object loadResource(URL url) {
		 try{
			  return ImageIO.read(url );
		 } catch (Exception e) {
			 System.out.println("No se pudo cargar la imagen de " +url );
			           System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
			           System.exit(0);
			           return null;
		 }
		 
	 }
	 
	 public BufferedImage getSprite(String nombre) {
		         return (BufferedImage) getResource(nombre);
		       }
	 
	
}