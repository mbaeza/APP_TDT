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
public class DispositivoVentaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3332065277045004889L;
	
	private String idConvenio;
	private String idCuenta;
	private String pin;
	private String aliasDispositivo;
	private String modeloDispositivo;
	

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
	 * @return the aliasDispositivo
	 */
	public String getAliasDispositivo() {
		return aliasDispositivo;
	}

	/**
	 * @param aliasDispositivo the aliasDispositivo to set
	 */
	public void setAliasDispositivo(String aliasDispositivo) {
		this.aliasDispositivo = aliasDispositivo;
	}

	/**
	 * @return the modeloDispositivo
	 */
	public String getModeloDispositivo() {
		return modeloDispositivo;
	}

	/**
	 * @param modeloDispositivo the modeloDispositivo to set
	 */
	public void setModeloDispositivo(String modeloDispositivo) {
		this.modeloDispositivo = modeloDispositivo;
	}
	
	/**
	 * @return the idCuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * @param idCuenta the idCuenta to set
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.idConvenio)
				|| !MotorPagosHelper.isValid(this.pin)
				|| !MotorPagosHelper.isValid(this.modeloDispositivo)
				|| !MotorPagosHelper.isValid(this.aliasDispositivo)
				|| !MotorPagosHelper.isValid(this.idCuenta)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(120);
		bf.append("IdConvenio : ");
		bf.append(this.idConvenio);
		bf.append(", IdCuenta : ");
		bf.append(this.idCuenta);
		bf.append(", ModeloDispositivo : ");
		bf.append(this.modeloDispositivo);
		bf.append(", AliasDispositivo : ");
		bf.append(this.aliasDispositivo);
		
		return bf.toString();
	}
}
