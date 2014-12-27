package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the VERSIONES_APPS database table.
 * 
 */
@Embeddable
public class VersionesAppPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String idapp;

	private String nroversion;

	public VersionesAppPK() {
	}
	public String getIdapp() {
		return this.idapp;
	}
	public void setIdapp(String idapp) {
		this.idapp = idapp;
	}
	public String getNroversion() {
		return this.nroversion;
	}
	public void setNroversion(String nroversion) {
		this.nroversion = nroversion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VersionesAppPK)) {
			return false;
		}
		VersionesAppPK castOther = (VersionesAppPK)other;
		return 
			this.idapp.equals(castOther.idapp)
			&& this.nroversion.equals(castOther.nroversion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idapp.hashCode();
		hash = hash * prime + this.nroversion.hashCode();
		
		return hash;
	}
}