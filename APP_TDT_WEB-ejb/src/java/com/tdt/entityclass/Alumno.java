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
@Table(name = "ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByIdAlumno", query = "SELECT a FROM Alumno a WHERE a.idAlumno = :idAlumno"),
    @NamedQuery(name = "Alumno.findByNombreAlumno", query = "SELECT a FROM Alumno a WHERE a.nombreAlumno = :nombreAlumno"),
    @NamedQuery(name = "Alumno.findByApellidoPaternoAlumno", query = "SELECT a FROM Alumno a WHERE a.apellidoPaternoAlumno = :apellidoPaternoAlumno"),
    @NamedQuery(name = "Alumno.findByApellidoMaternoAlumno", query = "SELECT a FROM Alumno a WHERE a.apellidoMaternoAlumno = :apellidoMaternoAlumno"),
    @NamedQuery(name = "Alumno.findByRut", query = "SELECT a FROM Alumno a WHERE a.rut = :rut"),
    @NamedQuery(name = "Alumno.findByFechaNacimiento", query = "SELECT a FROM Alumno a WHERE a.fechaNacimiento = :fechaNacimiento")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ALUMNO")
    private Integer idAlumno;
    @Size(max = 100)
    @Column(name = "NOMBRE_ALUMNO")
    private String nombreAlumno;
    @Size(max = 100)
    @Column(name = "APELLIDO_PATERNO_ALUMNO")
    private String apellidoPaternoAlumno;
    @Size(max = 100)
    @Column(name = "APELLIDO_MATERNO_ALUMNO")
    private String apellidoMaternoAlumno;
    @Size(max = 100)
    @Column(name = "RUT")
    private String rut;
    @Size(max = 100)
    @Column(name = "FECHA_NACIMIENTO")
    private String fechaNacimiento;
    @OneToMany(mappedBy = "idAlumno")
    private Collection<AlumnoColaborativo> alumnoColaborativoCollection;
    @JoinColumn(name = "ID_CENTRO_EDUCACIONAL", referencedColumnName = "ID_CENTRO_EDUCACIONAL")
    @ManyToOne(optional = false)
    private CentroEducacional idCentroEducacional;

    public Alumno() {
    }

    public Alumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoPaternoAlumno() {
        return apellidoPaternoAlumno;
    }

    public void setApellidoPaternoAlumno(String apellidoPaternoAlumno) {
        this.apellidoPaternoAlumno = apellidoPaternoAlumno;
    }

    public String getApellidoMaternoAlumno() {
        return apellidoMaternoAlumno;
    }

    public void setApellidoMaternoAlumno(String apellidoMaternoAlumno) {
        this.apellidoMaternoAlumno = apellidoMaternoAlumno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @XmlTransient
    public Collection<AlumnoColaborativo> getAlumnoColaborativoCollection() {
        return alumnoColaborativoCollection;
    }

    public void setAlumnoColaborativoCollection(Collection<AlumnoColaborativo> alumnoColaborativoCollection) {
        this.alumnoColaborativoCollection = alumnoColaborativoCollection;
    }

    public CentroEducacional getIdCentroEducacional() {
        return idCentroEducacional;
    }

    public void setIdCentroEducacional(CentroEducacional idCentroEducacional) {
        this.idCentroEducacional = idCentroEducacional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlumno != null ? idAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.idAlumno == null && other.idAlumno != null) || (this.idAlumno != null && !this.idAlumno.equals(other.idAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.Alumno[ idAlumno=" + idAlumno + " ]";
    }
    
}
