package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.border.TitledBorder;

import logica.CasillaEspecial;
import logica.CasillaX2Puntos;
import logica.Entregas;
import logica.Juego;
import logica.Regalos;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class VentanaPanelCasillas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelContador;
	private JButton botonSeleccionArticulos;
	private JPanel panelCasillas;
	
	private JPanel panelPuntos;
	private JTextField textFieldContador1;
	private JTextField textFieldContador3;
	private JTextField textFieldTotal;
	private JTextField textFieldContador2;

	private Juego juego;
	private Regalos regalos;
	private Entregas entregas;
	private VentanaRegistro ventanaRegistro;
	
	private ProcesaCasillaPuntos pP;
	private ProcesaCasillaEspecial pE;
	private ProcesaCasillaX2Puntos pX2;
	
	private HelpSet hs;
	private HelpBroker hb;
	
	public VentanaPanelCasillas(Juego juego, Regalos regalos, Entregas entregas, VentanaRegistro ventanaRegistro, HelpSet hs, HelpBroker hb) {
		this.juego = juego;
		this.regalos = regalos;
		this.entregas = entregas;
		this.ventanaRegistro = ventanaRegistro;
		this.pP = new ProcesaCasillaPuntos();
		this.pE = new ProcesaCasillaEspecial();
		this.pX2 = new ProcesaCasillaX2Puntos();
		this.hs = hs;
		this.hb = hb;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				asociaImagenBotones();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPanelCasillas.class.getResource("/img/panelpremios.PNG")));
		setTitle("Panel de Premios");
		setBounds(100, 100, 894, 443);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
		panelPrincipal.add(getPanelContador(), BorderLayout.EAST);
		panelPrincipal.add(getBotonSeleccionArticulos(), BorderLayout.SOUTH);
		panelPrincipal.add(getPanelCasillas(), BorderLayout.CENTER);
		configurarAyuda();
	}
	private void configurarAyuda() {
		hb.enableHelpKey(getRootPane(),"casillas", hs);
	    hb.enableHelp(panelContador, "casillas", hs);
		hb.enableHelp(panelCasillas, "casillas", hs);
		hb.enableHelp(botonSeleccionArticulos, "casillas", hs);
	}
	
	private JPanel getPanelContador() {
		if (panelContador == null) {
			panelContador = new JPanel();
			panelContador.setForeground(Color.WHITE);
			panelContador.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Contador", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
			panelContador.setLayout(new BorderLayout(0, 0));
			panelContador.add(getPanelPuntos(), BorderLayout.CENTER);
		}
		return panelContador;
	}
	private JButton getBotonSeleccionArticulos() {
		if (botonSeleccionArticulos == null) {
			botonSeleccionArticulos = new JButton("Seleccionar articulos");
			botonSeleccionArticulos.setEnabled(false);
			botonSeleccionArticulos.setBackground(Color.GREEN);
			botonSeleccionArticulos.setMnemonic('S');
			botonSeleccionArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								if (juego.getPuntos()==0) {
									crearVentanaConfirmacionSinPremios();						
								} else {
									crearVentanaRegalos();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});
		}
		return botonSeleccionArticulos;
	}
	
	private void crearVentanaConfirmacionSinPremios() {
		VentanaConfirmacionSinPremios dialog = new VentanaConfirmacionSinPremios(entregas, this, hs, hb);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	private void crearVentanaRegalos() {
		VentanaPanelRegalos frame = new VentanaPanelRegalos(juego, regalos, entregas, this, hs, hb);
		frame.setModal(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private JPanel getPanelCasillas() {
		if (panelCasillas == null) {
			panelCasillas = new JPanel();
			panelCasillas.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
			panelCasillas.setBackground(new Color(230, 230, 250));
			panelCasillas.setLayout(new GridLayout(5, 5, 0, 0));
			System.out.println("PUNTOS DEL PANEL DE CASILLAS: ");
			for (int i = 0; i < Juego.TAM; i++) {
				panelCasillas.add(nuevoBoton(i));
				System.out.println(i+1 + " -> " + juego.getCasillas()[i].toString());
			}
			validate();
		}
		return panelCasillas;
	}
	
	private JButton nuevoBoton(int i) {
		JButton bt = new JButton("");
		bt.setActionCommand(i + "");
		bt.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		bt.setToolTipText("Seleccione la casilla haciendo click");
		if (juego.getCasillas()[i] instanceof CasillaEspecial) {
			bt.addActionListener(pE);
		} else if (juego.getCasillas()[i] instanceof CasillaX2Puntos) {
			bt.addActionListener(pX2);
		} else {
			bt.addActionListener(pP);
		}
		return bt;
	}
	
	private void setImagenAdaptada(JButton boton, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(boton.getWidth(),boton.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 boton.setIcon(icon);
		 boton.setDisabledIcon(icon);
	}
	

	private void asociaImagenBotones() {
		for (int i = 0; i < panelCasillas.getComponents().length; i++)
		{
			if (panelCasillas.getComponents()[i].isEnabled()) {
				JButton boton = (JButton) (panelCasillas.getComponents()[i]);
				setImagenAdaptada(boton,"/img/casillasinseleccionar.PNG");
			} else {
				JButton boton = (JButton) (panelCasillas.getComponents()[i]);
				if (juego.getCasillas()[i] instanceof CasillaX2Puntos) {
					setImagenAdaptada(boton,"/img/premioX2puntos.PNG");
				} else if (juego.getCasillas()[i] instanceof CasillaEspecial) {
					setImagenAdaptada(boton,"/img/casillaespecial.PNG");
				} else {
					if (juego.getCasillas()[i].getPuntos() == 0) {
						setImagenAdaptada(boton,"/img/ningunpremio.PNG");
					} else {
						setImagenAdaptada(boton,"/img/premio" + juego.getCasillas()[i].getPuntos() + "puntos.png");
					}
				}
			}
		}
	}
	
	private void deshabilitarTablero() {
		panelCasillas.setEnabled(false);
		for(int i=0;i<panelCasillas.getComponentCount(); i++) {
			if (! panelCasillas.getComponents()[i].isEnabled()) {
				panelCasillas.getComponents()[i].setEnabled(false);
			}
			((JButton) panelCasillas.getComponents()[i]).setToolTipText(null);
		}
	}
	private JPanel getPanelPuntos() {
		if (panelPuntos == null) {
			panelPuntos = new JPanel();
			panelPuntos.setLayout(new BoxLayout(panelPuntos, BoxLayout.Y_AXIS));
			panelPuntos.add(getTextField_4());
			panelPuntos.add(getTextField_7());
			panelPuntos.add(getTextField_5());
			panelPuntos.add(getTextField_6());
			panelPuntos.setBounds(0, 0, 500, 0);
		}
		return panelPuntos;
	}
	private JTextField getTextField_4() {
		if (textFieldContador1 == null) {
			textFieldContador1 = new JTextField();
			textFieldContador1.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldContador1.setEditable(false);
			textFieldContador1.setColumns(10);
		}
		return textFieldContador1;
	}
	private JTextField getTextField_5() {
		if (textFieldContador3 == null) {
			textFieldContador3 = new JTextField();
			textFieldContador3.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldContador3.setEditable(false);
			textFieldContador3.setColumns(10);
		}
		return textFieldContador3;
	}
	private JTextField getTextField_6() {
		if (textFieldTotal == null) {
			textFieldTotal = new JTextField();
			textFieldTotal.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldTotal.setEditable(false);
			textFieldTotal.setColumns(10);
		}
		return textFieldTotal;
	}
	private JTextField getTextField_7() {
		if (textFieldContador2 == null) {
			textFieldContador2 = new JTextField();
			textFieldContador2.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldContador2.setEditable(false);
			textFieldContador2.setColumns(10);
		}
		return textFieldContador2;
	}

	protected VentanaRegistro getVentanaRegistro() {
		return ventanaRegistro;
	}
	
	class ProcesaCasillaPuntos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (! juego.isPartidaFinalizada()) {
				JButton bt = (JButton) e.getSource();
				int i = Integer.parseInt(bt.getActionCommand());
				if (juego.getCasillas()[i].getPuntos() == 0) {
					setImagenAdaptada(bt,"/img/ningunpremio.PNG");
				} else {
					setImagenAdaptada(bt,"/img/premio" + juego.getCasillas()[i].getPuntos() + "puntos.png");
				}
				bt.setToolTipText(juego.getCasillas()[i].toString());
				juego.seleccionaCasilla(i);
				JOptionPane.showMessageDialog(null, "Esta casilla contiene un premio de:\n\t\t\t" + juego.getCasillas()[i].getPuntos() + " PUNTOS\n"
						+ "CASILLAS RESTANTES POR DESCUBRIR: " + juego.getIntentos(), "Premio de la casilla", JOptionPane.INFORMATION_MESSAGE);
				panelCasillas.getComponents()[i].setEnabled(false);
				if (juego.getIntentos()==2) {
					textFieldContador1.setText("CASILLA 1 = " + juego.getCasillas()[i].getPuntos());
					textFieldContador1.setBackground(Color.GRAY);
				} else if (juego.getIntentos()==1) {
					textFieldContador2.setText("CASILLA 2 = " + juego.getCasillas()[i].getPuntos());
					textFieldContador2.setBackground(Color.GRAY);
				} else {
					textFieldContador3.setText("CASILLA 3 = " + juego.getCasillas()[i].getPuntos());
					textFieldTotal.setText("TOTAL = " + juego.getPuntos());
					textFieldContador3.setBackground(Color.GRAY);
					textFieldTotal.setBackground(Color.GRAY);
					deshabilitarTablero();
					botonSeleccionArticulos.setEnabled(true);
					entregas.setPuntosJuego(juego.getPuntos());
					entregas.setPuntosEntregas(juego.getPuntos());
					JOptionPane.showMessageDialog(null, "Acaban de terminar sus tiradas, ha conseguido un total de " + juego.getPuntos() + " puntos\n"
							+ "Avanza a la siguiente ventana, presionando Seleccionar articulos", "Fin de tiradas", JOptionPane.INFORMATION_MESSAGE);
					botonSeleccionArticulos.grabFocus();
				}
			} 
		}
	}
	
	class ProcesaCasillaEspecial implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (! juego.isPartidaFinalizada()) {
				JButton bt = (JButton) e.getSource();
				int i = Integer.parseInt(bt.getActionCommand());
				setImagenAdaptada(bt,"/img/casillaespecial.PNG");
				bt.setToolTipText(juego.getCasillas()[i].toString());
				juego.seleccionaCasilla(i);
				JOptionPane.showMessageDialog(null, "\tCASILLA ESPECIAL\n PUEDES DESTAPAR OTRA CASILLA MÁS\n\t¡ESTÁS DE SUERTE!\n  " 
						+ "CASILLAS RESTANTES POR DESCUBRIR: " + juego.getIntentos(), "Premio de la casilla", JOptionPane.INFORMATION_MESSAGE);
				panelCasillas.getComponents()[i].setEnabled(false);
			} 
		}
	}
	
	class ProcesaCasillaX2Puntos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (! juego.isPartidaFinalizada()) {
				JButton bt = (JButton) e.getSource();
				int i = Integer.parseInt(bt.getActionCommand());
				setImagenAdaptada(bt,"/img/premioX2puntos.PNG");
				bt.setToolTipText(juego.getCasillas()[i].toString());
				juego.seleccionaCasilla(i);
				JOptionPane.showMessageDialog(null, "\t¡DOBLA TUS PUNTOS!  X2\n HAS CONSEGUIDO MULTIPLICAR TU CONTADOR\n\t¡ENHORABUENA!\n  " 
						+ "CASILLAS RESTANTES POR DESCUBRIR: " + juego.getIntentos(), "Premio de la casilla", JOptionPane.INFORMATION_MESSAGE);
				panelCasillas.getComponents()[i].setEnabled(false);
				if (juego.getIntentos()==2) {
					textFieldContador1.setText("CASILLA 1(X2)=" + juego.getPuntos() / 2);
					textFieldContador1.setBackground(Color.GRAY);
				} else if (juego.getIntentos()==1) {
					textFieldContador2.setText("CASILLA 2(X2)=" + juego.getPuntos() / 2);
					textFieldContador2.setBackground(Color.GRAY);
				} else {
					textFieldContador3.setText("CASILLA 3(X2)=" + juego.getPuntos() / 2);
					textFieldContador3.setBackground(Color.GRAY);
					textFieldTotal.setText("TOTAL = " + juego.getPuntos());
					deshabilitarTablero();
					textFieldTotal.setBackground(Color.GRAY);
					botonSeleccionArticulos.setEnabled(true);
					entregas.setPuntosJuego(juego.getPuntos());
					entregas.setPuntosEntregas(juego.getPuntos());
					botonSeleccionArticulos.grabFocus();
					JOptionPane.showMessageDialog(null, "Acaban de terminar sus tiradas, ha conseguido un total de " + juego.getPuntos() + " puntos\n"
							+ "Avanza a la siguiente ventana, presionando Seleccionar articulos", "Fin de tiradas", JOptionPane.INFORMATION_MESSAGE);
				} 
			}
		}
	}

	
}
