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
public class CierreDiarioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6261149779517952603L;
	private String idDispositivo;
	private String pin;
	private String fechaCierre;
	private String idVendedor;
	
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
	 * @return the fechaCierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}
	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	/**
	 * @return the idVendedor
	 */
	public String getIdVendedor() {
		return idVendedor;
	}
	/**
	 * @param idVendedor the idVendedor to set
	 */
	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.fechaCierre)
				|| !MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.pin)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(120);
		bf.append("FechaCierre : ");
		bf.append(this.fechaCierre);
		bf.append(", IdDispositivo : ");
		bf.append(this.idDispositivo);
		bf.append(", IdVendedor : ");
		bf.append(this.idVendedor);
		
		return bf.toString();
	}
}