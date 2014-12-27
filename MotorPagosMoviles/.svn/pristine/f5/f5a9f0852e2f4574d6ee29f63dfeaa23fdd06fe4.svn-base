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
public class MovimientosVendedorVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9197911427931073915L;
	private ArrayList<MovimientosDispositivoVO> vendedor;

	/**
	 * @return the vendedor
	 */
	public ArrayList<MovimientosDispositivoVO> getVendedor() {
		return vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(ArrayList<MovimientosDispositivoVO> vendedor) {
		this.vendedor = vendedor;
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(30);
		
		if(this.vendedor!=null && this.vendedor.size()>0){
			for(MovimientosDispositivoVO movimiento:this.vendedor){
				bf.append(movimiento.toString());
				bf.append(", ");
			}
		}
		
		return bf.toString();
	}
}
