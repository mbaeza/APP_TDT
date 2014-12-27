/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class DispositivoSeguridadVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4210639595002696247L;
	
	private String serie;
	private String tipo;
	private CoordenadasDispositivoVO coordenadas; 
	
	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}
	/**
	 * @param serie the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return the coordenadas
	 */
	public CoordenadasDispositivoVO getCoordenadas() {
		return coordenadas;
	}
	/**
	 * @param coordenadas the coordenadas to set
	 */
	public void setCoordenadas(CoordenadasDispositivoVO coordenadas) {
		this.coordenadas = coordenadas;
	}
	@Override
	public String toString() {
		
		StringBuffer bf = new StringBuffer(90);
		bf.append("Serie : ");
		bf.append(this.serie);
		bf.append(", Tipo : ");
		bf.append(this.tipo);
		bf.append(", Coordenadas : ");
		
		if(this.coordenadas!=null){
			bf.append(this.coordenadas.toString());
		}
		
		return bf.toString();
	}
}
