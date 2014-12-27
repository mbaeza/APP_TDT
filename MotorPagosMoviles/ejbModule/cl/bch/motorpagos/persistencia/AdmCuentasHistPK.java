package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the adm_cuentas_hist database table.
 * 
 */
@Embeddable
public class AdmCuentasHistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String idCuenta;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fechaUpdate;

	public AdmCuentasHistPK() {
		super();
	}
	public String getIdCuenta() {
		return this.idCuenta;
	}
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
	public java.util.Date getFechaUpdate() {
		return this.fechaUpdate;
	}
	public void setFechaUpdate(java.util.Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmCuentasHistPK)) {
			return false;
		}
		AdmCuentasHistPK castOther = (AdmCuentasHistPK)other;
		return 
			this.idCuenta.equals(castOther.idCuenta)
			&& this.fechaUpdate.equals(castOther.fechaUpdate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCuenta.hashCode();
		hash = hash * prime + this.fechaUpdate.hashCode();
		
		return hash;
	}
}