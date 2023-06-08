package logica;

import java.util.ArrayList;
import java.util.List;

public class Usuarios {
	private static final String FICHERO_USUARIOS = "files/clientes.dat";
	private List<Usuario> listaUsuarios = null; 
	
	/**
	 * Constructor sin parámetros, crea una lista de usuarios y carga los usuarios del fichero "clientes.dat"
	 */
	public Usuarios(){
		listaUsuarios = new ArrayList<Usuario>();
		cargarUsuarios();
	}
	
	/**
	 * Carga los usuarios del fichero en la lista
	 */
	private void cargarUsuarios(){
		FileUtil.loadFileUsuarios(FICHERO_USUARIOS, listaUsuarios);
	}

	/**
	 * Devuelve un array de usuarios
	 * @return usuarios
	 */
	public Usuario[] getArticulos(){
		Usuario[] articulos = listaUsuarios.toArray(new Usuario[listaUsuarios.size()]); 
		return articulos;	
	}
	
	public boolean compruebaIdUsuario(String idCliente) {
		for (int i=0; i < listaUsuarios.size(); i++) {
			if (idCliente.equals(listaUsuarios.get(i).getIdCliente())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean compruebaPuedeJugarUsuario(String idCliente) {
		if (compruebaIdUsuario(idCliente)==false) { return false; }
		for (int i=0; i < listaUsuarios.size(); i++) {
			if (idCliente.equals(listaUsuarios.get(i).getIdCliente())) {
				if (listaUsuarios.get(i).getPuedeJugar()==1) {
					listaUsuarios.get(i).actualizarJugador();
					return true;
				} return false;
			}
		}
		return false;
	}

	public String getNombre(String id) {
		for (int i=0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getIdCliente().equals(id)) {
				return listaUsuarios.get(i).getNombreYApellidos();
			}
		}
		return null;
	}

	public void inicializar() {
		listaUsuarios.clear();
		cargarUsuarios();
	}
	
	public void actualizaFichero() {
		FileUtil.saveToFileUsuarios(FICHERO_USUARIOS, listaUsuarios);
	}
 
}
