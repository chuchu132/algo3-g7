package Test;
/*
 *Utilizando Carroceria se prueba Autoparte y  se prueba Carroceria.
 */
import Modelo.Carroceria;
import junit.framework.TestCase;

public class TestCarroceria extends TestCase {
 Carroceria miCarro;
 
  
 public TestCarroceria(String name) {
	super(name);
	
}

public void testGastar(){// prueba el metodo gastar de autoparte
	 miCarro = new Carroceria(100,700,"Torino",1,0.1);
	 miCarro.gastar(0.1);
	 assertTrue(0.89 < miCarro.getVidaUtil() && miCarro.getVidaUtil()<0.91);
	 miCarro.gastar(0.5);
	 assertTrue(0.44 < miCarro.getVidaUtil() && miCarro.getVidaUtil()<0.46);
	 }
 
 public void testGetPlusVelocidad(){//prueba el metodo getPlusVelocidad antes y despues de gastar la carroceria 
	 miCarro = new Carroceria(100,700,"Torino",1,0.1);
	 assertEquals(0.1,miCarro.getPlusVelocidad());
	 miCarro.gastar(0.1);
	 assertTrue(0.089 < miCarro.getPlusVelocidad() && miCarro.getPlusVelocidad()<0.091);
	 miCarro.gastar(0.1);
	 assertTrue(0.080 < miCarro.getPlusVelocidad() && miCarro.getPlusVelocidad()<0.082);
 }
 
 
 
}
