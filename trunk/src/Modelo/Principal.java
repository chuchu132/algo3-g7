package Modelo; 


public class Principal {
	public static void main(String[] args) {
		
		
		Motor miMotor = new Motor(200,6,0.2,1000,400);
		Carroceria miCarro = new Carroceria(300,700,"Torino",1,0.1);
		CajaVelocidades miCaja= new CajaVelocidades(5,200,80);
		TanqueCombustible miTanque = new TanqueCombustible(100,70);
		Rueda miRueda = new Rueda(100,20,0.9);
		Auto miAuto =  new Auto(miMotor,miCaja,null,miCarro,miTanque,miRueda);
		Pista unaPista = new Pista(100,0.1);
		
		
		
		miAuto.cargarCombustible(13.1,98);
		miAuto.encender();
		miAuto.irAPista(unaPista);
		miAuto.subirCambio();
		//miAuto.subirCambio();
		
		
		System.out.println("******* ACELERA EN PRIMERA ********");
	for(int i=0; i < 15; i++) {
		miAuto.acelerar(1);
		
		try {
			miAuto.simular(1);
		} catch (ProblemaTecnicoException e) {
			e.printStackTrace();
		}
		
		System.out.println(miAuto.getDetalles());
		System.out.println(/*miMotor.getDetalles()+ */ "\n");
		
	}
	
	System.out.println("******* DESACELERA ********");
	
	
	
	for(int i=0; i < 15; i++) {
		miAuto.desacelerar(1);
		
		try {
			miAuto.simular(1);
		} catch (ProblemaTecnicoException e) {
			e.printStackTrace();
		}
		
		System.out.println(miAuto.getDetalles());
		System.out.println(/*miMotor.getDetalles()+ */ "\n");
		
	}
	System.out.println("******* SUBE CAMBIO Y ACELERA********");
	miAuto.subirCambio();
	
	for(int i=0; i < 15; i++) {
		miAuto.acelerar(1);
		
		try {
			miAuto.simular(1);
		} catch (ProblemaTecnicoException e) {
			e.printStackTrace();
		}
		
		System.out.println(miAuto.getDetalles());
		System.out.println(/*miMotor.getDetalles()+ */ "\n");
		
	}
	
	
	System.out.println("******* SUBE CAMBIO Y ACELERA********");
	miAuto.subirCambio();
	
	for(int i=0; i < 5; i++) {
		miAuto.acelerar(1);
		
		try {
			miAuto.simular(1);
		} catch (ProblemaTecnicoException e) {
			e.printStackTrace();
		}
		
		System.out.println(miAuto.getDetalles());
		System.out.println(/*miMotor.getDetalles()+ */ "\n");
		
	}
	
	
	System.out.println("******* SUBE CAMBIO Y ACELERA********");
	miAuto.subirCambio();
	
	for(int i=0; i < 5; i++) {
		miAuto.acelerar(1);
		
		try {
			miAuto.simular(1);
		} catch (ProblemaTecnicoException e) {
			e.printStackTrace();
		}
		
		System.out.println(miAuto.getDetalles());
		System.out.println(/*miMotor.getDetalles()+ */ "\n");
		
	}
	
	System.out.println("******* SUBE CAMBIO Y ACELERA********");
	miAuto.subirCambio();
	
	for(int i=0; i < 15; i++) {
		miAuto.acelerar(1);
		
		try {
			miAuto.simular(1);
		} catch (ProblemaTecnicoException e) {
			e.printStackTrace();
		}
		
		System.out.println(miAuto.getDetalles());
		System.out.println(/*miMotor.getDetalles()+ */ "\n");
		
	}
	
}
	
}
