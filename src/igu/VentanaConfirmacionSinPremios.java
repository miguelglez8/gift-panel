package igu;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import logica.Entregas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class VentanaConfirmacionSinPremios extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel panelPrincipal = new JPanel();
	private JButton btnNewButtonFinalizar;
	private JLabel lblNewLabelPanel;
	private JLabel lblNewLabelDon;
	private JTextField textFieldUsuario;
	private JLabel lblNewLabelSentimos;
	private JLabel lblNewLabelIntentelo;
	private JLabel lblNewLabelPremio;
	private JLabel lblNewLabelPulseFinalizar;

	private Entregas entregas;
	private VentanaPanelCasillas ventanaPanelCasillas;
	
	public VentanaConfirmacionSinPremios(Entregas entregas, VentanaPanelCasillas ventanaPanelCasillas, HelpSet hs, HelpBroker hb) {
		this.entregas = entregas;
		this.ventanaPanelCasillas = ventanaPanelCasillas;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				btnNewButtonFinalizar.grabFocus();
			}
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Fin de partida");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacionSinPremios.class.getResource("/img/regalos.PNG")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnNewButtonFinalizar());
		getContentPane().add(getLblNewLabelPanel());
		getContentPane().add(getLblNewLabelDon());
		getContentPane().add(getTextFieldUsuario());
		getContentPane().add(getLblNewLabelSentimos());
		getContentPane().add(getLblNewLabelIntentelo());
		getContentPane().add(getLblNewLabelPremio());
		getContentPane().add(getLblNewLabelPulseFinalizar());
		panelPrincipal.setBounds(0, 0, 434, 261);
		configurarAyuda(hs, hb);
	}
	private void configurarAyuda(HelpSet hs, HelpBroker hb) {
		hb.enableHelpKey(getRootPane(),"derrota", hs);
		hb.enableHelp(textFieldUsuario, "derrota", hs);
		hb.enableHelp(btnNewButtonFinalizar, "derrota", hs);
	}
	
	protected JButton getBtnNewButtonFinalizar() {
		if (btnNewButtonFinalizar == null) {
			btnNewButtonFinalizar = new JButton("Finalizar");
			btnNewButtonFinalizar.setToolTipText("Pulse para finalizar la partida");
			btnNewButtonFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (confirmarFinalizarPartida()) {
						inicializar();
					}
				}
			});
			btnNewButtonFinalizar.setMnemonic('F');
			btnNewButtonFinalizar.setBackground(new Color(0, 255, 127));
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
		ventanaPanelCasillas.dispose();
		ventanaPanelCasillas.getVentanaRegistro().inicializarSinLeerFicheroUsuarios();
	}
	
	private JLabel getLblNewLabelPanel() {
		if (lblNewLabelPanel == null) {
			lblNewLabelPanel = new JLabel("PANEL DE REGALOS");
			lblNewLabelPanel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelPanel.setFont(new Font("Arial Black", Font.BOLD, 16));
			lblNewLabelPanel.setBounds(109, 11, 212, 23);
		}
		return lblNewLabelPanel;
	}
	private JLabel getLblNewLabelDon() {
		if (lblNewLabelDon == null) {
			lblNewLabelDon = new JLabel("Don/Do\u00F1a :");
			lblNewLabelDon.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelDon.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelDon.setBounds(75, 45, 89, 23);
		}
		return lblNewLabelDon;
	}
	private JTextField getTextFieldUsuario() {
		if (textFieldUsuario == null) {
			textFieldUsuario = new JTextField();
			textFieldUsuario.setForeground(Color.BLACK);
			textFieldUsuario.setBackground(Color.WHITE);
			textFieldUsuario.setEditable(false);
			textFieldUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldUsuario.setColumns(10);
			textFieldUsuario.setText(entregas.getNombre());
			textFieldUsuario.setBounds(165, 45, 205, 23);
		}
		return textFieldUsuario;
	}
	private JLabel getLblNewLabelSentimos() {
		if (lblNewLabelSentimos == null) {
			lblNewLabelSentimos = new JLabel("LO SENTIMOS");
			lblNewLabelSentimos.setForeground(new Color(255, 0, 0));
			lblNewLabelSentimos.setBounds(165, 79, 96, 30);
		}
		return lblNewLabelSentimos;
	}
	private JLabel getLblNewLabelIntentelo() {
		if (lblNewLabelIntentelo == null) {
			lblNewLabelIntentelo = new JLabel("Int\u00E9ntelo de nuevo la pr\u00F3xima vez");
			lblNewLabelIntentelo.setForeground(Color.BLUE);
			lblNewLabelIntentelo.setBounds(119, 150, 188, 30);
		}
		return lblNewLabelIntentelo;
	}
	private JLabel getLblNewLabelPremio() {
		if (lblNewLabelPremio == null) {
			lblNewLabelPremio = new JLabel("No has conseguido obtener ning\u00FAn premio");
			lblNewLabelPremio.setBounds(109, 109, 261, 30);
		}
		return lblNewLabelPremio;
	}
	private JLabel getLblNewLabelPulseFinalizar() {
		if (lblNewLabelPulseFinalizar == null) {
			lblNewLabelPulseFinalizar = new JLabel("Pulse Finalizar para volver a jugar");
			lblNewLabelPulseFinalizar.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelPulseFinalizar.setForeground(new Color(60, 179, 113));
			lblNewLabelPulseFinalizar.setBounds(109, 194, 212, 23);
		}
		return lblNewLabelPulseFinalizar;
	}
	protected VentanaPanelCasillas getVentanaPanelCasillas() {
		return ventanaPanelCasillas;
	}
}
