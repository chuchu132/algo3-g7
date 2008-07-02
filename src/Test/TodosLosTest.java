package Test;
import junit.textui.*;
public class TodosLosTest {
	
	public static void main(String[ ] args) {
		
		TestRunner.run(TestCajaVelocidades.class);
		TestRunner.run(TestCarroceria.class);
		TestRunner.run(TestSistemaCombustion.class);
		TestRunner.run(TestTanqueCombustible.class);
		TestRunner.run(TestMotor.class);
		TestRunner.run(TestAuto.class);
		TestRunner.run(TestCompraVenta.class);
	
	}
}
