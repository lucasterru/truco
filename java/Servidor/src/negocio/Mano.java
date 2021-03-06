package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import excepciones.CartaException;
import excepciones.JugadorException;

public class Mano {

	private List<Pareja> parejas;
	private List<Baza> bazas;
	private List<Jugador> jugadores;

	/// TODO VER!!
	private List<Puntuacion> puntos;

	private Envido envido;
	private Truco truco;

	private int puntoParaTerminarChico;
	private Mazo mazo;

	private List<Pareja> historicoPuntos = null;
	private Pareja ganadorBaza1 = null;
	private Jugada jugadaMayor = null;

	private int jugadorOrden = 0;

	public Mano(List<Pareja> parejas, List<Jugador> jugadores, int puntoParaTerminarChico) {
		super();
		this.parejas = parejas;
		this.jugadores = jugadores;
		setPuntoParaTerminarChico(puntoParaTerminarChico);
		this.mazo = new Mazo();
		this.bazas = new ArrayList<>();

		this.puntos = new ArrayList<>();
		this.historicoPuntos = new ArrayList<>();
		// puntos pareja mano
		for (Pareja p : parejas) {
			Puntuacion punt = new Puntuacion();
			punt.setPareja(p);
			puntos.add(punt);
		}

		repartir();
		// TODO primera baza
		altaBaza();

	}

	private void altaBaza() {
		Baza b = new Baza();
		b.setJugadores(jugadores);
		this.bazas.add(b);
		System.out.println("BAZA NUMERO " + this.bazas.size());
		jugadorOrden = 0;
	}

	public List<Puntuacion> getPuntos() {
		return this.puntos;
	}

	private void repartir() {
		for (Jugador jug : jugadores) {

			Vector<Carta> cartas = this.mazo.getTresCartasRandom();
			jug.setCartas(cartas);

		}
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getPuntoParaTerminarChico() {
		return puntoParaTerminarChico;
	}

	public void setPuntoParaTerminarChico(int puntoParaTerminarChico) {
		this.puntoParaTerminarChico = puntoParaTerminarChico;
	}

	// TODO AGREGAR BUSCA UN JUGADOR EN UNA PAREJA

	public void cantarTruco(int idJugador) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(idJugador);

		this.truco = new Truco();

	}

	public void cantarVale4(int idJugador) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(idJugador);

		if (this.truco == null)
			this.truco = new Truco();

