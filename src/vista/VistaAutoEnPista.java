package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import Modelo.Auto;
import Modelo.Carrera;
import Modelo.Juego;
import Recursos.SpriteCache;



public class VistaAutoEnPista {

private int x,y;
private String[] spriteNames;
private int fotogramaActual;
private final int CENTRO = 290;

private final int ABAJO = 380;
private Auto auto;
private Escenario escenario;
private SpriteCache spriteCache;
private boolean velocidad = true;
private boolean estado = true;
private boolean combustible = true;
private boolean cambioActual = true;
private boolean tiempo = true;
private boolean posicion = true;
private boolean mostrarAutos = true;
private Carrera carrera;

public  VistaAutoEnPista(Escenario escenario, Auto auto, Carrera carrera) {
	         this.escenario = escenario;
	         this.auto= auto;
	         spriteCache = escenario.getSpriteCache();
	         fotogramaActual = 0;
	         String modelo = auto.getModelo();
	         spriteNames = new String[]{modelo + "/acelerando.jpg",modelo + "/frenando.jpg"};
	         this.carrera=carrera;
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
       		g.drawString("Velocidad " + Math.round(auto.getVelocidad()*3.6)+ " Km/h" ,Escenario.WIDTH - 180, 22);
       		
           }
           
           private void mostrarCambioActual( Graphics h){
        	   Graphics2D g = (Graphics2D) h;
          		g.setPaint(Color.white);
          		g.setFont(new Font("Arial",Font.BOLD,18));
          		g.drawString("Cambio Actual " + auto.getCambioActual(),Escenario.WIDTH - 180,  44);
           }
           
           private void mostrarEstado(Graphics h){
        	   Graphics2D g = (Graphics2D) h;
          		g.setPaint(Color.white);
          		g.setFont(new Font("Arial",Font.BOLD,18));
          		DecimalFormat porcentage = new DecimalFormat("0.00");
          		g.drawString("Estado Gral: " + porcentage.format(auto.getPorcentageVidaUtilGeneral()*100) + " %",5, Escenario.ZONA_INFO -44);  
          		g.drawString("Estado Motor: " + porcentage.format(auto.getPorcentageVidaUtilMotor()*100) + " %",5, Escenario.ZONA_INFO -22); 
           }
         
           private void mostrarCombustible(Graphics h){
        	   Graphics2D g = (Graphics2D) h;
         		g.setPaint(Color.white);
         		g.setFont(new Font("Arial",Font.BOLD,18));
         		DecimalFormat cantidad = new DecimalFormat("0.00");
         		g.drawString("Combustible " + cantidad.format(auto.cantidadCombustible()) + " L",5, Escenario.ZONA_INFO);  
           }
           
           private void mostrarTiempo (Graphics h){
        	   
        	   Graphics2D g = (Graphics2D) h;
        	   g.setPaint(Color.white);
        	   g.setFont(new Font ("Arial", Font.BOLD, 18));
        	   DecimalFormat segundos = new DecimalFormat ("0.00");
        	   g.drawString("Tiempo : " + segundos.format(carrera.getTiempo()) +" seg", 10, 30);
           }
           
           private void mostrarPosicion (Graphics h){
        	   Graphics2D g = (Graphics2D) h;
        	   g.setPaint(Color.white);
        	   g.setFont(new Font ("Arial", Font.BOLD, 18));
        	   DecimalFormat posicion = new DecimalFormat ("0.00");
        	   g.drawString("Posicion : " + posicion.format(carrera.getPosJugador()) +" mts", 10, 60);
           }
           
           private void mostrarOponente (Graphics h){
        	   Graphics2D g = (Graphics2D) h;
        	   g.setPaint(Color.black);
        	   g.fillRect(Escenario.WIDTH - 30, 155, 10, 100);
        	   g.fillRect(Escenario.WIDTH - 75, 155, 10, 100);
        	   
           }
           
           private void mostrarJugadores (Graphics h){
        	   Graphics2D g = (Graphics2D) h;
        	   g.setPaint(Color.red);
        	   float longitudPista =(float) Juego.getPista().getLongitud();
        	   float pos1 = (float)carrera.getPosJugador();
        	   float posImagen1 = 243 - (88*pos1)/longitudPista;
        	   float pos2 = (float)carrera.getPosOponente();
        	   float posImagen2 =  243 -(88*pos2)/longitudPista;
        	   g.fillOval(Escenario.WIDTH - 75, Math.round(posImagen1), 10, 10);
        	   g.fillOval(Escenario.WIDTH - 30, Math.round(posImagen2), 10, 10);
           }
           
           private void mostrarNombres (Graphics h){
        	   Graphics2D g = (Graphics2D) h;
        	   g.setColor(Color.white);
        	   g.setFont(new Font ("Arial", Font.BOLD, 10));
        	   g.drawString("Jug 1     Com", Escenario.WIDTH - 80, 150);
           }
           
           private void cuentaRegresiva (Graphics h){
        	   Graphics2D g = (Graphics2D) h;
        	   g.setColor(Color.red);
        	   g.setFont(new Font ("Arial", Font.BOLD, 40));
        	   
        	   double dato = carrera.getCounter();
        	   DecimalFormat numero = new DecimalFormat ("0");
        	   if ((dato>0)&&(dato<3))
        	   g.drawString(numero.format(dato),350, 155);
        	   
           }
           
           
           public void pintar(Graphics g){
        	 cuentaRegresiva(g);
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
        	 if (tiempo){
        		 mostrarTiempo(g);
        	 }
        	 if (posicion)
        		 mostrarPosicion(g);
        	 if (mostrarAutos){
        		 mostrarOponente (g);    
        		 mostrarJugadores (g);
        		 mostrarNombres(g);
        	 }
      	   	 actualizarFotograma(); 
	    	 g.drawImage( spriteCache.getSprite(spriteNames[fotogramaActual]), CENTRO ,ABAJO, escenario );
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
		
		public void setTiempo (boolean tiempo){
			this.tiempo = tiempo;
		}

		public void setPosicion (boolean posicion){
			this.posicion=posicion;
		}
		
		public void setMostrarAutos (boolean mostrarAutos){
			this.mostrarAutos=mostrarAutos;
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
		
		public boolean isTiempo (){
	    	return tiempo;
	    }
	    
	    public boolean isPosicion(){
	    	return posicion;
	    }
	       
	    public boolean isMostrarAutos(){
	    	return mostrarAutos;
	    }

}


