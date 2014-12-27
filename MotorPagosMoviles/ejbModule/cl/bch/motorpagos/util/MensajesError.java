/**
 * 
 */
package cl.bch.motorpagos.util;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class MensajesError implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2660195199260772589L;
	public static final String ERROR_GENERICO = "En este momento no podemos atender tu solicitud, intenta más tarde.";
	public static final String OPERACION_EXITOSA = "Operación Exitosa.";
	public static final String ERROR_PARAMETROS_INVALIDOS = "Parametros No Validos.";
	public static final String ERROR_VERSION_OBSOLETA = "Debe actualizar la aplicación.";
	public static final String ERROR_METODO_DEPRECADO = "Metodo Deprecado.";
}
