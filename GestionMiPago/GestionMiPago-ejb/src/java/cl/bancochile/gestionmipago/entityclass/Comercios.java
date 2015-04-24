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
@Table(name = "COMERCIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comercios.findAll", query = "SELECT c FROM Comercios c"),
    @NamedQuery(name = "Comercios.findByIdcomercio", query = "SELECT c FROM Comercios c WHERE c.idcomercio = :idcomercio"),
    @NamedQuery(name = "Comercios.findByRutcomercio", query = "SELECT c FROM Comercios c WHERE c.rutcomercio = :rutcomercio"),
    @NamedQuery(name = "Comercios.findByRutapoderado", query = "SELECT c FROM Comercios c WHERE c.rutapoderado = :rutapoderado"),
    @NamedQuery(name = "Comercios.findByEmailcomercio", query = "SELECT c FROM Comercios c WHERE c.emailcomercio = :emailcomercio"),
    @NamedQuery(name = "Comercios.findByDireccioncomercio", query = "SELECT c FROM Comercios c WHERE c.direccioncomercio = :direccioncomercio"),
    @NamedQuery(name = "Comercios.findByRazonsocialcomercio", query = "SELECT c FROM Comercios c WHERE c.razonsocialcomercio = :razonsocialcomercio"),
    @NamedQuery(name = "Comercios.findByFechahoraactivacion", query = "SELECT c FROM Comercios c WHERE c.fechahoraactivacion = :fechahoraactivacion"),
    @NamedQuery(name = "Comercios.findByTipocomercio", query = "SELECT c FROM Comercios c WHERE c.tipocomercio = :tipocomercio")})
public class Comercios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDCOMERCIO")
    private String idcomercio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RUTCOMERCIO")
    private String rutcomercio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RUTAPODERADO")
    private String rutapoderado;
    @Size(max = 100)
    @Column(name = "EMAILCOMERCIO")
    private String emailcomercio;
    @Size(max = 200)
    @Column(name = "DIRECCIONCOMERCIO")
    private String direccioncomercio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RAZONSOCIALCOMERCIO")
    private String razonsocialcomercio;
    @Column(name = "FECHAHORAACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraactivacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "TIPOCOMERCIO")
    private String tipocomercio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcomercio")
    private List<Convenios> conveniosList;

    public Comercios() {
    }

    public Comercios(String idcomercio) {
        this.idcomercio = idcomercio;
    }

    public Comercios(String idcomercio, String rutcomercio, String rutapoderado, String razonsocialcomercio, String tipocomercio) {
        this.idcomercio = idcomercio;
        this.rutcomercio = rutcomercio;
        this.rutapoderado = rutapoderado;
        this.razonsocialcomercio = razonsocialcomercio;
        this.tipocomercio = tipocomercio;
    }

    public String getIdcomercio() {
        return idcomercio;
    }

    public void setIdcomercio(String idcomercio) {
        this.idcomercio = idcomercio;
    }

    public String getRutcomercio() {
        return rutcomercio;
    }

    public void setRutcomercio(String rutcomercio) {
        this.rutcomercio = rutcomercio;
    }

    public String getRutapoderado() {
        return rutapoderado;
    }

    public void setRutapoderado(String rutapoderado) {
        this.rutapoderado = rutapoderado;
    }

    public String getEmailcomercio() {
        return emailcomercio;
    }

    public void setEmailcomercio(String emailcomercio) {
        this.emailcomercio = emailcomercio;
    }

    public String getDireccioncomercio() {
        return direccioncomercio;
    }

    public void setDireccioncomercio(String direccioncomercio) {
        this.direccioncomercio = direccioncomercio;
    }

    public String getRazonsocialcomercio() {
        return razonsocialcomercio;
    }

    public void setRazonsocialcomercio(String razonsocialcomercio) {
        this.razonsocialcomercio = razonsocialcomercio;
    }

    public Date getFechahoraactivacion() {
        return fechahoraactivacion;
    }

    public void setFechahoraactivacion(Date fechahoraactivacion) {
        this.fechahoraactivacion = fechahoraactivacion;
    }

    public String getTipocomercio() {
        return tipocomercio;
    }

    public void setTipocomercio(String tipocomercio) {
        this.tipocomercio = tipocomercio;
    }

    @XmlTransient
    public List<Convenios> getConveniosList() {
        return conveniosList;
    }

    public void setConveniosList(List<Convenios> conveniosList) {
        this.conveniosList = conveniosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomercio != null ? idcomercio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comercios)) {
            return false;
        }
        Comercios other = (Comercios) object;
        if ((this.idcomercio == null && other.idcomercio != null) || (this.idcomercio != null && !this.idcomercio.equals(other.idcomercio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.bancochile.gestionmipago.entityclass.Comercios[ idcomercio=" + idcomercio + " ]";
    }
    
}
