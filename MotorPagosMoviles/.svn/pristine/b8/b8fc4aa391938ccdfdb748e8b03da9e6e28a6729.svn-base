/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaConsultaCuentaVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4169552257815817787L;
	private RespuestaVO retorno;
	private String nroCuenta;
	private String tipoCuenta;
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
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}
	/**
	 * @param nroCuenta the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	@Override
	public String toString() {
		
		StringBuffer bf = new StringBuffer(210);
		
		bf.append(this.retorno.toString());
		bf.append("nroCuenta : ");
		bf.append(this.nroCuenta);
		bf.append(", tipoCuenta : ");
		bf.append(this.tipoCuenta);		

		
		return bf.toString();
	}
}
