
public class Principal {
	public static void main(String[] args) {
		
		Motor miMotor = new Motor(150,6,200,250,300);
		TanqueCombustible miTanque = new TanqueCombustible(20,70);
		CajaVelocidades miCaja = new CajaVelocidades(6,100,100);
		SistemaCombustion miSC = new SistemaCombustion(100,5,"Inyeccion",0.2);
        
		miMotor.conectarTanque(miTanque);
		System.out.println(miMotor.getDetalles());
		miMotor.encender();
		if(miMotor.estaEncendido()) System.out.println("Prendido");
		else System.out.println("Apagado");
		System.out.println(miTanque.getDetalles()); 
	    
	    miTanque.cargarCombustible(10, 94);
	    System.out.println(miTanque.getDetalles());
	    miMotor.encender();
		if(miMotor.estaEncendido()) System.out.println("Prendido");
		else System.out.println("Apagado");
		System.out.println(miTanque.getDetalles());
		
		miMotor.cambiarSitemaCombustion(miSC);
		System.out.println(miMotor.getDetalles());
		
	    miMotor.conectarCaja(miCaja);
	    
	    
	
	
	}
}