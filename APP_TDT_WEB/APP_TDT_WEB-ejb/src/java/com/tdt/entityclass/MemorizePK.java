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
public class MemorizePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EJERCICIO")
    private int idEjercicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MEMORIZE")
    private int idMemorize;

    public MemorizePK() {
    }

    public MemorizePK(int idEjercicio, int idMemorize) {
        this.idEjercicio = idEjercicio;
        this.idMemorize = idMemorize;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getIdMemorize() {
        return idMemorize;
    }

    public void setIdMemorize(int idMemorize) {
        this.idMemorize = idMemorize;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEjercicio;
        hash += (int) idMemorize;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemorizePK)) {
            return false;
        }
        MemorizePK other = (MemorizePK) object;
        if (this.idEjercicio != other.idEjercicio) {
            return false;
        }
        if (this.idMemorize != other.idMemorize) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.MemorizePK[ idEjercicio=" + idEjercicio + ", idMemorize=" + idMemorize + " ]";
    }
    
}
