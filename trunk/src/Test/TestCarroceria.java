package Test;
/*
 *Utilizando Carroceria se prueba Autoparte y  se prueba Carroceria.
 */
import Excepciones.ProblemaTecnicoException;
import Modelo.Carroceria;
import junit.framework.TestCase;

public class TestCarroceria extends TestCase {
 Carroceria miCarro;
 
  
 public TestCarroceria(String name) {
	super(name);
	
}

public void testSimular(){// prueba el metodo gastar de autoparte
	 miCarro = new Carroceria(100,700,"Torino",1,0.1,1000);
	 try{
	 miCarro.simular(10);
	 }
	 catch (ProblemaTecnicoException e) {}

	 assertTrue(0.89 < miCarro.getPorcentajeVidaUtil() && miCarro.getPorcentajeVidaUtil()<0.91);
	try{
	 miCarro.simular(50);
	}
	catch (ProblemaTecnicoException e){}

	 assertTrue(0.40 < miCarro.getPorcentajeVidaUtil() && miCarro.getPorcentajeVidaUtil()<0.41);
	 }
 
 public void testGetPlusVelocidad(){//prueba el metodo getPlusVelocidad antes y despues de gastar la carroceria 
	 miCarro = new Carroceria(100,700,"Torino",1,0.1,1);
	 assertEquals(0.1,miCarro.getPlusVelocidad());
	}
 
 
 
}
