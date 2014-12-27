package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dispositivos_convenio database table.
 * 
 */
@Embeddable
public class DispositivosConvenioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String idDispositivo;

	@Column(insertable=false, updatable=false)
	private String idConvenio;

	public DispositivosConvenioPK() {
		super();
	}
	public String getIdDispositivo() {
		return this.idDispositivo;
	}
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
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
		if (!(other instanceof DispositivosConvenioPK)) {
			return false;
		}
		DispositivosConvenioPK castOther = (DispositivosConvenioPK)other;
		return 
			this.idDispositivo.equals(castOther.idDispositivo)
			&& this.idConvenio.equals(castOther.idConvenio);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idDispositivo.hashCode();
		hash = hash * prime + this.idConvenio.hashCode();
		
		return hash;
	}
}