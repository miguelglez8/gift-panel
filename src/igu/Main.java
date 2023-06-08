package igu;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import logica.Entregas;
import logica.Juego;
import logica.Regalos;
import logica.Usuarios;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
		Regalos regalos = new Regalos();
		Entregas entregas = new Entregas();
		Usuarios usuarios = new Usuarios();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Properties prop = new Properties();
					prop.put("logoString", "");
					prop.put("focusColor", "0 0 0");
					HiFiLookAndFeel.setCurrentTheme(prop);
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					VentanaRegistro frame = new VentanaRegistro(juego, regalos, entregas, usuarios);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "Hola, bienvenido/a al Panel de Regalos:\n"
							+ "El objetivo de este juego ser� conseguir el mayor n�mero de puntos, para as�, conseguir regalos para la Navidad\n"
							+ "En primer lugar, reg�strese si es usted cliente en la web (con su identificador), y pase a la siguiente ventana "
							+ "(recibir� instrucciones sobre donde est� situado el identificador de la tarjeta)\n"
							+ "Despu�s, dispondr�s de tres oportunidades para desvelar casillas, las cuales contendr�n diferentes premios\n"
							+ "A continuaci�n, podr�s elegir regalos conforme a la puntuaci�n que pueden ser de 4 categor�as distintas\n"
							+ "Solo quedar�a en �ltimo lugar confirmar los premios, y ya los recibir� a domicilio\n"
							+ "Para acceder a un manual de ayuda de la aplicaci�n, pulse F1 en cualquiera de las ventanas\n"
							+ "Le deseamos mucha suerte , � Gracias por participar !", "Bienvenido/a al Panel de Regalos", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
