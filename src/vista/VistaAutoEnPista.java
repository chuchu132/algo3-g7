package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

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
private boolean velocidad = false;
private boolean estado = false;
private boolean combustible = false;
private boolean cambioActual = false;

public  VistaAutoEnPista(Escenario escenario, Auto auto) {
	         this.escenario = escenario;
	         this.auto= auto;
	         spriteCache = escenario.getSpriteCache();
	         fotogramaActual = 0;
	         String modelo = auto.getModelo();
	         spriteNames = new String[]{modelo + "/acelerando.jpg",modelo + "/frenando.jpg"};
	        }	        
	      
           private void actualizarFotograma(){
        	   if(auto.getEstado() == 1){
	    		   fotogramaActual = 0 ;
	    		}
	    	   else{
	    		   fotogramaActual = 1;
	    	   }
           }
	       
           private void mostrarVelocidad( Graphics h){
        	   Graphics2D g = (Graphics2D) h;
       		g.setPaint(Color.white);
       		g.setFont(new Font("Arial",Font.BOLD,18));
       		g.drawString("Velocidad " + Math.round(auto.getVelocidad()) ,Escenario.WIDTH - 150, 22);
       		
           }
           
           private void mostrarCambioActual( Graphics h){
        	   Graphics2D g = (Graphics2D) h;
          		g.setPaint(Color.white);
          		g.setFont(new Font("Arial",Font.BOLD,18));
          		g.drawString("Cambio Actual " + auto.getCambioActual(),Escenario.WIDTH - 150,  44);
           }
           
           private void mostrarEstado(Graphics h){
        	   Graphics2D g = (Graphics2D) h;
          		g.setPaint(Color.white);
          		g.setFont(new Font("Arial",Font.BOLD,18));
          		DecimalFormat porcentage = new DecimalFormat("0.00");
          		g.drawString("Estado " + porcentage.format(auto.getPorcentageVidaUtilGeneral()*100) + " %",5, Escenario.ZONA_INFO -22);  
        	   
           }
         
           private void mostrarCombustible(Graphics h){
        	   Graphics2D g = (Graphics2D) h;
         		g.setPaint(Color.white);
         		g.setFont(new Font("Arial",Font.BOLD,18));
         		DecimalFormat cantidad = new DecimalFormat("0.00");
         		g.drawString("Combustible " + cantidad.format(auto.cantidadCombustible()) + " L",5, Escenario.ZONA_INFO);  
           }
           
           
           public void pintar(Graphics g){
        	 if(velocidad){
        	 mostrarVelocidad(g);}
        	 if(estado){
        		 mostrarEstado(g);}
        	 if(combustible){
        	   mostrarCombustible(g);
        		 }
        	 if(cambioActual){
        		 mostrarCambioActual(g);
        	 }
        	         	 
        	 actualizarFotograma(); 
	    	 g.drawImage( spriteCache.getSprite(spriteNames[fotogramaActual]), MANO_DERECHA ,ABAJO, escenario );
        	 } 
	       
	       
	       public int getX()  { return x; }
	       public void setX(int i) { x = i; }
	     
	       public int getY() { return y; }
	       public void setY(int i) { y = i; }

		
		public void setVelocidad(boolean velocidad) {
			this.velocidad = velocidad;
		}
		
		public void setEstado(boolean estado) {
			this.estado = estado;
		}
		
		public void setCombustible(boolean combustible) {
			this.combustible = combustible;
		}

		public boolean isVelocidad() {
			return velocidad;
		}

		public boolean isEstado() {
			return estado;
		}

		public boolean isCombustible() {
			return combustible;
		}

		public boolean isCambioActual() {
			return cambioActual;
		}

		public void setCambioActual(boolean cambioActual) {
			this.cambioActual = cambioActual;
		}
	       
	       
}


