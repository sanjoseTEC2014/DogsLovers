package dogslovers.control;

/**	 Controlador Acceso: 
 * 	 Esta clase implementa diversos m�todos que permiten validar
 *   el acceso del usuario al sistema.
 * 
 *	 Fecha de creaci�n: 28/10/2014
 * 
 *	@author Isaac Antonio Campos Mes�n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Mar�a Molina Corrales 2013006074
 *	@author Luis Andr�s Pe�a Castillo 2014057250 
 *  
 */

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

import net.coobird.thumbnailator.Thumbnails;
import dogslovers.control.excepciones.ImagenNoEncontradaException;

public class Imagenes {
	
	protected static String directorioSistema = System.getProperty("user.dir");
	protected static String rutaFotosMascotas = directorioSistema + "\\src\\dogslovers\\recursos\\imagenes\\mascotas\\";
	protected static String rutaFotosUsuarios = directorioSistema + "\\src\\dogslovers\\recursos\\imagenes\\usuarios\\";
	protected static String rutaFotosSistema  = directorioSistema + "\\src\\dogslovers\\recursos\\imagenes\\sistema\\";
	
	public static BufferedImage cargarImagen(String pRuta) throws ImagenNoEncontradaException{
		// JOptionPane.showMessageDialog(null, pRuta);
		try {
			return ImageIO.read(new File(pRuta));
		} catch (IOException e) {
			throw new ImagenNoEncontradaException(e.getMessage());
		}
	}
	
	public static BufferedImage getEstrellas(Double ponderado) throws ImagenNoEncontradaException{
		String rutaEstrellas = rutaFotosSistema + "estrellas\\";
		
		if (ponderado <= 5.0 && ponderado > 4.5) {
			rutaEstrellas += "5.0";
			
		} else if (ponderado == 4.5) {
			rutaEstrellas += "4.5";
			
		} else if (ponderado >= 4.0 && ponderado < 4.5) {
			rutaEstrellas += "4.0";
			
		} else if (ponderado == 3.5) {
			rutaEstrellas += "3.5";
			
		} else if (ponderado >= 3.0 && ponderado < 3.5) {
			rutaEstrellas += "3.0";
			
		} else if (ponderado == 2.5) {
			rutaEstrellas += "2.5";
			
		} else if (ponderado >= 2.0 && ponderado < 2.5) {
			rutaEstrellas += "2.0";
			
		} else if (ponderado == 1.5) {
			rutaEstrellas += "1.5";
			
		} else if (ponderado >= 1.0 && ponderado < 1.5) {
			rutaEstrellas += "1.0";
			
		} else if (ponderado == 0.5) {
			rutaEstrellas += "0.5";
			
		} else if (ponderado >= 0.0 && ponderado < 0.5) {
			rutaEstrellas += "0.0";
		}
		rutaEstrellas += ".png";
		return cargarImagen(rutaEstrellas);
	}
	
	public static BufferedImage getPerfilMascota(Integer idMascota) throws ImagenNoEncontradaException{
		return cargarImagen(rutaFotosMascotas + idMascota.toString() + ".jpg"); //   + "\\perfil.jpg");
	}
	
	public static BufferedImage getPerfil(String nickUsuario)  throws ImagenNoEncontradaException{
		return cargarImagen(rutaFotosUsuarios + nickUsuario + "perfil.jpg");
	}
	
	public static BufferedImage getImagenError() throws ImagenNoEncontradaException {
		return cargarImagen(rutaFotosSistema + "404.jpg");
	}
	
	public static BufferedImage getLogo() throws ImagenNoEncontradaException {
		return cargarImagen(rutaFotosSistema + "paws.png");
	}
	
	public static BufferedImage getIconoSistema() throws ImagenNoEncontradaException {
		return cargarImagen(rutaFotosSistema + "iconoHuella.png");
	}
	
	public static BufferedImage getIconoBusqueda() throws ImagenNoEncontradaException {
		return cargarImagen(rutaFotosSistema + "iconoBuscarMascota.png");
	}
	
	public static void guardarFotoPerfilUsuario(String nickUsuario, String rutaOrigen){
		String rutaDestino = rutaFotosUsuarios + nickUsuario + "perfil.jpg";
		guardarImagen(rutaOrigen, rutaDestino);
	}
	
	public static void guardarFotoPerfilMascota(Integer IDMascota, String rutaOrigen){
		String rutaDestino = rutaFotosMascotas + IDMascota.toString() + ".jpg";
		guardarImagen(rutaOrigen, rutaDestino);
	}
	
	private static void guardarImagen(String rutaOrigen, String rutaDestino) {
		try {
			FileInputStream flujoArchivoEntrada = new FileInputStream(rutaOrigen);
			FileOutputStream flujoArchivoSalida = new FileOutputStream(rutaDestino);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = flujoArchivoEntrada.read(buffer)) > 0) {
				flujoArchivoSalida.write(buffer, 0, length);
			}
			flujoArchivoEntrada.close();
			flujoArchivoSalida.close();
		} catch (IOException noHayArchivo) {
			JOptionPane.showMessageDialog(null,
				"No se pudo copiar la imagen seleccionada.\n" + noHayArchivo.getMessage(),
				"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static String seleccionarImagen() throws ImagenNoEncontradaException{
		JFileChooser selector = new JFileChooser();
		selector.setAcceptAllFileFilterUsed(false); // Deshabilita seleccionar todos los archivos
		selector.setFileFilter(new FileNameExtensionFilter("S�lo im�genes", ImageIO.getReaderFileSuffixes()));
		
		if (selector.showOpenDialog(null) == JFileChooser.CANCEL_OPTION){
			throw new ImagenNoEncontradaException("Se cancel� la operaci�n.");
		} else if (selector.getSelectedFile().getAbsolutePath() == null){
			throw new ImagenNoEncontradaException("No se seleccion� ninguna imagen.");
		}
		return selector.getSelectedFile().getAbsolutePath();
	}
	
	public static BufferedImage redimensionar(BufferedImage origen, int nuevoAncho, int nuevoAlto) throws ImagenNoEncontradaException {
		try {
			return Thumbnails.of(origen).size(nuevoAncho, nuevoAlto).asBufferedImage();
		} catch (IOException e) {
			throw new ImagenNoEncontradaException("No se encuentra la imagen requerida.");
		}
	}
	
}