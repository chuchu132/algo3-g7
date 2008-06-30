package Modelo;

public class AutoPc extends Auto{
	
	public AutoPc (){
	
		
		super ( new Motor(200,6,0.2,1000,400,1),new CajaAutomatica(3,200,80,1) , new SistemaCombustion(5,100,"Turbo",0.2,1), new Carroceria(300,700,"Ferrari F430",1,0.1,1)
		       , new TanqueCombustible(1000,70,1),new TipoRueda(100,20,0.9,1) );
	}
	
	
	

}
