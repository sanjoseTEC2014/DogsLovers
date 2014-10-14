package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import actores.Mascota;

public class BusquedaMascotas extends JFrame {
	private final JLabel lblNewLabel = new JLabel("B\u00FAsqueda Mascotas Extraviadas");
	private JTable table;
	private JTextField textNombre;
	private JTextField textLugar;
	private JTextField textChipID;
	private JCheckBox checkNombre;
	private JCheckBox checkLugar;
	private JCheckBox checkChipID;
	private JCheckBox checkEspecie;
	private JCheckBox checkRaza;
	private JComboBox<String> comboEspecies;
	private JComboBox<String> comboRazas;

	public static void main(String[] args) {
		
		// Establece un look and feel met�lico, si no lo encuentra, establece el look and feel del sistema operativo.
		try {
			UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e2) {
				e2.printStackTrace();
			}
		}
				
		Mascota.especies.add("Perro");
		Mascota.razas.add( new String[]{ "Otro", "Chihuahua", "Schnauzer", "Doberman", "Salchicha" } );		
		
		Calendar fechaLucky = Calendar.getInstance();
		fechaLucky.set(2014, 9, 26);
		
		
		LinkedList<Mascota> prototipo = new LinkedList<Mascota>();
		prototipo.add(new Mascota("Lucky", 83511265, "bormo1218@gmail.com", 0, 1, "Heredia", fechaLucky, 50000, "Agradable."));
		
		for (Mascota i : prototipo){
			System.out.println(i);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaMascotas window = new BusquedaMascotas();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BusquedaMascotas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,700);
		
		JPanel marcoTitulo = new JPanel();
		getContentPane().add(marcoTitulo, BorderLayout.NORTH);
		marcoTitulo.setLayout(new BorderLayout(0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		marcoTitulo.add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnBuscar = new JButton("Realizar B\u00FAsqueda");
		marcoTitulo.add(btnBuscar, BorderLayout.EAST);
		
		JButton btnAyuda = new JButton("\u00A1Necesito Ayuda!");
		marcoTitulo.add(btnAyuda, BorderLayout.WEST);
		
		JPanel marcoContenido = new JPanel();
		getContentPane().add(marcoContenido, BorderLayout.CENTER);
		marcoContenido.setLayout(new BorderLayout(0, 0));
		
		JPanel marcoOpciones = new JPanel();
		marcoContenido.add(marcoOpciones, BorderLayout.NORTH);
		marcoOpciones.setLayout(new GridLayout(5, 2, 0, 0));
		
		checkNombre = new JCheckBox("Nombre");
		checkNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNombre.isSelected()) { textNombre.setEnabled(true); } else { textNombre.setEnabled(false); } 
			}
		});
		marcoOpciones.add(checkNombre);
		
		textNombre = new JTextField();
		textNombre.setEnabled(false);
		marcoOpciones.add(textNombre);
		textNombre.setColumns(10);
		
		checkLugar = new JCheckBox("Lugar Extrav\u00EDo");
		checkLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkLugar.isSelected()) { textLugar.setEnabled(true); } else { textLugar.setEnabled(false); } 
			}
		});
		marcoOpciones.add(checkLugar);
		
		textLugar = new JTextField();
		textLugar.setEnabled(false);
		marcoOpciones.add(textLugar);
		textLugar.setColumns(10);
		
		checkChipID = new JCheckBox("N\u00FAmero de Chip");
		checkChipID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkChipID.isSelected()) { textChipID.setEnabled(true); } else { textChipID.setEnabled(false); } 
			}
		});
		marcoOpciones.add(checkChipID);
		
		textChipID = new JTextField();
		textChipID.setEnabled(false);
		marcoOpciones.add(textChipID);
		textChipID.setColumns(10);
		
		checkEspecie = new JCheckBox("Especie");
		checkEspecie .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkEspecie.isSelected()) {
					comboEspecies.setEnabled(true);
					checkRaza.setEnabled(true);
					comboEspecies.setModel(new DefaultComboBoxModel<String>(Mascota.especies.toArray(new String[Mascota.especies.size()])));
				} else {
					comboEspecies.setEnabled(false);
					comboEspecies.setModel(new DefaultComboBoxModel<String>());
					checkRaza.setSelected(false);
					checkRaza.setEnabled(false);
					comboRazas.setModel(new DefaultComboBoxModel<String>());}
					comboRazas.setEnabled(false);
				}
			}
		);
		marcoOpciones.add(checkEspecie);
		
		comboEspecies = new JComboBox<String>();
		comboEspecies.setEnabled(false);
		marcoOpciones.add(comboEspecies);
		
		//} else { comboEspecies.setEnabled(false); comboEspecies.setModel(new DefaultComboBoxModel<String>());}
		
		checkRaza = new JCheckBox("Raza");
		checkRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkRaza.isSelected()) {
					comboRazas.setEnabled(true);
					comboRazas.setModel(new DefaultComboBoxModel<String>(Mascota.razas.get(comboEspecies.getSelectedIndex())));
				} else {
					comboRazas.setModel(new DefaultComboBoxModel<String>());
					comboRazas.setEnabled(false);
				}
			}
		});
		checkRaza.setEnabled(false);
		marcoOpciones.add(checkRaza);
		
		comboRazas = new JComboBox<String>();
		comboRazas.setEnabled(false);
		marcoOpciones.add(comboRazas);
		
		JScrollPane scrollTabla = new JScrollPane();
		marcoContenido.add(scrollTabla, BorderLayout.SOUTH);
		
		table = new JTable();
		scrollTabla.add(table);
		
		JPanel marcoConfirmaciones = new JPanel();
		getContentPane().add(marcoConfirmaciones, BorderLayout.SOUTH);
		marcoConfirmaciones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnReportarEncuentro = new JButton("Reportar Encuentro");
		marcoConfirmaciones.add(btnReportarEncuentro);
		
		JButton btnVerDetalles = new JButton("Ver Detalles");
		marcoConfirmaciones.add(btnVerDetalles);
		
	}

}
