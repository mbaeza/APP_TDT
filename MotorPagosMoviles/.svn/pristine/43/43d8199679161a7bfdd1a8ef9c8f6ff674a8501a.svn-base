/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaListadoMovimientosVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5243433747502311111L;
	private RespuestaVO retorno;
	private MovimientosConvenioVO listadoMovimientos;
	private int numeroPaginas;
	private int paginaActual;
	private int totalRegistros;
	
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
	 * @return the listadoMovimientos
	 */
	public MovimientosConvenioVO getListadoMovimientos() {
		return listadoMovimientos;
	}
	/**
	 * @param listadoMovimientos the listadoMovimientos to set
	 */
	public void setListadoMovimientos(MovimientosConvenioVO listadoMovimientos) {
		this.listadoMovimientos = listadoMovimientos;
	}
	/**
	 * @return the numeroPaginas
	 */
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	/**
	 * @param numeroPaginas the numeroPaginas to set
	 */
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	/**
	 * @return the paginaActual
	 */
	public int getPaginaActual() {
		return paginaActual;
	}
	/**
	 * @param paginaActual the paginaActual to set
	 */
	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}
	/**
	 * @return the totalRegistros
	 */
	public int getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @param totalRegistros the totalRegistros to set
	 */
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(150);
		
		bf.append(this.retorno.toString());
		bf.append("numeroPaginas : ");
		bf.append(this.numeroPaginas);
		bf.append(", paginaActual : ");
		bf.append(this.paginaActual);
		bf.append(", totalRegistros : ");
		bf.append(this.totalRegistros);
		
		if(this.listadoMovimientos!=null){
			bf.append('[');
			bf.append(this.listadoMovimientos.toString());
			bf.append("],");
		}
		
		return bf.toString();
	}
}
