package logica;

public class Usuario {
	private String idCliente;
	private String nombreYApellidos;
	private int puedeJugar; // 0 si no puede - 1 si puede
	
	public Usuario(String idCliente, String nombreYApellidos, int puedeJugar) {
		this.idCliente = idCliente;
		this.nombreYApellidos = nombreYApellidos;
		this.puedeJugar = puedeJugar;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombreYApellidos() {
		return nombreYApellidos;
	}


	public void setNombreYApellidos(String nombreYApellidos) {
		this.nombreYApellidos = nombreYApellidos;
	}


	public int getPuedeJugar() {
		return puedeJugar;
	}


	public void setPuedeJugar(int puedeJugar) {
		this.puedeJugar = puedeJugar;
	}


	public void actualizarJugador() {
		this.puedeJugar = 0;
	}
	
	public String toString() {
		return idCliente + "@" + nombreYApellidos + "@" + puedeJugar;
	}

}
