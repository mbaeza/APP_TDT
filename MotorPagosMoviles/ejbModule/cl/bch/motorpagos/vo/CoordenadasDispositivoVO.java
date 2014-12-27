/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class CoordenadasDispositivoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3516624404122105780L;
	private String coordenada1;
	private String coordenada2;
	private String coordenada3;
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
	@Override
	public String toString() {
		
		StringBuffer bf = new StringBuffer(90);
		bf.append("Coordenada1 : ");
		bf.append(this.coordenada1);
		bf.append(", Coordenada2 : ");
		bf.append(this.coordenada2);
		bf.append(", Coordenada3 : ");
		bf.append(this.coordenada3);
		
		return bf.toString();
	}
}
