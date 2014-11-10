package dogslovers.control;

import java.awt.Window;

import javax.swing.JFrame;

import dogslovers.modelo.Mascota;
import dogslovers.modelo.Usuario;
import dogslovers.vista.*;

public class CoordinadorVisual {
	private VentanaInicioSesion inicioSesion;
	private VentanaParametrosSistema parametrosSistema;
	private VentanaMenuPrincipal menuPrincipal;
	private VentanaDetallesUsuario detallesUsuario;
	private VentanaDetallesMascota detallesMascota;
	private VentanaRegistroUsuarios registroUsuarios;
	private VentanaRegistroMascotas registroMascotas;
	private VentanaBusqueda busqueda;
	private VentanaAgregarComentario agregarComentario;
	
	private JFrame ventanaActiva;
	private VentanaCondicionesRefugio condicionesRefugio;
	private VentanaMascotasDeUsuario mascotasAsociadas;
	private VentanaCalificaciones calificaciones;
	
	public CoordinadorVisual(){
		inicioSesion = new VentanaInicioSesion();
		parametrosSistema = new VentanaParametrosSistema();
		menuPrincipal = new VentanaMenuPrincipal();
		detallesUsuario = new VentanaDetallesUsuario();
		detallesMascota = new VentanaDetallesMascota();
		registroUsuarios = new VentanaRegistroUsuarios();
		registroMascotas = new VentanaRegistroMascotas();
		busqueda = new VentanaBusqueda();
		condicionesRefugio = new VentanaCondicionesRefugio();
		agregarComentario = new VentanaAgregarComentario();
		mascotasAsociadas = new VentanaMascotasDeUsuario();
		calificaciones = new VentanaCalificaciones();
		
	}
	
	public synchronized void mostrarInicioSesion() {
		ocultarVentanas();
		inicioSesion.setVisible(true);
		ventanaActiva = inicioSesion;
	}

	public synchronized void mostrarParametrosSistema() {
		parametrosSistema.setVisible(Acceso.isAdministradorActivo());
		parametrosSistema.setMensajeNuevo();
		parametrosSistema.setVisible(true);
	}

	public synchronized void mostrarMenuPrincipal() {
		ocultarVentanas();
		menuPrincipal.setVisible(true);
	}

	public synchronized void mostrarDetallesUsuario(Usuario usuarioActual) {
		ocultarVentanas();
		detallesUsuario.setDatosIniciales(usuarioActual);
		detallesUsuario.setModoEdicion(usuarioActual == Acceso.getUsuarioActivo());
		detallesUsuario.setVisible(true);
	}

	public synchronized void mostrarDetallesMascota() {
		ocultarVentanas();
	}

	public synchronized void mostrarRegistroUsuarios() {
		ocultarVentanas();
	}

	public synchronized void mostrarRegistroMascotas() {
		ocultarVentanas();
	}

	public synchronized void mostrarBusqueda() {
		ocultarVentanas();
		busqueda.setVisible(true);
	}
	
	public synchronized void mostrarAgregarComentario(Usuario usuarioACalificar){
		agregarComentario.setUsuarioACalificar(usuarioACalificar);
		agregarComentario.setVisible(true);		
	}
	
	private synchronized void ocultarVentanas() {
		inicioSesion.setVisible(false);
		parametrosSistema.setVisible(false);
		menuPrincipal.setVisible(false);
		detallesUsuario.setVisible(false);
		detallesMascota.setVisible(false);
		registroUsuarios.setVisible(false);
		registroMascotas.setVisible(false);
		busqueda.setVisible(false);
		agregarComentario.setVisible(false);
	}
	
	public synchronized void mostrarVentanas() {
		inicioSesion.setVisible(true);
		parametrosSistema.setVisible(true);
		menuPrincipal.setVisible(true);
		detallesUsuario.setVisible(true);
		detallesMascota.setVisible(true);
		registroUsuarios.setVisible(true);
		registroMascotas.setVisible(true);
		busqueda.setVisible(true);
		agregarComentario.setVisible(true);
	}

	public void mostrarCondicionesRefugio(Usuario usuarioActual) {
		condicionesRefugio.setDatos(usuarioActual);
		condicionesRefugio.setVisible(true);
		condicionesRefugio.setModoEdicion(usuarioActual == Acceso.getUsuarioActivo());
	}
	
	public void mostrarMascotasAsociadas(Usuario usuarioActual){
		mascotasAsociadas.setUsuario(usuarioActual);
		mascotasAsociadas.setVisible(true);
	}

	public void mostrarCalificaciones(Usuario usuarioActual) {
		calificaciones.setUsuario(usuarioActual);
		calificaciones.setVisible(true);
		
	}

	public void mostrarDetallesMascota(Mascota mascota) {
		detallesMascota.setDatosIniciales(mascota);
		detallesMascota.setVisible(true);
	}
}
