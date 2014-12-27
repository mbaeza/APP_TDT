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
@Table(name = "SECUENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Secuencia.findAll", query = "SELECT s FROM Secuencia s"),
    @NamedQuery(name = "Secuencia.findByIdEjercicio", query = "SELECT s FROM Secuencia s WHERE s.secuenciaPK.idEjercicio = :idEjercicio"),
    @NamedQuery(name = "Secuencia.findByIdSecuencia", query = "SELECT s FROM Secuencia s WHERE s.secuenciaPK.idSecuencia = :idSecuencia"),
    @NamedQuery(name = "Secuencia.findByDescripcionEjercicio", query = "SELECT s FROM Secuencia s WHERE s.descripcionEjercicio = :descripcionEjercicio"),
    @NamedQuery(name = "Secuencia.findByNombreEjercicio", query = "SELECT s FROM Secuencia s WHERE s.nombreEjercicio = :nombreEjercicio"),
    @NamedQuery(name = "Secuencia.findByTextoPrincipal", query = "SELECT s FROM Secuencia s WHERE s.textoPrincipal = :textoPrincipal")})
public class Secuencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SecuenciaPK secuenciaPK;
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

    public Secuencia() {
    }

    public Secuencia(SecuenciaPK secuenciaPK) {
        this.secuenciaPK = secuenciaPK;
    }

    public Secuencia(int idEjercicio, int idSecuencia) {
        this.secuenciaPK = new SecuenciaPK(idEjercicio, idSecuencia);
    }

    public SecuenciaPK getSecuenciaPK() {
        return secuenciaPK;
    }

    public void setSecuenciaPK(SecuenciaPK secuenciaPK) {
        this.secuenciaPK = secuenciaPK;
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
        hash += (secuenciaPK != null ? secuenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Secuencia)) {
            return false;
        }
        Secuencia other = (Secuencia) object;
        if ((this.secuenciaPK == null && other.secuenciaPK != null) || (this.secuenciaPK != null && !this.secuenciaPK.equals(other.secuenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.Secuencia[ secuenciaPK=" + secuenciaPK + " ]";
    }
    
}
