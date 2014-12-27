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
public class PagoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4071981696267840330L;
	private String idTrxPago;
	private String idDispositivoCliente;
	private String pinCliente;
	private String estadoPago;	
		
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
	 * @return the idDispositivoCliente
	 */
	public String getIdDispositivoCliente() {
		return idDispositivoCliente;
	}
	/**
	 * @param idDispositivoCliente the idDispositivoCliente to set
	 */
	public void setIdDispositivoCliente(String idDispositivoCliente) {
		this.idDispositivoCliente = idDispositivoCliente;
	}
	/**
	 * @return the pinCliente
	 */
	public String getPinCliente() {
		return pinCliente;
	}
	/**
	 * @param pinCliente the pinCliente to set
	 */
	public void setPinCliente(String pinCliente) {
		this.pinCliente = pinCliente;
	}
	/**
	 * @return the estadoPago
	 */
	public String getEstadoPago() {
		return estadoPago;
	}
	/**
	 * @param estadoPago the estadoPago to set
	 */
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.idTrxPago)
				|| !MotorPagosHelper.isValid(this.idDispositivoCliente)
				|| !MotorPagosHelper.isValid(this.pinCliente)
				|| !MotorPagosHelper.isValid(this.estadoPago)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(120);
		bf.append("IdTrxPago : ");
		bf.append(this.idTrxPago);
		bf.append(", IdDispositivoCliente : ");
		bf.append(this.idDispositivoCliente);
		bf.append(", EstadoPago : ");
		bf.append(this.estadoPago);
		
		return bf.toString();
	}
}
