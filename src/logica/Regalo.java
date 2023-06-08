package logica;

public class Regalo {
	private String codigo;
	private char seccion;
	private String denominacion;
	private String descripcion;
	private int puntos;
	
	public Regalo(String codigo, char seccion, String denominacion, String descripcion, int puntos) {
		this.codigo = codigo;
		this.seccion = seccion;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.puntos = puntos;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public char getSeccion() {
		return seccion;
	}
	public void setSeccion(char seccion) {
		this.seccion = seccion;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return denominacion + " [" + puntos + " puntos]";
	}
	
	
	
	

}
