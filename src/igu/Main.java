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
							+ "El objetivo de este juego será conseguir el mayor número de puntos, para así, conseguir regalos para la Navidad\n"
							+ "En primer lugar, regístrese si es usted cliente en la web (con su identificador), y pase a la siguiente ventana "
							+ "(recibirá instrucciones sobre donde está situado el identificador de la tarjeta)\n"
							+ "Después, dispondrás de tres oportunidades para desvelar casillas, las cuales contendrán diferentes premios\n"
							+ "A continuación, podrás elegir regalos conforme a la puntuación que pueden ser de 4 categorías distintas\n"
							+ "Solo quedaría en último lugar confirmar los premios, y ya los recibirá a domicilio\n"
							+ "Para acceder a un manual de ayuda de la aplicación, pulse F1 en cualquiera de las ventanas\n"
							+ "Le deseamos mucha suerte , ¡ Gracias por participar !", "Bienvenido/a al Panel de Regalos", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
