package Modelo;

import java.util.ArrayList;

import Excepciones.NumeroAutoparteInexistente;

public class FabricaDeAutopartes {
	
	/* Colores */
	
	private final int blanco = 0;
	private final int rojo = 1;
	private final int verde = 2;
	private final int gris = 3;
	
	
	public Autoparte crearAutoparteNumero(int n) throws NumeroAutoparteInexistente {
		switch (n) {
			case 0: return crearCarroceriaTipo1();
			case 1: return crearCarroceriaTipo2();
			case 2: return crearTipoRuedaTipo1();
			case 3: return crearTipoRuedaTipo2();
			case 4: return crearTipoRuedaTipo3();
			case 5: return crearMotorTipo1();
			case 6: return crearTanqueTipo1();
			case 7: return crearSistemaTipo1();
			case 8: return crearCajaTipo1();
			default:
				 throw new NumeroAutoparteInexistente();
		}
	}
	
	
	public ArrayList<Producto> armarLista(){	
		
		ArrayList<Producto> listaAutopartes;
		
		Producto pAux;
		
		listaAutopartes = new ArrayList<Producto>();
		
		pAux = new Producto(0,parteCarroceria,nombreCarroceriaTipo1,
					  descripcionCarroceriaTipo1, precioCarroceriaTipo1);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(1,parteCarroceria,nombreCarroceriaTipo2,
						descripcionCarroceriaTipo2, precioCarroceriaTipo2);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(2,parteCarroceria,nombreCarroceriaTipo3,
						descripcionCarroceriaTipo3,precioCarroceriaTipo3);
		
		pAux = new Producto(3,parteRuedas,nombreTipoRuedaTipo1,
						descripcionTipoRuedaTipo1, precioTipoRuedaTipo1);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(4,parteRuedas,nombreTipoRuedaTipo2,
						descripcionTipoRuedaTipo2, precioTipoRuedaTipo2);
		listaAutopartes.add(pAux);

		
		pAux = new Producto(5,parteRuedas,nombreTipoRuedaTipo3,
					descripcionTipoRuedaTipo3, precioTipoRuedaTipo3);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(6,parteMotor,nombreMotorTipo1,
					descripcionMotorTipo1, precioMotorTipo1);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(7,parteMotor,nombreMotorTipo2,
				descripcionMotorTipo2, precioMotorTipo2);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(8,parteMotor,nombreMotorTipo3,
				descripcionMotorTipo3, precioMotorTipo3);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(9,parteTanque,nombreTanqueTipo1,
					descripcionTanqueTipo1, precioTanqueTipo1);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(10,parteTanque,nombreTanqueTipo2,
				descripcionTanqueTipo2, precioTanqueTipo2);
		listaAutopartes.add(pAux);
	
		pAux = new Producto(11,parteTanque,nombreTanqueTipo3,
				descripcionTanqueTipo3, precioTanqueTipo3);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(12,parteSistemaCombustion,nombreSistemaCombustionTipo1,
					descripcionSistemaCombustionTipo1, precioSistemaCombustionTipo1);
		listaAutopartes.add(pAux);

		pAux = new Producto(13,parteSistemaCombustion,nombreSistemaCombustionTipo2,
				descripcionSistemaCombustionTipo2, precioSistemaCombustionTipo2);
		listaAutopartes.add(pAux);
		
		pAux = new Producto(14,parteSistemaCombustion,nombreSistemaCombustionTipo3,
				descripcionSistemaCombustionTipo3, precioSistemaCombustionTipo3);
		listaAutopartes.add(pAux);

		pAux = new Producto(15,parteCajaVelocidades,nombreCajaVelocidadesTipo1,
					descripcionCajaVelocidadesTipo1, precioCajaVelocidadesTipo1);
		listaAutopartes.add(pAux);

		pAux = new Producto(16,parteCajaVelocidades,nombreCajaVelocidadesTipo2,
				descripcionCajaVelocidadesTipo2, precioCajaVelocidadesTipo2);
		listaAutopartes.add(pAux);

		pAux = new Producto(17,parteCajaVelocidades,nombreCajaVelocidadesTipo3,
				descripcionCajaVelocidadesTipo3, precioCajaVelocidadesTipo3);
		listaAutopartes.add(pAux);

		return listaAutopartes;
	}
	
	
	/*=================== CARROCERIAS ==========================*/
	private final String parteCarroceria = "Carroceria";
	/* Características CarroceríaTipo1 */
	private final String nombreCarroceriaTipo1 = "Carroceria 1";
	private final double precioCarroceriaTipo1 = 6000;
	private final double pesoCarroceriaTipo1 = 3000;
	private final String modeloCarroceriaTipo1 = "Tipo 1";
	private final int colorCarroceriaTipo1 = rojo;
	private final double plusCarroceriaTipo1 = 0.1;
	private final long vidaUtilCarroceriaTipo1 = 10000;
	private final String descripcionCarroceriaTipo1 = "copada";
	public Carroceria crearCarroceriaTipo1() {
		return new Carroceria(precioCarroceriaTipo1, pesoCarroceriaTipo1,
							  modeloCarroceriaTipo1, colorCarroceriaTipo1,
							  plusCarroceriaTipo1, vidaUtilCarroceriaTipo1);
	}
	
