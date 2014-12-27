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
public class FilialesEmpresaVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5302519888630720236L;
	private ArrayList<FilialVO> filial;

	/**
	 * @return the filial
	 */
	public ArrayList<FilialVO> getFilial() {
		return filial;
	}

	/**
	 * @param filial the filial to set
	 */
	public void setFilial(ArrayList<FilialVO> filial) {
		this.filial = filial;
	}

	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(30);
		
		if(this.filial!=null){
			for(int i=0;i<this.filial.size();i++){
				bf.append("Filial[");
				bf.append(i);
				bf.append(']');
				bf.append(this.filial.get(i).toString());
				bf.append(',');
			}
		}
		return bf.toString();
	}
	
}
