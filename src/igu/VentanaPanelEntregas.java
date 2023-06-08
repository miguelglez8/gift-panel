package igu;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.Entregas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class VentanaPanelEntregas extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButtonOjo;
	private JScrollPane scrollPanePedido;
	private JTextArea textAreaPedido;
	private JButton btnNewButtonAtras;
	private JButton btnNewButtonContinuar;
	private JTextField textFieldPuntosCanjeados;
	private JTextField textFieldPuntosSobrantes;
	private JTabbedPane tabbedPaneEntregas;
	
	private JLabel lblNewLabelConfirmar;
	private JLabel lblNewLabelArtSeleccionados;
	private JLabel lblNewLabelInformacion;
	private JLabel lblNewLabelCanjeados;
	private JLabel lblNewLabelSobrantes;
	private JLabel lblNewLabelEliminar;
	
	private Entregas entregas;
	private VentanaPanelRegalos ventanaPanelRegalos;
	
	private HelpSet hs;
	private HelpBroker hb;
	
	public VentanaPanelEntregas(Entregas entregas, VentanaPanelRegalos ventanaPanelRegalos, HelpSet hs, HelpBroker hb) {
		this.entregas = entregas;
		this.ventanaPanelRegalos = ventanaPanelRegalos;
		this.hs = hs;
		this.hb = hb;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				inicializarVentanaEntregas();
			}
		});
		setTitle("Panel de Premios : Ventana de Confirmaci\u00F3n de Premios");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1109, 411);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.add(getPanel());
		panelPrincipal.add(getBtnNewButtonAtras());
		panelPrincipal.add(getBtnNewButtonContinuar());
		panelPrincipal.add(getTextFieldPuntosCanjeados());
		panelPrincipal.add(getTextFieldPuntosSobrantes());
		panelPrincipal.add(getTabbedPaneEntregas());
		panelPrincipal.add(getLblNewLabelConfirmar());
		panelPrincipal.add(getLblNewLabelArtSeleccionados());
		panelPrincipal.add(getLblNewLabelInformacion());
		panelPrincipal.add(getLblNewLabelCanjeados());
		panelPrincipal.add(getLblNewLabelSobrantes());
		panelPrincipal.add(getLblNewLabelEliminar());
		configurarAyuda();
	}
	private void configurarAyuda() {
		hb.enableHelpKey(getRootPane(),"entrega", hs);
		hb.enableHelp(btnNewButtonContinuar, "entrega", hs);
		hb.enableHelp(tabbedPaneEntregas, "entrega", hs);
		hb.enableHelp(textFieldPuntosCanjeados, "entrega", hs);
		hb.enableHelp(textFieldPuntosCanjeados, "entrega", hs);
		hb.enableHelp(btnNewButtonOjo, "pedido", hs);
	}
	private void inicializarVentanaEntregas() {
		if (confirmarEliminacionArticulos()) {
			entregas.desconfirmarEntrega();
			dispose();
		}
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 228, 383);
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getBtnNewButtonOjo());
			panel.add(getScrollPanePedido());
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("PEDIDO");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(129, 33, 60, 14);
		}
		return lblNewLabel;
	}
	private JButton getBtnNewButtonOjo() {
		if (btnNewButtonOjo == null) {
			btnNewButtonOjo = new JButton("");
			btnNewButtonOjo.setBorder(new LineBorder(Color.BLACK));
			btnNewButtonOjo.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					scrollPanePedido.setVisible(true);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					scrollPanePedido.setVisible(false);
				}
			});
			btnNewButtonOjo.setToolTipText("Visualiza el pedido haciendo click");
			btnNewButtonOjo.setIcon(new ImageIcon(VentanaPanelEntregas.class.getResource("/img/vistapedido.PNG")));
			btnNewButtonOjo.setBounds(20, 11, 82, 60);
		}
		return btnNewButtonOjo;
	}
	private JScrollPane getScrollPanePedido() {
		if (scrollPanePedido == null) {
			scrollPanePedido = new JScrollPane();
			scrollPanePedido.setBorder(new LineBorder(Color.CYAN));
			scrollPanePedido.setEnabled(false);
			scrollPanePedido.setVisible(false);
			scrollPanePedido.setBounds(0, 94, 228, 260);
			scrollPanePedido.setViewportView(getTextAreaPedido());
		}
		return scrollPanePedido;
	}
	protected JTextArea getTextAreaPedido() {
		if (textAreaPedido == null) {
			textAreaPedido = new JTextArea();
			textAreaPedido.setWrapStyleWord(true);
			textAreaPedido.setLineWrap(true);
			textAreaPedido.setEnabled(false);
			textAreaPedido.setEditable(false);
		}
		return textAreaPedido;
	}
	private JButton getBtnNewButtonAtras() {
		if (btnNewButtonAtras == null) {
			btnNewButtonAtras = new JButton("");
			btnNewButtonAtras.setBorder(new LineBorder(Color.BLACK));
			btnNewButtonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializarVentanaEntregas();
				}
			});
			btnNewButtonAtras.setBackground(new Color(0, 0, 0));
			btnNewButtonAtras.setToolTipText("Volver atr\u00E1s");
			btnNewButtonAtras.setIcon(new ImageIcon(VentanaPanelEntregas.class.getResource("/img/flechahaciaatras.PNG")));
			btnNewButtonAtras.setBounds(227, 0, 31, 28);
		}
		return btnNewButtonAtras;
	}
	
	private boolean confirmarEliminacionArticulos() {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres volver a la pantalla de selección de artículos ?\n"
				+ "Debe de tener en cuenta de que solo permanecerán en la entrega aquellos que no hayan sido eliminados\n"
				+ "Tendrá que volver a confirmar los artículos que haya confirmado previamente al cerrar la ventana", "Volver a la pantalla del panel de regalos",
				JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
		
	private boolean confirmarEntrega() {
		boolean confir = false;
		int resp;
		int puntos = entregas.getPuntosJuego() - entregas.getTotal();
		if (puntos!=0) {
			resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres finalizar el proceso de confirmación de regalos ?\n"
					+ "Debe de tener en cuenta de que solo recibirá en la entrega aquellos artículos que hayan sido confirmados en esta ventana\n"
					+ "Además, en caso de tener puntos sin canjear en premios, debe ser consciente de que va a perder " + puntos + " puntos",
					"Finalizar entrega", JOptionPane.INFORMATION_MESSAGE);
		} else {
			resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres finalizar el proceso de confirmación de regalos ?\n"
					+ "Debe de tener en cuenta de que solo recibirá en la entrega aquellos artículos que hayan sido confirmados en esta ventana\n",
					"Finalizar entrega", JOptionPane.INFORMATION_MESSAGE);
		}
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
	
	
	private JButton getBtnNewButtonContinuar() {
		if (btnNewButtonContinuar == null) {
			btnNewButtonContinuar = new JButton("Continuar");
			btnNewButtonContinuar.setToolTipText("Avanza a la pantalla final");
			btnNewButtonContinuar.setBackground(Color.GREEN);
			btnNewButtonContinuar.setMnemonic('C');
			btnNewButtonContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (entregas.getTotal()==0) {
						JOptionPane.showMessageDialog(null, "Debe de confirmar al menos un premio para que pueda pasar a la siguiente ventana de confirmación de premios\n"
								+ "Para ello seleccione sobre el botón verde confirmar en cada una de las pestañas de las entregas que desea confirmar",
							"Error de confirmación de premios", JOptionPane.WARNING_MESSAGE);
					} else {
						if (confirmarEntrega()) {
							crearVentanaPremios();
						}
					}
				}
			});
			btnNewButtonContinuar.setBounds(956, 327, 89, 23);
		}
		return btnNewButtonContinuar;
	}
	
	private void crearVentanaPremios() {
		VentanaConfirmacionPremios dialog = new VentanaConfirmacionPremios(entregas, this, hs, hb);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	protected JTextField getTextFieldPuntosCanjeados() {
		if (textFieldPuntosCanjeados == null) {
			textFieldPuntosCanjeados = new JTextField();
			textFieldPuntosCanjeados.setEditable(false);
			textFieldPuntosCanjeados.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPuntosCanjeados.setText("0");
			textFieldPuntosCanjeados.setBounds(556, 279, 86, 20);
			textFieldPuntosCanjeados.setColumns(10);
		}
		return textFieldPuntosCanjeados;
	}
	protected JTextField getTextFieldPuntosSobrantes() {
		if (textFieldPuntosSobrantes == null) {
			textFieldPuntosSobrantes = new JTextField();
			textFieldPuntosSobrantes.setEditable(false);
			textFieldPuntosSobrantes.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPuntosSobrantes.setBounds(556, 310, 86, 20);
			textFieldPuntosSobrantes.setColumns(10);
			textFieldPuntosSobrantes.setText(entregas.getPuntosJuego() + "");
		}
		return textFieldPuntosSobrantes;
	}
	protected JTabbedPane getTabbedPaneEntregas() {
		if (tabbedPaneEntregas == null) {
			tabbedPaneEntregas = new JTabbedPane(JTabbedPane.TOP);
			tabbedPaneEntregas.setBorder(new LineBorder(Color.CYAN));
			tabbedPaneEntregas.setBounds(273, 122, 594, 119);
			for (int i = 0; i < entregas.getListaEntregas().size(); i++) {
				tabbedPaneEntregas.addTab(entregas.getListaEntregas().get(i).getNombre(), null, new PanelEntrega(i, this, entregas.getListaEntregas().get(i), hb, hs), null);
				tabbedPaneEntregas.setToolTipTextAt(i, entregas.getListaEntregas().get(i).toStringPedido());
			}
			validate();
		}
		return tabbedPaneEntregas;
	}
	private JLabel getLblNewLabelConfirmar() {
		if (lblNewLabelConfirmar == null) {
			lblNewLabelConfirmar = new JLabel("Recuerda confirmar los art\u00EDculos que desee haciendo click en Confirmar en cada ventana, una vez que los haya confirmado haz click en Continuar");
			lblNewLabelConfirmar.setBounds(237, 39, 846, 23);
		}
		return lblNewLabelConfirmar;
	}
	
	private JLabel getLblNewLabelArtSeleccionados() {
		if (lblNewLabelArtSeleccionados == null) {
			lblNewLabelArtSeleccionados = new JLabel("ARTICULOS SELECCIONADOS (NO CONFIRMADOS)");
			lblNewLabelArtSeleccionados.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelArtSeleccionados.setBounds(394, 7, 289, 28);
		}
		return lblNewLabelArtSeleccionados;
	}
	private JLabel getLblNewLabelInformacion() {
		if (lblNewLabelInformacion == null) {
			lblNewLabelInformacion = new JLabel("Podr\u00E1 visualizar su entrega haciendo click en el ojo de al lado del pedido, solo aparecer\u00E1n los art\u00EDculos que haya confirmado");
			lblNewLabelInformacion.setBounds(238, 91, 845, 20);
		}
		return lblNewLabelInformacion;
	}
	private JLabel getLblNewLabelCanjeados() {
		if (lblNewLabelCanjeados == null) {
			lblNewLabelCanjeados = new JLabel("PUNTOS TOTALES CANJEADOS (CONFIRMADOS):");
			lblNewLabelCanjeados.setBounds(266, 279, 277, 20);
		}
		return lblNewLabelCanjeados;
	}
	private JLabel getLblNewLabelSobrantes() {
		if (lblNewLabelSobrantes == null) {
			lblNewLabelSobrantes = new JLabel("PUNTOS SOBRANTES:");
			lblNewLabelSobrantes.setBounds(397, 310, 146, 20);
		}
		return lblNewLabelSobrantes;
	}
	
	private JLabel getLblNewLabelEliminar() {
		if (lblNewLabelEliminar == null) {
			lblNewLabelEliminar = new JLabel("Tambi\u00E9n puede eliminar art\u00EDculos (click en la papelera), pero cuando los elimine no podr\u00E1 recuperarlos (tampoco podr\u00E1 desconfirmar una entrega)");
			lblNewLabelEliminar.setBounds(238, 66, 845, 23);
		}
		return lblNewLabelEliminar;
	}
	
	protected Entregas getEntregas() {
		return entregas;
	}
	protected VentanaPanelRegalos getVentanaPanelRegalos() {
		return ventanaPanelRegalos;
	}
}
