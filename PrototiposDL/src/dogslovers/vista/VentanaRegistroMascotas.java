package dogslovers.vista;

import java.awt.Font;

import javax.swing.*;

import dogslovers.control.Acceso;
import dogslovers.control.Imagenes;
import dogslovers.control.Principal;
import dogslovers.control.excepciones.ImagenNoEncontradaException;
import dogslovers.modelo.Mascota;
import dogslovers.modelo.Suceso;
import dogslovers.recursos.Diseno;

import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.awt.BorderLayout;

public class VentanaRegistroMascotas extends JFrame {
	private JTextField campoNombre;
	private JTextField campoNumeroDeChip;
	private JFormattedTextField campoRecompensa;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButtonEncontrada;
	private JRadioButton radioButtonPerdida;
	private JRadioButton radioButtonAdoptable;
	private JEditorPane campoNotas;
	private JComboBox<String> comboBoxEspecie;
	private JComboBox<String> comboBoxRaza;
	private JComboBox<String> comboBoxTamanio;
	private JComboBox<String> comboBoxColor;
	private JComboBox<String> comboBoxSexo;
	private JCheckBox checkBoxCastrada;
	private JCheckBox checkBoxVacunada;
	private JCheckBox checkBoxDesparacitada;
	private String imagenSeleccionada;
	private JLabel lblRegistrarUnaMascota;
	private JLabel labelEspacioIzq;
	private JLabel labelEspacioDer;
	private JPanel panelContenido;
	private JPanel columnaIzq;
	private JPanel datosIniciales;
	private JLabel lblNombre;
	private JLabel lblNmeroDeChip;
	private JPanel caracteristicas;
	private JLabel lblEspecie;
	private JLabel lblRaza;
	private JLabel lblTamao;
	private JLabel lblColor;
	private JLabel lblSexo;
	private JPanel estado;
	private JPanel condicionesfisicas;
	private JPanel panelRecompensa;
	private JLabel lblRecompensa;
	
	public VentanaRegistroMascotas() {
		setResizable(false);
		setTitle("Registro de mascotas");
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(660,660);
		getContentPane().setLayout(new BorderLayout(20, 10));
		
		lblRegistrarUnaMascota = new JLabel("Registrar una mascota");
		lblRegistrarUnaMascota.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarUnaMascota.setFont(Diseno.fuenteTitulosVentanas.deriveFont(25f));
		getContentPane().add(lblRegistrarUnaMascota, BorderLayout.NORTH);
		
		
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("######");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		labelEspacioIzq = new JLabel(" ");
		getContentPane().add(labelEspacioIzq, BorderLayout.WEST);
		
		labelEspacioDer = new JLabel(" ");
		getContentPane().add(labelEspacioDer, BorderLayout.EAST);
		
		panelContenido = new JPanel();
		panelContenido.setOpaque(false);
		getContentPane().add(panelContenido, BorderLayout.CENTER);
		panelContenido.setLayout(new BorderLayout(0, 0));
		
		columnaIzq = new JPanel();
		panelContenido.add(columnaIzq, BorderLayout.WEST);
		columnaIzq.setOpaque(false);
		columnaIzq.setLayout(new BoxLayout(columnaIzq, BoxLayout.Y_AXIS));
		
		datosIniciales = new JPanel();
		datosIniciales.setOpaque(false);
		columnaIzq.add(datosIniciales);
		datosIniciales.setLayout(new GridLayout(4, 1, 0, 0));
		
		lblNombre = new JLabel("Nombre");
		datosIniciales.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		campoNombre = new JTextField();
		datosIniciales.add(campoNombre);
		campoNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoNombre.setColumns(10);
		
		lblNmeroDeChip = new JLabel("N\u00FAmero de chip");
		datosIniciales.add(lblNmeroDeChip);
		lblNmeroDeChip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		campoNumeroDeChip = new JTextField();
		datosIniciales.add(campoNumeroDeChip);
		campoNumeroDeChip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoNumeroDeChip.setColumns(10);
		
		caracteristicas = new JPanel();
		columnaIzq.add(caracteristicas);
		caracteristicas.setOpaque(false);
		caracteristicas.setBorder(new TitledBorder(null, "Caracter\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		caracteristicas.setLayout(new GridLayout(5, 2, 0, 5));
		
		lblEspecie = new JLabel("Especie");
		caracteristicas.add(lblEspecie);
		lblEspecie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxEspecie = new JComboBox<String>();
		comboBoxEspecie.setModel(Mascota.getModeloEspecies());
		comboBoxEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxRaza.setModel(Mascota.getModeloRazas((String) comboBoxEspecie.getSelectedItem()));
			}
		});
		caracteristicas.add(comboBoxEspecie);
		comboBoxEspecie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblRaza = new JLabel("Raza");
		caracteristicas.add(lblRaza);
		lblRaza.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxRaza = new JComboBox<String>();
		comboBoxRaza.setModel(Mascota.getModeloRazas((String) comboBoxEspecie.getSelectedItem()));
		comboBoxRaza.setFont(new Font("Tahoma", Font.PLAIN, 12));
		caracteristicas.add(comboBoxRaza);
		
