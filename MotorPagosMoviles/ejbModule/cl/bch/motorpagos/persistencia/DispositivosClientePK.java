package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dispositivos_cliente database table.
 * 
 */
@Embeddable
public class DispositivosClientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String idCliente;

	@Column(insertable=false, updatable=false)
	private String idDispositivo;

	public DispositivosClientePK() {
		super();
	}
	public String getIdCliente() {
		return this.idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getIdDispositivo() {
		return this.idDispositivo;
	}
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DispositivosClientePK)) {
			return false;
		}
		DispositivosClientePK castOther = (DispositivosClientePK)other;
		return 
			this.idCliente.equals(castOther.idCliente)
			&& this.idDispositivo.equals(castOther.idDispositivo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCliente.hashCode();
		hash = hash * prime + this.idDispositivo.hashCode();
		
		return hash;
	}
}