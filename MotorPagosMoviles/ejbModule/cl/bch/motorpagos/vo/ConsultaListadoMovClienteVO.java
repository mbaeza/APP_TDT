/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

import cl.bch.motorpagos.util.MotorPagosHelper;

/**
 * @author boyanedel
 *
 */
public class ConsultaListadoMovClienteVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1282877281822243045L;
	private String rutCliente;
	private String fechaDesde; 
	private String fechaHasta;
	private int numeroPagina;
	private String idApp;
	private String versionApp;
	
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
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	/**
	 * @return the numeroPagina
	 */
	public int getNumeroPagina() {
		return numeroPagina;
	}
	/**
	 * @param numeroPagina the numeroPagina to set
	 */
	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	/**
	 * @return the idApp
	 */
	public String getIdApp() {
		return idApp;
	}
	/**
	 * @param idApp the idApp to set
	 */
	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}
	/**
	 * @return the versionApp
	 */
	public String getVersionApp() {
		return versionApp;
	}
	/**
	 * @param versionApp the versionApp to set
	 */
	public void setVersionApp(String versionApp) {
		this.versionApp = versionApp;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.rutCliente)				
				|| !MotorPagosHelper.isValid(this.fechaDesde)
				|| !MotorPagosHelper.isValid(this.fechaHasta)
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.versionApp)){
			return false;
		}else{
			return true;
		}			
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(180);
		
		bf.append("RutCliente : ");
		bf.append(this.rutCliente);
		bf.append(", FechaDesde : ");
		bf.append(this.fechaDesde);
		bf.append(", FechaHasta : ");
		bf.append(this.fechaHasta);
		bf.append(", NumeroPagina : ");
		bf.append(this.numeroPagina);
		bf.append(", IdApp : ");
		bf.append(this.idApp);
		bf.append(", VersionApp : ");
		bf.append(this.versionApp);
		
		return bf.toString();
	}
}
