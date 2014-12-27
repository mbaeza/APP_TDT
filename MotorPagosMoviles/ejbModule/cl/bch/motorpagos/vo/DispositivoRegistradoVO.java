/**
 * 
 */
package cl.bch.motorpagos.vo;

import java.io.Serializable;

/**
 * @author boyanedel
 *
 */
public class DispositivoRegistradoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 697033699916346267L;
	private String id;
	private String alias;
	private String modelo;
	private String estado;
	private String tipo;
	private String fechaHoraCreacion;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	/**
	 * @return the fechaHoraCreacion
	 */
	public String getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}
	/**
	 * @param fechaHoraCreacion the fechaHoraCreacion to set
	 */
	public void setFechaHoraCreacion(String fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString(){
		StringBuffer bf = new StringBuffer(180);
		bf.append("Id : ");
		bf.append(this.id);
		bf.append(", Alias : ");
		bf.append(this.alias);
		bf.append(", Modelo : ");
		bf.append(this.modelo);
		bf.append(", Estado : ");
		bf.append(this.estado);
		bf.append(", Tipo : ");
		bf.append(this.tipo);
		bf.append(", FechaHoraCreacion : ");
		bf.append(this.fechaHoraCreacion);
		
		return bf.toString();
	}	
}
