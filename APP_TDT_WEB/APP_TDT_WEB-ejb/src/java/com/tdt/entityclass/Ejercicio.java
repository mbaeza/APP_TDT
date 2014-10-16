/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.entityclass;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author marcobaezasalazar
 */
@Entity
@Table(name = "EJERCICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e"),
    @NamedQuery(name = "Ejercicio.findByIdEjercicio", query = "SELECT e FROM Ejercicio e WHERE e.idEjercicio = :idEjercicio"),
    @NamedQuery(name = "Ejercicio.findByDescripcionEjercicio", query = "SELECT e FROM Ejercicio e WHERE e.descripcionEjercicio = :descripcionEjercicio"),
    @NamedQuery(name = "Ejercicio.findByNombreEjercicio", query = "SELECT e FROM Ejercicio e WHERE e.nombreEjercicio = :nombreEjercicio")})
public class Ejercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EJERCICIO")
    private Integer idEjercicio;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_EJERCICIO")
    private String descripcionEjercicio;
    @Size(max = 200)
    @Column(name = "NOMBRE_EJERCICIO")
    private String nombreEjercicio;
    @OneToMany(mappedBy = "idEjercicio")
    private Collection<AsignarEjercicio> asignarEjercicioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio")
    private Collection<Memorize> memorizeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio")
    private Collection<Secuencia> secuenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio")
    private Collection<Absurdo> absurdoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio")
    private Collection<Semejanza> semejanzaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEjercicio")
    private Collection<Imagen> imagenCollection;

    public Ejercicio() {
    }

    public Ejercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Integer getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getDescripcionEjercicio() {
        return descripcionEjercicio;
    }

    public void setDescripcionEjercicio(String descripcionEjercicio) {
        this.descripcionEjercicio = descripcionEjercicio;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    @XmlTransient
    public Collection<AsignarEjercicio> getAsignarEjercicioCollection() {
        return asignarEjercicioCollection;
    }

    public void setAsignarEjercicioCollection(Collection<AsignarEjercicio> asignarEjercicioCollection) {
        this.asignarEjercicioCollection = asignarEjercicioCollection;
    }

    @XmlTransient
    public Collection<Memorize> getMemorizeCollection() {
        return memorizeCollection;
    }

    public void setMemorizeCollection(Collection<Memorize> memorizeCollection) {
        this.memorizeCollection = memorizeCollection;
    }

    @XmlTransient
    public Collection<Secuencia> getSecuenciaCollection() {
        return secuenciaCollection;
    }

    public void setSecuenciaCollection(Collection<Secuencia> secuenciaCollection) {
        this.secuenciaCollection = secuenciaCollection;
    }

    @XmlTransient
    public Collection<Absurdo> getAbsurdoCollection() {
        return absurdoCollection;
    }

    public void setAbsurdoCollection(Collection<Absurdo> absurdoCollection) {
        this.absurdoCollection = absurdoCollection;
    }

    @XmlTransient
    public Collection<Semejanza> getSemejanzaCollection() {
        return semejanzaCollection;
    }

    public void setSemejanzaCollection(Collection<Semejanza> semejanzaCollection) {
        this.semejanzaCollection = semejanzaCollection;
    }

    @XmlTransient
    public Collection<Imagen> getImagenCollection() {
        return imagenCollection;
    }

    public void setImagenCollection(Collection<Imagen> imagenCollection) {
        this.imagenCollection = imagenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEjercicio != null ? idEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.idEjercicio == null && other.idEjercicio != null) || (this.idEjercicio != null && !this.idEjercicio.equals(other.idEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.Ejercicio[ idEjercicio=" + idEjercicio + " ]";
    }
    
}
