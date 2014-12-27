package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the convenios database table.
 * 
 */
@Entity
@Table(name="convenios")
@NamedQuery(name="Convenio.findAll", query="SELECT c FROM Convenio c")
public class Convenio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idConvenio;

	private String nombreConveniio;
	
	private int intentosFallidos;
	
	private String estadoConvenio;

	//bi-directional many-to-one association to Comercio
	@ManyToOne
	@JoinColumn(name="idComercio")
	private Comercio comercio;

	//bi-directional many-to-one association to TrxsPago
	@OneToMany(mappedBy="convenio")
	private List<TrxsPago> trxsPagos;

	public Convenio() {
		super();
	}

	public String getIdConvenio() {
		return this.idConvenio;
	}

	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}

	public String getNombreConveniio() {
		return this.nombreConveniio;
	}

	public void setNombreConveniio(String nombreConveniio) {
		this.nombreConveniio = nombreConveniio;
	}
	
	public Comercio getComercio() {
		return this.comercio;
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	public List<TrxsPago> getTrxsPagos() {
		return this.trxsPagos;
	}

	public void setTrxsPagos(List<TrxsPago> trxsPagos) {
		this.trxsPagos = trxsPagos;
	}

	
	public int getIntentosFallidos() {
		return intentosFallidos;
	}

	public void setIntentosFallidos(int intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}

	public String getEstadoConvenio() {
		return estadoConvenio;
	}

	public void setEstadoConvenio(String estadoConvenio) {
		this.estadoConvenio = estadoConvenio;
	}

	public TrxsPago addTrxsPago(TrxsPago trxsPago) {
		getTrxsPagos().add(trxsPago);
		trxsPago.setConvenio(this);

		return trxsPago;
	}

	public TrxsPago removeTrxsPago(TrxsPago trxsPago) {
		getTrxsPagos().remove(trxsPago);
		trxsPago.setConvenio(null);

		return trxsPago;
	}

}