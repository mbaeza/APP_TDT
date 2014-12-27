/**
 * 
 */
package cl.bch.motorpagos.srmclient;

/**
 * @author boyanedel
 *
 */
public class LoginException extends Exception {
	private static final long serialVersionUID = 1L;
	private final int codigoError;
	private final String mensaje;
	
	public LoginException(int cod, String mensaje, Exception e){
		super(e);
		this.codigoError=cod;
		this.mensaje = mensaje;
	}

	/**
	 * @return the codigoError
	 */
	public int getCodigoError() {
		return codigoError;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	
	
}
