/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.entityclass;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcobaezasalazar
 */
@Entity
@Table(name = "ALUMNO_COLABORATIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlumnoColaborativo.findAll", query = "SELECT a FROM AlumnoColaborativo a"),
    @NamedQuery(name = "AlumnoColaborativo.findByIdAlumnoColaborativo", query = "SELECT a FROM AlumnoColaborativo a WHERE a.idAlumnoColaborativo = :idAlumnoColaborativo"),
    @NamedQuery(name = "AlumnoColaborativo.findByObservacionColaboracion", query = "SELECT a FROM AlumnoColaborativo a WHERE a.observacionColaboracion = :observacionColaboracion")})
public class AlumnoColaborativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ALUMNO_COLABORATIVO")
    private Integer idAlumnoColaborativo;
    @Size(max = 200)
    @Column(name = "OBSERVACION_COLABORACION")
    private String observacionColaboracion;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_ALUMNO")
    @ManyToOne
    private Alumno idAlumno;
    @JoinColumn(name = "ID_ASIGNAR_EJERCICIO", referencedColumnName = "ID_ASIGNAR_EJERCICIO")
    @ManyToOne
    private AsignarEjercicio idAsignarEjercicio;

    public AlumnoColaborativo() {
    }

    public AlumnoColaborativo(Integer idAlumnoColaborativo) {
        this.idAlumnoColaborativo = idAlumnoColaborativo;
    }

    public Integer getIdAlumnoColaborativo() {
        return idAlumnoColaborativo;
    }

    public void setIdAlumnoColaborativo(Integer idAlumnoColaborativo) {
        this.idAlumnoColaborativo = idAlumnoColaborativo;
    }

    public String getObservacionColaboracion() {
        return observacionColaboracion;
    }

    public void setObservacionColaboracion(String observacionColaboracion) {
        this.observacionColaboracion = observacionColaboracion;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public AsignarEjercicio getIdAsignarEjercicio() {
        return idAsignarEjercicio;
    }

    public void setIdAsignarEjercicio(AsignarEjercicio idAsignarEjercicio) {
        this.idAsignarEjercicio = idAsignarEjercicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlumnoColaborativo != null ? idAlumnoColaborativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnoColaborativo)) {
            return false;
        }
        AlumnoColaborativo other = (AlumnoColaborativo) object;
        if ((this.idAlumnoColaborativo == null && other.idAlumnoColaborativo != null) || (this.idAlumnoColaborativo != null && !this.idAlumnoColaborativo.equals(other.idAlumnoColaborativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.AlumnoColaborativo[ idAlumnoColaborativo=" + idAlumnoColaborativo + " ]";
    }
    
}
