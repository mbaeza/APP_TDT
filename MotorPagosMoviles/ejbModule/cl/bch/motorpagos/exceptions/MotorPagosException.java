/**
 * 
 */
package cl.bch.motorpagos.exceptions;

/**
 * @author boyanedel
 *
 */
public class MotorPagosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264724164088306342L;
	
	public MotorPagosException(){
		super();
	}
	
	/**
	 * 
	 * @param message
	 */
	public MotorPagosException(Exception e){
		super(e);
	}

}
