/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaConsultaSaldoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4169552257815817787L;
	private RespuestaVO retorno;
	private int saldoDisponible;
	private int saldoContable;
	private int retencionesDia1;
	private int retencionesDiaN;
	private String moneda;
	private String idCuenta;
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
	 * @return the saldoDisponible
	 */
	public int getSaldoDisponible() {
		return saldoDisponible;
	}
	/**
	 * @param saldoDisponible the saldoDisponible to set
	 */
	public void setSaldoDisponible(int saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	/**
	 * @return the saldoContable
	 */
	public int getSaldoContable() {
		return saldoContable;
	}
	/**
	 * @param saldoContable the saldoContable to set
	 */
	public void setSaldoContable(int saldoContable) {
		this.saldoContable = saldoContable;
	}
	/**
	 * @return the retencionesDia1
	 */
	public int getRetencionesDia1() {
		return retencionesDia1;
	}
	/**
	 * @param retencionesDia1 the retencionesDia1 to set
	 */
	public void setRetencionesDia1(int retencionesDia1) {
		this.retencionesDia1 = retencionesDia1;
	}
	/**
	 * @return the retencionesDiaN
	 */
	public int getRetencionesDiaN() {
		return retencionesDiaN;
	}
	/**
	 * @param retencionesDiaN the retencionesDiaN to set
	 */
	public void setRetencionesDiaN(int retencionesDiaN) {
		this.retencionesDiaN = retencionesDiaN;
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
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	@Override
	public String toString() {
		
		StringBuffer bf = new StringBuffer(210);
		
		bf.append(this.retorno.toString());
		bf.append("saldoDisponible : ");
		bf.append(this.saldoDisponible);
		bf.append(", saldoContable : ");
		bf.append(this.saldoContable);
		bf.append(", retencionesDia1 : ");
		bf.append(this.retencionesDia1);
		bf.append(", retencionesDiaN : ");
		bf.append(this.retencionesDiaN);
		bf.append(", moneda : ");
		bf.append(this.moneda);
		bf.append(", idCuenta : ");
		bf.append(this.idCuenta);

		
		
		return bf.toString();
	}
}
