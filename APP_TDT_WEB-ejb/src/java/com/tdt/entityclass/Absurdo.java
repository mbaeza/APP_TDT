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
@Table(name = "ABSURDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Absurdo.findAll", query = "SELECT a FROM Absurdo a"),
    @NamedQuery(name = "Absurdo.findByIdEjercicio", query = "SELECT a FROM Absurdo a WHERE a.absurdoPK.idEjercicio = :idEjercicio"),
    @NamedQuery(name = "Absurdo.findByIdAbsurdo", query = "SELECT a FROM Absurdo a WHERE a.absurdoPK.idAbsurdo = :idAbsurdo"),
    @NamedQuery(name = "Absurdo.findByDescripcionEjercicio", query = "SELECT a FROM Absurdo a WHERE a.descripcionEjercicio = :descripcionEjercicio"),
    @NamedQuery(name = "Absurdo.findByNombreEjercicio", query = "SELECT a FROM Absurdo a WHERE a.nombreEjercicio = :nombreEjercicio"),
    @NamedQuery(name = "Absurdo.findByTextoPrincipal", query = "SELECT a FROM Absurdo a WHERE a.textoPrincipal = :textoPrincipal"),
    @NamedQuery(name = "Absurdo.findByColorTexto", query = "SELECT a FROM Absurdo a WHERE a.colorTexto = :colorTexto")})
public class Absurdo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AbsurdoPK absurdoPK;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_EJERCICIO")
    private String descripcionEjercicio;
    @Size(max = 200)
    @Column(name = "NOMBRE_EJERCICIO")
    private String nombreEjercicio;
    @Size(max = 100)
    @Column(name = "TEXTO_PRINCIPAL")
    private String textoPrincipal;
    @Size(max = 100)
    @Column(name = "COLOR_TEXTO")
    private String colorTexto;
    @JoinColumn(name = "ID_EJERCICIO", referencedColumnName = "ID_EJERCICIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ejercicio ejercicio;

    public Absurdo() {
    }

    public Absurdo(AbsurdoPK absurdoPK) {
        this.absurdoPK = absurdoPK;
    }

    public Absurdo(int idEjercicio, int idAbsurdo) {
        this.absurdoPK = new AbsurdoPK(idEjercicio, idAbsurdo);
    }

    public AbsurdoPK getAbsurdoPK() {
        return absurdoPK;
    }

    public void setAbsurdoPK(AbsurdoPK absurdoPK) {
        this.absurdoPK = absurdoPK;
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

    public String getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(String colorTexto) {
        this.colorTexto = colorTexto;
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
        hash += (absurdoPK != null ? absurdoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Absurdo)) {
            return false;
        }
        Absurdo other = (Absurdo) object;
        if ((this.absurdoPK == null && other.absurdoPK != null) || (this.absurdoPK != null && !this.absurdoPK.equals(other.absurdoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.Absurdo[ absurdoPK=" + absurdoPK + " ]";
    }
    
}
