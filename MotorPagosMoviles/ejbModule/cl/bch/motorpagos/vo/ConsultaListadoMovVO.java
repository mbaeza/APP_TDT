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
public class ConsultaListadoMovVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1282877281822243045L;
	private String rutEmpresa;
	private String rutApoderado;
	private String idConvenio; 
	private String fechaDesde; 
	private String fechaHasta;
	private int numeroPagina;
	private String idApp;
	private String versionApp;
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
		this.rutEmpresa = rutEmpresa.toUpperCase();
	}
	/**
	 * @return the rutApoderado
	 */
	public String getRutApoderado() {
		return rutApoderado;
	}
	/**
	 * @param rutApoderado the rutApoderado to set
	 */
	public void setRutApoderado(String rutApoderado) {
		this.rutApoderado = rutApoderado.toUpperCase();
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
		if(!MotorPagosHelper.isValid(this.rutEmpresa)
				|| !MotorPagosHelper.isValid(this.rutApoderado)
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
		
		bf.append("rutEmpresa : ");
		bf.append(this.rutEmpresa);
		bf.append(", rutApoderado : ");
		bf.append(this.rutApoderado);
		bf.append(", idConvenio : ");
		bf.append(this.idConvenio);
		bf.append(", fechaDesde : ");
		bf.append(this.fechaDesde);
		bf.append(", fechaHasta : ");
		bf.append(this.fechaHasta);
		bf.append(", numeroPagina : ");
		bf.append(this.numeroPagina);
		bf.append(", idApp : ");
		bf.append(this.idApp);
		bf.append(", versionApp : ");
		bf.append(this.versionApp);
		
		return bf.toString();
	}
}
