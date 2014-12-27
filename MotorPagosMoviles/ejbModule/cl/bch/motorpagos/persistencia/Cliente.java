package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idCliente;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHoraActivacion;

	private String mailcliente;
	
	private String rutCliente;

	private String nombreCliente;	
	
	private String codigoMarca;

	//bi-directional many-to-one association to TrxsPago
	@OneToMany(mappedBy="cliente")
	private List<TrxsPago> trxsPagos;

	public Cliente() {
		super();
	}

	public String getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaHoraActivacion() {
		return this.fechaHoraActivacion;
	}

	public void setFechaHoraActivacion(Date fechaHoraActivacion) {
		this.fechaHoraActivacion = fechaHoraActivacion;
	}

	public String getMailcliente() {
		return this.mailcliente;
	}

	public void setMailcliente(String mailcliente) {
		this.mailcliente = mailcliente;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public List<TrxsPago> getTrxsPagos() {
		return this.trxsPagos;
	}

	public void setTrxsPagos(List<TrxsPago> trxsPagos) {
		this.trxsPagos = trxsPagos;
	}
	
	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public TrxsPago addTrxsPago(TrxsPago trxsPago) {
		getTrxsPagos().add(trxsPago);
		trxsPago.setCliente(this);

		return trxsPago;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public TrxsPago removeTrxsPago(TrxsPago trxsPago) {
		getTrxsPagos().remove(trxsPago);
		trxsPago.setCliente(null);

		return trxsPago;
	}
}