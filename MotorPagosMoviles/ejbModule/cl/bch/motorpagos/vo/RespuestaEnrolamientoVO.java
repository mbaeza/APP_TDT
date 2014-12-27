package cl.bch.motorpagos.vo;

import java.io.Serializable;

public class RespuestaEnrolamientoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2610716771966949723L;
	
	private RespuestaVO retorno;
	private String idDispositivo;
	private String idConvenio;
	private String idCliente;
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
	 * @return the idConvenio
	 */
	public String getIdConvenio() {
		return idConvenio;
	}
	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}	
	/**
	 * @return the idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
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
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(120);
		
		bf.append(this.retorno.toString());
		bf.append(", IdDispositivo : ");
		bf.append(this.idDispositivo);
		bf.append(", IdConvenio : ");
		bf.append(this.idConvenio);
		bf.append(", IdCliente : ");
		bf.append(this.idCliente);
		bf.append(", IdCuenta : ");
		bf.append(this.idCuenta);
		
		return bf.toString();
	}

}
