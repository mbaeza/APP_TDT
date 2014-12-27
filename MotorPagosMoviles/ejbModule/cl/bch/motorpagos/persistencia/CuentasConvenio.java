package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cuentas_convenio database table.
 * 
 */
@Entity
@Table(name="cuentas_convenio")
@NamedQuery(name="CuentasConvenio.findAll", query="SELECT c FROM CuentasConvenio c")
public class CuentasConvenio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CuentasConvenioPK id;

	public CuentasConvenio() {
		super();
	}

	public CuentasConvenioPK getId() {
		return this.id;
	}

	public void setId(CuentasConvenioPK id) {
		this.id = id;
	}

}