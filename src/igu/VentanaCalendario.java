package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import logica.Entrega;

public class VentanaCalendario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel panelPrincipal = new JPanel();
	private JCalendar calendar;
	private JButton btnNewButtonOk;
	Date date = new Date();
	
	private Entrega entrega;

	public VentanaCalendario(Entrega entrega) {
		this.entrega = entrega;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCalendario.class.getResource("/img/iconocalendario.PNG")));
		getContentPane().setBackground(Color.GRAY);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Selecciona el d\u00EDa del viaje (posterior a la fecha actual)");
		setBounds(100, 100, 460, 294);
		getContentPane().setLayout(null);
		getCalendar().getYearChooser().add(panelPrincipal, BorderLayout.NORTH);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(getCalendar());
		getContentPane().add(getBtnNewButtonOk());
	}
	
	
	private JCalendar getCalendar() {
		if (calendar == null) {
			calendar = new JCalendar();
			calendar.setBounds(10, 11, 298, 233);
			panelPrincipal.setLayout(null);
		}
		return calendar;
	}
	
	private JButton getBtnNewButtonOk() {
		if (btnNewButtonOk == null) {
			btnNewButtonOk = new JButton("OK");
			btnNewButtonOk.setBackground(Color.GREEN);
			btnNewButtonOk.setMnemonic('O');
			btnNewButtonOk.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					if (calendar.getCalendar().get(java.util.Calendar.YEAR) < date.getYear() + 1900 || 
						calendar.getCalendar().get(java.util.Calendar.YEAR) == date.getYear() + 1900  && calendar.getCalendar().get(java.util.Calendar.MONTH) + 1 < date.getMonth() + 1 ||
						calendar.getCalendar().get(java.util.Calendar.YEAR) == date.getYear() + 1900  && calendar.getCalendar().get(java.util.Calendar.MONTH) + 1 == date.getMonth() + 1
						&& calendar.getCalendar().get(java.util.Calendar.DATE) <= date.getDay() + 19) {
						JOptionPane.showMessageDialog(null, "La fecha de inicio del viaje debe de ser posterior a la fecha actual"
								, "Fecha de inicio incorrecta", JOptionPane.ERROR_MESSAGE);
						
					} else {
						String año = Integer.toString(calendar.getCalendar().get(java.util.Calendar.YEAR));
						String mes = Integer.toString(calendar.getCalendar().get(java.util.Calendar.MONTH) + 1);
						String dia = Integer.toString(calendar.getCalendar().get(java.util.Calendar.DATE));
						String fecha = dia + "/" + mes + "/" + año;
						entrega.setFecha(fecha);
						if (confirmarFecha(fecha)) {
							JOptionPane.showMessageDialog(null, "La fecha de inicio del viaje será el " + fecha
									, "Fecha de inicio del viaje", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
				     }
				}
			});
			btnNewButtonOk.setToolTipText("Confirma la fecha del viaje haciendo click");
			btnNewButtonOk.setBounds(345, 221, 89, 23);
		}
		return btnNewButtonOk;
	}

	private boolean confirmarFecha(String fecha) {
		boolean confir = false;
		int resp = JOptionPane.showConfirmDialog(this, "¿ Estás seguro de que quieres que la fecha sea el " + fecha + " ?", "Fecha de inicio del viaje",
				JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			confir = true;
		}
		return confir;
	}

}
