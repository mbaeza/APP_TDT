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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "ASIGNAR_EJERCICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignarEjercicio.findAll", query = "SELECT a FROM AsignarEjercicio a"),
    @NamedQuery(name = "AsignarEjercicio.findByIdAsignarEjercicio", query = "SELECT a FROM AsignarEjercicio a WHERE a.idAsignarEjercicio = :idAsignarEjercicio"),
    @NamedQuery(name = "AsignarEjercicio.findByObservacionAsignarEjercicio", query = "SELECT a FROM AsignarEjercicio a WHERE a.observacionAsignarEjercicio = :observacionAsignarEjercicio")})
public class AsignarEjercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASIGNAR_EJERCICIO")
    private Integer idAsignarEjercicio;
    @Size(max = 200)
    @Column(name = "OBSERVACION_ASIGNAR_EJERCICIO")
    private String observacionAsignarEjercicio;
    @JoinColumn(name = "ID_EJERCICIO", referencedColumnName = "ID_EJERCICIO")
    @ManyToOne
    private Ejercicio idEjercicio;
    @JoinColumns({
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO"),
        @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")})
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "idAsignarEjercicio")
    private Collection<AlumnoColaborativo> alumnoColaborativoCollection;

    public AsignarEjercicio() {
    }

    public AsignarEjercicio(Integer idAsignarEjercicio) {
        this.idAsignarEjercicio = idAsignarEjercicio;
    }

    public Integer getIdAsignarEjercicio() {
        return idAsignarEjercicio;
    }

    public void setIdAsignarEjercicio(Integer idAsignarEjercicio) {
        this.idAsignarEjercicio = idAsignarEjercicio;
    }

    public String getObservacionAsignarEjercicio() {
        return observacionAsignarEjercicio;
    }

    public void setObservacionAsignarEjercicio(String observacionAsignarEjercicio) {
        this.observacionAsignarEjercicio = observacionAsignarEjercicio;
    }

    public Ejercicio getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Ejercicio idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<AlumnoColaborativo> getAlumnoColaborativoCollection() {
        return alumnoColaborativoCollection;
    }

    public void setAlumnoColaborativoCollection(Collection<AlumnoColaborativo> alumnoColaborativoCollection) {
        this.alumnoColaborativoCollection = alumnoColaborativoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignarEjercicio != null ? idAsignarEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignarEjercicio)) {
            return false;
        }
        AsignarEjercicio other = (AsignarEjercicio) object;
        if ((this.idAsignarEjercicio == null && other.idAsignarEjercicio != null) || (this.idAsignarEjercicio != null && !this.idAsignarEjercicio.equals(other.idAsignarEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.AsignarEjercicio[ idAsignarEjercicio=" + idAsignarEjercicio + " ]";
    }
    
}
