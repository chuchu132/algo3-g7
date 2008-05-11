
public class Principal {
	public static void main(String[] args) {
		
		Motor miMotor = new Motor(800,6,200,250,300);
		TanqueCombustible miTanque = new TanqueCombustible(20,70);
		CajaVelocidades miCaja = new CajaVelocidades(6,100,100);
		SistemaCombustion miSC = new SistemaCombustion(100,5,"Inyeccion",0.2);
        Carroceria miCarroceria = new Carroceria(100,500,"Falcon 80",1,1);
		Rueda unaRueda = new Rueda(100,20,4);
		Pista unaPista = new Pista(100,2);
		
		Auto miAuto = new Auto(miMotor,miCaja,null,miCarroceria,miTanque,unaRueda);
		
		
		miAuto.irAPista(unaPista);
		System.out.println(miTanque.getDetalles());
		miAuto.cargarCombustible(10,94);
		 System.out.println(miTanque.getDetalles());
		miAuto.encender();
		miAuto.subirCambio();
		while(miAuto.getPosicion() < unaPista.getLongitud()){
		miAuto.acelerar(0.0001);
		miAuto.simular(1);
		System.out.println(miTanque.getDetalles());
	    System.out.println( miAuto.getDetalles());
		miAuto.subirCambio();
		}
	    
	    
		
	
	
	}
}