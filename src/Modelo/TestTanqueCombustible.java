package Modelo;

import junit.framework.TestCase;

public class TestTanqueCombustible extends TestCase {
	
	TanqueCombustible miTanque;
	
 public void testEstaVacio(){
	 miTanque = new TanqueCombustible(100,70);
	 assertTrue(miTanque.estaVacio());
 }
 
 public void testCargarCombustible(){ // prueba que no este vacio despues de cargar
	 miTanque = new TanqueCombustible(100,70);
	 miTanque.cargarCombustible(30,95);
	 assertFalse(miTanque.estaVacio());
 }
 
 public void testCantidadCombustible(){// prueba que el tanque guarde lo que le cargue
	 miTanque = new TanqueCombustible(100,70);
	 miTanque.cargarCombustible(30,95);
	 assertEquals(30.0,miTanque.cantidadCombustible());
 }
 
 public void testCargarCombustible2(){ // prueba que el tanque no guarde mas de su capacidad maxima 
	 miTanque = new TanqueCombustible(100,70);
	 miTanque.cargarCombustible(30,95);
	 miTanque.cargarCombustible(50,98);
	 assertEquals(70.0,miTanque.cantidadCombustible());
 }
 
 
 public void testCargarCombustible3(){ //prueba el octanage resultante de mezclar 2 tipos de combustibles
	 miTanque = new TanqueCombustible(100,70);
	 miTanque.cargarCombustible(30,95);
	 miTanque.cargarCombustible(40,98);
	 assertEquals(96,miTanque.getOctanage());
 }
 
 public void testDarCombustible(){// prueba que entregue combustible segun el  octanage
	 miTanque = new TanqueCombustible(100,70);
	 miTanque.cargarCombustible(30,95);
	 miTanque.darCombustible(10.0);
	 assertEquals(19.5, miTanque.cantidadCombustible());
 }
 
 public void testDarCombustible2(){
	 miTanque = new TanqueCombustible(100,70);
	 miTanque.cargarCombustible(30,95);
	 miTanque.darCombustible(29.0);
	 assertTrue(miTanque.estaVacio());
	 
 }
 
}
