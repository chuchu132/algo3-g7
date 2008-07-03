package Test;

import Excepciones.NoAlcanzaDineroException;
import Excepciones.NotAutoException;
import Modelo.AutoPc;
import Modelo.Jugador;
import Modelo.Vendedor;
import junit.framework.TestCase;

public class TestCompraVenta extends TestCase {
	Jugador unJugador;
	Vendedor unVendedor;
	
	private final int AUTOPARTES = 0;
	private final int AUTOS = 1;
	private final int NAFTAS = 2;
	
	public TestCompraVenta(String name) {
			super(name);
	}
	
	protected void setUp(){
		unJugador = new Jugador();
	}
	
	public void testCompraAutoparte() {
		unJugador.setPlata(6000); //le ponemos dinero sufieciente para poder comprar
		try {
			unJugador.comprarProducto(AUTOPARTES, 0); //0 es la carroceria tipo 1
		} catch (NoAlcanzaDineroException e) { //el producto cuesta 6000
			e.printStackTrace();
		}
		
		assertEquals(0.0, unJugador.getPlata());
		
	}
	
	public void testCompraAuto() {
		unJugador.setPlata(20000); //le ponemos dinero sufieciente para poder comprar
		try {
			unJugador.comprarProducto(AUTOS, 0); //0 es la auto tipo 1
		} catch (NoAlcanzaDineroException e) { //el producto cuesta 20000
			e.printStackTrace();
		}
		
		assertEquals(0.0, unJugador.getPlata());
	}
	
	public void testCompraNafta() {
		try {
			unJugador.getTaller().agregarAuto(new AutoPc());
			unJugador.getTaller().elegirAuto(0);
		} catch (NotAutoException e1) {
				e1.printStackTrace();
		}
		unJugador.setPlata(150); //le ponemos dinero sufieciente para poder comprar
		unJugador.setLitrosParaCargar(50); //se cargaran 50 listros
		try {
			unJugador.comprarProducto(NAFTAS, 0); 
		} catch (NoAlcanzaDineroException e) { //el producto cuesta 3 por litro
			e.printStackTrace();
		}
		
		assertEquals(0.0, unJugador.getPlata());
	}
	
}
