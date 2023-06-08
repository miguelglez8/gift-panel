package igu;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.Entrega;
import logica.Entregas;
import logica.Regalo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextArea;

public class VentanaSeleccionViajes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JButton btnNewButtonExclamacion;
	private JButton btnNewButtonObservaciones;
	private JButton btnNewButtonFecha;
	
	private JLabel lblNewLabelFoto;
	private JTextField textFieldDenominacion;
	private JTextField textFieldPrecio;
	private JSpinner spinneruUds;
	private JButton btnNewButtonAñadir;
	private JLabel lblNewLabelDenominacion;
	private JLabel lblNewLabelCategoria;
	private JLabel lblNewLabelDescripcion;
	private JLabel lblNewLabelUnidades;
	private JLabel lblNewLabelPrecio;
	private JTextField txtviajesYExperiencias;
	private JTextArea textAreaDescripcion;
	
	private Regalo regalo;
	private Entrega entrega;
	private Entregas entregas;

	private HelpSet hs;
	private HelpBroker hb;
	
	public VentanaSeleccionViajes(Regalo regalo, Entrega entrega, Entregas entregas, HelpSet hs, HelpBroker hb) {
		this.regalo = regalo;
		this.entrega = entrega;
		this.entregas = entregas;
		this.hs = hs;
		this.hb = hb;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				btnNewButtonAñadir.grabFocus();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSeleccionViajes.class.getResource("/img/viajes.PNG")));
		setTitle("A\u00F1adir regalos al carrito");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 813, 388);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.add(getBtnNewButtonExclamacion());
		panelPrincipal.add(getBtnNewButtonObservaciones());
		panelPrincipal.add(getBtnNewButtonFecha());
		panelPrincipal.add(getLblNewLabelFoto());
		panelPrincipal.add(getTextField_3());
		panelPrincipal.add(getTextField_1_1());
		panelPrincipal.add(getSpinner_1());
		panelPrincipal.add(getBtnNewButton_4());
		panelPrincipal.add(getLblNewLabelDenominacion());
		panelPrincipal.add(getLblNewLabelCategoria());
		panelPrincipal.add(getLblNewLabelDescripcion());
		panelPrincipal.add(getLblNewLabelUnidades());
		panelPrincipal.add(getLblNewLabelPrecio());
		panelPrincipal.add(getTextField_3_1());
		panelPrincipal.add(getTextAreaDescripcion());
		btnNewButtonAñadir.grabFocus();
		configurarAyuda();
	}
	private void configurarAyuda() {
		hb.enableHelpKey(getRootPane(),"viaje", hs);
		hb.enableHelp(btnNewButtonAñadir, "viaje", hs);
		hb.enableHelp(btnNewButtonObservaciones, "observaciones", hs);
		hb.enableHelp(btnNewButtonFecha, "fecha", hs);
	}
	
	private boolean confirmarArticulo() {
		boolean confir = false;
		int unidades = (int) getSpinner_1().getValue();
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres añadir " + unidades + " unidades de " + regalo.getDenominacion() + "?",
				"Confirmar artículo", JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
	
	private JButton getBtnNewButtonExclamacion() {
		if (btnNewButtonExclamacion == null) {
			btnNewButtonExclamacion = new JButton("");
			btnNewButtonExclamacion.setBorder(new LineBorder(Color.GRAY));
			btnNewButtonExclamacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Para el caso de un viaje se deberán de elegir las unidades (en el spinner de abajo) como en las demás categorías\n"
							+ "También se tendrán que introducir las observaciones (al hacer click en Observaciones se desplegará una ventana donde tendremos que introducir el texto)\n"
							+ "La fecha de inicio se podrá elegir haciendo click en el calendario, y una vez allí seleccionar el día y pulsar OK\n"
							+ "No se admitirán fechas de inicio del viaje anteriores a la fecha actual, o lo que es lo mismo, solo se permiten fechas posteriores a la actual"
							, "¿ Cómo añadir un viaje al carro ?", JOptionPane.WARNING_MESSAGE);
				}
			});
			btnNewButtonExclamacion.setToolTipText("Pulse para ver m\u00E1s informaci\u00F3n sobre como reservar el viaje");
			btnNewButtonExclamacion.setIcon(new ImageIcon(VentanaSeleccionViajes.class.getResource("/img/exclamacionviajes.PNG")));
			btnNewButtonExclamacion.setBounds(730, 271, 30, 40);
		}
		return btnNewButtonExclamacion;
	}
	private JButton getBtnNewButtonObservaciones() {
		if (btnNewButtonObservaciones == null) {
			btnNewButtonObservaciones = new JButton("Observaciones");
			btnNewButtonObservaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					while (entrega.getObservaciones() == null || entrega.getObservaciones().equals("")) {
						String message = JOptionPane.showInputDialog(null, "Por favor, introduzca las observaciones que crea usted más convenientes para el viaje",
								"Introduzca observaciones", JOptionPane.INFORMATION_MESSAGE);
						entrega.setObservaciones(message);
					}
					btnNewButtonObservaciones.setEnabled(false);
				}
			});
			btnNewButtonObservaciones.setToolTipText("Incluye observaciones para el viaje");
			btnNewButtonObservaciones.setBackground(Color.WHITE);
			btnNewButtonObservaciones.setMnemonic('O');
			btnNewButtonObservaciones.setBounds(646, 188, 130, 23);
		}
		return btnNewButtonObservaciones;
	}
	private JButton getBtnNewButtonFecha() {
		if (btnNewButtonFecha == null) {
			btnNewButtonFecha = new JButton("");
			btnNewButtonFecha.setBorder(new LineBorder(Color.BLACK));
			btnNewButtonFecha.setToolTipText("Seleccione la fecha de inicio del viaje");
			btnNewButtonFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						try {
							VentanaCalendario dialog = new VentanaCalendario(entrega);
							dialog.setModal(true);
							dialog.setLocationRelativeTo(null);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							if (entrega.getFecha()!=null) {
								btnNewButtonFecha.setEnabled(false);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
		    );
			btnNewButtonFecha.setIcon(new ImageIcon(VentanaSeleccionViajes.class.getResource("/img/iconocalendario.PNG")));
			btnNewButtonFecha.setDisabledIcon(new ImageIcon(VentanaSeleccionViajes.class.getResource("/img/iconocalendario.PNG")));
			btnNewButtonFecha.setBounds(636, 37, 151, 140);
		}
		return btnNewButtonFecha;
	}
	private JLabel getLblNewLabelFoto() {
		if (lblNewLabelFoto == null) {
			lblNewLabelFoto = new JLabel("");
			lblNewLabelFoto.setBounds(10, 65, 142, 191);
			setImagenAdaptada(lblNewLabelFoto, "/img/" + regalo.getCodigo() + ".png");
		}
		return lblNewLabelFoto;
	}
	
	private void setImagenAdaptada(JLabel label, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 label.setIcon(icon);
	}
	
	private JTextField getTextField_3() {
		if (textFieldDenominacion == null) {
			textFieldDenominacion = new JTextField();
			textFieldDenominacion.setText((String) null);
			textFieldDenominacion.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldDenominacion.setEditable(false);
			textFieldDenominacion.setColumns(10);
			textFieldDenominacion.setBounds(279, 104, 238, 20);
			textFieldDenominacion.setText(regalo.getDenominacion());
		}
		return textFieldDenominacion;
	}
	private JTextField getTextField_1_1() {
		if (textFieldPrecio == null) {
			textFieldPrecio = new JTextField();
			textFieldPrecio.setText("0");
			textFieldPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPrecio.setEditable(false);
			textFieldPrecio.setColumns(10);
			textFieldPrecio.setBounds(272, 279, 86, 20);
			textFieldPrecio.setText(regalo.getPuntos() + "");
		}
		return textFieldPrecio;
	}
	private JSpinner getSpinner_1() {
		if (spinneruUds == null) {
			spinneruUds = new JSpinner();
			spinneruUds.setModel(new SpinnerNumberModel(1, 1, null, 1));
			spinneruUds.setBounds(273, 236, 30, 20);
		}
		return spinneruUds;
	}
	private JButton getBtnNewButton_4() {
		if (btnNewButtonAñadir == null) {
			btnNewButtonAñadir = new JButton("A\u00F1adir");
			btnNewButtonAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int unidades = (int) getSpinner_1().getValue();
					if (entregas.sePuedeComprar(entrega, unidades) == false) {
						JOptionPane.showMessageDialog(null, "Lo sentimos, no puede añadir tantas unidades de ese artículo\n Consulte sus puntos y mire sus posibilidades"
								, "Falta de puntos", JOptionPane.WARNING_MESSAGE);
					} else {
						if (entrega.getObservaciones()==null && entrega.getFecha()==null) {
							JOptionPane.showMessageDialog(null, "No puede confirmar el viaje sin antes seleccionar una fecha de inicio y observaciones para el viaje\n"
									+ "Para introducir observaciones debe seleccionar el botón Observaciones e introducir el texto en dicha ventana\n"
									+ "Para el caso de la fecha, seleccione el calendario y una vez allí, la fecha concreta (siempre posterior a la actual)"
									, "Introduce una fecha y observaciones para el viaje", JOptionPane.WARNING_MESSAGE);
						} else if (entrega.getObservaciones()!=null && entrega.getFecha()==null) {
							JOptionPane.showMessageDialog(null, "No puede confirmar el viaje sin antes seleccionar una fecha de inicio para el viaje\n"
									+ "Para el caso de la fecha, seleccione el calendario y una vez allí, la fecha concreta (siempre posterior a la actual)"
									, "Introduce una fecha para el viaje", JOptionPane.WARNING_MESSAGE);
						} else if (entrega.getObservaciones()==null && entrega.getFecha()!=null) {
							JOptionPane.showMessageDialog(null, "No puede confirmar el viaje sin antes seleccionar observaciones para el viaje\n"
									+ "Para introducir observaciones debe seleccionar el botón Observaciones e introducir el texto en dicha ventana\n"
									, "Introduce observaciones para el viaje", JOptionPane.WARNING_MESSAGE);
						} else {
							if (confirmarArticulo()) {
								entregas.add(entrega, unidades);
								JOptionPane.showMessageDialog(null, "Se han añadido " + unidades + " unidades de " + regalo.getDenominacion()
										, "Regalo añadido", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
						}
					} 
				}
			});
			btnNewButtonAñadir.setToolTipText("A\u00F1ade el viaje al carrito");
			btnNewButtonAñadir.setMnemonic('A');
			btnNewButtonAñadir.setBackground(Color.GREEN);
			btnNewButtonAñadir.setBounds(631, 278, 89, 23);
		}
		return btnNewButtonAñadir;
	}
	private JLabel getLblNewLabelDenominacion() {
		if (lblNewLabelDenominacion == null) {
			lblNewLabelDenominacion = new JLabel("Denominaci\u00F3n:");
			lblNewLabelDenominacion.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelDenominacion.setBounds(162, 102, 107, 20);
		}
		return lblNewLabelDenominacion;
	}
	private JLabel getLblNewLabelCategoria() {
		if (lblNewLabelCategoria == null) {
			lblNewLabelCategoria = new JLabel("ART\u00CDCULO");
			lblNewLabelCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelCategoria.setBounds(203, 11, 80, 20);
		}
		return lblNewLabelCategoria;
	}
	private JLabel getLblNewLabelDescripcion() {
		if (lblNewLabelDescripcion == null) {
			lblNewLabelDescripcion = new JLabel("Descripci\u00F3n:");
			lblNewLabelDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelDescripcion.setBounds(175, 155, 89, 20);
		}
		return lblNewLabelDescripcion;
	}
	private JLabel getLblNewLabelUnidades() {
		if (lblNewLabelUnidades == null) {
			lblNewLabelUnidades = new JLabel("Unidades:");
			lblNewLabelUnidades.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelUnidades.setDisplayedMnemonic('U');
			lblNewLabelUnidades.setBounds(189, 233, 80, 23);
		}
		return lblNewLabelUnidades;
	}
	private JLabel getLblNewLabelPrecio() {
		if (lblNewLabelPrecio == null) {
			lblNewLabelPrecio = new JLabel("Precio/ud:");
			lblNewLabelPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelPrecio.setBounds(182, 279, 80, 17);
		}
		return lblNewLabelPrecio;
	}
	private JTextField getTextField_3_1() {
		if (txtviajesYExperiencias == null) {
			txtviajesYExperiencias = new JTextField();
			txtviajesYExperiencias.setEditable(false);
			txtviajesYExperiencias.setHorizontalAlignment(SwingConstants.CENTER);
			txtviajesYExperiencias.setText("(Viajes y Experiencias)");
			txtviajesYExperiencias.setBounds(298, 11, 145, 20);
			txtviajesYExperiencias.setColumns(10);
		}
		return txtviajesYExperiencias;
	}
	private JTextArea getTextAreaDescripcion() {
		if (textAreaDescripcion == null) {
			textAreaDescripcion = new JTextArea();
			textAreaDescripcion.setLineWrap(true);
			textAreaDescripcion.setWrapStyleWord(true);
			textAreaDescripcion.setEditable(false);
			textAreaDescripcion.setBounds(279, 155, 330, 70);
			textAreaDescripcion.setText(regalo.getDescripcion());
		}
		return textAreaDescripcion;
	}
}
