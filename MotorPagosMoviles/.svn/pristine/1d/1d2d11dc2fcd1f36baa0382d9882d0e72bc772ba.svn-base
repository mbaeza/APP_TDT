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
public class ValidarPinVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1914180700918587892L;
	
	private String pinCinco;
	private String idDispositivo;	
	private String idApp;
	private String versionApp;
	
	/**
	 * @return the pinCinco
	 */
	public String getPinCinco() {
		return pinCinco;
	}
	/**
	 * @param pinCinco the pinCinco to set
	 */
	public void setPinCinco(String pinCinco) {
		this.pinCinco = pinCinco;
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
		if(!MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.pinCinco)
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.versionApp)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(60);
		
		bf.append("IdDispositivo : ");
		bf.append(this.idDispositivo);
		bf.append(", IdApp : ");
		bf.append(this.idApp);
		bf.append(", VersionApp : ");
		bf.append(this.versionApp);
		
		return bf.toString();
	}
	
}
