package logica;

public class Casilla {
	private int puntos;
	
	public Casilla(int puntos) {
		this.puntos = puntos;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public String toString() {
		return "Premio : " + getPuntos() + " puntos";
	}


}
