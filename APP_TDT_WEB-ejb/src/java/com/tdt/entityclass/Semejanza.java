/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.entityclass;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "SEMEJANZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semejanza.findAll", query = "SELECT s FROM Semejanza s"),
    @NamedQuery(name = "Semejanza.findByIdEjercicio", query = "SELECT s FROM Semejanza s WHERE s.semejanzaPK.idEjercicio = :idEjercicio"),
    @NamedQuery(name = "Semejanza.findByIdSemejanza", query = "SELECT s FROM Semejanza s WHERE s.semejanzaPK.idSemejanza = :idSemejanza"),
    @NamedQuery(name = "Semejanza.findByDescripcionEjercicio", query = "SELECT s FROM Semejanza s WHERE s.descripcionEjercicio = :descripcionEjercicio"),
    @NamedQuery(name = "Semejanza.findByNombreEjercicio", query = "SELECT s FROM Semejanza s WHERE s.nombreEjercicio = :nombreEjercicio"),
    @NamedQuery(name = "Semejanza.findByTextoPrincipal", query = "SELECT s FROM Semejanza s WHERE s.textoPrincipal = :textoPrincipal")})
public class Semejanza implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SemejanzaPK semejanzaPK;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_EJERCICIO")
    private String descripcionEjercicio;
    @Size(max = 200)
    @Column(name = "NOMBRE_EJERCICIO")
    private String nombreEjercicio;
    @Size(max = 100)
    @Column(name = "TEXTO_PRINCIPAL")
    private String textoPrincipal;
    @JoinColumn(name = "ID_EJERCICIO", referencedColumnName = "ID_EJERCICIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ejercicio ejercicio;

    public Semejanza() {
    }

    public Semejanza(SemejanzaPK semejanzaPK) {
        this.semejanzaPK = semejanzaPK;
    }

    public Semejanza(int idEjercicio, int idSemejanza) {
        this.semejanzaPK = new SemejanzaPK(idEjercicio, idSemejanza);
    }

    public SemejanzaPK getSemejanzaPK() {
        return semejanzaPK;
    }

    public void setSemejanzaPK(SemejanzaPK semejanzaPK) {
        this.semejanzaPK = semejanzaPK;
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

    public String getTextoPrincipal() {
        return textoPrincipal;
    }

    public void setTextoPrincipal(String textoPrincipal) {
        this.textoPrincipal = textoPrincipal;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (semejanzaPK != null ? semejanzaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semejanza)) {
            return false;
        }
        Semejanza other = (Semejanza) object;
        if ((this.semejanzaPK == null && other.semejanzaPK != null) || (this.semejanzaPK != null && !this.semejanzaPK.equals(other.semejanzaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.Semejanza[ semejanzaPK=" + semejanzaPK + " ]";
    }
    
}
