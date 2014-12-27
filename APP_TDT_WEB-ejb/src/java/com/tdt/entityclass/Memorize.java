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
@Table(name = "MEMORIZE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memorize.findAll", query = "SELECT m FROM Memorize m"),
    @NamedQuery(name = "Memorize.findByIdEjercicio", query = "SELECT m FROM Memorize m WHERE m.memorizePK.idEjercicio = :idEjercicio"),
    @NamedQuery(name = "Memorize.findByIdMemorize", query = "SELECT m FROM Memorize m WHERE m.memorizePK.idMemorize = :idMemorize"),
    @NamedQuery(name = "Memorize.findByDescripcionEjercicio", query = "SELECT m FROM Memorize m WHERE m.descripcionEjercicio = :descripcionEjercicio"),
    @NamedQuery(name = "Memorize.findByNombreEjercicio", query = "SELECT m FROM Memorize m WHERE m.nombreEjercicio = :nombreEjercicio"),
    @NamedQuery(name = "Memorize.findByTextoPrincipalMemorize", query = "SELECT m FROM Memorize m WHERE m.textoPrincipalMemorize = :textoPrincipalMemorize")})
public class Memorize implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MemorizePK memorizePK;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_EJERCICIO")
    private String descripcionEjercicio;
    @Size(max = 200)
    @Column(name = "NOMBRE_EJERCICIO")
    private String nombreEjercicio;
    @Size(max = 200)
    @Column(name = "TEXTO_PRINCIPAL_MEMORIZE")
    private String textoPrincipalMemorize;
    @JoinColumn(name = "ID_EJERCICIO", referencedColumnName = "ID_EJERCICIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ejercicio ejercicio;

    public Memorize() {
    }

    public Memorize(MemorizePK memorizePK) {
        this.memorizePK = memorizePK;
    }

    public Memorize(int idEjercicio, int idMemorize) {
        this.memorizePK = new MemorizePK(idEjercicio, idMemorize);
    }

    public MemorizePK getMemorizePK() {
        return memorizePK;
    }

    public void setMemorizePK(MemorizePK memorizePK) {
        this.memorizePK = memorizePK;
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

    public String getTextoPrincipalMemorize() {
        return textoPrincipalMemorize;
    }

    public void setTextoPrincipalMemorize(String textoPrincipalMemorize) {
        this.textoPrincipalMemorize = textoPrincipalMemorize;
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
        hash += (memorizePK != null ? memorizePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memorize)) {
            return false;
        }
        Memorize other = (Memorize) object;
        if ((this.memorizePK == null && other.memorizePK != null) || (this.memorizePK != null && !this.memorizePK.equals(other.memorizePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.Memorize[ memorizePK=" + memorizePK + " ]";
    }
    
}
