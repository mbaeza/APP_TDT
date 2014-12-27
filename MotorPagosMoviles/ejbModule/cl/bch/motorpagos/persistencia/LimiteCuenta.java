package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the limite_cuenta database table.
 * 
 */
@Entity
@Table(name="limite_cuenta")
@NamedQuery(name="LimiteCuenta.findAll", query="SELECT l FROM LimiteCuenta l")
public class LimiteCuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LimiteCuentaPK id;

	private int montoAcumulado;

	public LimiteCuenta() {
		super();
	}

	public LimiteCuentaPK getId() {
		return this.id;
	}

	public void setId(LimiteCuentaPK id) {
		this.id = id;
	}

	public int getMontoAcumulado() {
		return this.montoAcumulado;
	}

	public void setMontoAcumulado(int montoAcumulado) {
		this.montoAcumulado = montoAcumulado;
	}

}