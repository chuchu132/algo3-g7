package Modelo;

import junit.framework.TestCase;

public class TestCajaVelocidades extends TestCase {
 CajaVelocidades miCaja; 
 
 
 
 public void testGetCambioActual(){
	 miCaja= new CajaVelocidades(5,4,3);
	 assertEquals(0,miCaja.getCambioActual());
 }
 
public void testSubirCambio(){
	miCaja = new CajaVelocidades(5,4,3);
	miCaja.subirCambio();
	miCaja.subirCambio();
	assertEquals(2,miCaja.getCambioActual());
}

public void testSubirCambio2(){
	miCaja = new CajaVelocidades(3,4,3);
	miCaja.subirCambio();
	miCaja.subirCambio();
	miCaja.subirCambio();
	miCaja.subirCambio();
	assertEquals(3,miCaja.getCambioActual());
}

public void testBajarCambio(){
	miCaja = new CajaVelocidades(5,4,3);
	miCaja.subirCambio();
	miCaja.subirCambio();
	miCaja.bajarCambio();
	assertEquals(1,miCaja.getCambioActual());
}

public void testBajarCambio2(){
	miCaja = new CajaVelocidades(5,4,3);
	miCaja.subirCambio();
	miCaja.bajarCambio();
	miCaja.bajarCambio();
	assertEquals(0,miCaja.getCambioActual());
}

public void testObtenerRelacion(){
	miCaja = new CajaVelocidades(5,4,3);
	assertEquals(0.0,miCaja.obtenerRelacion());
	miCaja.subirCambio();
	assertEquals((5.0/5.0),miCaja.obtenerRelacion());
	miCaja.subirCambio();
	assertEquals((4.0/5.0),miCaja.obtenerRelacion());
	miCaja.subirCambio();
	assertEquals((3.0/5.0),miCaja.obtenerRelacion());
	miCaja.subirCambio();
	assertEquals((2.0/5.0),miCaja.obtenerRelacion());
	miCaja.subirCambio();
	assertEquals((1.0/5.0),miCaja.obtenerRelacion());
	
}

}
