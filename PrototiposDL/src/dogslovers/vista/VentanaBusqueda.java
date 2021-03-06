package dogslovers.vista;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import dogslovers.control.Busqueda;
import dogslovers.control.Principal;
import dogslovers.control.excepciones.MascotaNoEncontradaException;
import dogslovers.control.excepciones.UsuarioNoExisteException;
import dogslovers.modelo.Mascota;
import dogslovers.modelo.ModeloTablaMascotas;
import dogslovers.modelo.ModeloTablaUsuarios;
import dogslovers.recursos.Diseno;

public class VentanaBusqueda extends JFrame implements Runnable {

	private static final Integer altoVentanaContraida = 395;
	private static final Integer anchoVentana = 577;
	private JButton btnAyuda;
	private JButton btnBuscar;
	private JButton btnContraerVentana;
	private JButton btnVerDetalles;
	private JButton button;
	private JTextField campoApellidos;
	private JTextField campoCedula;
	private JTextField campoCorreoElectronico;
	private JTextField campoNickName;
	private JTextField campoNombreUsuario;
	private JTextField campoNumeroTelefonico;
	private JPanel campos;
	private JCheckBox checkApellidos;
	private JCheckBox checkCedula;
	private JCheckBox checkCorreoElectronico;
	private JCheckBox checkEspecie;
	private JCheckBox checkLugar;
	private JCheckBox checkMascotasAdoptadas;
	private JCheckBox checkMascotasEnAdopcion;
	private JCheckBox checkMascotasEncontradas;
	private JCheckBox checkMascotasEnRefugio;
	private JCheckBox checkMascotasPerdidas;
	private JCheckBox checkNickName;
	private JCheckBox checkNombreMascota;
	private JCheckBox checkNombreUsuario;
	private JCheckBox checkNumeroChip;
	private JCheckBox checkNumeroTelefonico;
	private JCheckBox checkRaza;
	private JCheckBox checkSoloUsuariosRefugiantes;
	private JComboBox<String> comboEspecies;
	private JComboBox<String> comboRazas;
	Thread hiloBarraProgreso;
	Thread hiloExpandirVentana;
	private JLabel labelTitulo1;
	private boolean[] listasSeleccionadas;
	private JPanel marcoCampos;
	private JPanel marcoContenidoMascotas;
	private JPanel marcoContenidoUsuarios;
	private JPanel marcoListas;
	private JPanel marcoOperaciones;
	private JPanel marcoParametrosMascota;
	private JPanel marcoParametrosUsuario;
	private JScrollPane marcoResultados;
	private JPanel marcoTituloMascotas;
	private JPanel marcoTituloUsuarios;
	private ModeloTablaMascotas modeloMascotas;
	private ModeloTablaUsuarios modeloUsuarios;
	private JPanel pestaniaMascotas;
	private JTabbedPane pestanias;
	private JPanel pestaniaUsuarios;
	private JProgressBar progressBar;
	private JProgressBar progressBar_1;
	private JScrollPane scrollPane;
	protected boolean soloUsusariosRefugiantes;
	private JTable tablaResultadosMascotas;
	private JTable tablaResultadosUsuarios;
	private JTextField textLugar;
	private JTextField textNombre;
	private JTextField textNumeroChip;
	boolean ventanaContraida;

