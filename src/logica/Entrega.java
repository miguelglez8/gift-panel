package logica;

public class Entrega {
	private String idCliente;
	private String codigo;
	private String fecha;
	private String observaciones;
	private int unidades;
	private int precio;
	private String nombre;
	private boolean confirmado;

	public Entrega(String idCliente, String codigo, String fecha, String observaciones, int precio, String nombre) {
		this.idCliente = idCliente;
		this.codigo = codigo;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.precio = precio;
		this.nombre = nombre;
		this.confirmado = false;
	}

	public Entrega(Entrega entrega) {
		this(entrega.idCliente, entrega.codigo, entrega.fecha, entrega.observaciones, entrega.precio, entrega.nombre);
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}
	
	public void confirmarEntrega() {
		this.confirmado = true;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		if (fecha == null && observaciones == null) {
			return idCliente + "@" + codigo;
		}
		return idCliente + "@" + codigo + "@" + fecha + "@"
				+ observaciones;
	}
	
	public String toStringPedido() {
		if (fecha == null) {
			return nombre + " - " + precio + " puntos - (Uds: " + unidades + ")";
		}
		return nombre + " - " + precio + " puntos (Uds: " + unidades + ")" + " Fecha : " + fecha;
	}

}
