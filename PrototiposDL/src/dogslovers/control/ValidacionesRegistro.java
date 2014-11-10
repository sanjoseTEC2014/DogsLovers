package dogslovers.control;

import java.awt.Component;
import java.awt.image.ComponentSampleModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComponent;

public class ValidacionesRegistro {

	public static boolean validarCorreo(String email) {
		final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	
		 // Compila la expresi๓n regular dada como un Pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Compara la entrada dada contra este Pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}

	public static boolean HayCamposVacios(String[] campos) {
		for (String campo : campos) {
			if (campo.trim().isEmpty()) return true;
		}
		return false;
	}
	
	//TODO validarSoloNumeros
	//TODO validarCorreo
	//TODO validarNickDisponible

}
