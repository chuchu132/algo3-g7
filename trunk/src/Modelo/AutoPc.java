package Modelo;

import Excepciones.ProblemaTecnicoException;

public class AutoPc extends Auto{
	
	public AutoPc (){
		
		super ( new Motor(200,6,0.2,1000,400,10000),new CajaAutomatica(2,200,80,10000) , new SistemaCombustion(5,100,"Comun",0.01,10000), new Carroceria(300,700,"Ferrari F430",1,0.1,10000)
		       , new TanqueCombustible(100,70,10000),new TipoRueda(100,20,0.9,10000) );
		super.cargarCombustible(100, new Nafta("Nafta",98));
		encender();
	}
	
	public void simular (double tiempo, Pista pista) throws ProblemaTecnicoException{ 
			
			acelerar();
			super.simular(tiempo, pista);
			
		}
}
	
	
	
	

