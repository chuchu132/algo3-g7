package Modelo;

public class FabricaDeAutopartes {
	
	/* Colores */
	
	private final int blanco = 0;
	private final int rojo = 1;
	private final int verde = 2;
	private final int gris = 3;
	
	
	
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
	private final double precioCarroceriaTipo2 = 6000;
	private final double pesoCarroceriaTipo2 = 3000;
	private final String modeloCarroceriaTipo2 = "Tipo 1";
	private final int colorCarroceriaTipo2 = rojo;
	private final double plusCarroceriaTipo2 = 0.1;
	private final long vidaUtilCarroceriaTipo2 = 10000;
	
	public Carroceria crearCarroceriaTipo2() {
		return new Carroceria(precioCarroceriaTipo2, pesoCarroceriaTipo2, modeloCarroceriaTipo2, colorCarroceriaTipo2, plusCarroceriaTipo2, vidaUtilCarroceriaTipo2);
	}
	
	/*=================== TIPORUEDAS ==========================*/
	
	private final double precioTipoRuedaTipo1 = 400;
	private final double pesoTipoRuedaTipo1 = 100;
	private final double CoeficienteAgarreTipoRuedaTipo1 = 0.1;
	private final float VidUtilTipoRuedaTipo1 = 12000;
	
	
	public TipoRueda crearTipoRuedaTipo1() {
		return new TipoRueda(precioTipoRuedaTipo1, pesoTipoRuedaTipo1,CoeficienteAgarreTipoRuedaTipo1, VidUtilTipoRuedaTipo1);
	}

	
	/*=================== MOTORES ==========================*/
	
	
	
	
	
	/*=================== TANQUES ==========================*/
	
	
	
	
	
	/*=================== SISTEMAS DE COMBUSTION ==========================*/
	
	
	
	/*=================== CAJAS DE VELOCIDADES ==========================*/
	
	
	
}
