package Test;

import Modelo.SistemaCombustion;
import Modelo.TanqueCombustible;
import junit.framework.TestCase;

public class TestSistemaCombustion extends TestCase {
    TanqueCombustible miTanque;
	SistemaCombustion miSC;
	 
protected void setUp(){
	miSC = new SistemaCombustion(5,100,"Turbo",0.2);
	miTanque = new TanqueCombustible(50,70);
}

	
public TestSistemaCombustion(String name) {
	super(name);
	
}


public void testDesconectarTanque(){// prueba desconectar tanque antes de conectarlo

    try{
    	miSC.desconectarTanque();
    }
    catch (NullPointerException e){ assertTrue(true);}
}

public void testConectarTanque(){// prueba conectar tanque y despues desconectarlo
	
    miSC.conectarTanque(miTanque);
    
    try{
    	miSC.desconectarTanque();
    }
    catch (NullPointerException e){ }
    
    assertTrue(true);
}

public void testQuemarCombustible(){//prueba quemar combustible con el tanque vacio
	miSC.conectarTanque(miTanque);
	assertFalse(miSC.quemarCombustible(10));
}

public void testQuemarCombustible2(){//prueba quemar combustible con el tanque con combustible
	miTanque.cargarCombustible(50, 98);
	miSC.conectarTanque(miTanque);
	assertTrue(miSC.quemarCombustible(10));
}

public void testTieneCombustible(){// prueba si hay combustible en el tanque estando vacio
	miSC.conectarTanque(miTanque);
	assertFalse(miSC.tieneCombustible());
}

public void testTieneCombustible2(){// prueba si hay combustible en el tanque estando casi lleno.
	miSC.conectarTanque(miTanque);
	miTanque.cargarCombustible(50, 98);
	assertTrue(miSC.tieneCombustible());
}



}