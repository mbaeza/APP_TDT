/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8476665975814201953L;
	
	private String codigoRetorno;
	private String glosaRetorno;
	
	/**
	 * @return the codigoRetorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	/**
	 * @param codigoRetorno the codigoRetorno to set
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	/**
	 * @return the glosaRetorno
	 */
	public String getGlosaRetorno() {
		return glosaRetorno;
	}
	/**
	 * @param glosaRetorno the glosaRetorno to set
	 */
	public void setGlosaRetorno(String glosaRetorno) {
		this.glosaRetorno = glosaRetorno;
	}
	
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(60);
		
		bf.append("CodigoRetorno : ");
		bf.append(this.codigoRetorno);
		bf.append(", GlosaRetorno : ");
		bf.append(this.glosaRetorno);
		bf.append(", ");
		
		return bf.toString();
	}

}
