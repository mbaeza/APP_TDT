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
public class MovimientosConvenioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3913713074524375245L;
	ArrayList<MovimientoVO> movimiento;

	/**
	 * @return the movimiento
	 */
	public ArrayList<MovimientoVO> getMovimiento() {
		return movimiento;
	}

	/**
	 * @param movimiento the movimiento to set
	 */
	public void setMovimiento(ArrayList<MovimientoVO> movimiento) {
		this.movimiento = movimiento;
	}
	
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(30);
		
		if(this.movimiento!=null && this.movimiento.size()>0){
			int i=0;
			for(MovimientoVO movimiento:this.movimiento){
				bf.append("Movimiento[");
				bf.append(i);
				bf.append("]");
				bf.append(movimiento.toString());
				bf.append(", ");
			}
		}
		
		return bf.toString();
	}

}
