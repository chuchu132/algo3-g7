package Test;

import Modelo.CajaVelocidades;
import junit.framework.TestCase;

public class TestCajaVelocidades extends TestCase {
 CajaVelocidades miCaja; 
 
 
 
 public TestCajaVelocidades(String name) {
	super(name);
	
}

protected void setUp(){
	 miCaja= new CajaVelocidades(5,4,3); 
 }
 
 public void testGetCambioActual(){// prueba que el cambio actual sea 0 cuando la caja no se uso
	  assertEquals(0,miCaja.getCambioActual());
 }
 
public void testSubirCambio(){// prueba que aumente el cambio actual
	miCaja.subirCambio();
	miCaja.subirCambio();
	assertEquals(2,miCaja.getCambioActual());
}

public void testSubirCambio2(){// prueba que no suba mas cambios de los que tiene 
	miCaja = new CajaVelocidades(3,4,3);
	miCaja.subirCambio();
	miCaja.subirCambio();
	miCaja.subirCambio();
	miCaja.subirCambio();
	assertEquals(3,miCaja.getCambioActual());
}

public void testBajarCambio(){// prueba que baje el cambio
	miCaja.subirCambio();
	miCaja.subirCambio();
	miCaja.bajarCambio();
	assertEquals(1,miCaja.getCambioActual());
}

public void testBajarCambio2(){//prueba que no baje mas cambios que 0
	miCaja.subirCambio();
	miCaja.bajarCambio();
	miCaja.bajarCambio();
	assertEquals(0,miCaja.getCambioActual());
}

public void testObtenerRelacion(){//prueba la relacion de caja para una caja de 5 velocidades
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
