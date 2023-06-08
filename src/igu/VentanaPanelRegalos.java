package igu;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logica.Entrega;
import logica.Entregas;
import logica.Juego;
import logica.Regalo;
import logica.Regalos;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.border.BevelBorder;

import java.awt.Font;
import javax.swing.JSlider;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPanelRegalos extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	
	private JPanel panelCategorias;
	private JLabel lblNewLabel;
	private JButton btnNewButtonReiniciar;
	private JButton btnNewButtonCarrito;
	private JButton btnNewButtonAlimentacion;
	private JButton btnNewButtonCategorias;
	private JButton btnNewButtonViajes;
	private JButton btnNewButtonDeportes;
	private JButton btnNewButtonElectronica;
	private JButton btnNewButtonJuguetes;
	private JTextField textFieldPuntos;
	private JPanel panelRegalos;
	private JLabel lblNewLabelFiltrarCategorías;
	private JButton btnNewButtonAyuda;
	private JLabel lblNewLabelFiltrar;
	private JSlider sliderPuntos;
	private JTextField textFieldPuntosSlider;
	private JLabel lblNewLabelValor;
	private JLabel lblNewLabelSliderExplicacion;
	private JTextField textFieldEntregasContador;
	private JLabel lblNewLabelDisponibles;
	private JPanel panelPuntos;
	
	private AccionBoton aB;

	private Regalos regalos;
	private Entregas entregas;
	private VentanaPanelCasillas ventanaPanelCasillas;
	
	private HelpSet hs;
	private HelpBroker hb;

	public VentanaPanelRegalos(Juego juego, Regalos regalos, Entregas entregas, VentanaPanelCasillas ventanaPanelCasillas, HelpSet hs, HelpBroker hb) {
		this.regalos = regalos;
		this.entregas = entregas;
		this.ventanaPanelCasillas = ventanaPanelCasillas;
		this.aB = new AccionBoton();
		this.hs = hs;
		this.hb = hb;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (confirmarVolverAtras()) {
					entregas.inicializar();
					entregas.setPuntosJuego(juego.getPuntos());
					entregas.setPuntosEntregas(juego.getPuntos());
					textFieldPuntosSlider.setText(entregas.getPuntosJuego() + "");
					sliderPuntos.setValue(entregas.getPuntosJuego());
					btnNewButtonCarrito.setToolTipText("Avanza a la siguiente ventana de confirmación de regalos");
					dispose();
				}
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				asociaImagenBotones();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPanelRegalos.class.getResource("/img/regalos.PNG")));
		setTitle("Panel de Premios : Canjea tus puntos");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1001, 562);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.add(getPanelCategorias());
		panelPrincipal.add(getLblNewLabel());
		panelPrincipal.add(getBtnNewButtonCarrito());
		panelPrincipal.add(getPanelRegalos());
		panelPrincipal.add(getLblNewLabelFiltrarCategorías());
		panelPrincipal.add(getBtnNewButtonAyuda());
		panelPrincipal.add(getLblNewLabelSliderExplicacion());
		panelPrincipal.add(getTextFieldEntregasContador());
		panelPrincipal.add(getPanelPuntos());
		textFieldPuntosSlider.setText(entregas.getPuntosJuego() + "");
		sliderPuntos.setValue(entregas.getPuntosJuego());
		this.filtraPor(entregas.getPuntosJuego());
		configurarAyuda();
	}
	private void configurarAyuda() {
		hb.enableHelpKey(getRootPane(),"regalos", hs);
		hb.enableHelp(panelRegalos, "regalos", hs);
		hb.enableHelp(textFieldPuntos, "regalos", hs);
		hb.enableHelp(panelCategorias, "filtrar", hs);
		hb.enableHelp(sliderPuntos, "filtrar", hs);
		hb.enableHelp(textFieldPuntosSlider, "filtrar", hs);
	}
	
	private boolean confirmarVolverAtras() {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres volver atrás ?\n Tendrás que añadir los regalos de nuevo al volver a esta pantalla",
				"Volver atrás", JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
	
	private void asociaImagenBotones() {
		for (int i = 0; i < panelRegalos.getComponents().length; i++)
		{
			setImagenAdaptada((JButton) panelRegalos.getComponents()[i], "/img/"+regalos.getRegalos()[i].getCodigo()+".png");
		}
		setImagenAdaptada((JButton) panelCategorias.getComponents()[0], "/img/regalos.png");
		setImagenAdaptada((JButton) panelCategorias.getComponents()[1], "/img/alimentacion.png");
		setImagenAdaptada((JButton) panelCategorias.getComponents()[2], "/img/deportes.png");
		setImagenAdaptada((JButton) panelCategorias.getComponents()[3], "/img/electronica.png");
		setImagenAdaptada((JButton) panelCategorias.getComponents()[4], "/img/juguetes.png");
		setImagenAdaptada((JButton) panelCategorias.getComponents()[5], "/img/viajes.png");
	}

	private JPanel getPanelCategorias() {
		if (panelCategorias == null) {
			panelCategorias = new JPanel();
			panelCategorias.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
			panelCategorias.setBounds(0, 41, 133, 482);
			panelCategorias.setLayout(new GridLayout(6, 1, 0, 0));
			panelCategorias.add(getBtnNewButtonCategorias());
			panelCategorias.add(getBtnNewButtonAlimentacion());
			panelCategorias.add(getBtnNewButtonDeportes());
			panelCategorias.add(getBtnNewButtonElectronica());
			panelCategorias.add(getBtnNewButtonJuguetes());
			panelCategorias.add(getBtnNewButtonViajes());
		}
		return panelCategorias;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Seleccione los regalos haciendo click, se desplegar\u00E1 una ventana para que pueda a\u00F1adirlos al carro, y all\u00ED confirmarlos:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel.setBounds(154, 41, 582, 19);
		}
		return lblNewLabel;
	}
	
	private JButton getBtnNewButtonReiniciar() {
		if (btnNewButtonReiniciar == null) {
			btnNewButtonReiniciar = new JButton("Reiniciar");
			btnNewButtonReiniciar.setBounds(503, 16, 73, 23);
			btnNewButtonReiniciar.setBackground(Color.YELLOW);
			btnNewButtonReiniciar.setMnemonic('R');
			btnNewButtonReiniciar.setToolTipText("Elimina el filtrado de puntos y de categor\u00EDas");
			btnNewButtonReiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarPanel();
				}
			});
		}
		return btnNewButtonReiniciar;
	}
	private JButton getBtnNewButtonCarrito() {
		if (btnNewButtonCarrito == null) {
			btnNewButtonCarrito = new JButton("");
			btnNewButtonCarrito.setBorder(new LineBorder(Color.BLACK));
			btnNewButtonCarrito.setToolTipText("Avanza a la siguiente ventana de confirmación de regalos");
			btnNewButtonCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								if (entregas.getListaEntregas().size()==0) {
									JOptionPane.showMessageDialog(null, "No puede avanzar a la siguiente ventana de confirmación de regalos\n"
											+ "Debe añadir al menos un artículo para ello", "Selecciona al menos"
													+ " un artículo", JOptionPane.WARNING_MESSAGE);
								} else {
									if (confirmarPasoVentana()) {
										crearVentanaEntregas();
										if (entregas.getListaEntregas().size()!=0) { 
											textFieldEntregasContador.setVisible(true);
											textFieldEntregasContador.setText("+" + entregas.getListaEntregas().size()); 
										} else {
											textFieldEntregasContador.setVisible(false);
										}
										textFieldPuntosSlider.setText(entregas.getPuntosEntregas() + "");
										filtraPor(entregas.getPuntosEntregas());
										textFieldPuntos.setText(entregas.getPuntosEntregas() + "");
										getSliderPuntos().setValue(entregas.getPuntosEntregas());
										if (entregas.getPuntosEntregas()==0) {
											for (int j=0; j < panelCategorias.getComponents().length; j++) {
												((JButton) panelCategorias.getComponents()[j]).setEnabled(false);
											}
										}
										btnNewButtonCarrito.setToolTipText(entregas.toStringCarro());
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});	
			btnNewButtonCarrito.setIcon(new ImageIcon(VentanaPanelRegalos.class.getResource("/img/iconocarrodelacompra.PNG")));
			btnNewButtonCarrito.setBounds(850, 0, 99, 66);
		}
		return btnNewButtonCarrito;
	}
	
	private void crearVentanaEntregas() {
		VentanaPanelEntregas frame = new VentanaPanelEntregas(entregas, this, hs, hb);
		frame.setModal(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private boolean confirmarPasoVentana() {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres pasar a la ventana de confirmación de regalos ?\n"
					+ "Una vez allí, cuando confirme sus premios, si desea volver a esta ventana tendrá que confirmar de nuevo los regalos en caso de "
					+ "no modificarlos en la entrega", "Pasar a ventana de confirmación de regalos", JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
	
	private JButton getBtnNewButtonAlimentacion() {
		if (btnNewButtonAlimentacion == null) {
			btnNewButtonAlimentacion = new JButton("");
			btnNewButtonAlimentacion.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
			btnNewButtonAlimentacion.setToolTipText("Filtrar por Alimentaci\u00F3n");
			btnNewButtonAlimentacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtraPor("A");
				}
			});
			btnNewButtonAlimentacion.setIcon(new ImageIcon(VentanaPanelRegalos.class.getResource("/img/alimentacion.PNG")));
		}
		return btnNewButtonAlimentacion;
	}
	private JButton getBtnNewButtonCategorias() {
		if (btnNewButtonCategorias == null) {
			btnNewButtonCategorias = new JButton("");
			btnNewButtonCategorias.setToolTipText("Eliminar el filtrado por categor\u00EDas");
			btnNewButtonCategorias.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
			btnNewButtonCategorias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i=0; i < panelRegalos.getComponentCount(); i++) {
						int value = getSliderPuntos().getValue();
						if (regalos.filtrarRegalosPrecio(value).contains(regalos.getRegalos()[i])) {
							panelRegalos.getComponents()[i].setEnabled(true);
						} else {
							panelRegalos.getComponents()[i].setEnabled(false);
						}
					}
				}
			});
			btnNewButtonCategorias.setIcon(new ImageIcon(VentanaPanelRegalos.class.getResource("/img/regalos.PNG")));
		}
		return btnNewButtonCategorias;
	}
	
	private void habilitarPanel() {
		for (int i=0; i < panelRegalos.getComponentCount(); i++) {
			panelRegalos.getComponents()[i].setEnabled(true);
		}
		getTextFieldPuntosSlider().setText(String.valueOf(2500));
		sliderPuntos.setValue(2500);
	}
	private JButton getBtnNewButtonViajes() {
		if (btnNewButtonViajes == null) {
			btnNewButtonViajes = new JButton("");
			btnNewButtonViajes.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
			btnNewButtonViajes.setBackground(Color.WHITE);
			btnNewButtonViajes.setToolTipText("Filtrar por Viajes y Experiencias");
			btnNewButtonViajes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtraPor("V");
				}
			});
			btnNewButtonViajes.setIcon(new ImageIcon(VentanaPanelRegalos.class.getResource("/img/viajes.PNG")));
		}
		return btnNewButtonViajes;
	}
	private JButton getBtnNewButtonDeportes() {
		if (btnNewButtonDeportes == null) {
			btnNewButtonDeportes = new JButton("");
			btnNewButtonDeportes.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
			btnNewButtonDeportes.setToolTipText("Filtrar por Deportes");
			btnNewButtonDeportes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtraPor("D");
				}
			});
			btnNewButtonDeportes.setIcon(new ImageIcon(VentanaPanelRegalos.class.getResource("/img/deportes.PNG")));
		}
		return btnNewButtonDeportes;
	}
	private JButton getBtnNewButtonElectronica() {
		if (btnNewButtonElectronica == null) {
			btnNewButtonElectronica = new JButton("");
			btnNewButtonElectronica.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
			btnNewButtonElectronica.setToolTipText("Filtrar por Electr\u00F3nica");
			btnNewButtonElectronica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtraPor("E");
				}
			});
			btnNewButtonElectronica.setIcon(new ImageIcon(VentanaPanelRegalos.class.getResource("/img/electronica.PNG")));
		}
		return btnNewButtonElectronica;
	}
	private JButton getBtnNewButtonJuguetes() {
		if (btnNewButtonJuguetes == null) {
			btnNewButtonJuguetes = new JButton("");
			btnNewButtonJuguetes.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
			btnNewButtonJuguetes.setToolTipText("Filtrar por Juguetes");
			btnNewButtonJuguetes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtraPor("J");
				}
			});
			btnNewButtonJuguetes.setIcon(new ImageIcon(VentanaPanelRegalos.class.getResource("/img/juguetes.PNG")));
		}
		return btnNewButtonJuguetes;
	}
	
	private void filtraPor(String string) {
		for (int i=0; i < panelRegalos.getComponentCount(); i++) {
			int value = getSliderPuntos().getValue();
			if (regalos.getRegalos(string, value).contains(regalos.getRegalos()[i])) {
				panelRegalos.getComponents()[i].setEnabled(true);
			} else {
				panelRegalos.getComponents()[i].setEnabled(false);
			}
		}
	}
	
	private JTextField getTextFieldPuntos() {
		if (textFieldPuntos == null) {
			textFieldPuntos = new JTextField();
			textFieldPuntos.setBounds(718, 17, 86, 20);
			textFieldPuntos.setEditable(false);
			textFieldPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPuntos.setColumns(10);
			textFieldPuntos.setText(entregas.getPuntosJuego() + "");
		}
		return textFieldPuntos;
	}
	private JPanel getPanelRegalos() {
		if (panelRegalos == null) {
			panelRegalos = new JPanel();
			panelRegalos.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
			panelRegalos.setBounds(143, 71, 814, 392);
			panelRegalos.setLayout(new GridLayout(0, 5, 3, 3));
			creaBotonesTablero();
		}
		return panelRegalos;
	}
	
	private void creaBotonesTablero() {
		panelRegalos.removeAll();
		for (int i=0; i < regalos.getRegalos().length; i++) {
			panelRegalos.add(nuevoBoton(i));
		}
	}
	
	private JButton nuevoBoton(Integer posicion) {
		JButton boton = new JButton("");
		boton.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		boton.setToolTipText(regalos.getRegalos()[posicion].toString());
		boton.setActionCommand(posicion.toString());
		boton.addActionListener(aB);
		return boton;
	}
	
	private void setImagenAdaptada(JButton boton, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(boton.getWidth(),boton.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 boton.setIcon(icon);
	}

	
	private JLabel getLblNewLabelFiltrarCategorías() {
		if (lblNewLabelFiltrarCategorías == null) {
			lblNewLabelFiltrarCategorías = new JLabel("Filtra por categor\u00EDas:");
			lblNewLabelFiltrarCategorías.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabelFiltrarCategorías.setBounds(10, 11, 134, 19);
		}
		return lblNewLabelFiltrarCategorías;
	}
	private JButton getBtnNewButtonAyuda() {
		if (btnNewButtonAyuda == null) {
			btnNewButtonAyuda = new JButton("");
			btnNewButtonAyuda.setBorder(new LineBorder(Color.RED));
			btnNewButtonAyuda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Para seleccionar un regalo, haz click en la foto de dicho artículo\n"
							+ "Dependiendo de si es un viaje u otro tipo de categoría se desplegará una ventana distinta\n"
							+ "Para cualquier caso, debemos seleccionar las unidades en el spinner de la izquierda (abajo) y hacer click en Añadir\n"
							+ "Si es un viaje (antes de añadir), además de eso, debemos de añadir observaciones y la fecha de inicio de dicho viaje\n"
							+ "Si lo hemos realizado correctamente, volveremos a la pantalla de selección de regalos y se habrán descontado los puntos\n"
							+ "Una vez que hemos añadido todos los artículos que queremos al carro, hacemos click en el carro y los confirmamos en la ventana "
							+ "que se desplegará a posteriori", 
							"Añadir regalos al carro de la compra", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			btnNewButtonAyuda.setIcon(new ImageIcon(VentanaPanelRegalos.class.getResource("/img/iconoAyuda.PNG")));
			btnNewButtonAyuda.setToolTipText("Haz click para obtener m\u00E1s informaci\u00F3n acerca de como a\u00F1adir los regalos al carro");
			btnNewButtonAyuda.setBounds(731, 38, 21, 27);
		}
		return btnNewButtonAyuda;
	}
	private JLabel getLblNewLabelFiltrar() {
		if (lblNewLabelFiltrar == null) {
			lblNewLabelFiltrar = new JLabel("Filtra por puntos:");
			lblNewLabelFiltrar.setLabelFor(getSliderPuntos());
			lblNewLabelFiltrar.setDisplayedMnemonic('F');
			lblNewLabelFiltrar.setBounds(0, 20, 105, 14);
		}
		return lblNewLabelFiltrar;
	}
	private JSlider getSliderPuntos() {
		if (sliderPuntos == null) {
			sliderPuntos = new JSlider();
			sliderPuntos.setBounds(103, 11, 200, 45);
			sliderPuntos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					cambiaValorSlider();
				}
			});
			sliderPuntos.setValue(entregas.getPuntosJuego());
			sliderPuntos.setToolTipText("Desliza el slider para filtrar por puntuaci\u00F3n (sin tener en cuenta categor\u00EDas)");
			sliderPuntos.setPaintTicks(true);
			sliderPuntos.setPaintLabels(true);
			sliderPuntos.setMinorTickSpacing(10);
			sliderPuntos.setMajorTickSpacing(500);
			sliderPuntos.setBackground(Color.CYAN);
			sliderPuntos.setMaximum(2500);
		}
		return sliderPuntos;
	}
	private void cambiaValorSlider() {
		int puntos = getSliderPuntos().getValue();
		getTextFieldPuntosSlider().setText(String.valueOf(puntos));
		filtraPor(puntos);
	}
	
	private void filtraPor(int puntos) {
		for (int i=0; i < panelRegalos.getComponentCount(); i++) {
			if (regalos.filtrarRegalosPrecio(puntos).contains(regalos.getRegalos()[i])) {
				panelRegalos.getComponents()[i].setEnabled(true);
			} else {
				panelRegalos.getComponents()[i].setEnabled(false);
			}
		}
	}
	private JTextField getTextFieldPuntosSlider() {
		if (textFieldPuntosSlider == null) {
			textFieldPuntosSlider = new JTextField();
			textFieldPuntosSlider.setBounds(407, 17, 86, 20);
			textFieldPuntosSlider.setText("0");
			textFieldPuntosSlider.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPuntosSlider.setEditable(false);
			textFieldPuntosSlider.setColumns(10);
		}
		return textFieldPuntosSlider;
	}
	private JLabel getLblNewLabelValor() {
		if (lblNewLabelValor == null) {
			lblNewLabelValor = new JLabel("Precio m\u00E1ximo:");
			lblNewLabelValor.setBounds(309, 20, 98, 14);
		}
		return lblNewLabelValor;
	}
	private JLabel getLblNewLabelSliderExplicacion() {
		if (lblNewLabelSliderExplicacion == null) {
			lblNewLabelSliderExplicacion = new JLabel("[Debe tener en cuenta que los regalos siempre est\u00E1n filtrados por los puntos del slider de abajo, a pesar de que se filtren por categor\u00EDas]");
			lblNewLabelSliderExplicacion.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabelSliderExplicacion.setForeground(new Color(0, 255, 255));
			lblNewLabelSliderExplicacion.setBounds(154, 13, 683, 14);
		}
		return lblNewLabelSliderExplicacion;
	}
	private JTextField getTextFieldEntregasContador() {
		if (textFieldEntregasContador == null) {
			textFieldEntregasContador = new JTextField();
			textFieldEntregasContador.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldEntregasContador.setVisible(false);
			textFieldEntregasContador.setBorder(new LineBorder(Color.BLACK));
			textFieldEntregasContador.setEditable(false);
			textFieldEntregasContador.setForeground(new Color(0, 0, 0));
			textFieldEntregasContador.setBackground(new Color(0, 255, 255));
			textFieldEntregasContador.setBounds(947, 47, 28, 19);
			textFieldEntregasContador.setColumns(10);
		}
		return textFieldEntregasContador;
	}
	private JLabel getLblNewLabelDisponibles() {
		if (lblNewLabelDisponibles == null) {
			lblNewLabelDisponibles = new JLabel("Puntos disponibles:");
			lblNewLabelDisponibles.setBounds(596, 20, 122, 14);
		}
		return lblNewLabelDisponibles;
	}
	private JPanel getPanelPuntos() {
		if (panelPuntos == null) {
			panelPuntos = new JPanel();
			panelPuntos.setBounds(143, 462, 814, 61);
			panelPuntos.setLayout(null);
			panelPuntos.add(getLblNewLabelFiltrar());
			panelPuntos.add(getSliderPuntos());
			panelPuntos.add(getLblNewLabelValor());
			panelPuntos.add(getTextFieldPuntosSlider());
			panelPuntos.add(getBtnNewButtonReiniciar());
			panelPuntos.add(getLblNewLabelDisponibles());
			panelPuntos.add(getTextFieldPuntos());
		}
		return panelPuntos;
	}

	protected VentanaPanelCasillas getVentanaPanelCasillas() {
		return ventanaPanelCasillas;
	}
	
	class AccionBoton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton) e.getSource();
			int i = Integer.parseInt(bt.getActionCommand());
			Regalo regalo = regalos.getRegalos()[i];
			Entrega entrega = new Entrega(entregas.getId(), regalo.getCodigo(), null, null, regalo.getPuntos(), regalo.getDenominacion());
			if (entregas.sePuedeComprar(entrega, 1)) {
				if (regalos.getRegalos()[i].getCodigo().contains("V")) {
						try {
							VentanaSeleccionViajes frame = new VentanaSeleccionViajes(regalo, entrega, entregas, hs, hb);
							frame.setModal(true);
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							if (entregas.getListaEntregas().size()!=0) { 
								textFieldEntregasContador.setVisible(true);
								textFieldEntregasContador.setText("+" + entregas.getListaEntregas().size()); 
							} else {
								textFieldEntregasContador.setVisible(false);
							}
							textFieldPuntosSlider.setText(entregas.getPuntosEntregas() + "");
							filtraPor(entregas.getPuntosEntregas());
							textFieldPuntos.setText(entregas.getPuntosEntregas() + "");
							getSliderPuntos().setValue(entregas.getPuntosEntregas());
							if (entregas.getPuntosEntregas()==0) {
								for (int j=0; j < panelCategorias.getComponents().length; j++) {
									((JButton) panelCategorias.getComponents()[j]).setEnabled(false);
								}
								JOptionPane.showMessageDialog(null, "No tiene más puntos, tiene que acceder a la siguiente ventana de confirmación de regalos\n"
										+ "Para ello selecciona el botón de arriba a la derecha (el carrito de la compra)", "Fin de puntos", JOptionPane.INFORMATION_MESSAGE);
								btnNewButtonCarrito.grabFocus();
							}
							btnNewButtonCarrito.setToolTipText(entregas.toStringCarro()); 
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				} else {
						try {
							VentanaSeleccionArticulo frame = new VentanaSeleccionArticulo(regalo, entrega, entregas, hs, hb);
							frame.setModal(true);
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							if (entregas.getListaEntregas().size()!=0) { 
								textFieldEntregasContador.setVisible(true);
								textFieldEntregasContador.setText("+" + entregas.getListaEntregas().size()); 
							} else {
								textFieldEntregasContador.setVisible(false);
							}
							textFieldPuntosSlider.setText(entregas.getPuntosEntregas() + "");
							filtraPor(entregas.getPuntosEntregas());
							textFieldPuntos.setText(entregas.getPuntosEntregas() + "");
							getSliderPuntos().setValue(entregas.getPuntosEntregas());
							if (entregas.getPuntosEntregas()==0) {
								for (int j=0; j < panelCategorias.getComponents().length; j++) {
									((JButton) panelCategorias.getComponents()[j]).setEnabled(false);
								}
								JOptionPane.showMessageDialog(null, "No tiene más puntos, tiene que acceder a la siguiente ventana de confirmación de regalos\n"
										+ "Para ello selecciona el botón de arriba a la derecha (el carrito de la compra)", "Fin de puntos", JOptionPane.INFORMATION_MESSAGE);
								btnNewButtonCarrito.grabFocus();
							}
							btnNewButtonCarrito.setToolTipText(entregas.toStringCarro());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
			} else {
				JOptionPane.showMessageDialog(null, "Lo sentimos, no puede comprar este artículo por falta de puntos\n Consulte sus puntos y mire otras posibilidades\n"
						+ "Si no puede acceder a ningún regalo más, vaya a la siguiente pantalla"
						, "Falta de puntos", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
