/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcobaeza
 */
@Entity
@Table(name = "primero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Primero.findAll", query = "SELECT p FROM Primero p"),
    @NamedQuery(name = "Primero.findByIdTablaPrimera", query = "SELECT p FROM Primero p WHERE p.idTablaPrimera = :idTablaPrimera")})
public class Primero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tabla_primera")
    private Integer idTablaPrimera;
    @JoinTable(name = "intermedio", joinColumns = {
        @JoinColumn(name = "id_primero_fk", referencedColumnName = "id_tabla_primera")}, inverseJoinColumns = {
        @JoinColumn(name = "id_segundo_fk", referencedColumnName = "id_tabla_segundo")})
    @ManyToMany
    private List<Segundo> segundoList;

    public Primero() {
    }

    public Primero(Integer idTablaPrimera) {
        this.idTablaPrimera = idTablaPrimera;
    }

    public Integer getIdTablaPrimera() {
        return idTablaPrimera;
    }

    public void setIdTablaPrimera(Integer idTablaPrimera) {
        this.idTablaPrimera = idTablaPrimera;
    }

    @XmlTransient
    public List<Segundo> getSegundoList() {
        return segundoList;
    }

    public void setSegundoList(List<Segundo> segundoList) {
        this.segundoList = segundoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTablaPrimera != null ? idTablaPrimera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Primero)) {
            return false;
        }
        Primero other = (Primero) object;
        if ((this.idTablaPrimera == null && other.idTablaPrimera != null) || (this.idTablaPrimera != null && !this.idTablaPrimera.equals(other.idTablaPrimera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityClass.Primero[ idTablaPrimera=" + idTablaPrimera + " ]";
    }
    
}
