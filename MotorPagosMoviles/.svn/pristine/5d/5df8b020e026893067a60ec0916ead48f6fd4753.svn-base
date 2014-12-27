package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dispositivos_cliente database table.
 * 
 */
@Entity
@Table(name="dispositivos_cliente")
@NamedQuery(name="DispositivosCliente.findAll", query="SELECT d FROM DispositivosCliente d")
public class DispositivosCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idCuenta;
	
	@EmbeddedId
	private DispositivosClientePK id;

	public DispositivosCliente() {
		super();
	}

	public DispositivosClientePK getId() {
		return this.id;
	}

	public void setId(DispositivosClientePK id) {
		this.id = id;
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	
}