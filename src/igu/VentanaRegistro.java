package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.Entregas;
import logica.Juego;
import logica.Regalos;
import logica.Usuarios;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JMenuBar menuBar;
	private JMenu menuOpciones;
	private JMenuItem mntmNewMenuNuevo;
	private JMenuItem mntmNewMenuSalir;
	private JMenu menuAyuda;
	private JMenuItem mntmNewMenuItemContenidos;
	private JMenuItem mntmNewMenuItemAcercaDe;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblHome;
	private JLabel lblTitulo;
	private JLabel lblFotoPanel;
	private JLabel lblLogo;
	private JLabel lblTarjeta;
	private JTextField textFieldIdentificador;
	private JButton btnNewButtonSiguiente;
	private JButton btnNewButtonReset;
	private JButton btnNewButtonAyuda;
	private JButton btnNewButtonJugar;
	private JButton btnNewButtonInterrogacion;
	private JLabel lblNewLabelUsuario;
	private JButton btnNewButtonConfirmar;
	private JMenuItem mntmNewMenuItemComoJugar;
	private JMenuItem mntmNewMenuItemIdentificador;
	private JSeparator separator_2;
	
	private Juego juego;
	private Regalos regalos;
	private Entregas entregas;
	private Usuarios usuarios;
	
	private HelpSet hs;
	private HelpBroker hb;
	
	public VentanaRegistro(Juego juego, Regalos regalos, Entregas entregas, Usuarios usuarios) {
		this.juego = juego;
		this.regalos = regalos;
		this.entregas = entregas;
		this.usuarios = usuarios;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (confirmarCancelacion()) {
					System.exit(0);
				}
			}
			@Override
			public void windowOpened(WindowEvent e) {
				textFieldIdentificador.grabFocus();
			}
		});
		setTitle("Panel de regalos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/img/panelderegalos.PNG")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 775, 547);
		setJMenuBar(getMenuBar_1());
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(230, 230, 250));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		panelPrincipal.add(getLblHome());
		panelPrincipal.add(getLblTitulo());
		panelPrincipal.add(getLblFotoPanel());
		panelPrincipal.add(getLblLogo());
		panelPrincipal.add(getLblTarjeta());
		panelPrincipal.add(getTextFieldIdentificador());
		panelPrincipal.add(getBtnNewButtonSiguiente());
		panelPrincipal.add(getBtnNewButtonReset());
		panelPrincipal.add(getBtnNewButtonAyuda());
		panelPrincipal.add(getBtnNewButtonJugar());
		panelPrincipal.add(getBtnNewButtonInterrogacion());
		panelPrincipal.add(getLblNewLabelUsuario());
		panelPrincipal.add(getBtnNewButtonConfirmar());
		cargaAyuda();
	}
	
	private void cargaAyuda(){
		   URL hsURL;

		    try {
			    	File fichero = new File("help/Ayuda.hs");
			    	hsURL = fichero.toURI().toURL();
			        hs = new HelpSet(null, hsURL);
			}

		    catch (Exception e){
		    		System.out.println("Ayuda no encontrada");
		    		return;
		    }

		   hb = hs.createHelpBroker();
		   hb.initPresentation();

		   hb.enableHelpKey(getRootPane(),"panel", hs);
		   hb.enableHelpOnButton(btnNewButtonAyuda, "panel", hs);
		   hb.enableHelpOnButton(mntmNewMenuItemContenidos, "panel", hs);
		   hb.enableHelp(textFieldIdentificador, "registrar", hs);
		   hb.enableHelp(btnNewButtonConfirmar, "registrar", hs);
		   hb.enableHelp(btnNewButtonInterrogacion, "identificador", hs);
	}

	private boolean confirmarCancelacion() {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres salir de la aplicación ?", "Salir de la aplicación",
				JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}
	
	private boolean confirmarNuevo() {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres iniciar un nuevo juego ?", "Iniciar la aplicación",
				JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenuOpciones());
			menuBar.add(getMenuAyuda());
		}
		return menuBar;
	}
	private JMenu getMenuOpciones() {
		if (menuOpciones == null) {
			menuOpciones = new JMenu("Jugar");
			menuOpciones.setMnemonic('J');
			menuOpciones.add(getMntmNewMenuNuevo());
			menuOpciones.add(getSeparator_1());
			menuOpciones.add(getMntmNewMenuItemComoJugar());
			menuOpciones.add(getMntmNewMenuItemIdentificador());
			menuOpciones.add(getSeparator_2());
			menuOpciones.add(getMntmNewMenuSalir());
		}
		return menuOpciones;
	}
	private JMenuItem getMntmNewMenuNuevo() {
		if (mntmNewMenuNuevo == null) {
			mntmNewMenuNuevo = new JMenuItem("Nuevo");
			mntmNewMenuNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
			mntmNewMenuNuevo.setMnemonic('N');
			mntmNewMenuNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (confirmarNuevo()) {
						inicializar();
					}
				}
			});
		}
		return mntmNewMenuNuevo;
	}
	private JMenuItem getMntmNewMenuSalir() {
		if (mntmNewMenuSalir == null) {
			mntmNewMenuSalir = new JMenuItem("Salir");
			mntmNewMenuSalir.setMnemonic('S');
			mntmNewMenuSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (confirmarCancelacion()) {
						System.exit(0);
					}
				}
			});
		}
		return mntmNewMenuSalir;
	}
	
	protected void inicializar() {
		btnNewButtonSiguiente.setEnabled(false);
		textFieldIdentificador.setText(null);
		btnNewButtonConfirmar.setEnabled(true);
		lblNewLabelUsuario.setText(null);
		textFieldIdentificador.setEditable(true);
		btnNewButtonReset.setEnabled(true);
		textFieldIdentificador.grabFocus();
		usuarios.inicializar();
		entregas.inicializar();
		juego.inicializar();
		this.setVisible(true);
	}
	
	protected void inicializarSinLeerFicheroUsuarios() {
		btnNewButtonSiguiente.setEnabled(false);
		textFieldIdentificador.setText(null);
		btnNewButtonConfirmar.setEnabled(true);
		lblNewLabelUsuario.setText(null);
		textFieldIdentificador.setEditable(true);
		btnNewButtonReset.setEnabled(true);
		textFieldIdentificador.grabFocus();
		entregas.inicializar();
		juego.inicializar();
		this.setVisible(true);
	}
	
	private JMenu getMenuAyuda() {
		if (menuAyuda == null) {
			menuAyuda = new JMenu("Ayuda");
			menuAyuda.setMnemonic('A');
			menuAyuda.add(getMntmNewMenuItemContenidos());
			menuAyuda.add(getSeparator());
			menuAyuda.add(getMntmNewMenuItemAcercaDe());
		}
		return menuAyuda;
	}
	private JMenuItem getMntmNewMenuItemContenidos() {
		if (mntmNewMenuItemContenidos == null) {
			mntmNewMenuItemContenidos = new JMenuItem("Contenidos");
			mntmNewMenuItemContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK));
			mntmNewMenuItemContenidos.setMnemonic('C');
		}
		return mntmNewMenuItemContenidos;
	}
	private JMenuItem getMntmNewMenuItemAcercaDe() {
		if (mntmNewMenuItemAcercaDe == null) {
			mntmNewMenuItemAcercaDe = new JMenuItem("Acerca de");
			mntmNewMenuItemAcercaDe.setMnemonic('e');
			mntmNewMenuItemAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Panel de regalos\n Realizado por Miguel González Navarro\n"
							+ " Prácticas CPM 21-22\n EII Oviedo", "Acerca de", JOptionPane.INFORMATION_MESSAGE);				}
			});
		}
		return mntmNewMenuItemAcercaDe;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JLabel getLblHome() {
		if (lblHome == null) {
			lblHome = new JLabel("");
			lblHome.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/img/home.PNG")));
			lblHome.setBounds(22, 11, 94, 109);
		}
		return lblHome;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("PANEL DE REGALOS");
			lblTitulo.setForeground(new Color(0, 0, 0));
			lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 22));
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setBounds(203, 11, 278, 54);
		}
		return lblTitulo;
	}
	private JLabel getLblFotoPanel() {
		if (lblFotoPanel == null) {
			lblFotoPanel = new JLabel("");
			lblFotoPanel.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/img/regalos.PNG")));
			lblFotoPanel.setBounds(149, 65, 379, 192);
		}
		return lblFotoPanel;
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setBounds(563, 46, 170, 171);
			setIconoAdaptado(lblLogo, "/img/logoPanelRegalos.PNG");
		}
		return lblLogo;
	}
	private JLabel getLblTarjeta() {
		if (lblTarjeta == null) {
			lblTarjeta = new JLabel("Introduzca el identificador de su tarjeta y haz click para validar su identificador en la web:");
			lblTarjeta.setForeground(new Color(0, 0, 0));
			lblTarjeta.setLabelFor(getTextFieldIdentificador());
			lblTarjeta.setDisplayedMnemonic('I');
			lblTarjeta.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblTarjeta.setBounds(22, 279, 610, 29);
		}
		return lblTarjeta;
	}
	private JTextField getTextFieldIdentificador() {
		if (textFieldIdentificador == null) {
			textFieldIdentificador = new JTextField();
			textFieldIdentificador.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldIdentificador.setBounds(55, 333, 278, 41);
			textFieldIdentificador.setColumns(10);
		}
		return textFieldIdentificador;
	}
	private JButton getBtnNewButtonSiguiente() {
		if (btnNewButtonSiguiente == null) {
			btnNewButtonSiguiente = new JButton("Siguiente");
			btnNewButtonSiguiente.setEnabled(false);
			btnNewButtonSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								VentanaPanelCasillas frame = crearVentanaCasillas();
								btnNewButtonSiguiente.setEnabled(false);
								JOptionPane.showMessageDialog(frame, "El objetivo de esta ventana es conseguir el mayor número de puntos posible para ganar más regalos\n"
										+ "Dispondrá de tres oportunidades (como mínimo) y no podrá elegir la misma casilla\n"
										+ "En caso de que toque una casilla especial dispondrá de otra tirada, como hay dos casillas de este tipo, como máximo podrá optar a 5\n"
										+ "El tablero contiene casillas especiales y una casilla X2 que dobla la puntuación actual (si disponemos de 250 puntos, la casilla sumará 250 puntos al contador)\n"
										+ "También existen otro tipo de casillas (las que dan puntos), pueden dar 1000, 250, 50 o 0 puntos\n"
										+ "Cuando finalicen las tiradas podrá avanzar a la siguiente ventana, haciendo click en Seleccionar artículos", "Panel de Casillas", JOptionPane.INFORMATION_MESSAGE);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});
			btnNewButtonSiguiente.setMnemonic('S');
			btnNewButtonSiguiente.setToolTipText("Seleccione siguiente para avanzar al panel de premios");
			btnNewButtonSiguiente.setBackground(Color.GREEN);
			btnNewButtonSiguiente.setBounds(425, 447, 89, 23);
		}
		return btnNewButtonSiguiente;
	}
	
	private VentanaPanelCasillas crearVentanaCasillas() {
		VentanaPanelCasillas frame = new VentanaPanelCasillas(juego, regalos, entregas, this, hs, hb);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		this.setVisible(false);
		return frame;
	}
	
	private JButton getBtnNewButtonReset() {
		if (btnNewButtonReset == null) {
			btnNewButtonReset = new JButton("Reset");
			btnNewButtonReset.setMnemonic('R');
			btnNewButtonReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldIdentificador.setText(null);
				}
			});
			btnNewButtonReset.setToolTipText("Borra los datos de su identificador");
			btnNewButtonReset.setBackground(Color.RED);
			btnNewButtonReset.setBounds(543, 447, 89, 23);
		}
		return btnNewButtonReset;
	}
	private JButton getBtnNewButtonAyuda() {
		if (btnNewButtonAyuda == null) {
			btnNewButtonAyuda = new JButton("Ayuda");
			btnNewButtonAyuda.setMnemonic('y');
			btnNewButtonAyuda.setToolTipText("Accede a un manual de ayuda del panel de regalos");
			btnNewButtonAyuda.setBackground(Color.ORANGE);
			btnNewButtonAyuda.setBounds(656, 447, 89, 23);
		}
		return btnNewButtonAyuda;
	}
	private JButton getBtnNewButtonJugar() {
		if (btnNewButtonJugar == null) {
			btnNewButtonJugar = new JButton("\u00BF C\u00F3mo jugar ?  En 5 sencillos pasos");
			btnNewButtonJugar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						VentanaComoJugar dialog = new VentanaComoJugar();
						dialog.setLocationRelativeTo(null);
						dialog.setModal(true);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			btnNewButtonJugar.setMnemonic('C');
			btnNewButtonJugar.setToolTipText("Accede a una nueva ventana, donde podr\u00E1s ver instrucciones sobre c\u00F3mo jugar");
			btnNewButtonJugar.setBackground(Color.ORANGE);
			btnNewButtonJugar.setBounds(74, 444, 253, 29);
		}
		return btnNewButtonJugar;
	}
	private JButton getBtnNewButtonInterrogacion() {
		if (btnNewButtonInterrogacion == null) {
			btnNewButtonInterrogacion = new JButton("");
			btnNewButtonInterrogacion.setBorder(new LineBorder(Color.RED));
			btnNewButtonInterrogacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						VentanaTarjetaFidelizacion dialog = new VentanaTarjetaFidelizacion();
						dialog.setLocationRelativeTo(null);
						dialog.setModal(true);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			btnNewButtonInterrogacion.setToolTipText("Accede a una nueva ventana donde podr\u00E1s observar donde est\u00E1 situado el identifcador de su tarjeta");
			btnNewButtonInterrogacion.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/img/ayudaPanelRegalos.PNG")));
			btnNewButtonInterrogacion.setBounds(628, 273, 35, 49);
		}
		return btnNewButtonInterrogacion;
	}
	private JLabel getLblNewLabelUsuario() {
		if (lblNewLabelUsuario == null) {
			lblNewLabelUsuario = new JLabel("");
			lblNewLabelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelUsuario.setBounds(55, 385, 316, 40);
		}
		return lblNewLabelUsuario;
	}
	private JButton getBtnNewButtonConfirmar() {
		if (btnNewButtonConfirmar == null) {
			btnNewButtonConfirmar = new JButton("");
			btnNewButtonConfirmar.setDisabledIcon(new ImageIcon(VentanaRegistro.class.getResource("/img/iconoConfirmacion.PNG")));
			btnNewButtonConfirmar.setBorder(new LineBorder(Color.GREEN));
			btnNewButtonConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (! usuarios.compruebaIdUsuario(textFieldIdentificador.getText())) {
						JOptionPane.showMessageDialog(null, "Usuario incorrecto, introduzca de nuevo el identificador de la tarjeta de fidelización."
								, "Error", JOptionPane.INFORMATION_MESSAGE);
						textFieldIdentificador.setText(null);
						textFieldIdentificador.grabFocus();
						
					} else if (! usuarios.compruebaPuedeJugarUsuario(textFieldIdentificador.getText())) {
						JOptionPane.showMessageDialog(null, "Usuario no válido, no tiene derecho a jugar. Lo sentimos."
								, "Error", JOptionPane.INFORMATION_MESSAGE);
						textFieldIdentificador.setText(null);
						textFieldIdentificador.grabFocus();
					} else {
						btnNewButtonSiguiente.setEnabled(true);
						textFieldIdentificador.setEditable(false);
						btnNewButtonConfirmar.setEnabled(false);
						getBtnNewButtonReset().setEnabled(false);
						entregas.setNombre(usuarios.getNombre(textFieldIdentificador.getText()));
						lblNewLabelUsuario.setText("Bienvenido/a : Don/Doña " + entregas.getNombre());
						entregas.setId(textFieldIdentificador.getText());
						btnNewButtonSiguiente.grabFocus();
						usuarios.actualizaFichero();
					}
					
				}
			});
			btnNewButtonConfirmar.setToolTipText("Pulse para registrar su identificador en el sistema");
			btnNewButtonConfirmar.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/img/iconoConfirmacion.PNG")));
			btnNewButtonConfirmar.setBounds(352, 333, 42, 41);
		}
		return btnNewButtonConfirmar;
	}
	
	private JMenuItem getMntmNewMenuItemComoJugar() {
		if (mntmNewMenuItemComoJugar == null) {
			mntmNewMenuItemComoJugar = new JMenuItem("\u00BF C\u00F3mo jugar ?");
			mntmNewMenuItemComoJugar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						VentanaComoJugar dialog = new VentanaComoJugar();
						dialog.setLocationRelativeTo(null);
						dialog.setModal(true);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			mntmNewMenuItemComoJugar.setMnemonic('C');
		}
		return mntmNewMenuItemComoJugar;
	}
	private JMenuItem getMntmNewMenuItemIdentificador() {
		if (mntmNewMenuItemIdentificador == null) {
			mntmNewMenuItemIdentificador = new JMenuItem("Localiza tu identificador");
			mntmNewMenuItemIdentificador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						VentanaTarjetaFidelizacion dialog = new VentanaTarjetaFidelizacion();
						dialog.setLocationRelativeTo(null);
						dialog.setModal(true);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			mntmNewMenuItemIdentificador.setMnemonic('L');
		}
		return mntmNewMenuItemIdentificador;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
		}
		return separator_2;
	}
	
	private void setIconoAdaptado(JLabel label, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 label.setIcon(icon);
	}
}
