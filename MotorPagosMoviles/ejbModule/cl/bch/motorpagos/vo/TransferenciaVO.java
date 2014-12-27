/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class TransferenciaVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1126212251669145513L;
	private String idCliente;
	private String idDispositivoCliente;
	private String claveOperacion;
	private String rutClienteOrigen;
	private String tokenCtaOrigen;
	private String tokenCtaDestino;
	private String ctaOrigen;
	private String ctaDestino;
	private String monto;
	private RespuestaVO retorno;
	private String horaCobro;
	private int limiteCuentaOrigen;
	
	private String mailCliente;	
	private String nombreCliente;
	private String nombreComercio;
	private String rutComercio;
	private String nombreConvenio;
	private String idVendedor;
	private String glosaTrx;
	private String fechaHoraTrx;
	private String tipoComercio;
	private String mailComercio;
	
	private String fechaContable;
	private TallerDeProductoVO taller;
	private String tipoTransferencia;
	
	/**
	 * @return the claveOperacion
	 */
	public String getClaveOperacion() {
		return claveOperacion;
	}
	/**
	 * @param claveOperacion the claveOperacion to set
	 */
	public void setClaveOperacion(String claveOperacion) {
		this.claveOperacion = claveOperacion;
	}
	/**
	 * @return the rutClienteOrigen
	 */
	public String getRutClienteOrigen() {
		return rutClienteOrigen;
	}
	/**
	 * @param rutClienteOrigen the rutClienteOrigen to set
	 */
	public void setRutClienteOrigen(String rutClienteOrigen) {
		this.rutClienteOrigen = rutClienteOrigen.toUpperCase();
	}
	/**
	 * @return the tokenCtaOrigen
	 */
	public String getTokenCtaOrigen() {
		return tokenCtaOrigen;
	}
	/**
	 * @param tokenCtaOrigen the tokenCtaOrigen to set
	 */
	public void setTokenCtaOrigen(String tokenCtaOrigen) {
		this.tokenCtaOrigen = tokenCtaOrigen;
	}
	/**
	 * @return the tokenCtaDestino
	 */
	public String getTokenCtaDestino() {
		return tokenCtaDestino;
	}
	/**
	 * @param tokenCtaDestino the tokenCtaDestino to set
	 */
	public void setTokenCtaDestino(String tokenCtaDestino) {
		this.tokenCtaDestino = tokenCtaDestino;
	}
	/**
	 * @return the ctaOrigen
	 */
	public String getCtaOrigen() {
		return ctaOrigen;
	}
	/**
	 * @param ctaOrigen the ctaOrigen to set
	 */
	public void setCtaOrigen(String ctaOrigen) {
		this.ctaOrigen = ctaOrigen;
	}
	/**
	 * @return the ctaDestino
	 */
	public String getCtaDestino() {
		return ctaDestino;
	}
	/**
	 * @param ctaDestino the ctaDestino to set
	 */
	public void setCtaDestino(String ctaDestino) {
		this.ctaDestino = ctaDestino;
	}
	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}	
	/**
	 * @return the retorno
	 */
	public RespuestaVO getRetorno() {
		return retorno;
	}
	/**
	 * @param retorno the retorno to set
	 */
	public void setRetorno(RespuestaVO retorno) {
		this.retorno = retorno;
	}	
	/**
	 * @return the idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return the idDispositivoCliente
	 */
	public String getIdDispositivoCliente() {
		return idDispositivoCliente;
	}
	/**
	 * @param idDispositivoCliente the idDispositivoCliente to set
	 */
	public void setIdDispositivoCliente(String idDispositivoCliente) {
		this.idDispositivoCliente = idDispositivoCliente;
	}	
	/**
	 * @return the horaCobro
	 */
	public String getHoraCobro() {
		return horaCobro;
	}
	/**
	 * @param horaCobro the horaCobro to set
	 */
	public void setHoraCobro(String horaCobro) {
		this.horaCobro = horaCobro;
	}	
	/**
	 * @return the mailCliente
	 */
	public String getMailCliente() {
		return mailCliente;
	}
	/**
	 * @param mailCliente the mailCliente to set
	 */
	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}	
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}	
	/**
	 * @return the nombreComercio
	 */
	public String getNombreComercio() {
		return nombreComercio;
	}
	/**
	 * @param nombreComercio the nombreComercio to set
	 */
	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}	
	/**
	 * @return the rutComercio
	 */
	public String getRutComercio() {
		return rutComercio;
	}
	/**
	 * @param rutComercio the rutComercio to set
	 */
	public void setRutComercio(String rutComercio) {
		this.rutComercio = rutComercio.toUpperCase();
	}
	/**
	 * @return the nombreConvenio
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	/**
	 * @param nombreConvenio the nombreConvenio to set
	 */
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
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
	 * @return the limiteCuentaOrigen
	 */
	public int getLimiteCuentaOrigen() {
		return limiteCuentaOrigen;
	}
	/**
	 * @param limiteCuentaOrigen the limiteCuentaOrigen to set
	 */
	public void setLimiteCuentaOrigen(int limiteCuentaOrigen) {
		this.limiteCuentaOrigen = limiteCuentaOrigen;
	}
	/**
	 * @return the tipoComercio
	 */
	public String getTipoComercio() {
		return tipoComercio;
	}
	/**
	 * @param tipoComercio the tipoComercio to set
	 */
	public void setTipoComercio(String tipoComercio) {
		this.tipoComercio = tipoComercio;
	}
	/**
	 * @return the fechaContable
	 */
	public String getFechaContable() {
		return fechaContable;
	}
	/**
	 * @param fechaContable the fechaContable to set
	 */
	public void setFechaContable(String fechaContable) {
		this.fechaContable = fechaContable;
	}
	/**
	 * @return the taller
	 */
	public TallerDeProductoVO getTaller() {
		return taller;
	}
	/**
	 * @param taller the taller to set
	 */
	public void setTaller(TallerDeProductoVO taller) {
		this.taller = taller;
	}
	/**
	 * @return the tipoTransferencia
	 */
	public String getTipoTransferencia() {
		return tipoTransferencia;
	}
	/**
	 * @param tipoTransferencia the tipoTransferencia to set
	 */
	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}
	/**
	 * @return the mailComercio
	 */
	public String getMailComercio() {
		return mailComercio;
	}
	/**
	 * @param mailComercio the mailComercio to set
	 */
	public void setMailComercio(String mailComercio) {
		this.mailComercio = mailComercio;
	}
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer(600);
		
		bf.append(this.retorno.toString());
        bf.append("ClaveOperacion : ");
        bf.append(this.claveOperacion);
		bf.append(", RutClienteOrigen : ");
		bf.append(this.rutClienteOrigen);
		bf.append(", CtaOrigen : ");
		bf.append(this.ctaOrigen);
		bf.append(", TokenCtaOrigen : ");
		bf.append(this.tokenCtaOrigen);
		bf.append(", CtaDestino : ");
		bf.append(this.ctaDestino);
		bf.append(", TokenCtaDestino : ");
		bf.append(this.tokenCtaDestino);		
		bf.append(", Monto : ");
		bf.append(this.monto);
		bf.append(", idCliente : ");
		bf.append(this.idCliente);
		bf.append(", idDispositivoCliente : ");
		bf.append(this.idDispositivoCliente);
		bf.append(", limiteCuentaOrigen : ");
		bf.append(this.limiteCuentaOrigen);
		bf.append(", horaCobro : ");
		bf.append(this.horaCobro);
		bf.append(", mailCliente : ");
		bf.append(this.mailCliente);
		bf.append(", nombreCliente : ");
		bf.append(this.nombreCliente);
		bf.append(", nombreComercio : ");
		bf.append(this.nombreComercio);
		bf.append(", rutComercio : ");
		bf.append(this.rutComercio);
		bf.append(", nombreConvenio : ");
		bf.append(this.nombreConvenio);
		bf.append(", idVendedor : ");
		bf.append(this.idVendedor);
		bf.append(", glosaTrx : ");
		bf.append(this.glosaTrx);
		bf.append(", fechaHoraTrx : ");
		bf.append(this.fechaHoraTrx);
		bf.append(", tipoComercio : ");
		bf.append(this.tipoComercio);
		bf.append(", fechaContable : ");
		bf.append(this.fechaContable);
		bf.append(", tipoTransferencia : ");
		bf.append(this.tipoTransferencia);
		bf.append(", mailComercio : ");
		bf.append(this.mailComercio);
		
		if(this.taller!=null){
			bf.append(", Taller de Producto: ");
			bf.append(this.taller.toString());
		}
		
		return bf.toString();
	}

}
