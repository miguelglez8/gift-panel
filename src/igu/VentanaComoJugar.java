package igu;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class VentanaComoJugar extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabelComoJugar;
	private JTextArea txtAreaExplicacionJuego;
	private JButton btnNewButtonAtras;

	public VentanaComoJugar() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaComoJugar.class.getResource("/img/panelderegalos.PNG")));
		setTitle("Panel de Regalos : \u00BF C\u00F3mo jugar ?");
		setBounds(100, 100, 781, 188);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabelComoJugar());
		getContentPane().add(getTxtAreaExplicacionJuego());
		getContentPane().add(getBtnNewButtonAtras());
	}
	private JLabel getLblNewLabelComoJugar() {
		if (lblNewLabelComoJugar == null) {
			lblNewLabelComoJugar = new JLabel("\u00BF C\u00D3MO JUGAR ? En 5 sencillos pasos");
			lblNewLabelComoJugar.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelComoJugar.setFont(new Font("Arial Black", Font.BOLD, 13));
			lblNewLabelComoJugar.setBounds(44, 0, 721, 33);
		}
		return lblNewLabelComoJugar;
	}
	private JTextArea getTxtAreaExplicacionJuego() {
		if (txtAreaExplicacionJuego == null) {
			txtAreaExplicacionJuego = new JTextArea();
			txtAreaExplicacionJuego.setText("1 - Introduzca el identificador de la tarjeta de fidelizaci\u00F3n, val\u00EDdalo y haz click en Siguiente\n2 - Seleccione al menos tres casillas para destapar los premios y vaya a Seleccionar art\u00EDculos\n3 - Clasifique por categor\u00EDas (y filtra por puntos) los regalos y vaya a\u00F1adi\u00E9ndolos al carrito (click en A\u00F1adir)\n4 - Seleccione el carrrito, confirme (o elimine) los art\u00EDculos y pulsa en Continuar\n5 - Presione Finalizar para registrar su entrega y volver a la pantalla de inicio de la web");
			txtAreaExplicacionJuego.setBounds(0, 47, 765, 102);
		}
		return txtAreaExplicacionJuego;
	}
	private JButton getBtnNewButtonAtras() {
		if (btnNewButtonAtras == null) {
			btnNewButtonAtras = new JButton("");
			btnNewButtonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButtonAtras.setIcon(new ImageIcon(VentanaComoJugar.class.getResource("/img/flechahaciaatras.PNG")));
			btnNewButtonAtras.setToolTipText("Volver atr\u00E1s");
			btnNewButtonAtras.setBorder(new LineBorder(Color.BLACK));
			btnNewButtonAtras.setBackground(Color.BLACK);
			btnNewButtonAtras.setBounds(0, 0, 31, 35);
		}
		return btnNewButtonAtras;
	}
}
