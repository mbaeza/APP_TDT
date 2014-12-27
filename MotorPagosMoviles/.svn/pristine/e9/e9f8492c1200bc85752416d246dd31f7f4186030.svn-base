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
public class CuentasClienteVO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2888598975738253540L;
	private ArrayList<CuentaVO> cuentasCliente;
	
	/**
	 * @return the cuentasCliente
	 */
	public ArrayList<CuentaVO> getCuentasCliente() {
		return cuentasCliente;
	}
	/**
	 * @param cuentasCliente the cuentasCliente to set
	 */
	public void setCuentasCliente(ArrayList<CuentaVO> cuentasCliente) {
		this.cuentasCliente = cuentasCliente;
	}
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(30);
		
		if(this.cuentasCliente!=null){
			for(int i=0;i<this.cuentasCliente.size();i++){
				bf.append("Cuenta[");
				bf.append(i);
				bf.append(']');
				bf.append(this.cuentasCliente.get(i).toString());
				bf.append(',');
			}
		}
		
		return bf.toString();
	}
}
