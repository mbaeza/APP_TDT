/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaDispRegistradosVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8082386506859136009L;
	private RespuestaVO retorno;
	private String rutCliente;
	private String nombreCliente;
	private ListaDispositivosVO listaDispositivos;
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
	 * @return the listaDispositivos
	 */
	public ListaDispositivosVO getListaDispositivos() {
		return listaDispositivos;
	}
	/**
	 * @param listaDispositivos the listaDispositivos to set
	 */
	public void setListaDispositivos(ListaDispositivosVO listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}		
	/**
	 * @return the rutCliente
	 */
	public String getRutCliente() {
		return rutCliente;
	}
	/**
	 * @param rutCliente the rutCliente to set
	 */
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	@Override
	public String toString() {
		
		StringBuffer bf = new StringBuffer(100);
		bf.append("Rut:");
		bf.append(this.rutCliente);
		bf.append("Nombre:");
		bf.append(this.nombreCliente);
		
		bf.append(this.retorno.toString());
		if (this.listaDispositivos!=null){
			bf.append(this.listaDispositivos.toString());
		}
				
		return bf.toString();
	}
}
