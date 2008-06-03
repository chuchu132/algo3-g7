package Modelo;

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
	
	
	
	/*=================== CARROCERIAS ==========================*/
	
	/* Características CarroceríaTipo1 */
	private final double precioCarroceriaTipo1 = 6000;
	private final double pesoCarroceriaTipo1 = 3000;
	private final String modeloCarroceriaTipo1 = "Tipo 1";
	private final int colorCarroceriaTipo1 = rojo;
	private final double plusCarroceriaTipo1 = 0.1;
	private final long vidaUtilCarroceriaTipo1 = 10000;
	
	public Carroceria crearCarroceriaTipo1() {
		return new Carroceria(precioCarroceriaTipo1, pesoCarroceriaTipo1, modeloCarroceriaTipo1, colorCarroceriaTipo1, plusCarroceriaTipo1, vidaUtilCarroceriaTipo1);
	}
	
	/* Características CarroceríaTipo2 */
	private final double precioCarroceriaTipo2 = 8000;
	private final double pesoCarroceriaTipo2 = 2500;
	private final String modeloCarroceriaTipo2 = "Tipo 2";
	private final int colorCarroceriaTipo2 = verde;
	private final double plusCarroceriaTipo2 = 0.13;
	private final long vidaUtilCarroceriaTipo2 = 12000;
	
	
	public Carroceria crearCarroceriaTipo2() {
		return new Carroceria(precioCarroceriaTipo2, pesoCarroceriaTipo2, modeloCarroceriaTipo2, colorCarroceriaTipo2, plusCarroceriaTipo2, vidaUtilCarroceriaTipo2);
	}
	
	/* Características CarroceríaTipo3 */
	private final double precioCarroceriaTipo3 = 5000;
	private final double pesoCarroceriaTipo3 = 3200;
	private final String modeloCarroceriaTipo3 = "Tipo 3";
	private final int colorCarroceriaTipo3 = blanco;
	private final double plusCarroceriaTipo3 = 0.09;
	private final long vidaUtilCarroceriaTipo3 = 9500;
	
	public Carroceria crearCarroceriaTipo3() {
		return new Carroceria(precioCarroceriaTipo3, pesoCarroceriaTipo3, modeloCarroceriaTipo3, colorCarroceriaTipo3, plusCarroceriaTipo3, vidaUtilCarroceriaTipo3);
	}
	
	/*=================== TIPORUEDAS ==========================*/
	
	/* Caracteristicas RuedasTipo1 */
	
	private final double precioTipoRuedaTipo1 = 400;
	private final double pesoTipoRuedaTipo1 = 100;
	private final double coeficienteAgarreTipoRuedaTipo1 = 0.1;
	private final float vidaUtilTipoRuedaTipo1 = 12000;
	
	
	public TipoRueda crearTipoRuedaTipo1() {
		return new TipoRueda(precioTipoRuedaTipo1, pesoTipoRuedaTipo1,coeficienteAgarreTipoRuedaTipo1, vidaUtilTipoRuedaTipo1);
	}

	/* Caracteristicas RuedasTipo2 */
	
	private final double precioTipoRuedaTipo2 = 480;
	private final double pesoTipoRuedaTipo2 = 98;
	private final double coeficienteAgarreTipoRuedaTipo2 = 0.09;
	private final float vidaUtilTipoRuedaTipo2 = 12500;
	
	
	public TipoRueda crearTipoRuedaTipo2() {
		return new TipoRueda(precioTipoRuedaTipo2, pesoTipoRuedaTipo2,coeficienteAgarreTipoRuedaTipo2, vidaUtilTipoRuedaTipo2);
	}

	/* Caracteristicas RuedasTipo3 */
	
	private final double precioTipoRuedaTipo3 = 340;
	private final double pesoTipoRuedaTipo3 = 160;
	private final double coeficienteAgarreTipoRuedaTipo3 = 0.15;
	private final float vidaUtilTipoRuedaTipo3 = 11200;
	
	
	public TipoRueda crearTipoRuedaTipo3() {
		return new TipoRueda(precioTipoRuedaTipo3, pesoTipoRuedaTipo3,coeficienteAgarreTipoRuedaTipo3, vidaUtilTipoRuedaTipo3);
	}

	
	/*=================== MOTORES ==========================*/
	
	/* Caracteristicas MotorTipo1 */
	
	private final double precioMotorTipo1 = 5000;
	private final double pesoMotorTipo1 = 1000;
	private final int hpMotorTipo1 = 500;
	private final int cilindrosMotorTipo1 = 8;
	private final double cubicajeMotorTipo1 = 3.5; 
	private final float vidaUtilMotorTipo1 = 12000;
	
	public Motor crearMotorTipo1(){
		return new Motor (hpMotorTipo1, cilindrosMotorTipo1, cubicajeMotorTipo1, precioMotorTipo1, pesoMotorTipo1, vidaUtilMotorTipo1);
	}
	
	
	/*=================== TANQUES ==========================*/
	
	/*Caracteristicas TanqueTipo1 */
	
	private final double precioTanqueTipo1 = 100;
	private final double capacidadMaximaTanqueTipo1 = 60;
	private final float vidaUtilTanqueTipo1 = 12000;
	
	public TanqueCombustible crearTanqueTipo1(){
		return new TanqueCombustible (precioTanqueTipo1, capacidadMaximaTanqueTipo1, vidaUtilTanqueTipo1);
	}
	
	
	/*=================== SISTEMAS DE COMBUSTION ==========================*/
	
	/*Caracteristicas SistemaCombustionTipo1 */
	
	private final double precioSistemaCombustionTipo1 = 200;
	private final double pesoSistemaCombustionTipo1 = 10;
	private final String tipoSistemaCombustionTipo1 = "Inyeccion";
	private final double plusSistemaCombustionTipo1 = 0.09;
	private final float vidaUtilSistemaCombustionTipo1 = 12000;
	
	public SistemaCombustion crearSistemaTipo1(){
		return new SistemaCombustion (precioSistemaCombustionTipo1, pesoSistemaCombustionTipo1, tipoSistemaCombustionTipo1,plusSistemaCombustionTipo1, vidaUtilSistemaCombustionTipo1);
	}
	
	/*=================== CAJAS DE VELOCIDADES ==========================*/
	
	/*caracteristicas CajaVelocidadesTipo1 */
	
	private final int cantidadCambiosCajaVelocidadesTipo1 = 5;
	private final double pesoCajaVelocidadesTipo1 = 500;
	private final double precioCajaVelocidadesTipo1 = 800;
	private final float vidaUtilCajaVelocidadesTipo1  =12000;
	
	public CajaVelocidades crearCajaTipo1(){
		return new CajaVelocidades (cantidadCambiosCajaVelocidadesTipo1, precioCajaVelocidadesTipo1, pesoCajaVelocidadesTipo1, vidaUtilCajaVelocidadesTipo1);
	}
	
}
