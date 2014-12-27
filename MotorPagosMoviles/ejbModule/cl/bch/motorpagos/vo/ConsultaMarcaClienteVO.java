/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

import cl.bch.motorpagos.util.MotorPagosHelper;

/**
 * @author fahumadaa
 *
 */
public class ConsultaMarcaClienteVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -10039995227581673L;
	private String idDispositivo;
	private String pin;
	/**
	 * @return the idDispositivo
	 */
	public String getIdDispositivo() {
		return this.idDispositivo;
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
		return this.pin;
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
		if(!MotorPagosHelper.isValid(this.getIdDispositivo())
				|| !MotorPagosHelper.isValid(this.getPin())){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		
		StringBuffer bf = new StringBuffer(90);
		bf.append(", pin : ");
		bf.append(this.pin);
		bf.append(", idDispositivo : ");
		bf.append(this.getIdDispositivo());
		
		return bf.toString();
	}
}
