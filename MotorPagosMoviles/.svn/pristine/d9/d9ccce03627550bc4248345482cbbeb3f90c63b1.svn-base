/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaMediosDePagoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3936611458740160606L;
	private RespuestaVO retorno;
	private CuentasClienteVO cuentas;
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
	 * @return the cuentas
	 */
	public CuentasClienteVO getCuentas() {
		return cuentas;
	}
	/**
	 * @param cuentas the cuentas to set
	 */
	public void setCuentas(CuentasClienteVO cuentas) {
		this.cuentas = cuentas;
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
		StringBuffer bf = new StringBuffer(90);
		
		bf.append(this.retorno.toString());
		
		if(this.cuentas!=null){
			bf.append(this.cuentas.toString());
		}
		if(this.dispositivos!=null){
			bf.append(this.dispositivos.toString());
		}
		return bf.toString();
	}
}
