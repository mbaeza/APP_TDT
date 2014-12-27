package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adm_cuentas_hist database table.
 * 
 */
@Entity
@Table(name="adm_cuentas_hist")
@NamedQuery(name="AdmCuentasHist.findAll", query="SELECT a FROM AdmCuentasHist a")
public class AdmCuentasHist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdmCuentasHistPK id;

	private String estadoCuenta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActivacion;

	private int limiteCuenta;

	private String nroCuenta;

	private String tipoCuenta;

	public AdmCuentasHist() {
		super();
	}

	public AdmCuentasHistPK getId() {
		return this.id;
	}

	public void setId(AdmCuentasHistPK id) {
		this.id = id;
	}

	public String getEstadoCuenta() {
		return this.estadoCuenta;
	}

	public void setEstadoCuenta(String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public Date getFechaActivacion() {
		return this.fechaActivacion;
	}

	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public int getLimiteCuenta() {
		return this.limiteCuenta;
	}

	public void setLimiteCuenta(int limiteCuenta) {
		this.limiteCuenta = limiteCuenta;
	}

	public String getNroCuenta() {
		return this.nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getTipoCuenta() {
		return this.tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

}