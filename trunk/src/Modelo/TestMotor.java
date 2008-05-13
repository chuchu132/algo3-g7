package Modelo;
import junit.framework.TestCase;


public class TestMotor extends TestCase {

	Motor miMotor;
	CajaVelocidades miCaja = new CajaVelocidades(5,200,50);
	CajaVelocidades otraCaja = new CajaVelocidades(6,200,50);
	CajaVelocidades miCajaVieja;
	TanqueCombustible miTanque = new TanqueCombustible(50,50);
	SistemaCombustion miSC = new SistemaCombustion(100,10,"Turbo",0.2);
	SistemaCombustion otroSC = new SistemaCombustion(100,10,"Nitro",0.5);
	SistemaCombustion miSCViejo;
	
	public void testCambiarCaja(){// prueba que la caja que saco es la que habia puesto
    miMotor = new Motor(200,6,0.2,1000,400);
    miMotor.conectarCaja(miCaja);
    miCajaVieja = miMotor.cambiarCaja(otraCaja);
    assertEquals(miCajaVieja,miCaja);
    }
	
	public void testCambiarSistemaCombustion(){//prueba cambiar de sistema de combustion 2 veces y obtener e viejo
	miMotor = new Motor(200,6,0.2,1000,400);
	miSCViejo = miMotor.cambiarSitemaCombustion(miSC);
	assertEquals(miSC,miMotor.cambiarSitemaCombustion(otroSC));
	}
	
	public void testEncenderSin(){
	miMotor = new Motor(200,6,0.2,1000,400);
	miMotor.conectarTanque(miTanque);
	miMotor.encender();
	assertFalse(miMotor.estaEncendido());
	}
	
	public void testEnenderCon(){
		miMotor = new Motor(200,6,0.2,1000,400);
		miMotor.conectarTanque(miTanque);
		miTanque.cargarCombustible(95, 20);
		miMotor.encender();
		assertFalse(miMotor.estaEncendido());
		
	}
	
	
	}
}