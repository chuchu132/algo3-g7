package Test;

import Excepciones.ProblemaTecnicoException;
import Modelo.Auto;
import Modelo.CajaVelocidades;
import Modelo.Carroceria;
import Modelo.Motor;
import Modelo.Nafta;
import Modelo.Pista;
import Modelo.TipoRueda;
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
	SistemaCombustion miSistemaCombustion;
	SistemaCombustion otroSC;
	SistemaCombustion miSCViejo;
	Carroceria miCarro;
	TipoRueda miRueda;
	TipoRueda otraRueda;
    Auto miAuto;
    Pista unaPista; 
    Nafta nafta;
    
   	public TestAuto(String name) {
		super(name);
	}


	protected void setUp(){
		miSistemaCombustion = new SistemaCombustion(5,100,"Turbo",0.2,1000);
		miMotor = new Motor(200,6,0.2,1000,400,9000);
		miCarro = new Carroceria(300,700,"Torino",1,0.1,1000);
		miCaja= new CajaVelocidades(5,200,80,1000);
		miTanque = new TanqueCombustible(100,70,1000);
		miRueda = new TipoRueda(100,20,0.9,1000);
		miAuto =  new Auto(miMotor,miCaja,miSistemaCombustion,miCarro,miTanque,miRueda);
	    unaPista = new Pista(100,0.1);
	    nafta = new Nafta("Nafta de Prueba",98);
	}
	
		
	public void testEstaCompleto(){
		assertTrue(miAuto.estaCompleto());
	}
	
	public void testCantidadDeCombustible(){
		
		miAuto.cargarCombustible(20,nafta);
		assertEquals(20.0, miAuto.cantidadCombustible());
	}
	
	public void testCargarCombustible(){
		
		miAuto.cargarCombustible(20, nafta);
		assertTrue(miAuto.cantidadCombustible() != 0.0);
	}
	
	public void testEncenderSinCombustible(){
		
		miAuto.encender();
		assertFalse(miAuto.estaEncendido());
	}
	 public void testEncenderConCombustiblre(){
		
		 miAuto.cargarCombustible(20, nafta);
		 miAuto.encender();
		 assertTrue(miAuto.estaEncendido());
	 }
	
	public void testCambiarUnTanqueLlenoPorUnoVacio(){
		
		otroTanque = new TanqueCombustible(100,80,1);
		miAuto.cargarCombustible(20, nafta);
		miTanque= miAuto.cambiarTanque(otroTanque);
		assertEquals(20.0, otroTanque.cantidadCombustible());
		assertEquals(0.0, miTanque.cantidadCombustible());
	}
	
		
	public void testGetPeso(){
		assertEquals(1367.0, miAuto.getPesoTotal());
		miAuto.cargarCombustible(13, nafta);
		assertEquals(1380.0, miAuto.getPesoTotal());
	}
	
	public void testAcelerar(){
		miAuto.cargarCombustible(13.1,nafta);
		miAuto.encender();
		
		miAuto.subirCambio();
		miAuto.acelerar();
			
		try{
		miAuto.simular(1,unaPista);}
		catch(ProblemaTecnicoException e){};		
		assertEquals(1962,miAuto.getRevoluciones() );
	}
	
	public void testDesacelerar(){
		miTanque.cargarCombustible(13.1, nafta);
		miAuto.encender();	
		
		miAuto.subirCambio();
		miAuto.acelerar();
		try{
			miAuto.simular(3,unaPista);}
			catch(ProblemaTecnicoException e){};
		assertEquals(4287,miAuto.getRevoluciones() );
		
		miAuto.desacelerar();
		try{
			miAuto.simular(1,unaPista);}
			catch(ProblemaTecnicoException e){};
		assertEquals(3124,miAuto.getRevoluciones() );
	}

	public void testSimularSinCombustible(){
		
		try{ miAuto.simular(1,unaPista);
		     fail("Deberia lanzar una excepcion");}
		catch (ProblemaTecnicoException e){ assertTrue(true);}
		
	}
	
	public void testSimularConMotorFundido(){
	   otroMotor = new Motor(200,6,0.2,1000,400,1);
	   otroMotor.gastar(0.90);
	   miAuto.cambiarMotor(otroMotor);
	   
		try{ miAuto.simular(1,unaPista);
	     fail("Deberia lanzar una excepcion");}
	   catch (ProblemaTecnicoException e){ assertTrue(true);}
		
	}
	
	public void testSimularConRuedasGastadas(){
		otraRueda= new  TipoRueda(100,20,0.9,1);
		otraRueda.gastar(0.79);
		
		try{ miAuto.simular(1,unaPista);
	     fail("Deberia lanzar una excepcion");}
	    catch (ProblemaTecnicoException e){ assertTrue(true);}
		
	}
	
	public void testSimularConAutoBien(){
		miAuto.cargarCombustible(13.1,nafta);
		miAuto.encender();
		miAuto.subirCambio();
		miAuto.acelerar();
		try{ miAuto.simular(0.1,unaPista);	     }
	    catch (ProblemaTecnicoException e){ fail("No deberia lanzar una excepcion");}
	    
	    assertTrue(8.84 < miAuto.getAceleracion() && miAuto.getAceleracion() < 8.86);
	    assertTrue(0.87< miAuto.getVelocidad() && miAuto.getVelocidad() < 0.89);
	    
	    
	   		
	}
	
	

}

