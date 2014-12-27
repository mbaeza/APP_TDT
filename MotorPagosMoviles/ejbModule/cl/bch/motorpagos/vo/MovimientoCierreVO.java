package cl.bch.motorpagos.vo;

import java.io.Serializable;

public class MovimientoCierreVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2786626559148345974L;
	private String fechaHoraTrx;
	private String glosaTrx;
	private int montoTrx;
	/**
	 * @return the fechaHoraTrx
	 */
	public String getFechaHoraTrx() {
		return fechaHoraTrx;
	}
	/**
	 * @param fechaHoraTrx the fechaHoraTrx to set
	 */
	public void setFechaHoraTrx(String fechaHoraTrx) {
		this.fechaHoraTrx = fechaHoraTrx;
	}
	/**
	 * @return the glosaTrx
	 */
	public String getGlosaTrx() {
		return glosaTrx;
	}
	/**
	 * @param glosaTrx the glosaTrx to set
	 */
	public void setGlosaTrx(String glosaTrx) {
		this.glosaTrx = glosaTrx;
	}
	/**
	 * @return the montoTrx
	 */
	public int getMontoTrx() {
		return montoTrx;
	}
	/**
	 * @param montoTrx the montoTrx to set
	 */
	public void setMontoTrx(int montoTrx) {
		this.montoTrx = montoTrx;
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(90);
		
		bf.append("FechaHoraTrx : ");
		bf.append(this.fechaHoraTrx);
		bf.append(", MontoTrx : ");
		bf.append(this.montoTrx);
		bf.append(", GlosaTrx : ");
		bf.append(this.glosaTrx);
				
		return bf.toString();
	}
}