		lblTamao = new JLabel("Tama\u00F1o");
		caracteristicas.add(lblTamao);
		lblTamao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxTamanio = new JComboBox<String>();
		caracteristicas.add(comboBoxTamanio);
		comboBoxTamanio.setModel(new DefaultComboBoxModel<String>(new String[] {"Peque\u00F1o", "Mediano", "Grande"}));
		comboBoxTamanio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblColor = new JLabel("Color");
		caracteristicas.add(lblColor);
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxColor = new JComboBox<String>();
		caracteristicas.add(comboBoxColor);
		comboBoxColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxColor.setModel(new DefaultComboBoxModel<String>(new String[] {"Blanco", "Negro", "Gris", "Caf\u00E9"}));
		
		lblSexo = new JLabel("Sexo");
		caracteristicas.add(lblSexo);
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxSexo = new JComboBox<String>();
		caracteristicas.add(comboBoxSexo);
		comboBoxSexo.setModel(new DefaultComboBoxModel<String>(new String[] {"Macho", "Hembra"}));
		comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		estado = new JPanel();
		columnaIzq.add(estado);
		estado.setBorder(new TitledBorder(null, "\u00BFC\u00F3mo desea registrar la mascota?", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		estado.setOpaque(false);
		
		radioButtonEncontrada = new JRadioButton("Encontrada");
		radioButtonEncontrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRecompensa.setVisible(false);
			}
		});
		radioButtonEncontrada.setSelected(true);
		radioButtonEncontrada.setOpaque(false);
		radioButtonEncontrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonGroup.add(radioButtonEncontrada);
		estado.add(radioButtonEncontrada);
		
		radioButtonPerdida = new JRadioButton("Perdida");
		radioButtonPerdida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRecompensa.setVisible(true);
			}
		});
		buttonGroup.add(radioButtonPerdida);
		radioButtonPerdida.setOpaque(false);
		estado.add(radioButtonPerdida);
		radioButtonPerdida.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		radioButtonAdoptable = new JRadioButton("Adoptable");
		radioButtonAdoptable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRecompensa.setVisible(false);
			}
		});
		buttonGroup.add(radioButtonAdoptable);
		radioButtonAdoptable.setOpaque(false);
		estado.add(radioButtonAdoptable);
		radioButtonAdoptable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		condicionesfisicas = new JPanel();
		columnaIzq.add(condicionesfisicas);
		condicionesfisicas.setOpaque(false);
		condicionesfisicas.setBorder(new TitledBorder(null, "Condiciones F\u00EDsicas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		condicionesfisicas.setLayout(new GridLayout(0, 1, 0, 0));
		
		checkBoxCastrada = new JCheckBox("Castrada");
		condicionesfisicas.add(checkBoxCastrada);
		checkBoxCastrada.setOpaque(false);
		checkBoxCastrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		checkBoxVacunada = new JCheckBox("Vacunada");
		condicionesfisicas.add(checkBoxVacunada);
		checkBoxVacunada.setOpaque(false);
		checkBoxVacunada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		checkBoxDesparacitada = new JCheckBox("Desparacitada");
		condicionesfisicas.add(checkBoxDesparacitada);
		checkBoxDesparacitada.setOpaque(false);
		checkBoxDesparacitada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		panelRecompensa = new JPanel();
		panelRecompensa.setVisible(false);
		panelRecompensa.setOpaque(false);
		columnaIzq.add(panelRecompensa);
		panelRecompensa.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblRecompensa = new JLabel("Recompensa (colones)");
		panelRecompensa.add(lblRecompensa);
		lblRecompensa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
				
				campoRecompensa = new JFormattedTextField(formatter);
				panelRecompensa.add(campoRecompensa);
				campoRecompensa.setFont(new Font("Tahoma", Font.PLAIN, 13));
				campoRecompensa.setColumns(10);
				
				JPanel columnaDer = new JPanel();
				panelContenido.add(columnaDer, BorderLayout.CENTER);
				columnaDer.setOpaque(false);
				columnaDer.setLayout(new BoxLayout(columnaDer, BoxLayout.Y_AXIS));
				
				JPanel panelDetalles = new JPanel();
				panelDetalles.setBorder(new TitledBorder(null, "Detalles", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				panelDetalles.setOpaque(false);
				columnaDer.add(panelDetalles);
				panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
				
				JPanel panelDireccion = new JPanel();
				panelDireccion.setOpaque(false);
				panelDetalles.add(panelDireccion);
				panelDireccion.setLayout(new BoxLayout(panelDireccion, BoxLayout.Y_AXIS));
				
				JLabel labelDireccion = new JLabel("Direcci\u00F3n del suceso:");
				labelDireccion.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
				labelDireccion.setHorizontalAlignment(SwingConstants.CENTER);
				panelDireccion.add(labelDireccion);
				
				JEditorPane editorDireccionSuceso = new JEditorPane();
				panelDireccion.add(editorDireccionSuceso);
				
				JPanel panelNotas = new JPanel();
				panelNotas.setOpaque(false);
				panelDetalles.add(panelNotas);
				panelNotas.setLayout(new BoxLayout(panelNotas, BoxLayout.Y_AXIS));
				
				JLabel labelNotas = new JLabel("Notas adicionales:");
				labelNotas.setHorizontalAlignment(SwingConstants.CENTER);
				panelNotas.add(labelNotas);
				labelNotas.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelNotas.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
				campoNotas = new JEditorPane();
				panelNotas.add(campoNotas);
				campoNotas.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
				JPanel panelFoto = new JPanel();
				panelFoto.setOpaque(false);
				columnaDer.add(panelFoto);
				panelFoto.setBorder(new TitledBorder(null, "Foto de su Mascota", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				panelFoto.setLayout(new BorderLayout(0, 0));
				
				JLabel labelFoto = new JLabel("Ninguna Seleccionada");
				labelFoto.setHorizontalAlignment(SwingConstants.CENTER);
				labelFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelFoto.add(labelFoto);
				
				JButton btnSeleccionarImagen = new JButton("Seleccionar Imagen");
				btnSeleccionarImagen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							imagenSeleccionada = Imagenes.seleccionarImagen();
							int ancho = labelFoto.getSize().width;
							int alto = labelFoto.getSize().height;
							BufferedImage porInsertar = Imagenes.redimensionar(
									Imagenes.cargarImagen(imagenSeleccionada), ancho, alto);
							labelFoto.setText("");
							labelFoto.setIcon(new ImageIcon(porInsertar));
						} catch (ImagenNoEncontradaException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(),
								"Advertencia", JOptionPane.WARNING_MESSAGE);
							imagenSeleccionada = "";
							labelFoto.setIcon(null);
							labelFoto.setText("Ninguna Seleccionada");
						}
					}
				});
				btnSeleccionarImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelFoto.add(btnSeleccionarImagen, BorderLayout.SOUTH);
		
		JPanel panelOperaciones = new JPanel();
		panelOperaciones.setOpaque(false);
		getContentPane().add(panelOperaciones, BorderLayout.SOUTH);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		panelOperaciones.add(botonCancelar);
		botonCancelar.setOpaque(false);
		botonCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton botonRegistrar = new JButton("Registrar");
		panelOperaciones.add(botonRegistrar);
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Mascota mascota = new Mascota(
						campoNombre.getText(),
						(String) comboBoxEspecie.getSelectedItem(),
						(String)comboBoxRaza.getSelectedItem(),
						Integer.getInteger(campoRecompensa.getText() == null ? "0" : campoRecompensa.getText()));
				
				mascota.setCastrada(checkBoxCastrada.isSelected());
				mascota.setColor((String) comboBoxColor.getSelectedItem());
				mascota.setDesparacitada(checkBoxDesparacitada.isSelected());
				mascota.setNumeroChip(campoNumeroDeChip.getText());
				mascota.setSexo((String) comboBoxSexo.getSelectedItem());
				mascota.setTamanio((String) comboBoxTamanio.getSelectedItem());
				mascota.setVacunada(checkBoxVacunada.isSelected());
				
				Suceso suceso = new Suceso(Acceso.getUsuarioActivo().getNickname(), editorDireccionSuceso.getText(), campoNotas.getText());
				
				if (radioButtonAdoptable.isSelected()){ 
					mascota.addAdopcion(suceso);
					Principal.enAdopcion.add(mascota);
				}
				
				if (radioButtonEncontrada.isSelected()){ 
					mascota.addEncuentro(suceso);
					Principal.encontradas.add(mascota);
				}
				
				if (radioButtonPerdida.isSelected()){
					mascota.addPerdida(suceso);
					Principal.perdidas.add(mascota);
				}
				
				JOptionPane.showMessageDialog(getContentPane(), 
						"Registro Satisfactorio, se le recuerda que puede editar la informacion\n de esta mascota en la pesta�a de mis mascotas", "Informaci�n",
						 JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
			}
		});
		botonRegistrar.setOpaque(false);
		botonRegistrar.setFont(new Font("Tahoma", Font.BOLD, 13));
	}
	
	public void close(){
		this.dispose();
	}
}
