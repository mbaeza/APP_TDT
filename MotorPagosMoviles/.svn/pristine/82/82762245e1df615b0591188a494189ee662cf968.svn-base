package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the limite_cuenta database table.
 * 
 */
@Embeddable
public class LimiteCuentaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String nroCuenta;

	private String idCliente;

	@Temporal(TemporalType.DATE)
	private java.util.Date fechaTrx;

	public LimiteCuentaPK() {
		super();
	}
	public String getNroCuenta() {
		return this.nroCuenta;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public String getIdCliente() {
		return this.idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public java.util.Date getFechaTrx() {
		return this.fechaTrx;
	}
	public void setFechaTrx(java.util.Date fechaTrx) {
		this.fechaTrx = fechaTrx;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LimiteCuentaPK)) {
			return false;
		}
		LimiteCuentaPK castOther = (LimiteCuentaPK)other;
		return 
			this.nroCuenta.equals(castOther.nroCuenta)
			&& this.idCliente.equals(castOther.idCliente)
			&& this.fechaTrx.equals(castOther.fechaTrx);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nroCuenta.hashCode();
		hash = hash * prime + this.idCliente.hashCode();
		hash = hash * prime + this.fechaTrx.hashCode();
		
		return hash;
	}
}