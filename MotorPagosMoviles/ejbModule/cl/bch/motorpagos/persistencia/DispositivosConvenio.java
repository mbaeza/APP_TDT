package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dispositivos_convenio database table.
 * 
 */
@Entity
@Table(name="dispositivos_convenio")
@NamedQuery(name="DispositivosConvenio.findAll", query="SELECT d FROM DispositivosConvenio d")
public class DispositivosConvenio implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idCuenta;
	
	@EmbeddedId
	private DispositivosConvenioPK id;

	public DispositivosConvenio() {
		super();
	}

	public DispositivosConvenioPK getId() {
		return this.id;
	}

	public void setId(DispositivosConvenioPK id) {
		this.id = id;
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
}