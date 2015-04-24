/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.entityclass;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "CLIENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findByIdcliente", query = "SELECT c FROM Clientes c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Clientes.findByRutcliente", query = "SELECT c FROM Clientes c WHERE c.rutcliente = :rutcliente"),
    @NamedQuery(name = "Clientes.findByFechahoraactivacion", query = "SELECT c FROM Clientes c WHERE c.fechahoraactivacion = :fechahoraactivacion"),
    @NamedQuery(name = "Clientes.findByMailcliente", query = "SELECT c FROM Clientes c WHERE c.mailcliente = :mailcliente"),
    @NamedQuery(name = "Clientes.findByNombrecliente", query = "SELECT c FROM Clientes c WHERE c.nombrecliente = :nombrecliente"),
    @NamedQuery(name = "Clientes.findByCodigomarca", query = "SELECT c FROM Clientes c WHERE c.codigomarca = :codigomarca")})
public class Clientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDCLIENTE")
    private String idcliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RUTCLIENTE")
    private String rutcliente;
    @Column(name = "FECHAHORAACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraactivacion;
    @Size(max = 100)
    @Column(name = "MAILCLIENTE")
    private String mailcliente;
    @Size(max = 100)
    @Column(name = "NOMBRECLIENTE")
    private String nombrecliente;
    @Size(max = 1)
    @Column(name = "CODIGOMARCA")
    private String codigomarca;
    @JoinTable(name = "CUENTAS_CLIENTE", joinColumns = {
        @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")}, inverseJoinColumns = {
        @JoinColumn(name = "IDCUENTAS", referencedColumnName = "IDCUENTA")})
    @ManyToMany
    private List<Cuentas> cuentasList;
    @OneToMany(mappedBy = "idcliente")
    private List<TrxsPago> trxsPagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientes")
    private List<DispositivosCliente> dispositivosClienteList;

    public Clientes() {
    }

    public Clientes(String idcliente) {
        this.idcliente = idcliente;
    }

    public Clientes(String idcliente, String rutcliente) {
        this.idcliente = idcliente;
        this.rutcliente = rutcliente;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getRutcliente() {
        return rutcliente;
    }

    public void setRutcliente(String rutcliente) {
        this.rutcliente = rutcliente;
    }

    public Date getFechahoraactivacion() {
        return fechahoraactivacion;
    }

    public void setFechahoraactivacion(Date fechahoraactivacion) {
        this.fechahoraactivacion = fechahoraactivacion;
    }

    public String getMailcliente() {
        return mailcliente;
    }

    public void setMailcliente(String mailcliente) {
        this.mailcliente = mailcliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getCodigomarca() {
        return codigomarca;
    }

    public void setCodigomarca(String codigomarca) {
        this.codigomarca = codigomarca;
    }

    @XmlTransient
    public List<Cuentas> getCuentasList() {
        return cuentasList;
    }

    public void setCuentasList(List<Cuentas> cuentasList) {
        this.cuentasList = cuentasList;
    }

    @XmlTransient
    public List<TrxsPago> getTrxsPagoList() {
        return trxsPagoList;
    }

    public void setTrxsPagoList(List<TrxsPago> trxsPagoList) {
        this.trxsPagoList = trxsPagoList;
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
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.bancochile.gestionmipago.entityclass.Clientes[ idcliente=" + idcliente + " ]";
    }
    
}
