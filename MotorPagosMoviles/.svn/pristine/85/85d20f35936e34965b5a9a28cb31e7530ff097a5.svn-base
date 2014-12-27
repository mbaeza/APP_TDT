package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the trxs_pago database table.
 * 
 */
@Entity
@Table(name="trxs_pago")
@NamedQuery(name="TrxsPago.findAll", query="SELECT t FROM TrxsPago t")
public class TrxsPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idTrxsPago;

	private String estadoTrxl;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHoraTrx;

	private String glosaTrx;

	private String idDispositivoCliente;

	private String idDispositivoComercio;

	private String idVendedor;

	private int montoTrx;
	
	private int propinaTrx;
	
	private int subTotalTrx;

	private String nroCuentaCliente;

	private String nroCuentaComercio;

	private String tipoCuentaCliente;

	private String tipoCuentaComercio;
	
	private String horaCobro;
	
	private String idTrxInterno;
	
	private String llaveComercio;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idCliente", referencedColumnName="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Convenio
	@ManyToOne
	@JoinColumn(name="idConvenio")
	private Convenio convenio;

	public TrxsPago() {
		super();
	}

	public String getIdTrxsPago() {
		return this.idTrxsPago;
	}

	public void setIdTrxsPago(String idTrxsPago) {
		this.idTrxsPago = idTrxsPago;
	}

	public String getEstadoTrxl() {
		return this.estadoTrxl;
	}

	public void setEstadoTrxl(String estadoTrxl) {
		this.estadoTrxl = estadoTrxl;
	}

	public Date getFechaHoraTrx() {
		return this.fechaHoraTrx;
	}

	public void setFechaHoraTrx(Date fechaHoraTrx) {
		this.fechaHoraTrx = fechaHoraTrx;
	}

	public String getGlosaTrx() {
		return this.glosaTrx;
	}

	public void setGlosaTrx(String glosaTrx) {
		this.glosaTrx = glosaTrx;
	}

	public String getIdDispositivoCliente() {
		return this.idDispositivoCliente;
	}

	public void setIdDispositivoCliente(String idDispositivoCliente) {
		this.idDispositivoCliente = idDispositivoCliente;
	}

	public String getIdDispositivoComercio() {
		return this.idDispositivoComercio;
	}

	public void setIdDispositivoComercio(String idDispositivoComercio) {
		this.idDispositivoComercio = idDispositivoComercio;
	}

	public String getIdVendedor() {
		return this.idVendedor;
	}

	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}

	public int getMontoTrx() {
		return this.montoTrx;
	}

	public void setMontoTrx(int montoTrx) {
		this.montoTrx = montoTrx;
	}

	public String getNroCuentaCliente() {
		return this.nroCuentaCliente;
	}

	public void setNroCuentaCliente(String nroCuentaCliente) {
		this.nroCuentaCliente = nroCuentaCliente;
	}

	public String getNroCuentaComercio() {
		return this.nroCuentaComercio;
	}

	public void setNroCuentaComercio(String nroCuentaComercio) {
		this.nroCuentaComercio = nroCuentaComercio;
	}

	public String getTipoCuentaCliente() {
		return this.tipoCuentaCliente;
	}

	public void setTipoCuentaCliente(String tipoCuentaCliente) {
		this.tipoCuentaCliente = tipoCuentaCliente;
	}

	public String getTipoCuentaComercio() {
		return this.tipoCuentaComercio;
	}

	public void setTipoCuentaComercio(String tipoCuentaComercio) {
		this.tipoCuentaComercio = tipoCuentaComercio;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Convenio getConvenio() {
		return this.convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public String getHoraCobro() {
		return horaCobro;
	}

	public void setHoraCobro(String horaCobro) {
		this.horaCobro = horaCobro;
	}

	public String getIdTrxInterno() {
		return idTrxInterno;
	}

	public void setIdTrxInterno(String idTrxInterno) {
		this.idTrxInterno = idTrxInterno;
	}

	public String getLlaveComercio() {
		return llaveComercio;
	}

	public void setLlaveComercio(String llaveComercio) {
		this.llaveComercio = llaveComercio;
	}

	public int getPropinaTrx() {
		return propinaTrx;
	}

	public void setPropinaTrx(int propinaTrx) {
		this.propinaTrx = propinaTrx;
	}

	public int getSubTotalTrx() {
		return subTotalTrx;
	}

	public void setSubTotalTrx(int subTotalTrx) {
		this.subTotalTrx = subTotalTrx;
	}
	
	
}