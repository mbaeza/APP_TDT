/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class FilialVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8083897880767635921L;
	private String rutFilial;
	private String nombreFilial;
	/**
	 * @return the rutFilial
	 */
	public String getRutFilial() {
		return rutFilial;
	}
	/**
	 * @param rutFilial the rutFilial to set
	 */
	public void setRutFilial(String rutFilial) {
		this.rutFilial = rutFilial;
	}
	/**
	 * @return the nombrefilial
	 */
	public String getNombreFilial() {
		return nombreFilial;
	}
	/**
	 * @param nombrefilial the nombreFilial to set
	 */
	public void setNombreFilial(String nombreFilial) {
		this.nombreFilial = nombreFilial;
	}
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(120);
		bf.append("RutFilial : ");
		bf.append(this.rutFilial);
		bf.append(", NombreFilial : ");
		bf.append(this.nombreFilial);	
		
		return bf.toString();
	}
	
}
