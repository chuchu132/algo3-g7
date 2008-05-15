package Test;

public class TodosLosTest {
	
	public static void main(String[ ] args) {
		
		junit.textui.TestRunner.run(TestCajaVelocidades.class);
		junit.textui.TestRunner.run(TestCarroceria.class);
		junit.textui.TestRunner.run(TestSistemaCombustion.class);
		junit.textui.TestRunner.run(TestTanqueCombustible.class);
		junit.textui.TestRunner.run(TestMotor.class);
		junit.textui.TestRunner.run(TestAuto.class);
	
	}
}
