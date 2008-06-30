package Modelo;

import Excepciones.ProblemaTecnicoException;

public class AutoPc extends Auto{
	
	public AutoPc (){
		
		super ( new Motor(200,6,0.2,1000,400,10000),new CajaAutomatica(3,200,80,10000) , new SistemaCombustion(5,100,"Turbo",0.2,10000), new Carroceria(300,700,"Ferrari F430",1,0.1,10000)
		       , new TanqueCombustible(1000,70,10000),new TipoRueda(100,20,0.9,10000) );
		super.cargarCombustible(1000, new Nafta("Nafta de Prueba",98));
	}
	
	public void simular (double tiempo, Pista pista) throws ProblemaTecnicoException{ 
			
			acelerar();
			super.simular(tiempo, pista);
			
		}
}
	
	
	
	

