package Modelo;

import Excepciones.NumeroAutoInexistenteException;
import Excepciones.NumeroAutoparteInexistenteException;

public class FabricaDeAutos {

	public Auto crearAutoNumero(int n) throws NumeroAutoInexistenteException {
		switch (n) {
			case 0: return crearAutoTiopo1();
			/*
			case 1: return crearCarroceriaTipo2();
			case 2: return crearTipoRuedaTipo1();
			case 3: return crearTipoRuedaTipo2();
			case 4: return crearTipoRuedaTipo3();
			case 5: return crearMotorTipo1();
			case 6: return crearTanqueTipo1();
			case 7: return crearSistemaTipo1();
			case 8: return crearCajaTipo1();
			*/
			default:
				 throw new NumeroAutoInexistenteException();
		}
	}
	

	public Auto crearAutoTiopo1() {
		
		FabricaDeAutopartes fabrica = new FabricaDeAutopartes();
		
		Motor motorAutoTipo1 = fabrica.crearMotorTipo1();
		CajaVelocidades cajaAutoTipo1 = fabrica.crearCajaTipo2();
		SistemaCombustion sistamaCombustionAutoTipo1 = fabrica.crearSistemaTipo3();
		Carroceria carroceriaAutoTipo1 = fabrica.crearCarroceriaTipo1();
		TanqueCombustible tanqueAutoTipo1 = fabrica.crearTanqueTipo3();
		TipoRueda tipoRuedaAutoTipo1 = fabrica.crearTipoRuedaTipo1();
		
		return new Auto(motorAutoTipo1, cajaAutoTipo1, 
				sistamaCombustionAutoTipo1, carroceriaAutoTipo1, 
				tanqueAutoTipo1, tipoRuedaAutoTipo1);
	}
	
}
