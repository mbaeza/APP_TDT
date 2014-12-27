/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class TallerDeProductoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4942674384755132544L;
	private String codProductoCargo;
	private String codTrxCargo;
	private String codExtCargo;
	private String codProductoAbono;
	private String codTrxAbono;
	private String codExtAbono;
	/**
	 * @return the codProductoCargo
	 */
	public String getCodProductoCargo() {
		return codProductoCargo;
	}
	/**
	 * @param codProductoCargo the codProductoCargo to set
	 */
	public void setCodProductoCargo(String codProductoCargo) {
		this.codProductoCargo = codProductoCargo;
	}
	/**
	 * @return the codTrxCargo
	 */
	public String getCodTrxCargo() {
		return codTrxCargo;
	}
	/**
	 * @param codTrxCargo the codTrxCargo to set
	 */
	public void setCodTrxCargo(String codTrxCargo) {
		this.codTrxCargo = codTrxCargo;
	}
	/**
	 * @return the codExtCargo
	 */
	public String getCodExtCargo() {
		return codExtCargo;
	}
	/**
	 * @param codExtCargo the codExtCargo to set
	 */
	public void setCodExtCargo(String codExtCargo) {
		this.codExtCargo = codExtCargo;
	}
	/**
	 * @return the codProductoAbono
	 */
	public String getCodProductoAbono() {
		return codProductoAbono;
	}
	/**
	 * @param codProductoAbono the codProductoAbono to set
	 */
	public void setCodProductoAbono(String codProductoAbono) {
		this.codProductoAbono = codProductoAbono;
	}
	/**
	 * @return the codTrxAbono
	 */
	public String getCodTrxAbono() {
		return codTrxAbono;
	}
	/**
	 * @param codTrxAbono the codTrxAbono to set
	 */
	public void setCodTrxAbono(String codTrxAbono) {
		this.codTrxAbono = codTrxAbono;
	}
	/**
	 * @return the codExtAbono
	 */
	public String getCodExtAbono() {
		return codExtAbono;
	}
	/**
	 * @param codExtAbono the codExtAbono to set
	 */
	public void setCodExtAbono(String codExtAbono) {
		this.codExtAbono = codExtAbono;
	}
	
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(600);
		
		bf.append("codProductoCargo : ");
        bf.append(this.codProductoCargo);
		bf.append(", codTrxCargo : ");
		bf.append(this.codTrxCargo);
		bf.append(", codExtCargo : ");
		bf.append(this.codExtCargo);
		bf.append(", codProductoAbono : ");
		bf.append(this.codProductoAbono);
		bf.append(", codTrxAbono : ");
		bf.append(this.codTrxAbono);
		bf.append(", codExtAbono : ");
		bf.append(this.codExtAbono);
		
		return bf.toString();
	}

}
