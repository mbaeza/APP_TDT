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
public class ComercioVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 74236633822745381L;
	
	private String rutPersonas;
	private String rutEmpresa;
	private String email;
	private String direccion;
	private String razonSocial;
	private String nombreConvenio;
	private String pin;
	private String llaveCuenta;
	private String idApp;
	private String aliasDispositivo;
	private String modeloDispositivo;
	
	private String serieDispSeguridad;
	private String tipoDispSeguridad;
	private String claveSeguridad;
	private String coordenada1;
	private String coordenada2;
	private String coordenada3;
	
	/**
	 * @return the rutPersonas
	 */
	public String getRutPersonas() {
		return rutPersonas;
	}
	/**
	 * @param rutPersonas the rutPersonas to set
	 */
	public void setRutPersonas(String rutPersonas) {
		this.rutPersonas = rutPersonas.toUpperCase();
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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
	 * @return the llaveCuenta
	 */
	public String getLlaveCuenta() {
		return llaveCuenta;
	}
	/**
	 * @param llaveCuenta the llaveCuenta to set
	 */
	public void setLlaveCuenta(String llaveCuenta) {
		this.llaveCuenta = llaveCuenta;
	}
	
	/**
	 * @return the aliasDispositivo
	 */
	public String getAliasDispositivo() {
		return aliasDispositivo;
	}
	/**
	 * @param aliasDispositivo the aliasDispositivo to set
	 */
	public void setAliasDispositivo(String aliasDispositivo) {
		this.aliasDispositivo = aliasDispositivo;
	}
	/**
	 * @return the modeloDispositivo
	 */
	public String getModeloDispositivo() {
		return modeloDispositivo;
	}
	/**
	 * @param modeloDispositivo the modeloDispositivo to set
	 */
	public void setModeloDispositivo(String modeloDispositivo) {
		this.modeloDispositivo = modeloDispositivo;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.rutPersonas)
				|| !MotorPagosHelper.isValid(this.llaveCuenta)
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.direccion)
				|| !MotorPagosHelper.isValid(this.email)
				|| !MotorPagosHelper.isValid(this.razonSocial)
				|| !MotorPagosHelper.isValid(this.nombreConvenio)
				|| !MotorPagosHelper.isValid(this.aliasDispositivo)
				|| !MotorPagosHelper.isValid(this.modeloDispositivo)
				|| !MotorPagosHelper.isValid(this.pin)){
			return false;
		}else{
			if(!MotorPagosHelper.isValid(this.tipoDispSeguridad)){
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
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(510);
		bf.append("RutPersonas : ");
		bf.append(this.rutPersonas);
		bf.append(", RutEmpresa : ");
		bf.append(this.rutEmpresa);
		bf.append(", Email : ");
		bf.append(this.email);
		bf.append(", LlaveCuenta : ");
		bf.append(this.llaveCuenta);
		bf.append(", IdApp : ");
		bf.append(this.idApp);		
		bf.append(", Direccion : ");
		bf.append(this.direccion);
		bf.append(", RazonSocial : ");
		bf.append(this.razonSocial);
		bf.append(", NombreConvenio : ");
		bf.append(this.nombreConvenio);
		bf.append(", aliasDispositivo : ");
		bf.append(this.aliasDispositivo);
		bf.append(", modeloDispositivo : ");
		bf.append(this.modeloDispositivo);
		bf.append(", SerieDispSeguridad : ");
		bf.append(this.serieDispSeguridad);
		bf.append(", TipoDispSeguridad : ");
		bf.append(this.tipoDispSeguridad);
				
		return bf.toString();
	}
	
}
