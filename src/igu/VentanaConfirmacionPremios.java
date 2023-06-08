package igu;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import logica.Entregas;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class VentanaConfirmacionPremios extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel panelPrincipal = new JPanel();
	private JButton btnNewButtonFinalizar;
	private JLabel lblNewLabelPanel;
	private JLabel lblNewLabelDon;
	private JTextField textFieldUsuario;
	private JLabel lblNewLabelEnhorabuena;
	private JLabel lblNewLabelParticipar;
	private JLabel lblNewLabelTotal;
	private JTextField textFieldPuntos;
	private JLabel lblNewLabelPuntos;
	private JLabel lblNewLabelPulseFinalizar;

	private Entregas entregas;
	private VentanaPanelEntregas ventanaPanelEntregas;
	
	public VentanaConfirmacionPremios(Entregas entregas, VentanaPanelEntregas ventanaPanelEntregas, HelpSet hs, HelpBroker hb) {
		this.entregas = entregas;
		this.ventanaPanelEntregas = ventanaPanelEntregas;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				btnNewButtonFinalizar.grabFocus();
			}
		});
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Ventana de Confirmaci\u00F3n de entrega");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacionPremios.class.getResource("/img/regalos.PNG")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnNewButtonFinalizar());
		getContentPane().add(getLblNewLabelPanel());
		getContentPane().add(getLblNewLabelDon());
		getContentPane().add(getTextFieldUsuario());
		getContentPane().add(getLblNewLabelEnhorabuena());
		getContentPane().add(getLblNewLabelParticipar());
		getContentPane().add(getLblNewLabelTotal());
		getContentPane().add(getTextFieldPuntos());
		getContentPane().add(getLblNewLabelPuntos());
		getContentPane().add(getLblNewLabelPulseFinalizar());
		panelPrincipal.setBounds(0, 0, 434, 261);
		configurarAyuda(hs, hb);
	}
	private void configurarAyuda(HelpSet hs, HelpBroker hb) {
		hb.enableHelpKey(getRootPane(),"finalizar", hs);
		hb.enableHelp(textFieldPuntos, "finalizar", hs);
		hb.enableHelp(textFieldUsuario, "finalizar", hs);
		hb.enableHelp(btnNewButtonFinalizar, "finalizar", hs);
	}
	
	private JButton getBtnNewButtonFinalizar() {
		if (btnNewButtonFinalizar == null) {
			btnNewButtonFinalizar = new JButton("Finalizar");
			btnNewButtonFinalizar.setToolTipText("Pulse para confirmar la entrega");
			btnNewButtonFinalizar.setMnemonic('F');
			btnNewButtonFinalizar.setBackground(new Color(0, 255, 127));
			btnNewButtonFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (confirmarFinalizarPartida()) {
						entregas.grabarPedido();
						inicializar();
					}
				}
			});
			btnNewButtonFinalizar.setBounds(335, 227, 89, 23);
		}
		return btnNewButtonFinalizar;
	}
	
	private boolean confirmarFinalizarPartida() {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres terminar la partida ?", "Terminar partida",
				JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
	
	private void inicializar() {
		dispose();
		ventanaPanelEntregas.dispose();
		ventanaPanelEntregas.getVentanaPanelRegalos().dispose();
		ventanaPanelEntregas.getVentanaPanelRegalos().getVentanaPanelCasillas().dispose();
		ventanaPanelEntregas.getVentanaPanelRegalos().getVentanaPanelCasillas().getVentanaRegistro().inicializarSinLeerFicheroUsuarios();
	}
	
	private JLabel getLblNewLabelPanel() {
		if (lblNewLabelPanel == null) {
			lblNewLabelPanel = new JLabel("PANEL DE REGALOS");
			lblNewLabelPanel.setFont(new Font("Arial Black", Font.BOLD, 16));
			lblNewLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelPanel.setBounds(107, 11, 212, 23);
		}
		return lblNewLabelPanel;
	}
	private JLabel getLblNewLabelDon() {
		if (lblNewLabelDon == null) {
			lblNewLabelDon = new JLabel("Don/Do\u00F1a :");
			lblNewLabelDon.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelDon.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelDon.setBounds(57, 45, 89, 23);
		}
		return lblNewLabelDon;
	}
	private JTextField getTextFieldUsuario() {
		if (textFieldUsuario == null) {
			textFieldUsuario = new JTextField();
			textFieldUsuario.setForeground(Color.BLACK);
			textFieldUsuario.setBackground(Color.WHITE);
			textFieldUsuario.setEditable(false);
			textFieldUsuario.setText(entregas.getNombre());
			textFieldUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldUsuario.setBounds(147, 45, 241, 23);
			textFieldUsuario.setColumns(10);
		}
		return textFieldUsuario;
	}
	private JLabel getLblNewLabelEnhorabuena() {
		if (lblNewLabelEnhorabuena == null) {
			lblNewLabelEnhorabuena = new JLabel("\u00A1 ENHORABUENA !");
			lblNewLabelEnhorabuena.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelEnhorabuena.setForeground(Color.BLUE);
			lblNewLabelEnhorabuena.setBounds(157, 79, 134, 30);
		}
		return lblNewLabelEnhorabuena;
	}
	private JLabel getLblNewLabelParticipar() {
		if (lblNewLabelParticipar == null) {
			lblNewLabelParticipar = new JLabel("\u00A1 Gracias por participar !");
			lblNewLabelParticipar.setForeground(Color.BLACK);
			lblNewLabelParticipar.setBounds(144, 105, 175, 30);
		}
		return lblNewLabelParticipar;
	}
	private JLabel getLblNewLabelTotal() {
		if (lblNewLabelTotal == null) {
			lblNewLabelTotal = new JLabel("Has conseguido un total de ");
			lblNewLabelTotal.setBounds(57, 146, 167, 30);
		}
		return lblNewLabelTotal;
	}
	private JTextField getTextFieldPuntos() {
		if (textFieldPuntos == null) {
			textFieldPuntos = new JTextField();
			textFieldPuntos.setEditable(false);
			textFieldPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPuntos.setText(entregas.getPuntosJuego() + "");
			textFieldPuntos.setBounds(222, 150, 57, 23);
			textFieldPuntos.setColumns(10);
		}
		return textFieldPuntos;
	}
	private JLabel getLblNewLabelPuntos() {
		if (lblNewLabelPuntos == null) {
			lblNewLabelPuntos = new JLabel("puntos");
			lblNewLabelPuntos.setBounds(289, 154, 51, 14);
		}
		return lblNewLabelPuntos;
	}
	private JLabel getLblNewLabelPulseFinalizar() {
		if (lblNewLabelPulseFinalizar == null) {
			lblNewLabelPulseFinalizar = new JLabel("Pulse Finalizar para confirmar la entrega");
			lblNewLabelPulseFinalizar.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelPulseFinalizar.setForeground(new Color(60, 179, 113));
			lblNewLabelPulseFinalizar.setBounds(94, 187, 256, 23);
		}
		return lblNewLabelPulseFinalizar;
	}

	protected VentanaPanelEntregas getVentanaPanelEntregas() {
		return ventanaPanelEntregas;
	}
}
