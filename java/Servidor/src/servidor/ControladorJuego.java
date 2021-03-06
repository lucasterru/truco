package servidor;

import java.util.LinkedList;
import java.util.List;

import excepciones.CartaException;
import excepciones.JuegoException;
import excepciones.JugadorException;
import negocio.FactoryJuegos;
import negocio.GrupoJuego;
import negocio.Juego;
import negocio.Pareja;

public class ControladorJuego {

	private List<Juego> juegos;
	private static FactoryJuegos fcJuegos;

	public ControladorJuego() {
		juegos = new LinkedList<Juego>();
		fcJuegos = new FactoryJuegos();
	}

	public void iniciarJuego(GrupoJuego grupo) throws JuegoException {
		Juego j = fcJuegos.getJuego(grupo.getTipoJuego());
		if (j != null) {
			j.setParejas(grupo.getParejas());
			j.crearChico();
			juegos.add(j);
		}
	}

	public void cantarTruco(int idJuego, int idJugador) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		j.cantarTruco(idJugador);

	}

	public void cantarReTruco(int idJuego, int idJugador) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		j.cantarReTruco(idJugador);

	}

	public void cantarVale4(int idJuego, int idJugador) throws JuegoException {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarVale4(idJugador);
	}

	public void cantarQuieroTruco(boolean quieroSiNo, int idJuego, int idJugador) throws JuegoException {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarQuieroTruco(quieroSiNo, idJugador);
	}

	public void cantarQuieroEnvido(int idJuego, boolean quieroSiNo) throws JuegoException {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarQuieroEnvido(quieroSiNo);
	}

	public void cantarEnvido(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		j.cantarEnvido();
	}

	// TODO AGREGAR
	public void aceptarTruco(int idJuego, int idJugador) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
	}

	public Juego buscarJuego(int idJuego) throws JuegoException {
		for (Juego juego : juegos) {
			if (juego.sosJuego(idJuego))
				return juego;
		}
		throw new JuegoException("El juego " + idJuego + "no existe.");
	}

	public void jugarCarta(int idJuego, int numero, String palo)
			throws JugadorException, CartaException, JuegoException {
		Juego j = this.buscarJuego(idJuego);
		j.jugarCarta(numero, palo);
	}

	public boolean verificarFinJuego(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		return j.verificarFinJuego();
	}

	public boolean terminoMano(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		return j.terminoMano();
	}

	public void sinCantar(int idJuego) throws JuegoException {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.sinCantar();

	}

	public boolean sePuedeCantarEnvido(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		return j.sePuedeCantarEnvido();
	}

	public void imprimirDbg() throws JuegoException {
		for (Juego juego : juegos) {
			List<Pareja> par = juego.getParejas();
			for (Pareja p : par) {

				System.out.println("Pareja num " + p.getIdPareja());
				System.out.println("Jugadores " + p.getJugadores().get(0).getNombre() + " y "
						+ p.getJugadores().get(1).getNombre());
				System.out.println("Cartas de " + p.getJugadores().get(0).getNombre());
				p.getJugadores().get(0).mostrarCartas();
				System.out.println("Cartas de " + p.getJugadores().get(1).getNombre());
				p.getJugadores().get(1).mostrarCartas();

				if (this.sePuedeCantarEnvido(juego.getId())) {

					System.out.println("Tanto para envido " + p.getMayorTanto());

				}
				juego.puntosDbg(p.getIdPareja());

				System.out.println("");

			}
			System.out.println("juega " + juego.proximoDbg().getNombre());
			System.out.println("");
		}
	}

}
