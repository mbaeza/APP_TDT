/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

import cl.bch.motorpagos.util.MotorPagosHelper;

/**
 * @author boyanedel
 *
 */
public class ObtenerMovRecientesVO implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2923697370849710063L;
	private String idDispositivo;
	private String pin;
	/**
	 * @return the idDispositivo
	 */
	public String getIdDispositivo() {
		return idDispositivo;
	}
	/**
	 * @param idDispositivo the idDispositivo to set
	 */
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.pin)){
			return false;
		}else{
			return true;
		}		
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(330);
		
		bf.append("idDispositivo : ");
		bf.append(this.idDispositivo);
								
		return bf.toString();
	}
}
