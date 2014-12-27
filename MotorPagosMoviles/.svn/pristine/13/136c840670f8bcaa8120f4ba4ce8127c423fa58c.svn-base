/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaPagoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4788184883020687526L;
	private RespuestaVO retorno;
	private String idTrxPago;
	private String glosaTrx;
	private String idVendedor;
	private int montoTrx;
	private String fechaHoraTrx;
	
	/**
	 * @return the retorno
	 */
	public RespuestaVO getRetorno() {
		return retorno;
	}
	/**
	 * @param retorno the retorno to set
	 */
	public void setRetorno(RespuestaVO retorno) {
		this.retorno = retorno;
	}
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
	 * @return the fechaHoraTrx
	 */
	public String getFechaHoraTrx() {
		return fechaHoraTrx;
	}
	/**
	 * @param fechaHoraTrx the fechaHoraTrx to set
	 */
	public void setFechaHoraTrx(String fechaHoraTrx) {
		this.fechaHoraTrx = fechaHoraTrx;
	}	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(180);
		
		bf.append(this.retorno.toString());
		bf.append(", IdTrxPago : ");
		bf.append(this.idTrxPago);
		bf.append(", GlosaTrx : ");
		bf.append(this.glosaTrx);
		bf.append(", IdVendedor : ");
		bf.append(this.idVendedor);
		bf.append(", MontoTrx : ");
		bf.append(this.montoTrx);
		bf.append(", FechaHoraTrx : ");
		bf.append(this.fechaHoraTrx);
				
		return bf.toString();
	}
}
