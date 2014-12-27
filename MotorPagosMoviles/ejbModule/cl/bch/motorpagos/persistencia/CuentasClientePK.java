package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cuentas_cliente database table.
 * 
 */
@Embeddable
public class CuentasClientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String idCuentas;

	@Column(insertable=false, updatable=false)
	private String idCliente;

	public CuentasClientePK() {
		super();
	}
	public String getIdCuentas() {
		return this.idCuentas;
	}
	public void setIdCuentas(String idCuentas) {
		this.idCuentas = idCuentas;
	}
	public String getIdCliente() {
		return this.idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CuentasClientePK)) {
			return false;
		}
		CuentasClientePK castOther = (CuentasClientePK)other;
		return 
			this.idCuentas.equals(castOther.idCuentas)
			&& this.idCliente.equals(castOther.idCliente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCuentas.hashCode();
		hash = hash * prime + this.idCliente.hashCode();
		
		return hash;
	}
}