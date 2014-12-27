/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class CodigoProductoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -981920347695579795L;
	
	private String idProducto;

	/**
	 * @return the idProducto
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
	
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(30);
		bf.append("IdProducto : ");
		bf.append(this.idProducto);
		
		return bf.toString();
	}
}
