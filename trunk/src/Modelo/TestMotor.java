package Modelo;
import junit.framework.TestCase;


public class TestMotor extends TestCase {

	Motor miMotor;
	TanqueCombustible miTanque;
	CajaVelocidades miCaja;
	CajaVelocidades otraCaja;
	CajaVelocidades miCajaVieja;
	SistemaCombustion miSC;
	SistemaCombustion otroSC;
	SistemaCombustion miSCViejo;
	
	protected void setUp(){
		miMotor = new Motor(200,6,0.2,1000,400);
		/*	PESO = 400
		 *  HP= 200;
		 *  cilindros = 6
		 *  cubicaje = 0.200
		 *	fuerzaMaxima = 6200.0
		 *	encendido=false
		 *	revolucionesMax = 6200
		 *	revolucionesActuales = 0
		 *	fuerzaInstantanea = 0
		 *	acelerando = false
		 *	sistemaC = new SistemaCombustion(0,0,"Comun de Fabrica",0);;
		 */
		miCaja = new CajaVelocidades(5,200,50);
		miMotor.conectarCaja(miCaja);
		/*RELACIONES:
		 * 0 - 0
		 * 1 - 1
		 * 2 - 4/5
		 * 3 - 3/5
		 * 4 - 2/5
		 * 5 - 1/5 
		 * */
		
		otraCaja = new CajaVelocidades(6,200,50);
		
		miSC = new SistemaCombustion(100,10,"Turbo",0.2);
		otroSC = new SistemaCombustion(100,10,"Nitro",0.5);
		
		miTanque = new TanqueCombustible(50,50);
		miMotor.conectarTanque(miTanque);
	
	}
	
	public void testCambiarCaja(){// prueba que la caja que saco es la que habia puesto
		
		miCajaVieja = miMotor.cambiarCaja(otraCaja);
		assertEquals(miCajaVieja,miCaja);
    }
	
	public void testCambiarSistemaCombustion(){//prueba cambiar de sistema de combustion 2 veces y obtener e viejo
		miSCViejo = miMotor.cambiarSitemaCombustion(miSC);
		assertEquals(miSC,miMotor.cambiarSitemaCombustion(otroSC));
	}
	
	public void testEncenderSin(){//prueba encender sin combustible
		miMotor.encender();
		assertFalse(miMotor.estaEncendido());
	}
	
	public void testEnenderCon(){//prueba encender con combustible
		miTanque.cargarCombustible(95, 20);
		miMotor.encender();
		assertTrue(miMotor.estaEncendido());
		
	}
	
	public void testQuemarCombustibleSin(){//prueba que el motor se apague si se queda sin combutible
	   	miTanque.cargarCombustible(10.1, 98);
		miMotor.encender();
		miMotor.quemarCombustible(10);
		assertFalse(miMotor.estaEncendido());
	}
	
	public void testQuemarCombustibleCon(){//prueba que el motor siga prendido despues de quemar combustible
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender();
		miMotor.quemarCombustible(5);
		assertTrue(miMotor.estaEncendido());
	}
	
	public void testObtenerDeltaRevoluciones(){ //prueba como varian las revoluciones segun el cambio en un intervalo de tiempo
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender();
		assertEquals(0.0,miMotor.obtenerDeltaRevoluciones(10));
		miCaja.subirCambio();
		assertEquals(11625.0,miMotor.obtenerDeltaRevoluciones(10));
		miCaja.subirCambio();
		miCaja.subirCambio();
		assertEquals(6975.0,miMotor.obtenerDeltaRevoluciones(10));
		
	}
	
	public void testAcelerar(){ // prueba como varian las revoluciones del Motor segun el tiempo de aceleracion y el cambio
		
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender();	
		
		miMotor.acelerar(1);
		assertEquals(800,miMotor.getRevolucionesActuales() );
		
		miCaja.subirCambio();
		miMotor.acelerar(1);
		assertEquals(1962,miMotor.getRevolucionesActuales() );
		
		miCaja.subirCambio();
		miCaja.subirCambio();
		miMotor.acelerar(1);
		assertEquals(2659,miMotor.getRevolucionesActuales() );
		
	}
	
    public void testDesacelerar(){// prueba acelerar en varios cambios y despues desacelerar
    	miTanque.cargarCombustible(10.1, 98);
		miMotor.encender();	
		
		miCaja.subirCambio();
		miMotor.acelerar(3);
		assertEquals(4287,miMotor.getRevolucionesActuales() );
		
		miMotor.desacelerar(1);
		assertEquals(3124,miMotor.getRevolucionesActuales() );
		
		miCaja.subirCambio();
		miMotor.acelerar(3);
		assertEquals(5914,miMotor.getRevolucionesActuales() );
		miMotor.desacelerar(3);
		assertEquals(3124,miMotor.getRevolucionesActuales() );
		miMotor.desacelerar(10);
		assertEquals(800,miMotor.getRevolucionesActuales() );
    }	
    
    	
	public void testEmbragarSubir() {//prueba como caen las revolucioes al subir de cambio
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender();	
		
		miMotor.embragarSubir(); 
		assertEquals(800,miMotor.getRevolucionesActuales() );
		
		miMotor.acelerar(3);
		miMotor.embragarSubir();
		assertEquals(1714,miMotor.getRevolucionesActuales() );
		
	}
	
	public void testEmbragarBajar() {//prueba como aumentan las revoluciones al bajar de cambio
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender();	
	}
	
}
