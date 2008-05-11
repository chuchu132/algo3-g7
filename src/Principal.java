
public class Principal {
	public static void main(String[] args) {
		
		Motor miMotor = new Motor(400,6,0.2,250,300);
		TanqueCombustible miTanque = new TanqueCombustible(20,120);
		CajaVelocidades miCaja = new CajaVelocidades(6,100,100);
		SistemaCombustion miSC = new SistemaCombustion(100,5,"Inyeccion",0.2);
        Carroceria miCarroceria = new Carroceria(100,500,"Falcon 80",1,1);
		Rueda unaRueda = new Rueda(100,20,4);
		Pista unaPista = new Pista(100,2);
		
		Auto miAuto = new Auto(miMotor,miCaja,null,miCarroceria,miTanque,unaRueda);
		
		
		miAuto.irAPista(unaPista);
		System.out.println(miTanque.getDetalles());
		miAuto.cargarCombustible(200,94);
		 System.out.println(miTanque.getDetalles());
		miAuto.encender();
		miAuto.subirCambio();
		
		while(miAuto.getPosicion() < unaPista.getLongitud()){
		
		miAuto.acelerar(0.1);
		miAuto.simular(0.1);
		System.out.println(miTanque.getDetalles());
	    System.out.println(miMotor.getDetalles());
		System.out.println( miAuto.getDetalles());
	    System.out.println(" cambio actual: " + miCaja.getCambioActual());
	    miAuto.subirCambio();
	    System.out.println(" cambio actual:  " + miCaja.getCambioActual());
		}
	    
	    
		
	
	
	}
}