package Test;

import Excepciones.TanqueVacioException;
import Modelo.Nafta;
import Modelo.TanqueCombustible;
import junit.framework.TestCase;

public class TestTanqueCombustible extends TestCase {
	
	TanqueCombustible miTanque;
	Nafta naftaComun;
	Nafta naftaSuper;
	
public TestTanqueCombustible(String name) {
		super(name);
		
	}

protected void setUp(){
	 miTanque = new TanqueCombustible(100,70,1);
	 naftaComun = new Nafta("Comun",91);
	 naftaSuper = new Nafta("Super",95);
}
	
 public void testEstaVacio(){
	  assertTrue(miTanque.estaVacio());
 }
 
 public void testCargarCombustible(){ // prueba que no este vacio despues de cargar
	 miTanque.cargarCombustible(30,naftaComun);
	 assertFalse(miTanque.estaVacio());
 }
 
 public void testCantidadCombustible(){// prueba que el tanque guarde lo que le cargue
	 miTanque.cargarCombustible(30,naftaComun);
	 assertEquals(30.0,miTanque.cantidadCombustible());
 }
 
 public void testCargarCombustible2(){ // prueba que el tanque no guarde mas de su capacidad maxima 
	 miTanque.cargarCombustible(30,naftaComun);
	 miTanque.cargarCombustible(50,naftaComun);
	 assertEquals(70.0,miTanque.cantidadCombustible());
 }
 
 
 public void testCargarCombustible3(){ //prueba el octanage resultante de mezclar 2 tipos de combustibles
	 miTanque.cargarCombustible(30,naftaComun);
	 miTanque.cargarCombustible(40,naftaSuper);
	 assertEquals("hibrida",miTanque.getTipoNafta().getNombre());
 }
 
 public void testDarCombustible(){// prueba que entregue combustible segun el  octanage
	 miTanque.cargarCombustible(30,naftaSuper);
	 try{
	 miTanque.darCombustible(10.0);
	 }
	 catch (TanqueVacioException e) {}
	 assertEquals(19.5, miTanque.cantidadCombustible());
 }
 
 public void testDarCombustible2(){
	 miTanque.cargarCombustible(30,naftaSuper);
	 try{
	 miTanque.darCombustible(29.0);
	 fail();
	 }
	 catch (TanqueVacioException e){assertTrue(true);}
	 
	 
 }
 
}