	/* Características CarroceríaTipo2 */
	private final String nombreCarroceriaTipo2 = "Carroceria 2";
	private final double precioCarroceriaTipo2 = 8000;
	private final double pesoCarroceriaTipo2 = 2500;
	private final String modeloCarroceriaTipo2 = "Tipo 2";
	private final int colorCarroceriaTipo2 = verde;
	private final double plusCarroceriaTipo2 = 0.13;
	private final long vidaUtilCarroceriaTipo2 = 12000;
	public final String descripcionCarroceriaTipo2 = "mas copada";
	
	public Carroceria crearCarroceriaTipo2() {
		return new Carroceria(precioCarroceriaTipo2, pesoCarroceriaTipo2,
							  modeloCarroceriaTipo2, colorCarroceriaTipo2,
							  plusCarroceriaTipo2, vidaUtilCarroceriaTipo2);
	}
	
	/* Características CarroceríaTipo3 */
	private final String nombreCarroceriaTipo3 = "Carroceria 3";
	private final double precioCarroceriaTipo3 = 5000;
	private final double pesoCarroceriaTipo3 = 3200;
	private final String modeloCarroceriaTipo3 = "Tipo 3";
	private final int colorCarroceriaTipo3 = blanco;
	private final double plusCarroceriaTipo3 = 0.09;
	private final long vidaUtilCarroceriaTipo3 = 9500;
	public final String descripcionCarroceriaTipo3 = "buena onda";
	
	public Carroceria crearCarroceriaTipo3() {
		return new Carroceria(precioCarroceriaTipo3, pesoCarroceriaTipo3,
							  modeloCarroceriaTipo3, colorCarroceriaTipo3,
							  plusCarroceriaTipo3, vidaUtilCarroceriaTipo3);
	}
	
	/*=================== TIPORUEDAS ==========================*/
	private final String parteRuedas = "Ruedas";
	/* Caracteristicas RuedasTipo1 */
	
	private final String nombreTipoRuedaTipo1 = "Ruedas 1";
	private final double precioTipoRuedaTipo1 = 400;
	private final double pesoTipoRuedaTipo1 = 100;
	private final double coeficienteAgarreTipoRuedaTipo1 = 0.1;
	private final float vidaUtilTipoRuedaTipo1 = 12000;
	private final String descripcionTipoRuedaTipo1 = "no resbalan";
	
	public TipoRueda crearTipoRuedaTipo1() {
		return new TipoRueda(precioTipoRuedaTipo1, pesoTipoRuedaTipo1,
							 coeficienteAgarreTipoRuedaTipo1, vidaUtilTipoRuedaTipo1);
	}

	/* Caracteristicas RuedasTipo2 */
	
	private final String nombreTipoRuedaTipo2 = "Ruedas 2";
	private final double precioTipoRuedaTipo2 = 480;
	private final double pesoTipoRuedaTipo2 = 98;
	private final double coeficienteAgarreTipoRuedaTipo2 = 0.09;
	private final float vidaUtilTipoRuedaTipo2 = 12500;
	private final String descripcionTipoRuedaTipo2 = "Excelentes";
	
	
	public TipoRueda crearTipoRuedaTipo2() {
		return new TipoRueda(precioTipoRuedaTipo2, pesoTipoRuedaTipo2,
							 coeficienteAgarreTipoRuedaTipo2, vidaUtilTipoRuedaTipo2);
	}

	/* Caracteristicas RuedasTipo3 */
	
	private final String nombreTipoRuedaTipo3 = "Ruedas 3";
	private final double precioTipoRuedaTipo3 = 340;
	private final double pesoTipoRuedaTipo3 = 160;
	private final double coeficienteAgarreTipoRuedaTipo3 = 0.15;
	private final float vidaUtilTipoRuedaTipo3 = 11200;
	private final String descripcionTipoRuedaTipo3 = "Buena";
	
	
	public TipoRueda crearTipoRuedaTipo3() {
		return new TipoRueda(precioTipoRuedaTipo3, pesoTipoRuedaTipo3,
					 		 coeficienteAgarreTipoRuedaTipo3, vidaUtilTipoRuedaTipo3);
	}

	
	/*=================== MOTORES ==========================*/
	private final String parteMotor = "Motor";
	/* Caracteristicas MotorTipo1 */
	
