package logica;

import java.util.ArrayList;
import java.util.List;

public class Regalos {
	private static final String FICHERO_REGALOS = "files/regalos.dat";
	private List<Regalo> listaRegalos = null; 
	private List<Regalo> listaRegalosTipo = new ArrayList<Regalo>(); 
	private List<Regalo> listaRegalosFiltroPrecio = new ArrayList<Regalo>();
	
	public List<Regalo> getListaRegalos() {
		return listaRegalos;
	}
	
	/**
	 * Constructor sin parámetros, crea una lista de regalos y carga los usuarios del fichero "regalos.dat"
	 */
	public Regalos(){
		listaRegalos = new ArrayList<Regalo>();
		cargarRegalos();
	}

	/**
	 * Carga los regalos del fichero en la lista
	 */
	private void cargarRegalos(){
		FileUtil.loadFileRegalos(FICHERO_REGALOS, listaRegalos);
	}

	/**
	 * Devuelve un array de regalos
	 * @return regalos
	 */
	public Regalo[] getRegalos(){
		Regalo[] regalos = listaRegalos.toArray(new Regalo[listaRegalos.size()]); 
		return regalos;	
	}
	
	public List<Regalo> getRegalos(String tipo, int total) {
		listaRegalosTipo.clear();
		for (Regalo regalo : listaRegalos) {
			if (regalo.getCodigo().contains(tipo) && regalo.getPuntos() <= total) {
				this.listaRegalosTipo.add(regalo);
			}
		}
		return listaRegalosTipo;
	}
	
	
	public List<Regalo> filtrarRegalosPrecio(int total) {
		listaRegalosFiltroPrecio.clear();
		for (Regalo regalo : listaRegalos) {
			if (regalo.getPuntos() <= total) {
				listaRegalosFiltroPrecio.add(regalo);
			}
		}
		return listaRegalosFiltroPrecio;
	}


}
