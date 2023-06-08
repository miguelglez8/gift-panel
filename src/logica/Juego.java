package logica;

public class Juego {
	private Casilla[] casillas;
	private int intentos;
	private int puntos;
	
	public static final int TAM = 25;
	public static final int INTENTOS = 3;
	
	public Juego() {
		casillas = new Casilla[TAM];
		this.intentos = INTENTOS;
		this.puntos = 0;
		rellenarCasillas();
	}
	
	public void inicializar() {
		rellenarCasillas();
		this.intentos = INTENTOS;
		this.puntos = 0;
	}

	private void rellenarCasillas() {
		rellenarSinPremio();
		colocaTipoCasilla(1, new Casilla(1000));
		colocaTipoCasilla(5, new Casilla(250));
		colocaTipoCasilla(8, new Casilla(50));
		colocaTipoCasilla(1, new CasillaX2Puntos(-1));
		colocaTipoCasilla(2, new CasillaEspecial(-1));
	}

	private void rellenarSinPremio() {
		for(int i=0; i < TAM; i++) {
			casillas[i] = new Casilla(0);
		}
	}
	
	private void colocaTipoCasilla(int numeroCasillas, Casilla casilla) {
		int posicionCasilla;
		for (int i = 0; i < numeroCasillas; i++) {
			do {
				posicionCasilla = lanzar(TAM);
			} while (! (casillas[posicionCasilla].getPuntos()==0));
			casillas[posicionCasilla] = casilla;
		}

	}
	
	public int lanzar(int max) { 
		return ((int) (Math.random() * max));
	}

	public void seleccionaCasilla(int i){
		intentos--;
		if (casillas[i] instanceof CasillaX2Puntos) {
			this.puntos = puntos * 2;
			return;
		} else if (casillas[i] instanceof CasillaEspecial) {
			intentos++;
			return;
		}
		puntos += casillas[i].getPuntos();
	}
	
	public boolean isPartidaFinalizada() {
		return intentos == 0;
	}
	
	public Casilla[] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[] casillas) {
		this.casillas = casillas;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	

}