	private final String nombreMotorTipo1 = "Motor 1";
	private final double precioMotorTipo1 = 5000;
	private final double pesoMotorTipo1 = 1000;
	private final int hpMotorTipo1 = 500;
	private final int cilindrosMotorTipo1 = 8;
	private final double cubicajeMotorTipo1 = 3.5; 
	private final float vidaUtilMotorTipo1 = 12000;
	private final String descripcionMotorTipo1 = "Gran vida util";
	
	public Motor crearMotorTipo1(){
		return new Motor (hpMotorTipo1, cilindrosMotorTipo1, cubicajeMotorTipo1,
						  precioMotorTipo1, pesoMotorTipo1, vidaUtilMotorTipo1);
	}
	
	/* Caracteristicas MotorTipo 2 */
	
	private final String nombreMotorTipo2 = "Motor 2";
	private final double precioMotorTipo2 = 5000;
	private final double pesoMotorTipo2 = 1000;
	private final int hpMotorTipo2 = 500;
	private final int cilindrosMotorTipo2 = 8;
	private final double cubicajeMotorTipo2 = 3.5; 
	private final float vidaUtilMotorTipo2 = 12000;
	private final String descripcionMotorTipo2 = "Gran vida util";
	
	public Motor crearMotorTipo2(){
		return new Motor (hpMotorTipo2, cilindrosMotorTipo2, cubicajeMotorTipo2,
						  precioMotorTipo2, pesoMotorTipo2, vidaUtilMotorTipo2);
	}
	
	/* Caracteristicas MotorTipo 3 */
	
	private final String nombreMotorTipo3 = "Motor 3";
	private final double precioMotorTipo3 = 5000;
	private final double pesoMotorTipo3 = 1000;
	private final int hpMotorTipo3 = 500;
	private final int cilindrosMotorTipo3 = 8;
	private final double cubicajeMotorTipo3 = 3.5; 
	private final float vidaUtilMotorTipo3 = 12000;
	private final String descripcionMotorTipo3 = "Gran vida util";
	
	public Motor crearMotorTipo3(){
		return new Motor (hpMotorTipo3, cilindrosMotorTipo3, cubicajeMotorTipo3,
						  precioMotorTipo3, pesoMotorTipo3, vidaUtilMotorTipo3);
	}
	
	/*=================== TANQUES ==========================*/
	private final String parteTanque = "Tanque";
	/*Caracteristicas TanqueTipo1 */
	
	private final String nombreTanqueTipo1 = "Tanque 1";
	private final double precioTanqueTipo1 = 100;
	private final double capacidadMaximaTanqueTipo1 = 60;
	private final float vidaUtilTanqueTipo1 = 12000;
	private final String descripcionTanqueTipo1 = "Gran vida util y economico";
	
	public TanqueCombustible crearTanqueTipo1(){
		return new TanqueCombustible (precioTanqueTipo1, capacidadMaximaTanqueTipo1,
									  vidaUtilTanqueTipo1);
	}
	
	/*Caracteristicas TanqueTipo2 */
	
	private final String nombreTanqueTipo2 = "Tanque 2";
	private final double precioTanqueTipo2 = 100;
	private final double capacidadMaximaTanqueTipo2 = 60;
	private final float vidaUtilTanqueTipo2 = 12000;
	private final String descripcionTanqueTipo2 = "Gran vida util y economico";
	
	public TanqueCombustible crearTanqueTipo2(){
		return new TanqueCombustible (precioTanqueTipo2, capacidadMaximaTanqueTipo2,
									  vidaUtilTanqueTipo2);
	}
	
	/*Caracteristicas TanqueTipo1 3 */
	
	private final String nombreTanqueTipo3 = "Tanque 3";
	private final double precioTanqueTipo3 = 100;
	private final double capacidadMaximaTanqueTipo3 = 60;
	private final float vidaUtilTanqueTipo3 = 12000;
	private final String descripcionTanqueTipo3 = "Gran vida util y economico";
	
	public TanqueCombustible crearTanqueTipo3(){
		return new TanqueCombustible (precioTanqueTipo3, capacidadMaximaTanqueTipo3,
									  vidaUtilTanqueTipo3);
	}
	
	
	/*=================== SISTEMAS DE COMBUSTION ==========================*/
	private final String parteSistemaCombustion = "Sistema de combustion";
	/*Caracteristicas SistemaCombustionTipo1 */
	
	private final String nombreSistemaCombustionTipo1 = "Sistema de Combustion 1";
	private final double precioSistemaCombustionTipo1 = 200;
	private final double pesoSistemaCombustionTipo1 = 10;
	private final String tipoSistemaCombustionTipo1 = "Inyeccion";
	private final double plusSistemaCombustionTipo1 = 0.09;
	private final float vidaUtilSistemaCombustionTipo1 = 12000;
	private final String descripcionSistemaCombustionTipo1 = "gran vida util";
	
