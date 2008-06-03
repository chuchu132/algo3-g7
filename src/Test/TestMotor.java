package Test;
import Excepciones.ProblemaTecnicoException;
import Excepciones.TanqueVacioException;
import Modelo.CajaVelocidades;
import Modelo.Motor;
import Modelo.SistemaCombustion;
import Modelo.TanqueCombustible;
import junit.framework.TestCase;


public class TestMotor extends TestCase {

	Motor miMotor;
	TanqueCombustible miTanque;
	CajaVelocidades miCaja;
	CajaVelocidades miCajaVieja;
	SistemaCombustion miSC;
	SistemaCombustion otroSC;
	SistemaCombustion miSCViejo;
	
		
	public TestMotor(String name) {
		super(name);
		
	}

	protected void setUp(){
		miMotor = new Motor(200,6,0.2,1000,400,1);
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
		miCaja = new CajaVelocidades(5,200,50,1);
		miCaja.addObserver(this.miMotor);
		/*RELACIONES:
		 * 0 - 0
		 * 1 - 1
		 * 2 - 4/5
		 * 3 - 3/5
		 * 4 - 2/5
		 * 5 - 1/5 
		 * */
		
		miSC = new SistemaCombustion(100,10,"Turbo",0.2,1);
		otroSC = new SistemaCombustion(100,10,"Nitro",0.5,1);
		
		miTanque = new TanqueCombustible(50,50,1);
	
	
	}
		
		
	public void testEncenderSin(){//prueba encender sin combustible
		miMotor.encender(miSC,miTanque);
		assertFalse(miMotor.estaEncendido());
	}
	
	public void testEncenderCon(){//prueba encender con combustible
		miTanque.cargarCombustible(95, 20);
		miMotor.encender(miSC,miTanque);
		assertTrue(miMotor.estaEncendido());
		
	}
	
	public void testQuemarCombustibleSin(){//prueba que el motor se apague si se queda sin combutible
	   	miTanque.cargarCombustible(0.1, 98);
	   	miMotor.encender(miSC,miTanque);
	   	try{
		miMotor.simular(10,miSC,miTanque);
	    fail();
	   	}
	   	catch (ProblemaTecnicoException e){assertTrue(true);}
		
	}
	
	public void testQuemarCombustibleCon(){//prueba que el motor siga prendido despues de quemar combustible
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender(miSC,miTanque);
		try{
			miMotor.quemarCombustible(8,miSC,miTanque);
			assertTrue(true);
		   	}
		   	catch (TanqueVacioException e){ fail();}
		}
	
	public void testObtenerDeltaRevoluciones(){ //prueba como varian las revoluciones segun el cambio en un intervalo de tiempo
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender(miSC,miTanque);
		assertEquals(0.0,miMotor.obtenerDeltaRevoluciones(10));
		miCaja.subirCambio();
		assertEquals(11625.0,miMotor.obtenerDeltaRevoluciones(10));
		miCaja.subirCambio();
		miCaja.subirCambio();
		assertEquals(6975.0,miMotor.obtenerDeltaRevoluciones(10));
		
	}
	
	
	public void testFuerzaInstantanea(){
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender(miSC,miTanque);
		
		assertEquals(0.0,miMotor.getFuerzaInstantanea(miCaja, 100,miSC));
		
		miCaja.subirCambio();
		miMotor.acelerar(1);
		assertEquals(6200.0,miMotor.getFuerzaInstantanea(miCaja, 100,miSC));
		
		miMotor.acelerar(3);
		assertEquals(100.0,miMotor.getFuerzaInstantanea(miCaja, 100,miSC));
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
		
		//Caja en 0
		
		miMotor.embragarBajar();// Vuelve a poner 0
		assertEquals(800,miMotor.getRevolucionesActuales());
		
		miMotor.embragarSubir();//1era
		miMotor.acelerar(2);
		miMotor.embragarBajar();// pone punto muerto el motor cae a 800 siempre
		assertEquals(800,miMotor.getRevolucionesActuales());
		
		miMotor.embragarSubir();//1era
		miMotor.acelerar(2);
		miMotor.embragarSubir();//2da
		miMotor.acelerar(1);
		miMotor.embragarBajar();//1era
		assertEquals(5450,miMotor.getRevolucionesActuales());
		
		miMotor.embragarBajar();// 0
		miMotor.embragarSubir();// 1era
		miMotor.acelerar(2);
		miMotor.embragarSubir();// 2da
		miMotor.acelerar(2);
		
		assertTrue(miMotor.getVidaUtil() == 1);// vida util antes de forzar el motor
		
		miMotor.embragarBajar(); // 1era pero con el motor muy acelerado llega a rev Maximas se desgasta el motor.
		assertEquals(6200,miMotor.getRevolucionesActuales());
		
		assertTrue(miMotor.getVidaUtil() < 1);// vida util despues de forzar el motor
	}
	
	
	
	public void testSimular() { // prueba el consumo de combustible con el motor bien y con suficiente combustible en el tanque
		miTanque.cargarCombustible(10.1, 98);
		miMotor.encender();
		
		
		miMotor.embragarSubir();
		try{
		miMotor.simular(10);
		}
		catch(ProblemaTecnicoException e){
			fail("La excepcion no deberia haber sido lanzada");
		}
		
		assertTrue(9.92 < miTanque.cantidadCombustible()&& miTanque.cantidadCombustible() < 9.94 );
			
	}
	
	public void testSimular2() { // prueba el consumo de combustible con el motor bien y sin suficiente combustible en el tanque
		miTanque.cargarCombustible(0.13, 98);
		miMotor.encender();
		
		
		miMotor.embragarSubir();
		try{
		miMotor.simular(10);
		fail("Deberia haber lanzado una excepcion");
		}
		catch(ProblemaTecnicoException e){
			assertEquals("Sin Combustible",e.getProblema());
			}
		
		}
	
	public void testSimular3(){// prueba simular con el motor fundido
		
		miMotor.gastar(0.9);
		
		miMotor.embragarSubir();
		try{
		miMotor.simular(10);
		fail("Deberia haber lanzado una excepcion");
		}
		catch(ProblemaTecnicoException e){
			assertEquals("Motor Fundido",e.getProblema());
			}
			
	}
		
	
	
}
