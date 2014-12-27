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
public class ValidarPagoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4884050607927313102L;
	private String idTrxPago;
	private String idConvenio;
	private String idDispositivo;
	private String pin;
	
	/**
	 * @return the idTrxPago
	 */
	public String getIdTrxPago() {
		return idTrxPago;
	}
	/**
	 * @param idTrxPago the idTrxPago to set
	 */
	public void setIdTrxPago(String idTrxPago) {
		this.idTrxPago = idTrxPago;
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
		if(!MotorPagosHelper.isValid(this.idTrxPago)
				|| !MotorPagosHelper.isValid(this.idConvenio)
				|| !MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.pin)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(60);
		
		bf.append("IdTrxPago : ");
		bf.append(this.idTrxPago);
		bf.append(", IdConvenio : ");
		bf.append(this.idConvenio);
		bf.append(", IdDispositivo : ");
		bf.append(this.idDispositivo);
		
		return bf.toString();
	}
}