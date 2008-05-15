package Test;

import Modelo.Auto;
import Modelo.CajaVelocidades;
import Modelo.Carroceria;
import Modelo.Motor;
import Modelo.Pista;
import Modelo.ProblemaTecnicoException;
import Modelo.Rueda;
import Modelo.SistemaCombustion;
import Modelo.TanqueCombustible;
import junit.framework.TestCase;

public class TestAuto extends TestCase {
	Motor miMotor;
	Motor otroMotor;
	TanqueCombustible miTanque;
	TanqueCombustible otroTanque;
	CajaVelocidades miCaja;
	CajaVelocidades otraCaja;
	CajaVelocidades miCajaVieja;
	SistemaCombustion miSC;
	SistemaCombustion otroSC;
	SistemaCombustion miSCViejo;
	Carroceria miCarro;
	Rueda miRueda;
	Rueda otraRueda;
    Auto miAuto;
    
    
   	public TestAuto(String name) {
		super(name);
	}


	protected void setUp(){
		miSC = new SistemaCombustion(5,100,"Turbo",0.2);
		miMotor = new Motor(200,6,0.2,1000,400);
		miCarro = new Carroceria(300,700,"Torino",1,0.1);
		miCaja= new CajaVelocidades(5,200,80);
		miTanque = new TanqueCombustible(100,70);
		miRueda = new Rueda(100,20,0.9);
		miAuto =  new Auto(miMotor,miCaja,null,miCarro,miTanque,miRueda);
		Pista unaPista = new Pista(100,0.1);
		miAuto.irAPista(unaPista);
	}
	
		
	public void testEstaCompleto(){
		assertTrue(miAuto.estaCompleto());
	}
	
	public void testCantidadDeCombustible(){
		
		miAuto.cargarCombustible(20, 98);
		assertEquals(20.0, miAuto.cantidadCombustible());
	}
	
	public void testCargarCombustible(){
		
		miAuto.cargarCombustible(20, 98);
		assertTrue(miAuto.cantidadCombustible() != 0.0);
	}
	
	public void testEncenderSinCombustible(){
		
		miAuto.encender();
		assertFalse(miAuto.estaEncendido());
	}
	 public void testEncenderConCombustiblre(){
		
		 miAuto.cargarCombustible(20, 98);
		 miAuto.encender();
		 assertTrue(miAuto.estaEncendido());
	 }
	
	public void testCambiarUnTanqueLlenoPorUnoVacio(){
		
		otroTanque = new TanqueCombustible(100,80);
		miAuto.cargarCombustible(20, 98);
		miTanque= miAuto.cambiarTanque(otroTanque);
		assertEquals(20.0, otroTanque.cantidadCombustible());
		assertEquals(0.0, miTanque.cantidadCombustible());
	}
	
	public void testCambiarMotorConCajaYSistemaPorUnoSinNada(){
		
		otroMotor = new Motor(170,6,0.2,1000,400);
		miMotor.cambiarSitemaCombustion(miSC);
		miAuto.cambiarMotor(otroMotor);
		assertEquals(otroMotor.getSistemaCombustion().getDetalles(),miSC.getDetalles());
		assertEquals(otroMotor.cambiarCaja(null).getDetalles(),miCaja.getDetalles());
	}
	
	public void testGetPeso(){
		assertEquals(1267.0, miAuto.getPesoTotal());
		miAuto.cargarCombustible(13, 98);
		assertEquals(1280.0, miAuto.getPesoTotal());
	}
	
	public void testAcelerar(){
		miAuto.cargarCombustible(13.1,98);
		miAuto.encender();
		miAuto.subirCambio();
		miAuto.acelerar(1);
		assertEquals(1962,miAuto.getRevoluciones() );
	}
	
	public void testDesacelerar(){
		miTanque.cargarCombustible(13.1, 98);
		miMotor.encender();	
		
		miAuto.subirCambio();
		miAuto.acelerar(3);
		assertEquals(4287,miAuto.getRevoluciones() );
		
		miAuto.desacelerar(1);
		assertEquals(3124,miAuto.getRevoluciones() );
	}

	public void testSimularSinCombustible(){
		
		try{ miAuto.simular(1);
		     fail("Deberia lanzar una excepcion");}
		catch (ProblemaTecnicoException e){ assertTrue(true);}
		
	}
	
	public void testSimularConMotorFundido(){
	   otroMotor = new Motor(200,6,0.2,1000,400);
	   otroMotor.gastar(0.90);
	   miAuto.cambiarMotor(otroMotor);
	   
		try{ miAuto.simular(1);
	     fail("Deberia lanzar una excepcion");}
	   catch (ProblemaTecnicoException e){ assertTrue(true);}
		
	}
	
	public void testSimularConRuedasGastadas(){
		otraRueda= new  Rueda(100,20,0.9);
		otraRueda.gastar(0.79);
		
		try{ miAuto.simular(1);
	     fail("Deberia lanzar una excepcion");}
	    catch (ProblemaTecnicoException e){ assertTrue(true);}
		
	}
	
	public void testSimularConAutoBien(){
		miAuto.cargarCombustible(13.1,98);
		miAuto.encender();
		miAuto.subirCambio();
		miAuto.acelerar(1);
		try{ miAuto.simular(1);	     }
	    catch (ProblemaTecnicoException e){ fail("No deberia lanzar una excepcion");}
	    
	    assertTrue(3.95 < miAuto.getAceleracion() && miAuto.getAceleracion() < 3.97);
		assertTrue(3.95 < miAuto.getVelocidad() && miAuto.getVelocidad() < 3.97);
		assertTrue(3.95 < miAuto.getPosicion() && miAuto.getPosicion() < 3.97);
	}
	
	

}

