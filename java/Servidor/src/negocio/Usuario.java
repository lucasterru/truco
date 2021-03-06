package negocio;

public class Usuario {
	private int idUsuario;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int puntaje;

	private String apodo;
	private String pass;
	private String email;

	private Categoria categoria;
	private boolean activo;

	public Usuario() {
	}

	public Usuario(String apodo, String email, String password) {
		super();
		setApodo(apodo);
		setEmail(email);
		setPass(password);
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}

	public void setPartidasPerdidas(int partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean validarLogin(String pass) {
		return getPass().equals(pass);
	}

	// TODO Agregar al Diagrama.
	public boolean esUsuario(String apodo) {
		return getApodo().equals(apodo);
	}
}