	public SistemaCombustion crearSistemaTipo1(){
		return new SistemaCombustion (precioSistemaCombustionTipo1, pesoSistemaCombustionTipo1,
									  tipoSistemaCombustionTipo1,plusSistemaCombustionTipo1,
									  vidaUtilSistemaCombustionTipo1);
	}
	
	/*Caracteristicas SistemaCombustionTipo2 */
	
	private final String nombreSistemaCombustionTipo2 = "Sistema de Combustion 2";
	private final double precioSistemaCombustionTipo2 = 200;
	private final double pesoSistemaCombustionTipo2 = 10;
	private final String tipoSistemaCombustionTipo2 = "Inyeccion";
	private final double plusSistemaCombustionTipo2 = 0.09;
	private final float vidaUtilSistemaCombustionTipo2 = 12000;
	private final String descripcionSistemaCombustionTipo2 = "gran vida util";
	
	public SistemaCombustion crearSistemaTipo2(){
		return new SistemaCombustion (precioSistemaCombustionTipo2, pesoSistemaCombustionTipo2,
									  tipoSistemaCombustionTipo2,plusSistemaCombustionTipo2,
									  vidaUtilSistemaCombustionTipo2);
	}
	
	/*Caracteristicas SistemaCombustionTipo3 */
	
	private final String nombreSistemaCombustionTipo3 = "Sistema de Combustion 3";
	private final double precioSistemaCombustionTipo3 = 200;
	private final double pesoSistemaCombustionTipo3 = 10;
	private final String tipoSistemaCombustionTipo3 = "Inyeccion";
	private final double plusSistemaCombustionTipo3 = 0.09;
	private final float vidaUtilSistemaCombustionTipo3 = 12000;
	private final String descripcionSistemaCombustionTipo3 = "gran vida util";
	
	public SistemaCombustion crearSistemaTipo3(){
		return new SistemaCombustion (precioSistemaCombustionTipo3, pesoSistemaCombustionTipo3,
									  tipoSistemaCombustionTipo3,plusSistemaCombustionTipo3,
									  vidaUtilSistemaCombustionTipo3);
	}
	
	/*=================== CAJAS DE VELOCIDADES ==========================*/
	public final String parteCajaVelocidades = "Caja de velocidades";
	/*caracteristicas CajaVelocidadesTipo1 */
	
	private final String nombreCajaVelocidadesTipo1 = "Caja de Velocidades 1";
	private final int cantidadCambiosCajaVelocidadesTipo1 = 5;
	private final double pesoCajaVelocidadesTipo1 = 500;
	private final double precioCajaVelocidadesTipo1 = 800;
	private final float vidaUtilCajaVelocidadesTipo1  =12000;
	private final String descripcionCajaVelocidadesTipo1 = "5 cambios";
	
	public CajaVelocidades crearCajaTipo1(){
		return new CajaVelocidades (cantidadCambiosCajaVelocidadesTipo1,
									precioCajaVelocidadesTipo1, pesoCajaVelocidadesTipo1,
									vidaUtilCajaVelocidadesTipo1);
	}
	
	/*caracteristicas CajaVelocidadesTipo2 */
	
	private final String nombreCajaVelocidadesTipo2 = "Caja de Velocidades 2";
	private final int cantidadCambiosCajaVelocidadesTipo2 = 5;
	private final double pesoCajaVelocidadesTipo2 = 500;
	private final double precioCajaVelocidadesTipo2 = 800;
	private final float vidaUtilCajaVelocidadesTipo2  =12000;
	private final String descripcionCajaVelocidadesTipo2 = "5 cambios";
	
	public CajaVelocidades crearCajaTipo2(){
		return new CajaVelocidades (cantidadCambiosCajaVelocidadesTipo2,
									precioCajaVelocidadesTipo2, pesoCajaVelocidadesTipo2,
									vidaUtilCajaVelocidadesTipo2);
	}
	
	/*caracteristicas CajaVelocidadesTipo3 */
	
	private final String nombreCajaVelocidadesTipo3 = "Caja de Velocidades 3";
	private final int cantidadCambiosCajaVelocidadesTipo3 = 5;
	private final double pesoCajaVelocidadesTipo3 = 500;
	private final double precioCajaVelocidadesTipo3 = 800;
	private final float vidaUtilCajaVelocidadesTipo3  =12000;
	private final String descripcionCajaVelocidadesTipo3 = "5 cambios";
	
	public CajaVelocidades crearCajaTipo3(){
		return new CajaVelocidades (cantidadCambiosCajaVelocidadesTipo3,
									precioCajaVelocidadesTipo3, pesoCajaVelocidadesTipo3,
									vidaUtilCajaVelocidadesTipo3);
	}
	
	
}
