package cl.bch.motorpagos.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the dispositivos database table.
 * 
 */
@Entity
@Table(name="dispositivos")
@NamedQuery(name="Dispositivo.findAll", query="SELECT d FROM Dispositivo d")
public class Dispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idDispositivo;

	private String claveDispositivo;

	private String estadoDispositivo;

	private int intentosFallidos;
	
	private String aliasDispositivo;
	
	private String modeloDispositivo;
	
	private Date fechaHoraCreacion;
	
	private String tipoDispositivo;

	public Dispositivo() {
		super();
	}

	public String getIdDispositivo() {
		return this.idDispositivo;
	}

	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getClaveDispositivo() {
		return this.claveDispositivo;
	}

	public void setClaveDispositivo(String claveDispositivo) {
		this.claveDispositivo = claveDispositivo;
	}

	public String getEstadoDispositivo() {
		return this.estadoDispositivo;
	}

	public void setEstadoDispositivo(String estadoDispositivo) {
		this.estadoDispositivo = estadoDispositivo;
	}

	public int getIntentosFallidos() {
		return intentosFallidos;
	}

	public void setIntentosFallidos(int intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}

	public String getAliasDispositivo() {
		return aliasDispositivo;
	}

	public void setAliasDispositivo(String aliasDispositivo) {
		this.aliasDispositivo = aliasDispositivo;
	}

	public String getModeloDispositivo() {
		return modeloDispositivo;
	}

	public void setModeloDispositivo(String modeloDispositivo) {
		this.modeloDispositivo = modeloDispositivo;
	}

	public Date getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

	public String getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}
	
	

}