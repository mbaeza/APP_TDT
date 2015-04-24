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
@Table(name = "DISPOSITIVOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispositivos.findAll", query = "SELECT d FROM Dispositivos d"),
    @NamedQuery(name = "Dispositivos.findByIddispositivo", query = "SELECT d FROM Dispositivos d WHERE d.iddispositivo = :iddispositivo"),
    @NamedQuery(name = "Dispositivos.findByEstadodispositivo", query = "SELECT d FROM Dispositivos d WHERE d.estadodispositivo = :estadodispositivo"),
    @NamedQuery(name = "Dispositivos.findByClavedispositivo", query = "SELECT d FROM Dispositivos d WHERE d.clavedispositivo = :clavedispositivo"),
    @NamedQuery(name = "Dispositivos.findByIntentosfallidos", query = "SELECT d FROM Dispositivos d WHERE d.intentosfallidos = :intentosfallidos"),
    @NamedQuery(name = "Dispositivos.findByAliasdispositivo", query = "SELECT d FROM Dispositivos d WHERE d.aliasdispositivo = :aliasdispositivo"),
    @NamedQuery(name = "Dispositivos.findByModelodispositivo", query = "SELECT d FROM Dispositivos d WHERE d.modelodispositivo = :modelodispositivo"),
    @NamedQuery(name = "Dispositivos.findByFechahoracreacion", query = "SELECT d FROM Dispositivos d WHERE d.fechahoracreacion = :fechahoracreacion"),
    @NamedQuery(name = "Dispositivos.findByTipodispositivo", query = "SELECT d FROM Dispositivos d WHERE d.tipodispositivo = :tipodispositivo")})
public class Dispositivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDDISPOSITIVO")
    private String iddispositivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADODISPOSITIVO")
    private String estadodispositivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "CLAVEDISPOSITIVO")
    private String clavedispositivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTENTOSFALLIDOS")
    private BigInteger intentosfallidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ALIASDISPOSITIVO")
    private String aliasdispositivo;
    @Size(max = 50)
    @Column(name = "MODELODISPOSITIVO")
    private String modelodispositivo;
    @Column(name = "FECHAHORACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoracreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TIPODISPOSITIVO")
    private String tipodispositivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dispositivos")
    private List<DispositivosCliente> dispositivosClienteList;

    public Dispositivos() {
    }

    public Dispositivos(String iddispositivo) {
        this.iddispositivo = iddispositivo;
    }

    public Dispositivos(String iddispositivo, String estadodispositivo, String clavedispositivo, BigInteger intentosfallidos, String aliasdispositivo, String tipodispositivo) {
        this.iddispositivo = iddispositivo;
        this.estadodispositivo = estadodispositivo;
        this.clavedispositivo = clavedispositivo;
        this.intentosfallidos = intentosfallidos;
        this.aliasdispositivo = aliasdispositivo;
        this.tipodispositivo = tipodispositivo;
    }

    public String getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(String iddispositivo) {
        this.iddispositivo = iddispositivo;
    }

    public String getEstadodispositivo() {
        return estadodispositivo;
    }

    public void setEstadodispositivo(String estadodispositivo) {
        this.estadodispositivo = estadodispositivo;
    }

    public String getClavedispositivo() {
        return clavedispositivo;
    }

    public void setClavedispositivo(String clavedispositivo) {
        this.clavedispositivo = clavedispositivo;
    }

    public BigInteger getIntentosfallidos() {
        return intentosfallidos;
    }

    public void setIntentosfallidos(BigInteger intentosfallidos) {
        this.intentosfallidos = intentosfallidos;
    }

    public String getAliasdispositivo() {
        return aliasdispositivo;
    }

    public void setAliasdispositivo(String aliasdispositivo) {
        this.aliasdispositivo = aliasdispositivo;
    }

    public String getModelodispositivo() {
        return modelodispositivo;
    }

    public void setModelodispositivo(String modelodispositivo) {
        this.modelodispositivo = modelodispositivo;
    }

    public Date getFechahoracreacion() {
        return fechahoracreacion;
    }

    public void setFechahoracreacion(Date fechahoracreacion) {
        this.fechahoracreacion = fechahoracreacion;
    }

    public String getTipodispositivo() {
        return tipodispositivo;
    }

    public void setTipodispositivo(String tipodispositivo) {
        this.tipodispositivo = tipodispositivo;
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
        hash += (iddispositivo != null ? iddispositivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispositivos)) {
            return false;
        }
        Dispositivos other = (Dispositivos) object;
        if ((this.iddispositivo == null && other.iddispositivo != null) || (this.iddispositivo != null && !this.iddispositivo.equals(other.iddispositivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.bancochile.gestionmipago.entityclass.Dispositivos[ iddispositivo=" + iddispositivo + " ]";
    }
    
}
