package igu;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaTarjetaFidelizacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelTarjetaCliente;
	private JPanel panelLabelDisfrutaPremios;
	private JLabel lblNewLabelDisfrutaPremios;
	private JLabel lblNewLabelTarjeta;
	private JLabel lblNewLabelUsuarioTarjeta;
	private JLabel lblNewLabelIdentificadorTarjeta;
	private JTextField textField;
	private JLabel lblNewLabelLogo;
	private JLabel lblNewLabelUsuario;
	private JLabel lblNewLabelMinisterio;
	private JLabel lblNewLabelDe;
	private JLabel lblNewLabelEspaña;
	private JPanel panelLabelNavidad;
	private JLabel lblNewLabelNavidad;
	private JLabel lblNewLabelPremios;
	private JLabel lblNewLabelEscudo;
	private JTextField textField_1;
	private JButton btnNewButtonOk;
	private JLabel lblNewLabelPanelRegalos;

	public VentanaTarjetaFidelizacion() {
		setTitle("Observe el reverso de su tarjeta de cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaTarjetaFidelizacion.class.getResource("/img/panelderegalos.PNG")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 654, 559);
		getContentPane().setLayout(null);
		getContentPane().add(getPanelTarjetaCliente_1());
		getContentPane().add(getBtnNewButtonOk());
		getContentPane().add(getLblNewLabelPanelRegalos());
	}
	private JPanel getPanelTarjetaCliente_1() {
		if (panelTarjetaCliente == null) {
			panelTarjetaCliente = new JPanel();
			panelTarjetaCliente.setLayout(null);
			panelTarjetaCliente.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
			panelTarjetaCliente.setBackground(new Color(175, 238, 238));
			panelTarjetaCliente.setBounds(27, 39, 529, 391);
			panelTarjetaCliente.add(getPanelLabelDisfrutaPremios_1());
			panelTarjetaCliente.add(getLblNewLabelTarjeta_1());
			panelTarjetaCliente.add(getLblNewLabelUsuarioTarjeta_1());
			panelTarjetaCliente.add(getLblNewLabelIdentificadorTarjeta_1());
			panelTarjetaCliente.add(getTextField());
			panelTarjetaCliente.add(getLblNewLabelLogo_1());
			panelTarjetaCliente.add(getLblNewLabelUsuario_1());
			panelTarjetaCliente.add(getLblNewLabelMinisterio_1());
			panelTarjetaCliente.add(getLblNewLabelDe_1());
			panelTarjetaCliente.add(getLblNewLabelEspaña_1());
			panelTarjetaCliente.add(getPanelLabelNavidad_1());
			panelTarjetaCliente.add(getLblNewLabelPremios_1());
			panelTarjetaCliente.add(getLblNewLabelEscudo_1());
			panelTarjetaCliente.add(getTextField_1());
		}
		return panelTarjetaCliente;
	}
	private JPanel getPanelLabelDisfrutaPremios_1() {
		if (panelLabelDisfrutaPremios == null) {
			panelLabelDisfrutaPremios = new JPanel();
			panelLabelDisfrutaPremios.setLayout(null);
			panelLabelDisfrutaPremios.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(127, 255, 212), new Color(127, 255, 212), new Color(127, 255, 212), new Color(127, 255, 212)));
			panelLabelDisfrutaPremios.setBackground(new Color(176, 224, 230));
			panelLabelDisfrutaPremios.setBounds(0, 312, 531, 39);
			panelLabelDisfrutaPremios.add(getLblNewLabelDisfrutaPremios_1());
		}
		return panelLabelDisfrutaPremios;
	}
	private JLabel getLblNewLabelDisfrutaPremios_1() {
		if (lblNewLabelDisfrutaPremios == null) {
			lblNewLabelDisfrutaPremios = new JLabel("PODR\u00C1S DISFRUTAR DE PREMIOS EN EL PANEL");
			lblNewLabelDisfrutaPremios.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelDisfrutaPremios.setBackground(new Color(176, 224, 230));
			lblNewLabelDisfrutaPremios.setBounds(0, 11, 503, 17);
		}
		return lblNewLabelDisfrutaPremios;
	}
	private JLabel getLblNewLabelTarjeta_1() {
		if (lblNewLabelTarjeta == null) {
			lblNewLabelTarjeta = new JLabel("TARJETA DE FIDELIZACI\u00D3N");
			lblNewLabelTarjeta.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelTarjeta.setBounds(115, 169, 163, 14);
		}
		return lblNewLabelTarjeta;
	}
	private JLabel getLblNewLabelUsuarioTarjeta_1() {
		if (lblNewLabelUsuarioTarjeta == null) {
			lblNewLabelUsuarioTarjeta = new JLabel("USUARIO:");
			lblNewLabelUsuarioTarjeta.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelUsuarioTarjeta.setBounds(30, 207, 74, 20);
		}
		return lblNewLabelUsuarioTarjeta;
	}
	private JLabel getLblNewLabelIdentificadorTarjeta_1() {
		if (lblNewLabelIdentificadorTarjeta == null) {
			lblNewLabelIdentificadorTarjeta = new JLabel("IDENTIFICADOR:");
			lblNewLabelIdentificadorTarjeta.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelIdentificadorTarjeta.setBounds(10, 258, 95, 14);
		}
		return lblNewLabelIdentificadorTarjeta;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText("miguelgonzaleznavarro123");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBounds(125, 207, 163, 20);
		}
		return textField;
	}
	private JLabel getLblNewLabelLogo_1() {
		if (lblNewLabelLogo == null) {
			lblNewLabelLogo = new JLabel("");
			lblNewLabelLogo.setIcon(new ImageIcon(VentanaTarjetaFidelizacion.class.getResource("/img/logoPanelRegalos.PNG")));
			lblNewLabelLogo.setBackground(new Color(175, 238, 238));
			lblNewLabelLogo.setBounds(217, 0, 170, 152);
		}
		return lblNewLabelLogo;
	}
	private JLabel getLblNewLabelUsuario_1() {
		if (lblNewLabelUsuario == null) {
			lblNewLabelUsuario = new JLabel("");
			lblNewLabelUsuario.setIcon(new ImageIcon(VentanaTarjetaFidelizacion.class.getResource("/img/usuario.PNG")));
			lblNewLabelUsuario.setBackground(new Color(175, 238, 238));
			lblNewLabelUsuario.setBounds(386, 0, 145, 152);
		}
		return lblNewLabelUsuario;
	}
	private JLabel getLblNewLabelMinisterio_1() {
		if (lblNewLabelMinisterio == null) {
			lblNewLabelMinisterio = new JLabel("MINISTERIO");
			lblNewLabelMinisterio.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelMinisterio.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelMinisterio.setBounds(137, 45, 70, 14);
		}
		return lblNewLabelMinisterio;
	}
	private JLabel getLblNewLabelDe_1() {
		if (lblNewLabelDe == null) {
			lblNewLabelDe = new JLabel("DE");
			lblNewLabelDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelDe.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelDe.setBounds(147, 70, 46, 14);
		}
		return lblNewLabelDe;
	}
	private JLabel getLblNewLabelEspaña_1() {
		if (lblNewLabelEspaña == null) {
			lblNewLabelEspaña = new JLabel("ESPA\u00D1A");
			lblNewLabelEspaña.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelEspaña.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelEspaña.setBounds(147, 95, 46, 14);
		}
		return lblNewLabelEspaña;
	}
	private JPanel getPanelLabelNavidad_1() {
		if (panelLabelNavidad == null) {
			panelLabelNavidad = new JPanel();
			panelLabelNavidad.setLayout(null);
			panelLabelNavidad.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(127, 255, 212), new Color(127, 255, 212), new Color(127, 255, 212), new Color(127, 255, 212)));
			panelLabelNavidad.setBackground(new Color(176, 224, 230));
			panelLabelNavidad.setBounds(0, 350, 531, 39);
			panelLabelNavidad.add(getLblNewLabelNavidad_1());
		}
		return panelLabelNavidad;
	}
	private JLabel getLblNewLabelNavidad_1() {
		if (lblNewLabelNavidad == null) {
			lblNewLabelNavidad = new JLabel("S\u00D3LO EN NAVIDAD");
			lblNewLabelNavidad.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelNavidad.setBackground(new Color(176, 224, 230));
			lblNewLabelNavidad.setBounds(0, 11, 503, 17);
		}
		return lblNewLabelNavidad;
	}
	private JLabel getLblNewLabelPremios_1() {
		if (lblNewLabelPremios == null) {
			lblNewLabelPremios = new JLabel("");
			lblNewLabelPremios.setIcon(new ImageIcon(VentanaTarjetaFidelizacion.class.getResource("/img/panelpremios.PNG")));
			lblNewLabelPremios.setBounds(337, 169, 113, 119);
		}
		return lblNewLabelPremios;
	}
	private JLabel getLblNewLabelEscudo_1() {
		if (lblNewLabelEscudo == null) {
			lblNewLabelEscudo = new JLabel("");
			lblNewLabelEscudo.setIcon(new ImageIcon(VentanaTarjetaFidelizacion.class.getResource("/img/escudoespa\u00F1a.PNG")));
			lblNewLabelEscudo.setBackground(new Color(175, 238, 238));
			lblNewLabelEscudo.setBounds(0, 0, 127, 152);
		}
		return lblNewLabelEscudo;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setText("MGN123");
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(125, 255, 163, 20);
		}
		return textField_1;
	}
	private JButton getBtnNewButtonOk() {
		if (btnNewButtonOk == null) {
			btnNewButtonOk = new JButton("OK");
			btnNewButtonOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButtonOk.setToolTipText("Volver a la ventana de registro");
			btnNewButtonOk.setMnemonic('O');
			btnNewButtonOk.setBackground(Color.CYAN);
			btnNewButtonOk.setBounds(567, 468, 61, 23);
		}
		return btnNewButtonOk;
	}
	private JLabel getLblNewLabelPanelRegalos() {
		if (lblNewLabelPanelRegalos == null) {
			lblNewLabelPanelRegalos = new JLabel("www.panelderegalos.es");
			lblNewLabelPanelRegalos.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 14));
			lblNewLabelPanelRegalos.setBounds(202, 444, 170, 25);
		}
		return lblNewLabelPanelRegalos;
	}
}
