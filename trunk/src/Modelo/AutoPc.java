package Modelo;

public class AutoPc extends Auto{
	
	public AutoPc (){
		Motor pcMotor = new Motor(200,6,0.2,1000,400,1);
		TanqueCombustible pcTanque = new TanqueCombustible(1000,70,1);
		CajaVelocidades pcCaja= new CajaVelocidades(5,200,80,1);
		SistemaCombustion pcSistemaCombustion = new SistemaCombustion(5,100,"Turbo",0.2,1);;
		Carroceria pcCarro = new Carroceria(300,700,"Ferrari F430",1,0.1,1);
		TipoRueda pcRueda = new TipoRueda(100,20,0.9,1);
		
		
		super ( pcmotor, pccaja, pcsistemaCombustion, pccarroceria, pctanque, pcrueda);
	}
	
	
	

}
