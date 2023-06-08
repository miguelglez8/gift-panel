package igu;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Entrega;
import logica.Entregas;
import logica.Regalo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextArea;

public class VentanaSeleccionArticulo extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JLabel lblNewLabelFoto;
	private JTextField textFieldDenominacion;
	private JTextField textFieldPrecio;
	private JSpinner spinnerUds;
	private JButton btnNewButtonAñadir;
	private JTextField textFieldCategoria;
	private JLabel lblNewLabelDenominacion;
	private JLabel lblNewLabelCategoria;
	private JLabel lblNewLabelDescripcion;
	private JLabel lblNewLabelUnidades;
	private JLabel lblNewLabelPrecio;
	private JTextArea textAreaDescripcion;
	
	private Regalo regalo;
	private Entrega entrega;
	private Entregas entregas;

	public VentanaSeleccionArticulo(Regalo regalo, Entrega entrega, Entregas entregas, HelpSet hs, HelpBroker hb) {
		this.regalo = regalo;
		this.entrega = entrega;
		this.entregas = entregas;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				btnNewButtonAñadir.grabFocus();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSeleccionArticulo.class.getResource("/img/panelderegalos.PNG")));
		setTitle("A\u00F1adir regalos al carrito");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 373);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.add(getLblNewLabelFoto());
		panelPrincipal.add(getTextFieldDenominacion());
		panelPrincipal.add(getTextFieldPrecio());
		panelPrincipal.add(getSpinnerUds());
		panelPrincipal.add(getBtnNewButtonAñadir());
		panelPrincipal.add(getTextFieldCategoria());
		panelPrincipal.add(getLblNewLabelDenominacion());
		panelPrincipal.add(getLblNewLabelCategoria());
		panelPrincipal.add(getLblNewLabelDescripcion());
		panelPrincipal.add(getLblNewLabelUnidades());
		panelPrincipal.add(getLblNewLabelPrecio());
		panelPrincipal.add(getTextAreaDescripcion());
		configurarAyuda(hs, hb);
	}
	private void configurarAyuda(HelpSet hs, HelpBroker hb) {
		hb.enableHelpKey(getRootPane(),"articulo", hs);
		hb.enableHelp(btnNewButtonAñadir, "articulo", hs);
	}
	private JLabel getLblNewLabelFoto() {
		if (lblNewLabelFoto == null) {
			lblNewLabelFoto = new JLabel("");
			lblNewLabelFoto.setBounds(10, 54, 115, 175);
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
	
	private JTextField getTextFieldDenominacion() {
		if (textFieldDenominacion == null) {
			textFieldDenominacion = new JTextField();
			textFieldDenominacion.setEditable(false);
			textFieldDenominacion.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldDenominacion.setText(regalo.getDenominacion());
			textFieldDenominacion.setBounds(254, 114, 154, 20);
			textFieldDenominacion.setColumns(10);
			textFieldDenominacion.setText(regalo.getDenominacion());
		}
		return textFieldDenominacion;
	}
	private JTextField getTextFieldPrecio() {
		if (textFieldPrecio == null) {
			textFieldPrecio = new JTextField();
			textFieldPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPrecio.setEditable(false);
			textFieldPrecio.setBounds(262, 301, 86, 20);
			textFieldPrecio.setColumns(10);
			textFieldPrecio.setText(regalo.getPuntos() + "");
		}
		return textFieldPrecio;
	}
	private JSpinner getSpinnerUds() {
		if (spinnerUds == null) {
			spinnerUds = new JSpinner();
			spinnerUds.setModel(new SpinnerNumberModel(1, 1, null, 1));
			spinnerUds.setBounds(262, 263, 30, 20);
		}
		return spinnerUds;
	}
	private JButton getBtnNewButtonAñadir() {
		if (btnNewButtonAñadir == null) {
			btnNewButtonAñadir = new JButton("A\u00F1adir");
			btnNewButtonAñadir.setToolTipText("A\u00F1ade el regalo al carrito");
			btnNewButtonAñadir.setBackground(Color.GREEN);
			btnNewButtonAñadir.setMnemonic('A');
			btnNewButtonAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int unidades = (int) getSpinnerUds().getValue();
					if (entregas.sePuedeComprar(entrega, unidades) == false) {
						JOptionPane.showMessageDialog(null, "Lo sentimos, no puede añadir tantas unidades de ese artículo\n Consulte sus puntos y mire sus posibilidades"
								, "Falta de puntos", JOptionPane.WARNING_MESSAGE);
					} else {
						if (confirmarArticulo()) {
							entregas.add(entrega, unidades);
							JOptionPane.showMessageDialog(null, "Se han añadido " + unidades + " unidades de " + regalo.getDenominacion()
									, "Regalo añadido", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} 
					}
				}
			});
			btnNewButtonAñadir.setBounds(468, 298, 89, 23);
		}
		return btnNewButtonAñadir;
	}
	
	private boolean confirmarArticulo() {
		boolean confir = false;
		int unidades = (int) getSpinnerUds().getValue();
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres añadir " + unidades + " unidades de " + regalo.getDenominacion() + "?");
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
	
	private JTextField getTextFieldCategoria() {
		if (textFieldCategoria == null) {
			textFieldCategoria = new JTextField();
			textFieldCategoria.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldCategoria.setEditable(false);
			textFieldCategoria.setBounds(263, 13, 115, 20);
			textFieldCategoria.setColumns(10);
			if (regalo.getCodigo().contains("A")) {
				textFieldCategoria.setText(" (ALIMENTACIÓN) ");
			} else if (regalo.getCodigo().contains("D")) {
				textFieldCategoria.setText(" (DEPORTES) ");
			} else if (regalo.getCodigo().contains("E")) {
				textFieldCategoria.setText(" (ELECTRÓNICA) ");
			} else {
				textFieldCategoria.setText(" (JUGUETES) ");
			}
		}
		return textFieldCategoria;
	}
	private JLabel getLblNewLabelDenominacion() {
		if (lblNewLabelDenominacion == null) {
			lblNewLabelDenominacion = new JLabel("Denominaci\u00F3n:");
			lblNewLabelDenominacion.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelDenominacion.setBounds(137, 112, 107, 20);
		}
		return lblNewLabelDenominacion;
	}
	private JLabel getLblNewLabelCategoria() {
		if (lblNewLabelCategoria == null) {
			lblNewLabelCategoria = new JLabel("ART\u00CDCULO");
			lblNewLabelCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelCategoria.setBounds(173, 11, 80, 20);
		}
		return lblNewLabelCategoria;
	}
	private JLabel getLblNewLabelDescripcion() {
		if (lblNewLabelDescripcion == null) {
			lblNewLabelDescripcion = new JLabel("Descripci\u00F3n:");
			lblNewLabelDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelDescripcion.setBounds(157, 164, 89, 20);
		}
		return lblNewLabelDescripcion;
	}
	private JLabel getLblNewLabelUnidades() {
		if (lblNewLabelUnidades == null) {
			lblNewLabelUnidades = new JLabel("Unidades:");
			lblNewLabelUnidades.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelUnidades.setBounds(172, 260, 80, 23);
			lblNewLabelUnidades.setLabelFor(getSpinnerUds());
			lblNewLabelUnidades.setDisplayedMnemonic('U');
		}
		return lblNewLabelUnidades;
	}
	private JLabel getLblNewLabelPrecio() {
		if (lblNewLabelPrecio == null) {
			lblNewLabelPrecio = new JLabel("Precio/ud:");
			lblNewLabelPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelPrecio.setBounds(173, 301, 80, 17);
		}
		return lblNewLabelPrecio;
	}
	private JTextArea getTextAreaDescripcion() {
		if (textAreaDescripcion == null) {
			textAreaDescripcion = new JTextArea();
			textAreaDescripcion.setLineWrap(true);
			textAreaDescripcion.setWrapStyleWord(true);
			textAreaDescripcion.setEditable(false);
			textAreaDescripcion.setBounds(254, 164, 297, 65);
			textAreaDescripcion.setText(regalo.getDescripcion());
		}
		return textAreaDescripcion;
	}
	
}
