package cl.bch.motorpagos.vo;

import java.io.Serializable;

import cl.bch.motorpagos.util.MotorPagosHelper;

public class ClienteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981549113710608251L;
	private String rut;
	private String pin;
	private String llaveCuenta;
	private String idApp;
	private String nombre;
	private String marca;
	private String mail;
	private String aliasDispositivo;
	private String modeloDispositivo;
	private String direccion;
		
	private String serieDispSeguridad;
	private String tipoDispSeguridad;
	private String claveSeguridad;
	private String coordenada1;
	private String coordenada2;
	private String coordenada3;
		
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
		this.rut = rut.toUpperCase();
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.rut)
				|| !MotorPagosHelper.isValid(this.llaveCuenta)
				|| !MotorPagosHelper.isValid(this.idApp)
				|| !MotorPagosHelper.isValid(this.nombre)
				|| !MotorPagosHelper.isValid(this.mail)
				|| !MotorPagosHelper.isValid(this.aliasDispositivo)
				|| !MotorPagosHelper.isValid(this.modeloDispositivo)
				|| !MotorPagosHelper.isValid(this.pin)
				|| !MotorPagosHelper.isValid(this.direccion)){
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
		StringBuffer bf = new StringBuffer(420);
		bf.append("Rut : ");
		bf.append(this.rut);
		bf.append(", LlaveCuenta : ");
		bf.append(this.llaveCuenta);
		bf.append(", IdApp : ");
		bf.append(this.idApp);
		bf.append(", Nombre : ");
		bf.append(this.nombre);
		bf.append(", Marca : ");
		bf.append(this.marca);
		bf.append(", Mail : ");
		bf.append(this.mail);
		bf.append(", Dirección : ");
		bf.append(this.direccion);		
		bf.append(", AliasDispositivo : ");
		bf.append(this.aliasDispositivo);
		bf.append(", ModeloDispositivo : ");
		bf.append(this.modeloDispositivo);
		bf.append(", SerieDispSeguridad : ");
		bf.append(this.serieDispSeguridad);
		bf.append(", TipoDispSeguridad : ");
		bf.append(this.tipoDispSeguridad);
		
		return bf.toString();
	}
	
}
