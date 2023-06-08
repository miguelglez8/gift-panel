package igu;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logica.Entrega;

public class PanelEntrega extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabelFoto;
	private JTextField textFieldDenominacion;
	private JTextField textFieldUnidades;
	private JTextField textFieldTotal;
	private JButton botonEliminar;
	private JButton botonConfirmar;
	private JLabel lblNewLabelTotal;
	private JLabel lblNewLabelDenominacion;
	private JLabel lblNewLabelUds;
	
	private int pos;
	private Entrega entrega;
	private VentanaPanelEntregas ventanaPanel;
	
	public PanelEntrega(int pos, VentanaPanelEntregas ventanaPanelEntregas, Entrega entrega, HelpBroker hb, HelpSet hs) {
		this.pos = pos;
		this.ventanaPanel = ventanaPanelEntregas;
		this.entrega = entrega;
	    setLayout(null);
		add(getLblNewLabelFoto());
		add(getTextFieldDenominacion());
		add(getTextFieldUnidades());
		add(getTextFieldTotal());
		add(getBtnNewButtonEliminar());
		add(getBtnNewButtonConfirmar());
		add(getLblNewLabelTotal());
		add(getLblNewLabelDenominacion());
		add(getLblNewLabelUds());
		configuraAyuda(hs, hb);
	}
	
	private void configuraAyuda(HelpSet hs, HelpBroker hb) {
		hb.enableHelp(botonConfirmar, "confirmacion", hs);
		hb.enableHelp(botonEliminar, "eliminacion", hs);
	}

	private void setImagenAdaptada(JLabel label, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 label.setIcon(icon);
	}
	
	private JLabel getLblNewLabelFoto() {
		if (lblNewLabelFoto == null) {
			lblNewLabelFoto = new JLabel("");
			lblNewLabelFoto.setBounds(10, 11, 77, 69);
			setImagenAdaptada(lblNewLabelFoto, "/img/" + entrega.getCodigo() + ".png");
		}
		return lblNewLabelFoto;
	}

	
	private JTextField getTextFieldDenominacion() {
		if (textFieldDenominacion == null) {
			textFieldDenominacion = new JTextField();
			textFieldDenominacion.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldDenominacion.setEditable(false);
			textFieldDenominacion.setBounds(193, 7, 150, 20);
			textFieldDenominacion.setColumns(10);
			textFieldDenominacion.setText(entrega.getNombre() + "");
		}
		return textFieldDenominacion;
	}
	private JTextField getTextFieldUnidades() {
		if (textFieldUnidades == null) {
			textFieldUnidades = new JTextField();
			textFieldUnidades.setEditable(false);
			textFieldUnidades.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldUnidades.setBounds(193, 60, 86, 20);
			textFieldUnidades.setColumns(10);
			textFieldUnidades.setText(entrega.getUnidades() + "");
		}
		return textFieldUnidades;
	}
	
	private JTextField getTextFieldTotal() {
		if (textFieldTotal == null) {
			textFieldTotal = new JTextField();
			textFieldTotal.setEditable(false);
			textFieldTotal.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldTotal.setBounds(278, 35, 86, 20);
			textFieldTotal.setColumns(10);
			textFieldTotal.setText((entrega.getUnidades() * entrega.getPrecio()) + "");
		}
		return textFieldTotal;
	}
	
	private void setImagenAdaptada(JButton bt, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(bt.getWidth(),bt.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 bt.setIcon(icon);
		 bt.setDisabledIcon(icon);
	}
	
	private JButton getBtnNewButtonEliminar() {
		if (botonEliminar == null) {
			botonEliminar = new JButton("");
			botonEliminar.setToolTipText("Eliminar art\u00EDculo");
			botonEliminar.setBounds(397, 0, 93, 91);
			setImagenAdaptada(botonEliminar, "/img/papelera.PNG");
			botonEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (entrega.getUnidades()==1) {
						if (confirmarEliminacion(entrega.getNombre(), entrega.getUnidades())) { 
							ventanaPanel.getTabbedPaneEntregas().setBackgroundAt(pos, Color.RED);
							ventanaPanel.getTabbedPaneEntregas().setToolTipTextAt(pos, "Artículo eliminado");
							ventanaPanel.getEntregas().remove(entrega, entrega.getUnidades());	
							botonConfirmar.setEnabled(false);
							botonEliminar.setEnabled(false);
							botonEliminar.setBorder(new LineBorder(Color.RED));
						}
					} else {
						int uds = -1;
						do {
							try {
								uds = Integer.parseInt(JOptionPane.showInputDialog(null, "Tiene en la cesta " + entrega.getUnidades() + " unidades de " + entrega.getNombre() + "\n" +
							          "Por favor, introduzca las unidades que desea eliminar de " + entrega.getNombre(), "Eliminar unidades de un artículo", JOptionPane.INFORMATION_MESSAGE));
							} catch (NumberFormatException e1) {
								System.out.println("Error al introducir las unidades que desea borrar. Ejecute de nuevo la aplicación, por favor");
							}
							
						} while (uds < 1 || uds > entrega.getUnidades());
						if (confirmarEliminacion(entrega.getNombre(), uds)) { {
							if (uds == entrega.getUnidades()) {
								ventanaPanel.getTabbedPaneEntregas().setBackgroundAt(pos, Color.RED);
								ventanaPanel.getTabbedPaneEntregas().setToolTipTextAt(pos, "Artículo eliminado");
								ventanaPanel.getEntregas().remove(entrega, uds);	
								botonConfirmar.setEnabled(false);
								botonEliminar.setEnabled(false);
								botonEliminar.setBorder(new LineBorder(Color.RED));
							} else {
								ventanaPanel.getEntregas().remove(entrega, uds);
								getTextFieldTotal().setText((entrega.getUnidades()*entrega.getPrecio()) + "");
								textFieldUnidades.setText(entrega.getUnidades() + "");
							}
						}
					  }
					}
				}
			});
			
		}
		return botonEliminar;
	}
	private JButton getBtnNewButtonConfirmar() {
		if (botonConfirmar == null) {
			botonConfirmar = new JButton("");
			botonConfirmar.setToolTipText("Confirmar art\u00EDculo");
			botonConfirmar.setBounds(491, 0, 98, 91);
			setImagenAdaptada(botonConfirmar, "/img/confirmacion.PNG");
			botonConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (confirmarConfirmacion(entrega.getNombre(), entrega.getUnidades())) {
						ventanaPanel.getTabbedPaneEntregas().setBackgroundAt(pos, Color.GREEN);
						ventanaPanel.getTabbedPaneEntregas().setToolTipTextAt(pos, "Artículo confirmado");
						entrega.confirmarEntrega();
						botonConfirmar.setEnabled(false);
						botonEliminar.setEnabled(false);
						botonConfirmar.setBorder(new LineBorder(Color.GREEN));
						ventanaPanel.getTextFieldPuntosCanjeados().setText(ventanaPanel.getEntregas().getTotal() + "");
						ventanaPanel.getTextFieldPuntosSobrantes().setText((ventanaPanel.getEntregas().getPuntosJuego() - ventanaPanel.getEntregas().getTotal()) + "");
						ventanaPanel.getTextAreaPedido().setText(ventanaPanel.getEntregas().toString());
						if (ventanaPanel.getEntregas().getTotal()!=0) {
							ventanaPanel.getTextAreaPedido().append("TOTAL = " + ventanaPanel.getEntregas().getTotal() + " puntos");
						}
					}
				}
			});
		}
		return botonConfirmar;
	}
	
	private JLabel getLblNewLabelTotal() {
		if (lblNewLabelTotal == null) {
			lblNewLabelTotal = new JLabel("TOTAL:");
			lblNewLabelTotal.setBounds(222, 38, 46, 14);
		}
		return lblNewLabelTotal;
	}
	private JLabel getLblNewLabelDenominacion() {
		if (lblNewLabelDenominacion == null) {
			lblNewLabelDenominacion = new JLabel("DENOMINACI\u00D3N:");
			lblNewLabelDenominacion.setBounds(97, 10, 100, 14);
		}
		return lblNewLabelDenominacion;
	}
	private JLabel getLblNewLabelUds() {
		if (lblNewLabelUds == null) {
			lblNewLabelUds = new JLabel("UNIDADES:");
			lblNewLabelUds.setBounds(122, 63, 66, 14);
		}
		return lblNewLabelUds;
	}
	
	private boolean confirmarEliminacion(String denominacion, int unidades) {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres eliminar " + unidades + " unidades de " + denominacion + " ?", "Eliminar regalos",
				JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
	
	private boolean confirmarConfirmacion(String denominacion, int unidades) {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres confirmar " + unidades + " unidades de " + denominacion + " ?", "Confirmar regalos",
				JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
}
