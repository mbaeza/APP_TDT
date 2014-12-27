/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaConveniosVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5940156480599177880L;
	private RespuestaVO retorno;
	private ConveniosComercioVO listaConvenios;
	private String rutEmpresa;
	private String nombreEmpresa;
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
	 * @return the listaConvenios
	 */
	public ConveniosComercioVO getListaConvenios() {
		return listaConvenios;
	}
	/**
	 * @param listaConvenios the listaConvenios to set
	 */
	public void setListaConvenios(ConveniosComercioVO listaConvenios) {
		this.listaConvenios = listaConvenios;
	}	
	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	@Override
	public String toString() {
		
		StringBuffer bf = new StringBuffer(90);
		bf.append("Rut : ");
		bf.append(this.rutEmpresa);
		bf.append(", Nombre : ");
		bf.append(this.nombreEmpresa);
		
		bf.append(this.retorno.toString());
		
		if(this.listaConvenios!=null){
			bf.append(this.listaConvenios.toString());
		}
		
		return bf.toString();
	}
	

}
