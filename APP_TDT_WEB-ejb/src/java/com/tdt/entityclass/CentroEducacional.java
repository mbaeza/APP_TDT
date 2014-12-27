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
@Table(name = "CENTRO_EDUCACIONAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentroEducacional.findAll", query = "SELECT c FROM CentroEducacional c"),
    @NamedQuery(name = "CentroEducacional.findByIdCentroEducacional", query = "SELECT c FROM CentroEducacional c WHERE c.idCentroEducacional = :idCentroEducacional"),
    @NamedQuery(name = "CentroEducacional.findByNombreCentroEducacional", query = "SELECT c FROM CentroEducacional c WHERE c.nombreCentroEducacional = :nombreCentroEducacional"),
    @NamedQuery(name = "CentroEducacional.findByUbicacion", query = "SELECT c FROM CentroEducacional c WHERE c.ubicacion = :ubicacion")})
public class CentroEducacional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CENTRO_EDUCACIONAL")
    private Integer idCentroEducacional;
    @Size(max = 100)
    @Column(name = "NOMBRE_CENTRO_EDUCACIONAL")
    private String nombreCentroEducacional;
    @Size(max = 100)
    @Column(name = "UBICACION")
    private String ubicacion;
    @JoinColumns({
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO"),
        @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")})
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCentroEducacional")
    private Collection<Alumno> alumnoCollection;
    @OneToMany(mappedBy = "idCentroEducacional")
    private Collection<Usuario> usuarioCollection;

    public CentroEducacional() {
    }

    public CentroEducacional(Integer idCentroEducacional) {
        this.idCentroEducacional = idCentroEducacional;
    }

    public Integer getIdCentroEducacional() {
        return idCentroEducacional;
    }

    public void setIdCentroEducacional(Integer idCentroEducacional) {
        this.idCentroEducacional = idCentroEducacional;
    }

    public String getNombreCentroEducacional() {
        return nombreCentroEducacional;
    }

    public void setNombreCentroEducacional(String nombreCentroEducacional) {
        this.nombreCentroEducacional = nombreCentroEducacional;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Alumno> getAlumnoCollection() {
        return alumnoCollection;
    }

    public void setAlumnoCollection(Collection<Alumno> alumnoCollection) {
        this.alumnoCollection = alumnoCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCentroEducacional != null ? idCentroEducacional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroEducacional)) {
            return false;
        }
        CentroEducacional other = (CentroEducacional) object;
        if ((this.idCentroEducacional == null && other.idCentroEducacional != null) || (this.idCentroEducacional != null && !this.idCentroEducacional.equals(other.idCentroEducacional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.CentroEducacional[ idCentroEducacional=" + idCentroEducacional + " ]";
    }
    
}
