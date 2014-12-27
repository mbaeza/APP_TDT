/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.entityclass;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author marcobaezasalazar
 */
@Embeddable
public class SemejanzaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EJERCICIO")
    private int idEjercicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SEMEJANZA")
    private int idSemejanza;

    public SemejanzaPK() {
    }

    public SemejanzaPK(int idEjercicio, int idSemejanza) {
        this.idEjercicio = idEjercicio;
        this.idSemejanza = idSemejanza;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getIdSemejanza() {
        return idSemejanza;
    }

    public void setIdSemejanza(int idSemejanza) {
        this.idSemejanza = idSemejanza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEjercicio;
        hash += (int) idSemejanza;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SemejanzaPK)) {
            return false;
        }
        SemejanzaPK other = (SemejanzaPK) object;
        if (this.idEjercicio != other.idEjercicio) {
            return false;
        }
        if (this.idSemejanza != other.idSemejanza) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.SemejanzaPK[ idEjercicio=" + idEjercicio + ", idSemejanza=" + idSemejanza + " ]";
    }
    
}
