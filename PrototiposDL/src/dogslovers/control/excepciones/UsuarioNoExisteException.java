package dogslovers.control.excepciones;

public class UsuarioNoExisteException extends Exception {
	public UsuarioNoExisteException(String pString) {
		super(pString);
	}
}
