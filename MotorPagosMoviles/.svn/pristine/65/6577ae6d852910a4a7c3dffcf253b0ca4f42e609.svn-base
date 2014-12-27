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
public class AtribucionesComercioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7109393131541808806L;
	private String rutEmpresa;
	private String rutPersona;
	private String llaveCuenta;
	
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
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.rutEmpresa)
				|| !MotorPagosHelper.isValid(this.rutPersona)
				|| !MotorPagosHelper.isValid(this.llaveCuenta)){
			return false;
		}else{
			return true;
		}
	}	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(90);
		bf.append("RutEmpresa : ");
		bf.append(this.rutEmpresa);
		bf.append(", RutPersona : ");
		bf.append(this.rutPersona);
		bf.append(", LlaveCuenta : ");
		bf.append(this.llaveCuenta);
		
		return bf.toString();
	}
}
