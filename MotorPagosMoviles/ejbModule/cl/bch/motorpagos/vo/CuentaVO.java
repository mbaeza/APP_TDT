/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class CuentaVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -743805343512021206L;
	
	private String llaveCuenta;
	private String mascaraCuenta;
	private int limiteCuenta;
	private int utilizadoCuenta;
		
	
	/**
	 * @return the mascaraCuenta
	 */
	public String getMascaraCuenta() {
		return mascaraCuenta;
	}
	/**
	 * @param mascaraCuenta the mascaraCuenta to set
	 */
	public void setMascaraCuenta(String mascaraCuenta) {
		this.mascaraCuenta = mascaraCuenta;
	}
	/**
	 * 
	 * @return
	 */
	public String getLlaveCuenta() {
		return llaveCuenta;
	}
	/**
	 * 
	 * @param llaveCuenta
	 */
	public void setLlaveCuenta(String llaveCuenta) {
		this.llaveCuenta = llaveCuenta;
	}
	
	/**
	 * @return the limiteCuenta
	 */
	public int getLimiteCuenta() {
		return limiteCuenta;
	}
	/**
	 * @param limiteCuenta the limiteCuenta to set
	 */
	public void setLimiteCuenta(int limiteCuenta) {
		this.limiteCuenta = limiteCuenta;
	}
	
	/**
	 * @return the utilizadoCuenta
	 */
	public int getUtilizadoCuenta() {
		return utilizadoCuenta;
	}
	/**
	 * @param utilizadoCuenta the utilizadoCuenta to set
	 */
	public void setUtilizadoCuenta(int utilizadoCuenta) {
		this.utilizadoCuenta = utilizadoCuenta;
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(120);
		bf.append("LlaveCuenta : ");
		bf.append(this.llaveCuenta);
		bf.append(", Mascara : ");
		bf.append(this.mascaraCuenta);
		bf.append(", LimiteCuenta : ");
		bf.append(this.limiteCuenta);
		bf.append(", UtilizadoCuenta : ");
		bf.append(this.utilizadoCuenta);
				
		return bf.toString();
	}
}