		Vale4 v4 = new Vale4();
		this.truco.addDec(v4);

	}

	public void cantarReTruco(int idJugador) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(idJugador);
		this.truco = new Truco();

		ReTruco rt = new ReTruco();

		Vale4 v4 = new Vale4();
		v4.addDec(rt);

		this.truco.addDec(v4);

	}

	public void cantarQuieroTruco(boolean quieroSiNo, int idJugador) {

		Puntuacion p = getPuntosPareja(idJugador);

		if (quieroSiNo) {
			p.sumarPuntos(this.envido.getPuntosQuiero());

			System.out.println("puntos quiero Truco " + this.truco.getPuntosQuiero());
		} else {
			p.sumarPuntos(this.envido.getPuntosNoQuiero());

			System.out.println("puntos no Quiero Truco  " + this.truco.getPuntosNoQuiero());
		}
		// TODO Auto-generated method stub

	}

	public void cantarEnvido() {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarEnvido(jugadorOrden);
		if (this.envido == null)
			this.envido = new Envido();
		else {
			Envido env = new Envido();
			this.envido.addDec(env);

		}

	}

	public Jugador proximoDbg() {
		return jugadores.get(jugadorOrden);
	}

	public Pareja getPareja(int idJugador) {
		for (Pareja p : parejas) {
			if (p.tieneJugador(idJugador))
				return p;
		}
		return null;
	}

	public Pareja getParejaActual() {
		for (Pareja p : parejas) {
			if (p.tieneJugador(jugadorOrden))
				return p;
		}
		return null;
	}

	public Pareja getParejaContrariaActual() {
		for (Pareja p : parejas) {
			if (!p.tieneJugador(jugadorOrden))
				return p;
		}
		return null;
	}

	public void cantarQuieroEnvido(boolean quieroSiNo) {

		// TODO Auto-generated method stub el jugador +1 es de la otra pareja
		Puntuacion p;
		// si quiere envido
		//

		if (quieroSiNo) {

			Pareja parejaactual = getParejaActual();
			Pareja parejacontraria = getParejaContrariaActual();

			if (parejaactual.getMayorTanto() > parejacontraria.getMayorTanto()) {
				// gana pareja 1
				p = getPuntosPareja(jugadorOrden);

			} else {
				// gana pareja 2

				p = getPuntosParejaContraria(jugadorOrden);

			}

			p.sumarPuntos(this.envido.getPuntosQuiero());

			System.out.println("puntos quiero Envido " + this.envido.getPuntosQuiero());

		} else {
			p = getPuntosPareja(jugadorOrden);
			p.sumarPuntos(this.envido.getPuntosNoQuiero());

			System.out.println("puntos no quiero Envido  " + this.envido.getPuntosNoQuiero());

		}

	}

	/// TODO AGREGAR!
	public void sinCantar() {
		// TODO Auto-generated method stub

		Jugada j = this.bazas.get(this.bazas.size() - 1).jugadaMayor();
		Puntuacion p = getPuntosPareja(j.getJugador().getId());

		// sin truco ni envido 1 PUNTO
		p.sumarPuntos(1);

	}

	// TODO agregar

	private Puntuacion getPuntosParejaContraria(int idJugador) {
		for (Puntuacion pun : puntos)
			/// TODO VER!!
			if (!pun.tieneJugador(idJugador))
				return pun;

		return null;
	}

	private Puntuacion getPuntosPareja(int idJugador) {
		for (Puntuacion pun : puntos)
			/// TODO VER!!
			if (pun.tieneJugador(idJugador))
				return pun;

		return null;
	}

	public void jugarCarta(int numero, String palo) throws JugadorException, CartaException {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).jugarCarta(jugadorOrden, numero, palo);
		jugadorOrden++;
	}

	public boolean finalizoMano() {

		jugadaMayor = this.bazas.get(this.bazas.size() - 1).jugadaMayor();

		if (this.bazas.get(this.bazas.size() - 1).finalizoBaza()) {

			Pareja g = getPareja(jugadaMayor.getJugador().getId());

			if (this.bazas.size() < 3) {

				if (historicoPuntos.indexOf(g) > 0) {
					ganadorBaza1 = g;

					return true;

				} else
					historicoPuntos.add(g);

				cambiarOrden();
				altaBaza();

			} else {

				Puntuacion pmano = getPuntosPareja(jugadaMayor.getJugador().getId());
				pmano.sumarPuntos(1);

				ganadorBaza1 = g;

				return true;
			}

		}
		return false;
	}

	private void cambiarOrden() {
		// preguntar quien gano , ponerlo adelante
		Jugador jugador = jugadaMayor.getJugador();

		int i = 0;
		int j = jugadores.indexOf(jugador);

		List<Jugador> jugadoresNuevo = new ArrayList<Jugador>();

		jugadoresNuevo.add(jugador);

		while (i < 3) {
			j++;

			if (j > 3)
				j = 0;

			jugadoresNuevo.add(jugadores.get(j));

			i++;

		}
		jugadores = jugadoresNuevo;
	}

	public boolean sePuedeCantarEnvido() {
		// TODO Auto-generated method stub
		return false;
	}

	public void puntosDbg(int idPareja) {
		// TODO Auto-generated method stub
		for (Puntuacion p : this.puntos) {
			if (idPareja == p.getPareja().getIdPareja()) {
				System.out.println("Puntos =>" + p.getPuntos());
			}

		}
	}

}
