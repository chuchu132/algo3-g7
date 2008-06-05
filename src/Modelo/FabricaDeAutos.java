package Modelo;

import java.util.ArrayList;

import Excepciones.NumeroAutoInexistenteException;
import Excepciones.NumeroAutoparteInexistenteException;

public class FabricaDeAutos extends FabricaDeProductos {

	public Auto crearAutoNumero(int n) throws NumeroAutoInexistenteException {
		switch (n) {
			case 0: return crearAutoTipo1();
			case 1: return crearAutoTipo2();
			/*
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
	
	public ArrayList<Producto> armarLista(){	
		
		ArrayList<Producto> listaAutos;
		
		Producto pAux;
		
		listaAutos = new ArrayList<Producto>();
		
		pAux = new Producto(0,tipoAuto,nombreAutoTipo1,
					  descripcionAutoTipo1, precioAutoTipo1);
		listaAutos.add(pAux);
		
		pAux = new Producto(1,tipoAuto,nombreAutoTipo2,
						descripcionAutoTipo2,precioAutoTipo2);
		return listaAutos;
	}
	
	/* ======================== AUTOS ==================== */
	private final String tipoAuto = "Auto";
	/* Auto Tipo 1 */
	private final String descripcionAutoTipo1 = "descripcion";
	private final String nombreAutoTipo1 = "Auto 1";
	private final double precioAutoTipo1 = 20000;
	
	public Auto crearAutoTipo1() {
		
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
	
	/* Auto Tipo 2 */
	private final String descripcionAutoTipo2 = "go speed racer";
	private final String nombreAutoTipo2 = "Match 5";
	private final double precioAutoTipo2 = 30000;
	
	public Auto crearAutoTipo2() {
		FabricaDeAutopartes fabrica = new FabricaDeAutopartes();
		
		Motor motorAutoTipo2 = fabrica.crearMotorTipo3();
		CajaVelocidades cajaAutoTipo2 = fabrica.crearCajaTipo1();
		SistemaCombustion sistamaCombustionAutoTipo2 = fabrica.crearSistemaTipo3();
		Carroceria carroceriaAutoTipo2 = fabrica.crearCarroceriaTipo1();
		TanqueCombustible tanqueAutoTipo2 = fabrica.crearTanqueTipo3();
		TipoRueda tipoRuedaAutoTipo2 = fabrica.crearTipoRuedaTipo2();
		
		return new Auto(motorAutoTipo2, cajaAutoTipo2, 
				sistamaCombustionAutoTipo2, carroceriaAutoTipo2, 
				tanqueAutoTipo2, tipoRuedaAutoTipo2);
	
		
	}
}

