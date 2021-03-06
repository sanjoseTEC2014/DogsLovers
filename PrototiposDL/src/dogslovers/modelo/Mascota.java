package dogslovers.modelo;

/**	Clase Mascota: 
 * 	Esta clase implementa cada uno de los atributos,
 *  listas y m�todos correspondientes a las mascotas.
 * 
 *	Fecha de creaci�n: 24/10/2014
 * 
 *	@author Isaac Antonio Campos Mes�n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Mar�a Molina Corrales 2013006074
 *	@author Luis Andr�s Pe�a Castillo 2014057250 
 *  
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import dogslovers.control.Imagenes;
import dogslovers.control.MaquinaEstadosMascotas;
import dogslovers.control.excepciones.ImagenNoEncontradaException;
import dogslovers.modelo.Suceso;

public class Mascota {

	private static LinkedList<String> tamanios = new LinkedList<String>();
	private static LinkedList<String> colores = new LinkedList<String>();
	private static LinkedList<String> especies = new LinkedList<String>();
	private static LinkedList<LinkedList<String>> razas = new LinkedList<LinkedList<String>>();
	
	private static Integer totalIDsRegistradas = 0;

	private Integer id;
	private String nombre;
	private String numeroChip;
	private String especie;
	private String raza;
	private String color;
	private String edad;
	private String sexo;
	private boolean castrada;
	private boolean vacunada;
	private boolean desparacitada;
	private String tamanio;
	
	private ArrayList<Suceso> sucesos = new ArrayList<Suceso>(); //para cada suceso del array corresponde un estado
	private ArrayList<String> estados = new ArrayList<String>();
	
	private Integer recompensa;

	public Mascota(String pNombre, String pEspecie, String pRaza, Integer pRecompensa) {
		id = ++totalIDsRegistradas;
		nombre = pNombre;
		especie = pEspecie;
		raza = pRaza;
		recompensa = pRecompensa;
	}

	public Integer getID() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroChip() {
		return numeroChip;
	}

	public void setNumeroChip(String string) {
		this.numeroChip = string;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isCastrada() {
		return castrada;
	}

	public void setCastrada(boolean castrada) {
		this.castrada = castrada;
	}

	public boolean isVacunada() {
		return vacunada;
	}

	public void setVacunada(boolean vacunada) {
		this.vacunada = vacunada;
	}

	public boolean isDesparacitada(){
		return desparacitada;
	}

	public void setDesparacitada(boolean desparacitada) {
		this.desparacitada = desparacitada;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public void addPerdida(Suceso perdida) {
		sucesos.add(perdida);
		estados.add(MaquinaEstadosMascotas.estadoPERDIDA);
	}

	public void addEncuentro(Suceso encuentro) {
		sucesos.add(encuentro);
		estados.add(MaquinaEstadosMascotas.estadoENCONTRADA);
	}

	public void addLocalizacion(Suceso localizacion) {
		sucesos.add(localizacion);
		estados.add(MaquinaEstadosMascotas.estadoLOCALIZADA);
	}

	public void addRefugio(Suceso refugio) {
		sucesos.add(refugio);
		estados.add(MaquinaEstadosMascotas.estadoREFUGIADA);
	}

	public void addAdoptable(Suceso adopcion) {
		sucesos.add(adopcion);
		estados.add(MaquinaEstadosMascotas.estadoADOPTABLE);
	}

	public void addAdoptada(Suceso adopcion) {
		sucesos.add(adopcion);
		estados.add(MaquinaEstadosMascotas.estadoADOPTADA);
	}

	public void addDefuncion(Suceso defuncion) {
		sucesos.add(defuncion);
		estados.add(MaquinaEstadosMascotas.estadoMUERTA);
	}

	public Integer getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Integer recompensa) {
		this.recompensa = recompensa;
	}

	// MAQUINA DE ESTADOS
	public String getEstadoActual() {
		return estados.get(estados.size()-1);
	}
	
	public Suceso getUltimoSuceso() {
		return sucesos.get((sucesos.size())-1);
	}
	
	public void notificar(){
		// MaquinaEstadosMascotas.getMaquina().capturarReporte();
	}

	public BufferedImage getImagen() throws ImagenNoEncontradaException{
		return Imagenes.getPerfilMascota(id);
	}
	
	public String toString() {
		String msg = "Nombre de la mascota: " + getNombre();
		// msg += "\nID: " + getID();
		msg += "\nEstado: " + getEstadoActual();
		msg += "\nEspecie: " + getEspecie();
		msg += "\nRaza: " + getRaza();
		msg += "\n";
		// msg += "\nMonto de recompensa: " + getRecompensa() + "\n";
		// msg += "\nNotas Secundarias: " + getNotasSecundarias();
		return msg;
	}

	// Constructor invocado �nicamente por el m�todo .clone()
	private Mascota(Integer pID, String pNombre, String pEspecie, String pRaza, Integer pRecompensa) {
		id = pID;
		nombre = pNombre;
		especie = pEspecie;
		raza = pRaza;
		recompensa = pRecompensa;
	}
	
	public Mascota clone() {
		  Mascota clone = new Mascota(id, nombre, especie, raza, recompensa);
		  clone.setColor(color);
		  clone.setEdad(edad);
		  clone.setNumeroChip(numeroChip);
		  clone.setSexo(sexo);
		  clone.setTamanio(tamanio);		  
		  clone.setCastrada(castrada);
		  clone.setDesparacitada(desparacitada);
		  clone.setVacunada(vacunada);
		  return clone;
	}

	public static DefaultComboBoxModel<String> getModeloEspecies() {
		int size = especies.size();
		return new DefaultComboBoxModel<String>(especies.toArray(new String[size]));
	}
	
	// Recibe la especie seleccionada en el JComboBox para que �ste pueda refrescarse
	// cuando se est� editando la especie de una Mascota.
	public static DefaultComboBoxModel<String> getModeloRazas(String pEspecie) {
		int size = razas.get(especies.indexOf(pEspecie)).size();
		return new DefaultComboBoxModel<String>(razas.get(especies.indexOf(pEspecie)).toArray(new String[size]));
	}
	
	public static DefaultComboBoxModel<String> getModeloTamanios() {
		  return new DefaultComboBoxModel<String>(tamanios.toArray(new String[3]));
	}
	
	public static DefaultComboBoxModel<String> getModeloColores() {
		int size = colores.size();
		return new DefaultComboBoxModel<String>(colores.toArray(new String[size]));
	}
	
	public static void addTamanio(String pTamanio){
		tamanios.add(pTamanio);
	}
	
	public static void addEspecie(String pEspecie){
		especies.add(pEspecie);
		razas.add(new LinkedList<String>());
	}
	
	public static void addRaza(String pEspecie, String pRaza){
		razas.get(especies.indexOf(pEspecie)).add(pRaza);	
	}
	
	public static void addColor(String pColor){
		colores.add(pColor);
	}
	
	public static LinkedList<String> getEspecies() {
		return especies;
	}

	public static LinkedList<LinkedList<String>> getRazas() {
		return razas;
	}

	public static Integer getTotalChips() {
		return totalIDsRegistradas;
	}

	public static ComboBoxModel<String> getModeloSexos() {
		return new DefaultComboBoxModel<String>(new String[]{"Macho", "Hembra"});
	}
	
}
