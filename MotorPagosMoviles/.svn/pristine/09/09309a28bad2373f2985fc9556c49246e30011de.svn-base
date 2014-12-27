package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the VERSIONES_APPS database table.
 * 
 */
@Entity
@Table(name="VERSIONES_APPS")
@NamedQuery(name="VersionesApp.findAll", query="SELECT v FROM VersionesApp v")
public class VersionesApp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VersionesAppPK id;

	private String estado;

	public VersionesApp() {
	}

	public VersionesAppPK getId() {
		return this.id;
	}

	public void setId(VersionesAppPK id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}