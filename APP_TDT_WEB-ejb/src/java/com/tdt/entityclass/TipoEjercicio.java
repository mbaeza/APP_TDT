/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tdt.entityclass;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcobaeza
 */
@Entity
@Table(name = "TIPO_EJERCICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEjercicio.findAll", query = "SELECT t FROM TipoEjercicio t"),
    @NamedQuery(name = "TipoEjercicio.findByIdTipoEjercicio", query = "SELECT t FROM TipoEjercicio t WHERE t.idTipoEjercicio = :idTipoEjercicio"),
    @NamedQuery(name = "TipoEjercicio.findByNombreTipoEjercicio", query = "SELECT t FROM TipoEjercicio t WHERE t.nombreTipoEjercicio = :nombreTipoEjercicio")})
public class TipoEjercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_EJERCICIO")
    private Integer idTipoEjercicio;
    @Size(max = 100)
    @Column(name = "NOMBRE_TIPO_EJERCICIO")
    private String nombreTipoEjercicio;
    @OneToMany(mappedBy = "fkTipoEjercicio")
    private Collection<Ejercicio> ejercicioCollection;

    public TipoEjercicio() {
    }

    public TipoEjercicio(Integer idTipoEjercicio) {
        this.idTipoEjercicio = idTipoEjercicio;
    }

    public Integer getIdTipoEjercicio() {
        return idTipoEjercicio;
    }

    public void setIdTipoEjercicio(Integer idTipoEjercicio) {
        this.idTipoEjercicio = idTipoEjercicio;
    }

    public String getNombreTipoEjercicio() {
        return nombreTipoEjercicio;
    }

    public void setNombreTipoEjercicio(String nombreTipoEjercicio) {
        this.nombreTipoEjercicio = nombreTipoEjercicio;
    }

    @XmlTransient
    public Collection<Ejercicio> getEjercicioCollection() {
        return ejercicioCollection;
    }

    public void setEjercicioCollection(Collection<Ejercicio> ejercicioCollection) {
        this.ejercicioCollection = ejercicioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEjercicio != null ? idTipoEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEjercicio)) {
            return false;
        }
        TipoEjercicio other = (TipoEjercicio) object;
        if ((this.idTipoEjercicio == null && other.idTipoEjercicio != null) || (this.idTipoEjercicio != null && !this.idTipoEjercicio.equals(other.idTipoEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.TipoEjercicio[ idTipoEjercicio=" + idTipoEjercicio + " ]";
    }
    
}
