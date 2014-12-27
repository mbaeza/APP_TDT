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
public class CambioPinVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9047277275753022891L;
	private String nuevoPin;
	private String pinActual;
	private String idDispositivo;
	/**
	 * @return the nuevoPin
	 */
	public String getNuevoPin() {
		return nuevoPin;
	}
	/**
	 * @param nuevoPin the nuevoPin to set
	 */
	public void setNuevoPin(String nuevoPin) {
		this.nuevoPin = nuevoPin;
	}
	/**
	 * @return the pinActual
	 */
	public String getPinActual() {
		return pinActual;
	}
	/**
	 * @param pinActual the pinActual to set
	 */
	public void setPinActual(String pinActual) {
		this.pinActual = pinActual;
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
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.pinActual)
				|| !MotorPagosHelper.isValid(this.nuevoPin)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(90);
		bf.append("IdDispositivo");
		bf.append(this.idDispositivo);
				
		return bf.toString();
	}
}
