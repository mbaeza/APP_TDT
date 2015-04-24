/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.entityclass;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcobaeza
 */
@Entity
@Table(name = "DISPOSITIVOS_CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DispositivosCliente.findAll", query = "SELECT d FROM DispositivosCliente d"),
    @NamedQuery(name = "DispositivosCliente.findByIdcliente", query = "SELECT d FROM DispositivosCliente d WHERE d.dispositivosClientePK.idcliente = :idcliente"),
    @NamedQuery(name = "DispositivosCliente.findByIddispositivo", query = "SELECT d FROM DispositivosCliente d WHERE d.dispositivosClientePK.iddispositivo = :iddispositivo")})
public class DispositivosCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DispositivosClientePK dispositivosClientePK;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clientes clientes;
    @JoinColumn(name = "IDCUENTA", referencedColumnName = "IDCUENTA")
    @ManyToOne(optional = false)
    private Cuentas idcuenta;
    @JoinColumn(name = "IDDISPOSITIVO", referencedColumnName = "IDDISPOSITIVO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dispositivos dispositivos;

    public DispositivosCliente() {
    }

    public DispositivosCliente(DispositivosClientePK dispositivosClientePK) {
        this.dispositivosClientePK = dispositivosClientePK;
    }

    public DispositivosCliente(String idcliente, String iddispositivo) {
        this.dispositivosClientePK = new DispositivosClientePK(idcliente, iddispositivo);
    }

    public DispositivosClientePK getDispositivosClientePK() {
        return dispositivosClientePK;
    }

    public void setDispositivosClientePK(DispositivosClientePK dispositivosClientePK) {
        this.dispositivosClientePK = dispositivosClientePK;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Cuentas getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Cuentas idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Dispositivos getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(Dispositivos dispositivos) {
        this.dispositivos = dispositivos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dispositivosClientePK != null ? dispositivosClientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DispositivosCliente)) {
            return false;
        }
        DispositivosCliente other = (DispositivosCliente) object;
        if ((this.dispositivosClientePK == null && other.dispositivosClientePK != null) || (this.dispositivosClientePK != null && !this.dispositivosClientePK.equals(other.dispositivosClientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.bancochile.gestionmipago.entityclass.DispositivosCliente[ dispositivosClientePK=" + dispositivosClientePK + " ]";
    }
    
}
