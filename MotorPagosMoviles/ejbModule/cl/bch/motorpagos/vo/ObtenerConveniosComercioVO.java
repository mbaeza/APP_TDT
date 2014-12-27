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
public class ObtenerConveniosComercioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3094136808132470122L;
	private String rutEmpresa;
	private String rutApoderado;
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
		this.rutEmpresa = rutEmpresa;
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
		this.rutApoderado = rutApoderado;
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
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.versionApp)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(90);
		bf.append("RutEmpresa : ");
		bf.append(this.rutEmpresa);
		bf.append("RutApoderado : ");
		bf.append(this.rutApoderado);
		bf.append(", IdApp : ");
		bf.append(this.idApp);
		bf.append(", VersionApp : ");
		bf.append(this.versionApp);
		
		return bf.toString();
	}
}
