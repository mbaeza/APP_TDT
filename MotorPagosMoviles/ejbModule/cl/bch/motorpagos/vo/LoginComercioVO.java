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
public class LoginComercioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2890377134463871278L;
	
	private String rutPersona;
	private String rutEmpresa;
	private String clave;
	private String idApp;
	private String versionApp;
	/**
	 * @return the rutPersona
	 */
	public String getRutPersona() {
		return rutPersona;
	}
	/**
	 * @param rutPersona the rutPersona to set
	 */
	public void setRutPersona(String rutPersona) {
		this.rutPersona = rutPersona.toUpperCase();
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
		this.rutEmpresa = rutEmpresa.toUpperCase();
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
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
		if(!MotorPagosHelper.isValid(this.rutPersona)
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.versionApp)
				|| !MotorPagosHelper.isValid(this.clave)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(120);
		bf.append("RutApoderado : ");
		bf.append(this.rutPersona);
		bf.append(", RutEmpresa : ");
		bf.append(this.rutEmpresa);
		bf.append(", IdApp : ");
		bf.append(this.idApp);		
		bf.append(", VersionApp : ");
		bf.append(this.versionApp);	
		
		return bf.toString();
	}
}
