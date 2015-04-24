/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.entityclass;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcobaeza
 */
@Entity
@Table(name = "CUENTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c"),
    @NamedQuery(name = "Cuentas.findByIdcuenta", query = "SELECT c FROM Cuentas c WHERE c.idcuenta = :idcuenta"),
    @NamedQuery(name = "Cuentas.findByNrocuenta", query = "SELECT c FROM Cuentas c WHERE c.nrocuenta = :nrocuenta"),
    @NamedQuery(name = "Cuentas.findByTipocuenta", query = "SELECT c FROM Cuentas c WHERE c.tipocuenta = :tipocuenta"),
    @NamedQuery(name = "Cuentas.findByEstadocuenta", query = "SELECT c FROM Cuentas c WHERE c.estadocuenta = :estadocuenta"),
    @NamedQuery(name = "Cuentas.findByFechaactivacion", query = "SELECT c FROM Cuentas c WHERE c.fechaactivacion = :fechaactivacion"),
    @NamedQuery(name = "Cuentas.findByLimitecuenta", query = "SELECT c FROM Cuentas c WHERE c.limitecuenta = :limitecuenta")})
public class Cuentas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDCUENTA")
    private String idcuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NROCUENTA")
    private String nrocuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TIPOCUENTA")
    private String tipocuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADOCUENTA")
    private String estadocuenta;
    @Column(name = "FECHAACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaactivacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMITECUENTA")
    private BigInteger limitecuenta;
    @ManyToMany(mappedBy = "cuentasList")
    private List<Convenios> conveniosList;
    @ManyToMany(mappedBy = "cuentasList")
    private List<Clientes> clientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcuenta")
    private List<DispositivosCliente> dispositivosClienteList;

    public Cuentas() {
    }

    public Cuentas(String idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Cuentas(String idcuenta, String nrocuenta, String tipocuenta, String estadocuenta, BigInteger limitecuenta) {
        this.idcuenta = idcuenta;
        this.nrocuenta = nrocuenta;
        this.tipocuenta = tipocuenta;
        this.estadocuenta = estadocuenta;
        this.limitecuenta = limitecuenta;
    }

    public String getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(String idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getNrocuenta() {
        return nrocuenta;
    }

    public void setNrocuenta(String nrocuenta) {
        this.nrocuenta = nrocuenta;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public String getEstadocuenta() {
        return estadocuenta;
    }

    public void setEstadocuenta(String estadocuenta) {
        this.estadocuenta = estadocuenta;
    }

    public Date getFechaactivacion() {
        return fechaactivacion;
    }

    public void setFechaactivacion(Date fechaactivacion) {
        this.fechaactivacion = fechaactivacion;
    }

    public BigInteger getLimitecuenta() {
        return limitecuenta;
    }

    public void setLimitecuenta(BigInteger limitecuenta) {
        this.limitecuenta = limitecuenta;
    }

    @XmlTransient
    public List<Convenios> getConveniosList() {
        return conveniosList;
    }

    public void setConveniosList(List<Convenios> conveniosList) {
        this.conveniosList = conveniosList;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    @XmlTransient
    public List<DispositivosCliente> getDispositivosClienteList() {
        return dispositivosClienteList;
    }

    public void setDispositivosClienteList(List<DispositivosCliente> dispositivosClienteList) {
        this.dispositivosClienteList = dispositivosClienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuenta != null ? idcuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.idcuenta == null && other.idcuenta != null) || (this.idcuenta != null && !this.idcuenta.equals(other.idcuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.bancochile.gestionmipago.entityclass.Cuentas[ idcuenta=" + idcuenta + " ]";
    }
    
}
