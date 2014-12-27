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
public class LoginAdmWebVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7031864290398221187L;
	
	private String idUsuario;
	private String claveUsuario;
	private String aliasOperario;
	private String idApp;
	private String versionApp;
	
	
	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the claveUsuario
	 */
	public String getClaveUsuario() {
		return claveUsuario;
	}

	/**
	 * @param claveUsuario the claveUsuario to set
	 */
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	/**
	 * @return the aliasOperario
	 */
	public String getAliasOperario() {
		return aliasOperario;
	}

	/**
	 * @param aliasOperario the aliasOperario to set
	 */
	public void setAliasOperario(String aliasOperario) {
		this.aliasOperario = aliasOperario;
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
		if(!MotorPagosHelper.isValid(this.idUsuario)
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.claveUsuario)
				|| !MotorPagosHelper.isValid(this.aliasOperario)
				|| !MotorPagosHelper.isValid(this.versionApp)){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(90);
		bf.append("idUsuario : ");
		bf.append(this.idUsuario);
		bf.append(", aliasOperario : ");
		bf.append(this.aliasOperario);
		bf.append(", IdApp : ");
		bf.append(this.idApp);
		bf.append(", VersionApp : ");
		bf.append(this.versionApp);
		
		return bf.toString();
	}
}
