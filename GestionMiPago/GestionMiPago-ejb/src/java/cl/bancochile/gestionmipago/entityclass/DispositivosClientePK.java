/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.entityclass;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author marcobaeza
 */
@Embeddable
public class DispositivosClientePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDCLIENTE")
    private String idcliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDDISPOSITIVO")
    private String iddispositivo;

    public DispositivosClientePK() {
    }

    public DispositivosClientePK(String idcliente, String iddispositivo) {
        this.idcliente = idcliente;
        this.iddispositivo = iddispositivo;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(String iddispositivo) {
        this.iddispositivo = iddispositivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        hash += (iddispositivo != null ? iddispositivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DispositivosClientePK)) {
            return false;
        }
        DispositivosClientePK other = (DispositivosClientePK) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        if ((this.iddispositivo == null && other.iddispositivo != null) || (this.iddispositivo != null && !this.iddispositivo.equals(other.iddispositivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.bancochile.gestionmipago.entityclass.DispositivosClientePK[ idcliente=" + idcliente + ", iddispositivo=" + iddispositivo + " ]";
    }
    
}
