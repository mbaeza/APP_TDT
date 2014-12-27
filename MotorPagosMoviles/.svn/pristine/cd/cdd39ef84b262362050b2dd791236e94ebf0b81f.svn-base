package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cuentas_convenio database table.
 * 
 */
@Embeddable
public class CuentasConvenioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String idCuentas;

	@Column(insertable=false, updatable=false)
	private String idConvenio;

	public CuentasConvenioPK() {
		super();
	}
	public String getIdCuentas() {
		return this.idCuentas;
	}
	public void setIdCuentas(String idCuentas) {
		this.idCuentas = idCuentas;
	}
	public String getIdConvenio() {
		return this.idConvenio;
	}
	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CuentasConvenioPK)) {
			return false;
		}
		CuentasConvenioPK castOther = (CuentasConvenioPK)other;
		return 
			this.idCuentas.equals(castOther.idCuentas)
			&& this.idConvenio.equals(castOther.idConvenio);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCuentas.hashCode();
		hash = hash * prime + this.idConvenio.hashCode();
		
		return hash;
	}
}