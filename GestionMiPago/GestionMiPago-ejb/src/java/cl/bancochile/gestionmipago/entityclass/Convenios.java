/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.entityclass;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcobaeza
 */
@Entity
@Table(name = "CONVENIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convenios.findAll", query = "SELECT c FROM Convenios c"),
    @NamedQuery(name = "Convenios.findByIdconvenio", query = "SELECT c FROM Convenios c WHERE c.idconvenio = :idconvenio"),
    @NamedQuery(name = "Convenios.findByNombreconveniio", query = "SELECT c FROM Convenios c WHERE c.nombreconveniio = :nombreconveniio"),
    @NamedQuery(name = "Convenios.findByIntentosfallidos", query = "SELECT c FROM Convenios c WHERE c.intentosfallidos = :intentosfallidos"),
    @NamedQuery(name = "Convenios.findByEstadoconvenio", query = "SELECT c FROM Convenios c WHERE c.estadoconvenio = :estadoconvenio")})
public class Convenios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDCONVENIO")
    private String idconvenio;
    @Size(max = 45)
    @Column(name = "NOMBRECONVENIIO")
    private String nombreconveniio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTENTOSFALLIDOS")
    private BigInteger intentosfallidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADOCONVENIO")
    private String estadoconvenio;
    @JoinTable(name = "CUENTAS_CONVENIO", joinColumns = {
        @JoinColumn(name = "IDCONVENIO", referencedColumnName = "IDCONVENIO")}, inverseJoinColumns = {
        @JoinColumn(name = "IDCUENTAS", referencedColumnName = "IDCUENTA")})
    @ManyToMany
    private List<Cuentas> cuentasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idconvenio")
    private List<TrxsPago> trxsPagoList;
    @JoinColumn(name = "IDCOMERCIO", referencedColumnName = "IDCOMERCIO")
    @ManyToOne(optional = false)
    private Comercios idcomercio;

    public Convenios() {
    }

    public Convenios(String idconvenio) {
        this.idconvenio = idconvenio;
    }

    public Convenios(String idconvenio, BigInteger intentosfallidos, String estadoconvenio) {
        this.idconvenio = idconvenio;
        this.intentosfallidos = intentosfallidos;
        this.estadoconvenio = estadoconvenio;
    }

    public String getIdconvenio() {
        return idconvenio;
    }

    public void setIdconvenio(String idconvenio) {
        this.idconvenio = idconvenio;
    }

    public String getNombreconveniio() {
        return nombreconveniio;
    }

    public void setNombreconveniio(String nombreconveniio) {
        this.nombreconveniio = nombreconveniio;
    }

    public BigInteger getIntentosfallidos() {
        return intentosfallidos;
    }

    public void setIntentosfallidos(BigInteger intentosfallidos) {
        this.intentosfallidos = intentosfallidos;
    }

    public String getEstadoconvenio() {
        return estadoconvenio;
    }

    public void setEstadoconvenio(String estadoconvenio) {
        this.estadoconvenio = estadoconvenio;
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

    public Comercios getIdcomercio() {
        return idcomercio;
    }

    public void setIdcomercio(Comercios idcomercio) {
        this.idcomercio = idcomercio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconvenio != null ? idconvenio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Convenios)) {
            return false;
        }
        Convenios other = (Convenios) object;
        if ((this.idconvenio == null && other.idconvenio != null) || (this.idconvenio != null && !this.idconvenio.equals(other.idconvenio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.bancochile.gestionmipago.entityclass.Convenios[ idconvenio=" + idconvenio + " ]";
    }
    
}
