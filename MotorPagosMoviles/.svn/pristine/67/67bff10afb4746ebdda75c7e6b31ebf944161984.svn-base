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
public class CambioEstadoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1309252035924423033L;
	private String id;
	private String estado;
	private String rutCliente;
	private String rutApoderado;
	private String rutEmpresa;
	private String idApp;
	private String versionApp;
	
	private String serieDispSeguridad;
	private String tipoDispSeguridad;
	private String claveSeguridad;
	private String coordenada1;
	private String coordenada2;
	private String coordenada3;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
		if(!MotorPagosHelper.isValid(this.id)
				|| !MotorPagosHelper.isValid(this.estado)
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.versionApp)
				|| (!MotorPagosHelper.isValid(this.rutCliente) && (!MotorPagosHelper.isValid(this.rutApoderado) || !MotorPagosHelper.isValid(this.rutEmpresa)))){
			
			return false;
		}else{
			if(!MotorPagosHelper.isValid(this.tipoDispSeguridad)){
				return true;
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
	}
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(330);
		bf.append("Id : ");
		bf.append(this.id);
		bf.append(", Estado : ");
		bf.append(this.estado);		
		bf.append(", Rut : ");
		bf.append(this.rutCliente);
		bf.append(", RutApoderado : ");
		bf.append(this.rutApoderado);
		bf.append(", RutEmpresa : ");
		bf.append(this.rutEmpresa);
		
		bf.append(", SerieDispSeguridad : ");
		bf.append(this.serieDispSeguridad);
		bf.append(", TipoDispSeguridad : ");
		bf.append(this.tipoDispSeguridad);
//		bf.append(", ClaveSeguridad : ");
//		bf.append(this.claveSeguridad);
//		bf.append(", Coordenada1 : ");
//		bf.append(this.coordenada1);
//		bf.append(", Coordenada2 : ");
//		bf.append(this.coordenada2);
//		bf.append(", Coordenada3 : ");
//		bf.append(this.coordenada3);		
		
		return bf.toString();
	}
	
}
