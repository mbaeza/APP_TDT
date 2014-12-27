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
public class CancelaCobroVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5861098973867434056L;
	private String idDispositivo;
	private String pin;
	private String idConvenio;
	private String idTrx;
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
	 * @return the idTrx
	 */
	public String getIdTrx() {
		return idTrx;
	}
	/**
	 * @param idTrx the idTrx to set
	 */
	public void setIdTrx(String idTrx) {
		this.idTrx = idTrx;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.idTrx)
				|| !MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.pin)
				|| !MotorPagosHelper.isValid(this.idConvenio)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(120);
		
		bf.append("IdTrx : ");
		bf.append(this.idTrx);	
		bf.append(", IdDispositivo : ");
		bf.append(this.idDispositivo);	
		bf.append(", IdConvenio : ");
		bf.append(this.idConvenio);
						
		return bf.toString();
	}
}
