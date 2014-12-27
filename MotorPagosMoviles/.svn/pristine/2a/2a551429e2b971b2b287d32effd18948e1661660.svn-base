/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaDispositivosSeguridadVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4879607416013913200L;
	private RespuestaVO retorno;
	private DispositivosClienteVO dispositivos;
	
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
	 * @return the dispositivos
	 */
	public DispositivosClienteVO getDispositivos() {
		return dispositivos;
	}
	/**
	 * @param dispositivos the dispositivos to set
	 */
	public void setDispositivos(DispositivosClienteVO dispositivos) {
		this.dispositivos = dispositivos;
	}
	@Override
	public String toString() {		
		StringBuffer bf = new StringBuffer(60);
		
		bf.append(this.retorno.toString());
		
		if(this.dispositivos!=null){
			bf.append(this.dispositivos.toString());
		}			
		return bf.toString();
	}
}
