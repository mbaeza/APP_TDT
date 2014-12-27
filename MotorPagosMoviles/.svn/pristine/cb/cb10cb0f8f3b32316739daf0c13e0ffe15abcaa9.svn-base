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
public class MovimientosDispositivoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6921185831712220183L;
	private ArrayList<MovimientoCierreVO> movimiento;
	private String idVendedor;
	private int montoAcumulado;
	private int numeroVentas;
	private int propinaAcumulada;
	private int subTotalAcumulado;
	/**
	 * @return the movimiento
	 */
	public ArrayList<MovimientoCierreVO> getMovimiento() {
		return movimiento;
	}
	/**
	 * @param movimiento the movimiento to set
	 */
	public void setMovimiento(ArrayList<MovimientoCierreVO> movimiento) {
		this.movimiento = movimiento;
	}
	/**
	 * @return the idVendedor
	 */
	public String getIdVendedor() {
		return idVendedor;
	}
	/**
	 * @param idVendedor the idVendedor to set
	 */
	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}
	/**
	 * @return the montoAcumulado
	 */
	public int getMontoAcumulado() {
		return montoAcumulado;
	}
	/**
	 * @param montoAcumulado the montoAcumulado to set
	 */
	public void setMontoAcumulado(int montoAcumulado) {
		this.montoAcumulado = montoAcumulado;
	}
	/**
	 * @return the numeroVentas
	 */
	public int getNumeroVentas() {
		return numeroVentas;
	}
	/**
	 * @param numeroVentas the numeroVentas to set
	 */
	public void setNumeroVentas(int numeroVentas) {
		this.numeroVentas = numeroVentas;
	}
	/**
	 * @return the propinaAcumulada
	 */
	public int getPropinaAcumulada() {
		return propinaAcumulada;
	}
	/**
	 * @param propinaAcumulada the propinaAcumulada to set
	 */
	public void setPropinaAcumulada(int propinaAcumulada) {
		this.propinaAcumulada = propinaAcumulada;
	}
	/**
	 * @return the subTotalAcumulado
	 */
	public int getSubTotalAcumulado() {
		return subTotalAcumulado;
	}
	/**
	 * @param subTotalAcumulado the subTotalAcumulado to set
	 */
	public void setSubTotalAcumulado(int subTotalAcumulado) {
		this.subTotalAcumulado = subTotalAcumulado;
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(120);
		
		bf.append("IdVendedor : ");
		bf.append(this.idVendedor);
		bf.append(", PropinaAcumulada : ");
		bf.append(this.propinaAcumulada);
		bf.append(", SubTotalAcumulado : ");
		bf.append(this.subTotalAcumulado);
		bf.append(", MontoAcumulado : ");
		bf.append(this.montoAcumulado);
		bf.append(", NumeroVentas : ");
		bf.append(this.numeroVentas);
		
		if(this.movimiento!=null && this.movimiento.size()>0){
			for(MovimientoCierreVO movimiento:this.movimiento){
				bf.append(movimiento.toString());
				bf.append(", ");
			}
		}
		
		return bf.toString();
	}
}
