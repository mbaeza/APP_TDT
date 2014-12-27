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
public class ObtenerDispConvenioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3328354589815707488L;
	private String idConvenio;
	private String idDispositivo;
	private String pin;
	/**
	 * @return the idConvenio
	 */
	public String getIdConvenio() {
		return idConvenio;
	}
	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}
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
		if(!MotorPagosHelper.isValid(this.idConvenio)
				|| !MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.pin)){
			return false;
		}else{
			return true;
		}		
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(330);
		bf.append("idConvenio : ");
		bf.append(this.idConvenio);
		bf.append("idDispositivo : ");
		bf.append(this.idDispositivo);
								
		return bf.toString();
	}
}
