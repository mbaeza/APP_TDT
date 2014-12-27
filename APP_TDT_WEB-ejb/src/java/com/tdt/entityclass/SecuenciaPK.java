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
public class SecuenciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EJERCICIO")
    private int idEjercicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SECUENCIA")
    private int idSecuencia;

    public SecuenciaPK() {
    }

    public SecuenciaPK(int idEjercicio, int idSecuencia) {
        this.idEjercicio = idEjercicio;
        this.idSecuencia = idSecuencia;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(int idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEjercicio;
        hash += (int) idSecuencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecuenciaPK)) {
            return false;
        }
        SecuenciaPK other = (SecuenciaPK) object;
        if (this.idEjercicio != other.idEjercicio) {
            return false;
        }
        if (this.idSecuencia != other.idSecuencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.SecuenciaPK[ idEjercicio=" + idEjercicio + ", idSecuencia=" + idSecuencia + " ]";
    }
    
}
