/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author fahumadaa
 *
 */
public class RespuestaMarcaClienteVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1439361530337112563L;
	private RespuestaVO retorno;
	private String codigoMarca;

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
	 * @return the marca
	 */
	public String getCodigoMarca() {
		return this.codigoMarca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(120);
		bf.append(this.retorno.toString());
		bf.append("CodigoMarca : ");
		bf.append(this.codigoMarca);
		
		return bf.toString();
	}
}
