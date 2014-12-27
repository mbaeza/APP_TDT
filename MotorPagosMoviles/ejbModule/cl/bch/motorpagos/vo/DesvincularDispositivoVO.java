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
public class DesvincularDispositivoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3474426709182635754L;
	private String pinAdmin;
	private String idDispositivoAdmin;
	private String idDispositivo;
	private String idConvenio;
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
	 * @return the pinAdmin
	 */
	public String getPinAdmin() {
		return pinAdmin;
	}
	/**
	 * @param pinAdmin the pinAdmin to set
	 */
	public void setPinAdmin(String pinAdmin) {
		this.pinAdmin = pinAdmin;
	}
	
	/**
	 * @return the idDispositivoAdmin
	 */
	public String getIdDispositivoAdmin() {
		return idDispositivoAdmin;
	}
	/**
	 * @param idDispositivoAdmin the idDispositivoAdmin to set
	 */
	public void setIdDispositivoAdmin(String idDispositivoAdmin) {
		this.idDispositivoAdmin = idDispositivoAdmin;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.idConvenio)
				|| !MotorPagosHelper.isValid(this.pinAdmin)
				|| !MotorPagosHelper.isValid(this.idDispositivoAdmin)){
			return false;
		}else{
			return true;
		}
	}	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(120);
		bf.append("IdDispositivo : ");
		bf.append(this.idDispositivo);
		bf.append(", IdConvenio : ");
		bf.append(this.idConvenio);	
		bf.append("IdDispositivoAdmin : ");
		bf.append(this.idDispositivoAdmin);
		
		return bf.toString();
	}
}
