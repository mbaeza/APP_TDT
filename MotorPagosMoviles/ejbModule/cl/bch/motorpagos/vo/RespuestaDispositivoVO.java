/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaDispositivoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407484584679590502L;
	
	private String idDispositivo;
	private RespuestaVO retorno;
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
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(60);
		
		bf.append(this.retorno.toString());
		bf.append(", IdDispositivo : ");
		bf.append(this.idDispositivo);
		
		return bf.toString();
	}
}
