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
public class DispositivosClienteVO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7010199598929091564L;
	
	private ArrayList<DispositivoSeguridadVO> dispSeguridad;

	/**
	 * @return the dispSeguridad
	 */
	public ArrayList<DispositivoSeguridadVO> getDispSeguridad() {
		return dispSeguridad;
	}
	/**
	 * @param dispSeguridad the dispSeguridad to set
	 */
	public void setDispSeguridad(ArrayList<DispositivoSeguridadVO> dispSeguridad) {
		this.dispSeguridad = dispSeguridad;
	}
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(30);
		
		if(this.dispSeguridad!=null){
			for(int i=0;i<this.dispSeguridad.size();i++){
				bf.append("Dispositivos[");
				bf.append(i);
				bf.append(']');
				bf.append(this.dispSeguridad.get(i).toString());
				bf.append(',');
			}
		}
		return bf.toString();
	}
}
