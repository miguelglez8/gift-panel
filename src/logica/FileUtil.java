package logica;

import java.io.*;
import java.util.*;

public abstract class FileUtil {

	/**
	 * Carga los artículos del fichero con nombre "nombreFicheroEntrada" en la lista "listaUsuarios"
	 * @param nombreFicheroEntrada
	 * @param listaUsuarios
	 */
	public static void loadFileUsuarios(String nombreFicheroEntrada, List<Usuario> listaUsuarios) {
		
	    String linea;
	    String[] datosUsuarios= null;	   
	    
	    try {
	    	 BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
	    	 while (fichero.ready()) {
	    		linea = fichero.readLine();
	    		datosUsuarios = linea.split("@");
	    		listaUsuarios.add(new Usuario(datosUsuarios[0],datosUsuarios[1], Integer.parseInt(datosUsuarios[2])));
	    	}
	    	fichero.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("El archivo no se ha encontrado.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("Error de entrada/salida.");
	    } 
	  }
	
	/**
	 * Carga los artículos del fichero con nombre "nombreFicheroEntrada" en la lista "listaRegalos"
	 * @param nombreFicheroEntrada
	 * @param listaRegalos
	 */
	public static void loadFileRegalos(String nombreFicheroEntrada, List<Regalo> listaRegalos) {
		
	    String linea;
	    String[] datosRegalos= null;	   
	    
	    try {
	    	 BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
	    	 while (fichero.ready()) {
	    		linea = fichero.readLine();
	    		datosRegalos = linea.split("@");
	    		listaRegalos.add(new Regalo(datosRegalos[0],datosRegalos[1].charAt(0),datosRegalos[2],datosRegalos[3],Integer.parseInt(datosRegalos[4])));
	    	}
	    	fichero.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("El archivo no se ha encontrado.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("Error de entrada/salida.");
	    } 
	  }
	
	/**
	 * Guarda en un fichero con nombre "nombreFicheroSalida" las entregas que hay en "listaEntregas", las va añadiendo al fichero
	 * @param nombreFicheroSalida
	 * @param listaEntregas 
	 */
	public static void saveToFileEntregas(String nombreFicheroSalida, List<Entrega> listaEntregas){
		try {
				BufferedWriter fichero;
				fichero = new BufferedWriter(new FileWriter("files/" + nombreFicheroSalida + ".dat", true));
				String linea = "";
			      for (int i=0; i < listaEntregas.size(); i++) {
			        linea += listaEntregas.get(i).toString() + "\n";
			    }
			    fichero.append(linea);
			    fichero.close();
		}

		catch (FileNotFoundException fnfe) {
		      System.out.println("El archivo no se ha podido guardar");
		    }
		catch (IOException ioe) {
		      new RuntimeException("Error de entrada/salida");
		}
	  }
	
	/**
	 * Guarda en un fichero con nombre "nombreFicheroSalida" los usuarios de la lista
	 * @param nombreFicheroSalida
	 * @param listaUsuarios 
	 */
	public static void saveToFileUsuarios(String nombreFicheroSalida, List<Usuario> listaUsuarios){
		try {
				BufferedWriter fichero;
				fichero = new BufferedWriter(new FileWriter(nombreFicheroSalida));
		        String linea = "";
		        for (int i=0; i < listaUsuarios.size(); i++) {
		        	linea += listaUsuarios.get(i).toString() + "\n";
		        }
		        fichero.append(linea.substring(0, linea.length()-1));
		        fichero.close();
			}

		catch (FileNotFoundException fnfe) {
		      System.out.println("El archivo no se ha podido guardar");
		    }
		catch (IOException ioe) {
		      new RuntimeException("Error de entrada/salida");
		}
	  }
}