	public VentanaBusqueda() {
		
		Thread hiloBarraProgreso = new Thread(this);
		Thread hiloExpandirVentana = new Thread(this); 
		
		setName("barraCarga");

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(anchoVentana, altoVentanaContraida);	

		getContentPane().setBackground(Diseno.fondoVentanas);
		ventanaContraida = true;
		
		labelTitulo1 = new JLabel("B\u00FAsqueda");
		getContentPane().add(labelTitulo1, BorderLayout.NORTH);
		labelTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo1.setFont(Diseno.fuenteTitulosVentanas.deriveFont(35f));

		pestanias = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(pestanias, BorderLayout.CENTER);

		pestaniaMascotas = new JPanel();
		pestaniaMascotas.setOpaque(false);
		pestaniaMascotas.setLayout(new BorderLayout(0, 0));
		pestaniaMascotas.setBackground(Diseno.fondoVentanas);

		marcoTituloMascotas = new JPanel();
		marcoTituloMascotas.setOpaque(false);
		pestaniaMascotas.add(marcoTituloMascotas, BorderLayout.NORTH);
		marcoTituloMascotas.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();
		marcoTituloMascotas.add(progressBar, BorderLayout.CENTER);
		progressBar.setStringPainted(true);
		progressBar.setString("");
		// Perdidas, Encontradas, Adoptadas, Adoptables, Refugiadas

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
	



			public void actionPerformed(ActionEvent arg0) {

				LinkedList<String> terminos = new LinkedList<String>();
				if (checkNombreMascota.isSelected()) {
					terminos.add(textNombre.getText());
				} else {
					terminos.add("");
				}
				if (checkLugar.isSelected()) {
					terminos.add(textLugar.getText());
				} else {
					terminos.add("");
				}
				if (checkNumeroChip.isSelected()) {
					// TODO Validar que lo que est� escrito en el textChip
					terminos.add(textNumeroChip.getText());
				} else {
					terminos.add("");
				}
				if (checkEspecie.isSelected()) {
					terminos.add((String) comboEspecies.getSelectedItem());
					if (checkRaza.isSelected()) {
						terminos.add((String) comboRazas.getSelectedItem());
					} else {
						terminos.add("");
					}
				} else {
					// Sin especie y sin raza.
					terminos.add("");
					terminos.add("");
				}

				listasSeleccionadas[0] = checkMascotasEncontradas.isSelected();
				listasSeleccionadas[1] = checkMascotasPerdidas.isSelected();
				listasSeleccionadas[2] = checkMascotasAdoptadas.isSelected();
				listasSeleccionadas[3] = checkMascotasEnAdopcion.isSelected();
				listasSeleccionadas[4] = checkMascotasEnRefugio.isSelected();

				if (algunaListaSeleccionada(listasSeleccionadas)) {
					//modelo = new Busqueda(terminos, listasSeleccionadas);

					modeloMascotas = new ModeloTablaMascotas(Busqueda.buscarMascotas(terminos, listasSeleccionadas));
					tablaResultadosMascotas.setModel(modeloMascotas);
					tablaResultadosMascotas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					tablaResultadosMascotas.setAutoCreateRowSorter(true);
					tablaResultadosMascotas.setVisible(true);
					progressBar.setMaximum(modeloMascotas.getCantidadDeResultados());

					if (ventanaContraida) {
						// Se intenta hacer procesamiento en paralelo...
						//hiloBarraProgreso.start();
						expandirVentana();
						//hiloExpandirVentana.start();
						
					}
				} else {
					progressBar.setString("No se ha seleccionado ninguna lista");
				}
				SwingUtilities.updateComponentTreeUI(getContentPane());
			}

			

			private boolean algunaListaSeleccionada(boolean[] listasSeleccionadas) {
				for (boolean x : listasSeleccionadas) {
					if (x)
						return true;
				}
				return false;
			}

		});
		marcoTituloMascotas.add(btnBuscar, BorderLayout.EAST);

		marcoContenidoMascotas = new JPanel();
		marcoContenidoMascotas.setOpaque(false);
		pestaniaMascotas.add(marcoContenidoMascotas, BorderLayout.CENTER);
		marcoContenidoMascotas.setLayout(new BorderLayout(0, 0));

		marcoParametrosMascota = new JPanel();
		marcoParametrosMascota.setOpaque(false);
		marcoParametrosMascota.setLayout(new BorderLayout(0, 0));
		marcoContenidoMascotas.add(marcoParametrosMascota, BorderLayout.NORTH);

		marcoCampos = new JPanel();
		marcoCampos.setOpaque(false);
		marcoCampos.setBorder(new TitledBorder(null, "Par\u00E1metros de B\u00FAsqueda", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		marcoCampos.setLayout(new GridLayout(5, 2, 0, 0));
		marcoParametrosMascota.add(marcoCampos, BorderLayout.CENTER);

		checkNombreMascota = new JCheckBox("Nombre");
		checkNombreMascota.setOpaque(false);
		checkNombreMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNombreMascota.isSelected()) {
					textNombre.setEnabled(true);
				} else {
					textNombre.setText("");
					textNombre.setEnabled(false);
				}
			}
		});
		marcoCampos.add(checkNombreMascota);

		textNombre = new JTextField();
		textNombre.setEnabled(false);
		textNombre.setColumns(10);
		marcoCampos.add(textNombre);

		checkLugar = new JCheckBox("Lugar P\u00E9rdida / Encuentro");
		checkLugar.setOpaque(false);
		checkLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkLugar.isSelected()) {
					textLugar.setEnabled(true);
				} else {
					textLugar.setText("");
					textLugar.setEnabled(false);
				}
			}
		});
		marcoCampos.add(checkLugar);

		textLugar = new JTextField();
		textLugar.setEnabled(false);
		textLugar.setColumns(10);
		marcoCampos.add(textLugar);

		checkNumeroChip = new JCheckBox("N\u00FAmero de Chip");
		checkNumeroChip.setOpaque(false);
		checkNumeroChip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNumeroChip.isSelected()) {
					textNumeroChip.setEnabled(true);
				} else {
					textNumeroChip.setText("");
					textNumeroChip.setEnabled(false);
				}
			}
		});
		marcoCampos.add(checkNumeroChip);

		textNumeroChip = new JTextField();
		textNumeroChip.setEnabled(false);
		textNumeroChip.setColumns(10);
		marcoCampos.add(textNumeroChip);

		checkEspecie = new JCheckBox("Especie");
		checkEspecie.setOpaque(false);
		checkEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkEspecie.isSelected()) {
					comboEspecies.setEnabled(true);
					checkRaza.setEnabled(true);
					comboEspecies.setModel(new DefaultComboBoxModel<String>(Mascota.getEspecies()
							.toArray(new String[Mascota.getEspecies().size()])));
				} else {
					comboEspecies.setEnabled(false);
					comboEspecies.setModel(new DefaultComboBoxModel<String>());
					checkRaza.setSelected(false);
					checkRaza.setEnabled(false);
					comboRazas.setModel(new DefaultComboBoxModel<String>());
				}
				comboRazas.setEnabled(false);
			}
		});
		marcoCampos.add(checkEspecie);

		comboEspecies = new JComboBox<String>();
		comboEspecies.setEnabled(false);
		marcoCampos.add(comboEspecies);

		checkRaza = new JCheckBox("Raza");
		checkRaza.setOpaque(false);
		checkRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkRaza.isSelected()) {
					comboRazas.setEnabled(true);
					comboRazas.setModel(new DefaultComboBoxModel<String>( (String[]) Mascota.getRazas().get(comboEspecies
							.getSelectedIndex()).toArray()));
				} else {
					comboRazas.setModel(new DefaultComboBoxModel<String>());
					comboRazas.setEnabled(false);
				}
			}
		});
		checkRaza.setEnabled(false);
		marcoCampos.add(checkRaza);

		comboRazas = new JComboBox<String>();
		comboRazas.setEnabled(false);
		marcoCampos.add(comboRazas);

		marcoListas = new JPanel();
		marcoListas.setOpaque(false);
		marcoListas.setBorder(new TitledBorder(null, "\u00BFD\u00F3nde desea buscar?", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) marcoListas.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		marcoParametrosMascota.add(marcoListas, BorderLayout.SOUTH);

		checkMascotasPerdidas = new JCheckBox("Perdidas");
		checkMascotasPerdidas.setOpaque(false);
		marcoListas.add(checkMascotasPerdidas);

		checkMascotasEncontradas = new JCheckBox("Encontradas");
		checkMascotasEncontradas.setOpaque(false);
		checkMascotasEncontradas.setSelected(true);
		marcoListas.add(checkMascotasEncontradas);

		checkMascotasAdoptadas = new JCheckBox("Adoptadas");
		checkMascotasAdoptadas.setOpaque(false);
		checkMascotasAdoptadas.setSelected(true);
		marcoListas.add(checkMascotasAdoptadas);

		checkMascotasEnAdopcion = new JCheckBox("En Adopci�n");
		checkMascotasEnAdopcion.setOpaque(false);
		marcoListas.add(checkMascotasEnAdopcion);
		checkMascotasEnAdopcion.setSelected(true);

		checkMascotasEnRefugio = new JCheckBox("Refugiadas");
		checkMascotasEnRefugio.setOpaque(false);
		checkMascotasEnRefugio.setSelected(true);
		marcoListas.add(checkMascotasEnRefugio);

		marcoResultados = new JScrollPane();
		marcoResultados.setOpaque(false);
		marcoContenidoMascotas.add(marcoResultados, BorderLayout.CENTER);

		tablaResultadosMascotas = new JTable();
		tablaResultadosMascotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaResultadosMascotas.setShowHorizontalLines(true);
		tablaResultadosMascotas.setShowVerticalLines(true);
		marcoResultados.setViewportView(tablaResultadosMascotas);

		pestaniaUsuarios = new JPanel();
		pestaniaUsuarios.setOpaque(false);
		pestaniaUsuarios.setLayout(new BorderLayout(0, 0));
		pestaniaUsuarios.setBackground(Diseno.fondoVentanas);


		marcoOperaciones = new JPanel();
		marcoOperaciones.setOpaque(false);
		getContentPane().add(marcoOperaciones, BorderLayout.SOUTH);
		marcoOperaciones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
								btnAyuda = new JButton("\u00A1Necesito Ayuda!");
								btnAyuda.setOpaque(false);
								btnAyuda.setBackground(Diseno.fondoVentanas);
								marcoOperaciones.add(btnAyuda);
								btnAyuda.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										mostrarMensajeAyuda();
										
									}
								});
				
						btnContraerVentana = new JButton("Ocultar Resultados");
						btnContraerVentana.setOpaque(false);
						marcoOperaciones.add(btnContraerVentana);
						btnContraerVentana.setVisible(false);
						btnContraerVentana.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!ventanaContraida) {
									// Contrae la ventana un p�xel a la vez
									for (int i = 0; i < 200; i++) {
										setSize(anchoVentana, getHeight() - 1);
									}
									ventanaContraida = true;
									btnContraerVentana.setVisible(false);
									tablaResultadosMascotas.setVisible(false);
									progressBar.setValue(0);
									progressBar.setString("");

								}
							}
						});

		btnVerDetalles = new JButton("Ver Detalles");
		btnVerDetalles.setOpaque(false);
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (pestanias.getSelectedIndex() == 0){
					int fila = tablaResultadosUsuarios.getSelectedRow();
					if (fila != -1){
						String nick = (String) tablaResultadosUsuarios.getValueAt(fila, 0);						
						try {
							Principal.coordinador.mostrarDetallesUsuario(Principal.getUsuarioListaBlanca(nick));
						} catch (UsuarioNoExisteException e) {
							JOptionPane.showMessageDialog(getContentPane(), "error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Informaci�n", "Debe seleccionar un usuario primero", JOptionPane.INFORMATION_MESSAGE);			
					}
					
				}
				
				if (pestanias.getSelectedIndex() == 1){
					int fila = tablaResultadosMascotas.getSelectedRow();
					if (fila != -1){
						String IDMascota = (String) tablaResultadosMascotas.getValueAt(fila, 0);
						try {
							Principal.coordinador.mostrarDetallesMascota(Principal.getMascotaID(Integer.parseInt(IDMascota)));
						} catch (MascotaNoEncontradaException e) {
							JOptionPane.showMessageDialog(getContentPane(),  e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Informaci�n", "Debe seleccionar una mascota primero", JOptionPane.INFORMATION_MESSAGE);			
					}
					
				}
				
			}
		});
		marcoOperaciones.add(btnVerDetalles);

		listasSeleccionadas = new boolean[] { false, false, false, false, false };
		
		pestanias.addTab("Usuarios", null, pestaniaUsuarios, null);
		
		marcoTituloUsuarios = new JPanel();
		marcoTituloUsuarios.setOpaque(false);
		pestaniaUsuarios.add(marcoTituloUsuarios, BorderLayout.NORTH);
		marcoTituloUsuarios.setLayout(new BorderLayout(0, 0));
		
		progressBar_1 = new JProgressBar();
		marcoTituloUsuarios.add(progressBar_1, BorderLayout.CENTER);
		
		button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LinkedList<String> terminos = new LinkedList<String>();
				
				if (checkNickName.isSelected()) {
					terminos.add(campoNickName.getText());
				} else {
					terminos.add("");
				}
				
				if (checkNombreUsuario.isSelected()) {
					terminos.add(campoNombreUsuario.getText());
				} else {
					terminos.add("");
				}
				
				if (checkApellidos.isSelected()) {
					terminos.add(campoApellidos.getText());
				} else {
					terminos.add("");
				}
				
				if (checkCedula.isSelected()) {
					terminos.add(campoCedula.getText());
				} else {
					terminos.add("");
				}
				
				if (checkNumeroTelefonico.isSelected()) {
					terminos.add(campoNumeroTelefonico.getText());
				} else {
					terminos.add("");
				}
				
				if (checkCorreoElectronico.isSelected()) {
					terminos.add(campoCorreoElectronico.getText());
				} else {
					terminos.add("");
				}

				soloUsusariosRefugiantes = checkSoloUsuariosRefugiantes.isSelected();

				modeloUsuarios = new ModeloTablaUsuarios(Busqueda.buscarUsuarios(terminos, soloUsusariosRefugiantes));
				tablaResultadosUsuarios.setModel(modeloUsuarios);
				tablaResultadosUsuarios.setVisible(true);
				tablaResultadosUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tablaResultadosUsuarios.setAutoCreateRowSorter(true);
				progressBar_1.setMaximum(modeloUsuarios.getCantidadDeResultados());

				if (ventanaContraida) {
					// Se intenta hacer procesamiento en paralelo...
					//hiloBarraProgreso.start();
					expandirVentana();
					//hiloExpandirVentana.start();
						
					}
