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
public class AvatarComercioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7527216247990286906L;
	private String idConvenio;
	private String imagenBase64;
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
	 * @return the imagenBase64
	 */
	public String getImagenBase64() {
		return imagenBase64;
	}
	/**
	 * @param imagenBase64 the imagenBase64 to set
	 */
	public void setImagenBase64(String imagenBase64) {
		this.imagenBase64 = imagenBase64;
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
				|| !MotorPagosHelper.isValid(this.pin)
				|| !MotorPagosHelper.isValid(this.imagenBase64)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(120);
		bf.append("IdConvenio : ");
		bf.append(this.idConvenio);
		bf.append(", IdDispositivo : ");
		bf.append(this.idDispositivo);
		
		return bf.toString();
	}
}
