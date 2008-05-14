package Modelo; 


public class Principal {
	public static void main(String[] args) {
		
		Motor miMotor = new Motor(170,6,0.2,250,300);
		TanqueCombustible miTanque = new TanqueCombustible(20,120);
		CajaVelocidades miCaja = new CajaVelocidades(6,100,100);
		SistemaCombustion miSC = new SistemaCombustion(100,5,"Inyeccion",0.05);
        Carroceria miCarroceria = new Carroceria(100,500,"Falcon 80",1,0);
		Rueda unaRueda = new Rueda(100,20,0.9); //los coeficientes deben ser < 1
		Pista unaPista = new Pista(100,0.1); //los coeficientes deben ser < 1
		
		Auto miAuto = new Auto(miMotor,miCaja,miSC,miCarroceria,miTanque,unaRueda);
		
		
		miAuto.cargarCombustible(200,94);
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
