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
public class ActualizarMedioDePagoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2221461446922409503L;
	private String idCliente;
	private String idConvenio;
	private String llaveCuenta;
	private String pin;
	private String idDispositivo;
	private int limiteCuenta;
	
	private String serieDispSeguridad;
	private String tipoDispSeguridad;
	private String claveSeguridad;
	private String coordenada1;
	private String coordenada2;
	private String coordenada3;
	
	/**
	 * @return the idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
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
	 * @return the limiteCuenta
	 */
	public int getLimiteCuenta() {
		return limiteCuenta;
	}
	/**
	 * @param limiteCuenta the limiteCuenta to set
	 */
	public void setLimiteCuenta(int limiteCuenta) {
		this.limiteCuenta = limiteCuenta;
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
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.idCliente)
				|| !MotorPagosHelper.isValid(this.idDispositivo)
				|| !MotorPagosHelper.isValid(this.llaveCuenta)
				|| !MotorPagosHelper.isValid(this.pin)
				|| !MotorPagosHelper.isValid(this.idConvenio)){
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
		StringBuffer bf = new StringBuffer(330);
		bf.append("idCliente : ");
		bf.append(this.idCliente);
		bf.append("idDispositivo : ");
		bf.append(this.idDispositivo);
		bf.append(", LlaveCuenta : ");
		bf.append(this.llaveCuenta);
		bf.append(", limiteCuenta : ");
		bf.append(this.limiteCuenta);
		bf.append(", SerieDispSeguridad : ");
		bf.append(this.serieDispSeguridad);
		bf.append(", TipoDispSeguridad : ");
		bf.append(this.tipoDispSeguridad);
				
		return bf.toString();
	}
}
