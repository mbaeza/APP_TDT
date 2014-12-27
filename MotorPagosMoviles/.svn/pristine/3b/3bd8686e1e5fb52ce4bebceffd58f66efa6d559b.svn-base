/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class RespuestaCierreDiarioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5490839656561081944L;
	private RespuestaVO retorno;
	private long totalDia;
	private long totalPropinasDia;
	private long totalSubTotalDia;
	private long totalVentasDia;
	private String idDispositivo;
	private MovimientosVendedorVO vendedores;
	private String fechaHoraSistema;
	private String rutComercio;
	private String nombreComercio;
	private String nombreConvenio;
	
	/**
	 * @return the totalDia
	 */
	public long getTotalDia() {
		return totalDia;
	}
	/**
	 * @param totalDia the totalDia to set
	 */
	public void setTotalDia(long totalDia) {
		this.totalDia = totalDia;
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
	 * @return the vendedores
	 */
	public MovimientosVendedorVO getVendedores() {
		return vendedores;
	}
	/**
	 * @param vendedores the vendedores to set
	 */
	public void setVendedores(MovimientosVendedorVO vendedores) {
		this.vendedores = vendedores;
	}
	/**
	 * @return the totalVentasDia
	 */
	public long getTotalVentasDia() {
		return totalVentasDia;
	}
	/**
	 * @param totalVentasDia the totalVentasDia to set
	 */
	public void setTotalVentasDia(long totalVentasDia) {
		this.totalVentasDia = totalVentasDia;
	}	
	/**
	 * @return the fechaHoraSistema
	 */
	public String getFechaHoraSistema() {
		return fechaHoraSistema;
	}
	/**
	 * @param fechaHoraSistema the fechaHoraSistema to set
	 */
	public void setFechaHoraSistema(String fechaHoraSistema) {
		this.fechaHoraSistema = fechaHoraSistema;
	}
	/**
	 * @return the rutComercio
	 */
	public String getRutComercio() {
		return rutComercio;
	}
	/**
	 * @param rutComercio the rutComercio to set
	 */
	public void setRutComercio(String rutComercio) {
		this.rutComercio = rutComercio.toUpperCase();
	}
	/**
	 * @return the nombreComercio
	 */
	public String getNombreComercio() {
		return nombreComercio;
	}
	/**
	 * @param nombreComercio the nombreComercio to set
	 */
	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}
	/**
	 * @return the nombreConvenio
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	/**
	 * @param nombreConvenio the nombreConvenio to set
	 */
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}
	/**
	 * @return the totalPropinasDia
	 */
	public long getTotalPropinasDia() {
		return totalPropinasDia;
	}
	/**
	 * @param totalPropinasDia the totalPropinasDia to set
	 */
	public void setTotalPropinasDia(long totalPropinasDia) {
		this.totalPropinasDia = totalPropinasDia;
	}
	/**
	 * @return the totalSubTotalDia
	 */
	public long getTotalSubTotalDia() {
		return totalSubTotalDia;
	}
	/**
	 * @param totalSubTotalDia the totalSubTotalDia to set
	 */
	public void setTotalSubTotalDia(long totalSubTotalDia) {
		this.totalSubTotalDia = totalSubTotalDia;
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(180);
		
		bf.append(this.retorno.toString());
		bf.append("IdDispositivo : ");
		bf.append(this.idDispositivo);
		bf.append(", RutComercio : ");
		bf.append(this.rutComercio);
		bf.append(", NombreComercio : ");
		bf.append(this.nombreComercio);
		bf.append(", NombreConvenio : ");
		bf.append(this.nombreConvenio);
		bf.append(", TotalPropinasDia : ");
		bf.append(this.totalPropinasDia);
		bf.append(", TotalSubTotalDia : ");
		bf.append(this.totalSubTotalDia);
		bf.append(", TotalDia : ");
		bf.append(this.totalDia);
		bf.append(", TotalVentasDia : ");
		bf.append(this.totalVentasDia);
		bf.append(", FechaHoraSistema : ");
		bf.append(this.fechaHoraSistema);
				
		if(this.vendedores!=null){
			bf.append(this.vendedores.toString());
		}
		
		return bf.toString();
	}
}
