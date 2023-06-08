package logica;

import java.util.ArrayList;
import java.util.List;

public class Entregas {
	// atributos de la clase
	private List<Entrega> listaEntregas = null; // lista de entregas
	private String id = null; // id del usuario
	private String nombre = null; // nombre del usuario
	private int puntosJuego = 0; // puntos del usuario en el juego
	private int puntosEntregas = 0; // puntos del usuario según sus entregas

	/**
	* Crea una lista de entregas y borra los artículos anteriores
	*/
	public Entregas(){
		listaEntregas = new ArrayList<Entrega>();
	}

	/**
	* Borra las entregas
	*/
	public void inicializar(){
		listaEntregas.clear();
		this.id = null;
		this.nombre = null;
		this.puntosEntregas = 0;
		this.puntosJuego = 0;
	}
		
	/**
	* Añade a la lista de entregas la entrega según las unidades
	* @param entrega
	* @param unidades
	* @return true si se puede añadir
	*/
	public boolean add(Entrega entrega, int unidades){
		if (sePuedeComprar(entrega, unidades)) {
			Entrega entregaPedido = null;
			
			for (Entrega a : listaEntregas){
				if (a.getCodigo().equals(entrega.getCodigo()))
				{
					entregaPedido = a;
					entregaPedido.setUnidades(entregaPedido.getUnidades() + unidades);
					puntosEntregas = puntosEntregas - entregaPedido.getPrecio() * unidades;
					break;
				}
			}
				
			if (entregaPedido == null){
				Entrega entregaAPedido  = new Entrega(entrega);
				entregaAPedido.setUnidades(unidades);
				listaEntregas.add(entregaAPedido);
				puntosEntregas = puntosEntregas - entregaAPedido.getPrecio() * unidades;
			}
			
			return true;
		} else {
			return false;
		}		
	}

	public boolean sePuedeComprar(Entrega entrega, int unidades) {
		return entrega.getPrecio() * unidades <= puntosEntregas;
	}
		
	/**
	* Elimina de la lista el articulo con sus unidades
    * @param entrega
	* @param unidades
	*/
	public void remove(Entrega entrega, int unidades){
		Entrega entregaPedido = null;
		for (Entrega a : listaEntregas){
			if (a.getCodigo().equals(entrega.getCodigo())) {
				entregaPedido = a;
			}
		}
		if (entregaPedido != null) {
			if (entregaPedido.getUnidades() > unidades) {
				entregaPedido.setUnidades(entregaPedido.getUnidades() - unidades);
				puntosEntregas = puntosEntregas + entregaPedido.getPrecio() * unidades;
			} else {
				listaEntregas.remove(entregaPedido);
				puntosEntregas = puntosEntregas + entregaPedido.getPrecio() * entregaPedido.getUnidades();
			}
		}
	}

	/**
	* Devuelve el precio total de todos las entregas del pedido
	* @return precio
	*/
	public int getTotal() {
		int precio = 0;
		for (Entrega a : listaEntregas){
			if (a.isConfirmado()) {
				precio += a.getPrecio()* a.getUnidades();
			}
		}
		return precio;
	}
		
		
	/**
	* Guarda en un fichero de texto las entregas
	*/
	public void grabarPedido() {
		List<Entrega> listaEntregasConfirmadas = new ArrayList<Entrega>();
		for (int i=0; i < listaEntregas.size(); i++) {
			if (listaEntregas.get(i).isConfirmado()) {
				listaEntregasConfirmadas.add(listaEntregas.get(i));
			}
		}
		FileUtil.saveToFileEntregas("entregas", listaEntregasConfirmadas);
	}
		
	/**
	* Devuelve el método toString de cada artículo del pedido
	* @return cadena, de tipo String
	*/
	public String toString() {
		String result = "";
		for(int i=0;i<listaEntregas.size();i++) {
			if (listaEntregas.get(i).isConfirmado()) {
				result = result + listaEntregas.get(i).toStringPedido() + "\n";
			}
		}
		return result;
	}
	
	public void confirmarEntrega(String codigo) {
		for (int i=0; i < listaEntregas.size(); i++) {
			if (listaEntregas.get(i).getCodigo().equals(codigo)) {
				listaEntregas.get(i).confirmarEntrega();
			}
		}
	}
	
	public void desconfirmarEntrega() {
		for (int i=0; i < listaEntregas.size(); i++) {
			listaEntregas.get(i).setConfirmado(false);
		}
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public int getPuntosJuego() {
		return puntosJuego;
	}

	public void setPuntosJuego(int puntos) {
		this.puntosJuego = puntos;
	}

	public List<Entrega> getListaEntregas() {
		return listaEntregas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntosEntregas() {
		return puntosEntregas;
	}
	
	public void setPuntosEntregas(int puntos) {
		this.puntosEntregas = puntos;
	}

	public String toStringCarro() {
		if (listaEntregas.size()==0) { return ""; }
		String result = "[";
		for(int i=0;i<listaEntregas.size();i++) {
			result = result + listaEntregas.get(i).getNombre() + "(" + listaEntregas.get(i).getUnidades() + "),";
		}
		result = result.substring(0, result.length()-1);
		result = result + "]";
		return result;
	}

	
}
