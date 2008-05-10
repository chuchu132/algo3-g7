
public class Principal {
	public static void main(String[] args) {
	
		Motor miMotor = new Motor(150,6,200,250,300);
		TanqueCombustible miTanque = new TanqueCombustible(20,70);
		CajaVelocidades miCaja = new CajaVelocidades(6,100,100);
		SistemaCombustion miSC = new SistemaCombustion(100,5,"Inyeccion",0.2);
        Pista miPista = new Pista (10, 2);
        Rueda miRueda = new Rueda (10, 10, 4);
        Carroceria miCarroceria = new Carroceria (10, 250, "Ferrari", 1, 20);
		Auto miAuto = new Auto (miMotor, miCaja, miSC, miCarroceria, miTanque, miRueda);
		miAuto.encender();
		miAuto.subirCambio();
	    miAuto.acelerar(5);
	    miAuto.simular(5);
	    miAuto.getDetalles();
	
	}
}