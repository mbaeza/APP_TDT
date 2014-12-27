/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

import cl.bch.motorpagos.util.MotorPagosHelper;

/**
 * @author fahumadaa
 *
 */
public class ObtenerDatosFichaChicaVO implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3835270087021458107L;
	private String rut;
	
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
	 * 
	 * @return
	 */
	public boolean validaParametros(){
		if(!MotorPagosHelper.isValid(this.rut)
				){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(90);
		bf.append("Rut : ");
		bf.append(this.rut);
		
		return bf.toString();
	}
}
