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
public class ValidarDispositivoSegVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8645887963050229527L;
	private String rutCliente;	
	private String rutEmpresa;
	private String serieDispSeguridad;
	private String tipoDispSeguridad;
	private String claveSeguridad;
	private String coordenada1;
	private String coordenada2;
	private String coordenada3;
	private String idApp;
	
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
		this.rutCliente = rutCliente.toUpperCase();
	}
	/**
	 * @return the serieDispSeguridad
	 */
	public String getSerieDispSeguridad() {
		return serieDispSeguridad;
	}
	/**
	 * @param serieDispSeguridad the serieDispSeguridad to set
	 */
	public void setSerieDispSeguridad(String serieDispSeguridad) {
		this.serieDispSeguridad = serieDispSeguridad;
	}
	/**
	 * @return the tipoDispSeguridad
	 */
	public String getTipoDispSeguridad() {
		return tipoDispSeguridad;
	}
	/**
	 * @param tipoDispSeguridad the tipoDispSeguridad to set
	 */
	public void setTipoDispSeguridad(String tipoDispSeguridad) {
		this.tipoDispSeguridad = tipoDispSeguridad;
	}
	/**
	 * @return the claveSeguridad
	 */
	public String getClaveSeguridad() {
		return claveSeguridad;
	}
	/**
	 * @param claveSeguridad the claveSeguridad to set
	 */
	public void setClaveSeguridad(String claveSeguridad) {
		this.claveSeguridad = claveSeguridad;
	}
	/**
	 * @return the coordenada1
	 */
	public String getCoordenada1() {
		return coordenada1;
	}
	/**
	 * @param coordenada1 the coordenada1 to set
	 */
	public void setCoordenada1(String coordenada1) {
		this.coordenada1 = coordenada1;
	}
	/**
	 * @return the coordenada2
	 */
	public String getCoordenada2() {
		return coordenada2;
	}
	/**
	 * @param coordenada2 the coordenada2 to set
	 */
	public void setCoordenada2(String coordenada2) {
		this.coordenada2 = coordenada2;
	}
	/**
	 * @return the coordenada3
	 */
	public String getCoordenada3() {
		return coordenada3;
	}
	/**
	 * @param coordenada3 the coordenada3 to set
	 */
	public void setCoordenada3(String coordenada3) {
		this.coordenada3 = coordenada3;
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
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.tipoDispSeguridad)
				|| !MotorPagosHelper.isValid(this.rutCliente)){
			return false;
		}else{
			if("8".equals(this.tipoDispSeguridad)){
				if(!MotorPagosHelper.isValid(this.claveSeguridad)){
					return false;
				}else{
					return true;
				}
			}else if("1".equals(this.tipoDispSeguridad)){
				if(!MotorPagosHelper.isValid(this.coordenada1) 
						|| !MotorPagosHelper.isValid(this.coordenada2) 
						|| !MotorPagosHelper.isValid(this.coordenada3)){
					return false;
				}else{
					return true;
				}
			}else{
				return false;
			}				
		}		
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(210);
		
		bf.append("Rut : ");
		bf.append(this.rutCliente);	
		bf.append("RutEmpresa : ");
		bf.append(this.rutEmpresa);	
		bf.append(", SerieDispSeguridad : ");
		bf.append(this.serieDispSeguridad);
		bf.append(", TipoDispSeguridad : ");
		bf.append(this.tipoDispSeguridad);
		bf.append(", IdApp : ");
		bf.append(this.idApp);

		return bf.toString();
	}
}
