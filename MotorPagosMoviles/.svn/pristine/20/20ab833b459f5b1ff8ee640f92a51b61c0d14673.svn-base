/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author boyanedel
 *
 */
public class ConveniosComercioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6451106736774641452L;
	ArrayList<ConvenioVO> convenios;	
	/**
	 * @return the convenios
	 */
	public ArrayList<ConvenioVO> getConvenios() {
		return convenios;
	}
	/**
	 * @param convenios the convenios to set
	 */
	public void setConvenios(ArrayList<ConvenioVO> convenios) {
		this.convenios = convenios;
	}
	@Override
	public String toString() {
		
		StringBuffer bf = new StringBuffer(30);
		
		if(this.convenios!=null){
			for(int i=0; i<this.convenios.size(); i++){
				bf.append(this.convenios.get(i).toString());
				bf.append(", ");
			}
		}
		
		return bf.toString();
	}

}
