package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the comercios database table.
 * 
 */
@Entity
@Table(name="comercios")
@NamedQuery(name="Comercio.findAll", query="SELECT c FROM Comercio c")
public class Comercio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idComercio;

	private String direccionComercio;

	private String emailComercio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHoraActivacion;

	private String razonSocialComercio;

	private String rutComercio;
	
	private String rutApoderado;
	
	private String tipoComercio;

	//bi-directional many-to-one association to Convenio
	@OneToMany(mappedBy="comercio")
	private List<Convenio> convenios;

	public Comercio() {
		super();
	}

	public String getIdComercio() {
		return this.idComercio;
	}

	public void setIdComercio(String idComercio) {
		this.idComercio = idComercio;
	}

	public String getDireccionComercio() {
		return this.direccionComercio;
	}

	public void setDireccionComercio(String direccionComercio) {
		this.direccionComercio = direccionComercio;
	}

	public String getEmailComercio() {
		return this.emailComercio;
	}

	public void setEmailComercio(String emailComercio) {
		this.emailComercio = emailComercio;
	}

	public Date getFechaHoraActivacion() {
		return this.fechaHoraActivacion;
	}

	public void setFechaHoraActivacion(Date fechaHoraActivacion) {
		this.fechaHoraActivacion = fechaHoraActivacion;
	}

	public String getRazonSocialComercio() {
		return this.razonSocialComercio;
	}

	public void setRazonSocialComercio(String razonSocialComercio) {
		this.razonSocialComercio = razonSocialComercio;
	}

	public String getRutComercio() {
		return this.rutComercio;
	}

	public String getRutApoderado() {
		return rutApoderado;
	}

	public void setRutApoderado(String rutApoderado) {
		this.rutApoderado = rutApoderado;
	}

	public void setRutComercio(String rutComercio) {
		this.rutComercio = rutComercio;
	}

	public List<Convenio> getConvenios() {
		return this.convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	

	public String getTipoComercio() {
		return tipoComercio;
	}

	public void setTipoComercio(String tipoComercio) {
		this.tipoComercio = tipoComercio;
	}

	public Convenio addConvenio(Convenio convenio) {
		getConvenios().add(convenio);
		convenio.setComercio(this);

		return convenio;
	}

	public Convenio removeConvenio(Convenio convenio) {
		getConvenios().remove(convenio);
		convenio.setComercio(null);

		return convenio;
	}

}