//				} else {
//					progressBar.setString("No se ha seleccionado ninguna lista");
//				}
				SwingUtilities.updateComponentTreeUI(getContentPane());
			}
		});
		marcoTituloUsuarios.add(button, BorderLayout.EAST);
		
		marcoContenidoUsuarios = new JPanel();
		marcoContenidoUsuarios.setOpaque(false);
		pestaniaUsuarios.add(marcoContenidoUsuarios, BorderLayout.CENTER);
		marcoContenidoUsuarios.setLayout(new BorderLayout(0, 0));
		
		marcoParametrosUsuario = new JPanel();
		marcoParametrosUsuario.setOpaque(false);
		marcoContenidoUsuarios.add(marcoParametrosUsuario, BorderLayout.NORTH);
		marcoParametrosUsuario.setLayout(new BorderLayout(0, 0));
		
		campos = new JPanel();
		campos.setOpaque(false);
		campos.setBorder(new TitledBorder(null, "Parametros de busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		marcoParametrosUsuario.add(campos);
		campos.setLayout(new GridLayout(7, 2, 0, 0));
		
		checkNickName = new JCheckBox("NickName");
		checkNickName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNickName.isSelected()) {
					campoNickName.setEnabled(true);
				} else {
					campoNickName.setText("");
					campoNickName.setEnabled(false);
				}
			}
		});
		campos.add(checkNickName);
		
		campoNickName = new JTextField();
		campoNickName.setEnabled(false);
		campos.add(campoNickName);
		campoNickName.setColumns(10);
		
		checkNombreUsuario = new JCheckBox("Nombre");
		checkNombreUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNombreUsuario.isSelected()) {
					campoNombreUsuario.setEnabled(true);
				} else {
					campoNombreUsuario.setText("");
					campoNombreUsuario.setEnabled(false);
				}
			}
		});
		campos.add(checkNombreUsuario);
		
		campoNombreUsuario = new JTextField();
		campoNombreUsuario.setEnabled(false);
		campoNombreUsuario.setColumns(10);
		campos.add(campoNombreUsuario);
		
		checkApellidos = new JCheckBox("Apellidos");
		checkApellidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkApellidos.isSelected()) {
					campoApellidos.setEnabled(true);
				} else {
					campoApellidos.setText("");
					campoApellidos.setEnabled(false);
				}
			}
		});
		campos.add(checkApellidos);
		
		campoApellidos = new JTextField();
		campoApellidos.setEnabled(false);
		campoApellidos.setColumns(10);
		campos.add(campoApellidos);
		
		checkCedula = new JCheckBox("C\u00E9dula");
		checkCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkCedula.isSelected()) {
					campoCedula.setEnabled(true);
				} else {
					campoCedula.setText("");
					campoCedula.setEnabled(false);
				}
			}
		});
		campos.add(checkCedula);
		
		campoCedula = new JTextField();
		campoCedula.setEnabled(false);
		campoCedula.setColumns(10);
		campos.add(campoCedula);
		
		checkNumeroTelefonico = new JCheckBox("N\u00FAmero Telefonico");
		checkNumeroTelefonico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNumeroTelefonico.isSelected()) {
					campoNumeroTelefonico.setEnabled(true);
				} else {
					campoNumeroTelefonico.setText("");
					campoNumeroTelefonico.setEnabled(false);
				}
			}
		});
		campos.add(checkNumeroTelefonico);
		
		campoNumeroTelefonico = new JTextField();
		campoNumeroTelefonico.setEnabled(false);
		campoNumeroTelefonico.setColumns(10);
		campos.add(campoNumeroTelefonico);
		
		checkCorreoElectronico = new JCheckBox("Correo Electronico");
		checkCorreoElectronico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkCorreoElectronico.isSelected()) {
					campoCorreoElectronico.setEnabled(true);
				} else {
					campoCorreoElectronico.setText("");
					campoCorreoElectronico.setEnabled(false);
				}
				
			}
		});
		campos.add(checkCorreoElectronico);
		
		campoCorreoElectronico = new JTextField();
		campoCorreoElectronico.setEnabled(false);
		campoCorreoElectronico.setColumns(10);
		campos.add(campoCorreoElectronico);
		
		checkSoloUsuariosRefugiantes = new JCheckBox("Buscar unicamente usuarios refugiantes");
		checkSoloUsuariosRefugiantes.setHorizontalAlignment(SwingConstants.LEFT);
		campos.add(checkSoloUsuariosRefugiantes);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		marcoContenidoUsuarios.add(scrollPane);
		
		tablaResultadosUsuarios = new JTable();
		tablaResultadosUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tablaResultadosUsuarios);
		pestanias.addTab("Mascotas", null, pestaniaMascotas, null);

	}

	private void actualizarBarraProgreso() {
		for (Integer i = 0; i < modeloMascotas.getCantidadDeResultados(); i++) {
			try {
				progressBar.setValue(i);
				Integer mascotas = i + 1;
				progressBar.setString(mascotas.toString().concat(" MascotasEncontradas"));
				java.lang.Thread.sleep(100);

			} catch (InterruptedException e) {
			}
		}
	}

	public void busquedaGeneral(){
		pestanias.addTab("Usuarios", null, pestaniaUsuarios, null);
		pestanias.addTab("Mascotas", null, pestaniaMascotas, null);
	}

	public void busquedaRefugiantes() {
		pestanias.addTab("Usuarios", null, pestaniaUsuarios, null);
	}

	private void expandirVentana() {
		// Expande la ventana
		for (int i = 0; i < 100; i++) {
			// Cada iteraci�n expande la ventana 2 p�xeles hasta
			// un m�ximo de 200 p�xeles estirados
			setSize(anchoVentana, getHeight() + 2);
		}
		ventanaContraida = false;
		btnContraerVentana.setVisible(true);
	}
	
	
	protected void mostrarMensajeAyuda() {
		JOptionPane.showMessageDialog(getContentPane(),
				"Para realizar una b�squeda, seleccione los campos que \n"
						+ "desee tomar en cuenta. Si un campo no est� seleccionado, \n"
						+ "las mascotas que aparezcan en los resultados de b�squeda \n"
						+ "no estar�n filtradas (exclu�das) por dicho campo. \n\n"
						+ "Para m�s informaci�n, contacte al equipo de desarrollo.", "Ayuda ",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void run() {

		Thread ct = Thread.currentThread();

		while (ct == hiloBarraProgreso) {
			actualizarBarraProgreso();
		}
		
		while (ct == hiloExpandirVentana) {
			expandirVentana();
		
		}
		
	}
}
