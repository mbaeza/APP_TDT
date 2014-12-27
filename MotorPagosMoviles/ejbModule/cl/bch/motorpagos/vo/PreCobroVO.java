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
public class PreCobroVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5871194395909683537L;
	
	private String idTrxPago;
	private String idConvenio;
	private String idDispositivoComercio;
	private String idVendedor;
	private int propinaTrx;
	private int subTotalTrx;
	private int montoTrx;
	private String glosaTrx;	
	private String horaCobro;
	private String llaveComercio;
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
	 * @return the idDispositivoComercio
	 */
	public String getIdDispositivoComercio() {
		return idDispositivoComercio;
	}
	/**
	 * @param idDispositivoComercio the idDispositivoComercio to set
	 */
	public void setIdDispositivoComercio(String idDispositivoComercio) {
		this.idDispositivoComercio = idDispositivoComercio;
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
	 * @return the montoTrx
	 */
	public int getMontoTrx() {
		return montoTrx;
	}
	/**
	 * @param montoTrx the montoTrx to set
	 */
	public void setMontoTrx(int montoTrx) {
		this.montoTrx = montoTrx;
	}
	/**
	 * @return the glosaTrx
	 */
	public String getGlosaTrx() {
		return glosaTrx;
	}
	/**
	 * @param glosaTrx the glosaTrx to set
	 */
	public void setGlosaTrx(String glosaTrx) {
		this.glosaTrx = glosaTrx;
	}	
	/**
	 * @return the horaCobro
	 */
	public String getHoraCobro() {
		return horaCobro;
	}
	/**
	 * @param horaCobro the horaCobro to set
	 */
	public void setHoraCobro(String horaCobro) {
		this.horaCobro = horaCobro;
	}	
	/**
	 * @return the llaveComercio
	 */
	public String getLlaveComercio() {
		return llaveComercio;
	}
	/**
	 * @param llaveComercio the llaveComercio to set
	 */
	public void setLlaveComercio(String llaveComercio) {
		this.llaveComercio = llaveComercio;
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
	 * @return the propinaTrx
	 */
	public int getPropinaTrx() {
		return propinaTrx;
	}
	/**
	 * @param propinaTrx the propinaTrx to set
	 */
	public void setPropinaTrx(int propinaTrx) {
		this.propinaTrx = propinaTrx;
	}	
	/**
	 * @return the subTotalTrx
	 */
	public int getSubTotalTrx() {
		return subTotalTrx;
	}
	/**
	 * @param subTotalTrx the subTotalTrx to set
	 */
	public void setSubTotalTrx(int subTotalTrx) {
		this.subTotalTrx = subTotalTrx;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.idTrxPago)
				|| !MotorPagosHelper.isValid(this.idConvenio)
				|| !MotorPagosHelper.isValid(this.idDispositivoComercio)
				|| !MotorPagosHelper.isValid(this.idVendedor)				
				|| !MotorPagosHelper.isValid(this.horaCobro)
				|| !MotorPagosHelper.isValid(this.llaveComercio)
				|| !MotorPagosHelper.isValid(this.pin)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(210);
		
		bf.append("IdTrxPago : ");
		bf.append(this.idTrxPago);
		bf.append(", IdConvenio : ");
		bf.append(this.idConvenio);
		bf.append(", IdDispositivoComercio : ");
		bf.append(this.idDispositivoComercio);
		bf.append(", IdVendedor : ");
		bf.append(this.idVendedor);
		bf.append(", MontoTrx : ");
		bf.append(this.montoTrx);
		bf.append(", PropinaTrx : ");
		bf.append(this.propinaTrx);
		bf.append(", SubTotalTrx : ");
		bf.append(this.subTotalTrx);
		bf.append(", GlosaTrx : ");
		bf.append(this.glosaTrx);
		bf.append(", HoraCobro : ");
		bf.append(this.horaCobro);
		bf.append(", LlaveComercio : ");
		bf.append(this.llaveComercio);
		
		return bf.toString();
	}
	
}
