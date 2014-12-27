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
public class ObtenerCuentasFilialVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 589381143374780702L;
	private String rut;
	private String idApp;
	private String versionApp;
	private String rutEmpresa;
	private String rutPersona;
	private String clave;
	
	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
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
	 * @return the rutPersona
	 */
	public String getRutPersona() {
		return rutPersona;
	}
	/**
	 * @param rutPersona the rutPersona to set
	 */
	public void setRutPersona(String rutPersona) {
		this.rutPersona = rutPersona;
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
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.rut)
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.versionApp)
				|| !MotorPagosHelper.isValid(this.rutEmpresa)
				|| !MotorPagosHelper.isValid(this.rutPersona)
				|| !MotorPagosHelper.isValid(this.clave)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(120);
		bf.append("RutFilial : ");
		bf.append(this.rut);
		bf.append(", RutEmpresa : ");
		bf.append(this.rutEmpresa);
		bf.append(", RutPersona : ");
		bf.append(this.rutPersona);		
		bf.append(", IdApp : ");
		bf.append(this.idApp);
		bf.append(", VersionApp : ");
		bf.append(this.versionApp);
						
		return bf.toString();
	}
}
