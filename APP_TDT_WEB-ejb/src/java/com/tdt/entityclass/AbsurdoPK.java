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
public class AbsurdoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EJERCICIO")
    private int idEjercicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ABSURDO")
    private int idAbsurdo;

    public AbsurdoPK() {
    }

    public AbsurdoPK(int idEjercicio, int idAbsurdo) {
        this.idEjercicio = idEjercicio;
        this.idAbsurdo = idAbsurdo;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getIdAbsurdo() {
        return idAbsurdo;
    }

    public void setIdAbsurdo(int idAbsurdo) {
        this.idAbsurdo = idAbsurdo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEjercicio;
        hash += (int) idAbsurdo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbsurdoPK)) {
            return false;
        }
        AbsurdoPK other = (AbsurdoPK) object;
        if (this.idEjercicio != other.idEjercicio) {
            return false;
        }
        if (this.idAbsurdo != other.idAbsurdo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.AbsurdoPK[ idEjercicio=" + idEjercicio + ", idAbsurdo=" + idAbsurdo + " ]";
    }
    
}
