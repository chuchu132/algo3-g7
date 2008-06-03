package Test;

import Excepciones.TanqueVacioException;
import Modelo.SistemaCombustion;
import Modelo.TanqueCombustible;
import junit.framework.TestCase;

public class TestSistemaCombustion extends TestCase {
    TanqueCombustible miTanque;
	SistemaCombustion miSC;
	 
protected void setUp(){
	miSC = new SistemaCombustion(5,100,"Turbo",0.2,1);
	miTanque = new TanqueCombustible(50,70,1);
}

	
public TestSistemaCombustion(String name) {
	super(name);
	
}

public void testQuemarCombustible(){//prueba quemar combustible con el tanque vacio
	try{
    miSC.quemarCombustible(10,miTanque);
    fail();
	}
	catch (TanqueVacioException e){assertTrue(true);}
}

public void testQuemarCombustible2(){//prueba quemar combustible con el tanque con combustible
	miTanque.cargarCombustible(50, 98);
	try{
	    miSC.quemarCombustible(10,miTanque);
	    assertTrue(true);
		}
		catch (TanqueVacioException e){assertTrue(true);fail();}
}


}