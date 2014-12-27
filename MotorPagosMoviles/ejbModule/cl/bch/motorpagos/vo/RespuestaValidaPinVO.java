/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaValidaPinVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7723500108615047210L;
	private String tipoDispositivo;
	private String rutDispositivo;
	private RespuestaVO retorno;
	/**
	 * @return the tipoDispositivo
	 */
	public String getTipoDispositivo() {
		return tipoDispositivo;
	}
	/**
	 * @param tipoDispositivo the tipoDispositivo to set
	 */
	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}
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
	 * @return the rutDispositivo
	 */
	public String getRutDispositivo() {
		return rutDispositivo;
	}
	/**
	 * @param rutDispositivo the rutDispositivo to set
	 */
	public void setRutDispositivo(String rutDispositivo) {
		this.rutDispositivo = rutDispositivo;
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(60);
		
		bf.append(this.retorno.toString());
		bf.append(", tipoDispositivo : ");
		bf.append(this.tipoDispositivo);		
				
		return bf.toString();
	}
}
