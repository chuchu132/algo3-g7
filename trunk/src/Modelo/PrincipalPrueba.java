package Modelo;

public class PrincipalPrueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jugador miJugador = new Jugador();
		
		miJugador.pedirMostrarListaAutopartes();
		miJugador.ComprarAutoparte(0);
		miJugador.listarMisAutopartes();

	}

}
