package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cuentas_cliente database table.
 * 
 */
@Entity
@Table(name="cuentas_cliente")
@NamedQuery(name="CuentasCliente.findAll", query="SELECT c FROM CuentasCliente c")
public class CuentasCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CuentasClientePK id;

	public CuentasCliente() {
		super();
	}

	public CuentasClientePK getId() {
		return this.id;
	}

	public void setId(CuentasClientePK id) {
		this.id = id;
	}

}