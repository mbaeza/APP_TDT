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
@Table(name = "segundo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Segundo.findAll", query = "SELECT s FROM Segundo s"),
    @NamedQuery(name = "Segundo.findByIdTablaSegundo", query = "SELECT s FROM Segundo s WHERE s.idTablaSegundo = :idTablaSegundo")})
public class Segundo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tabla_segundo")
    private Integer idTablaSegundo;
    @ManyToMany(mappedBy = "segundoList")
    private List<Primero> primeroList;

    public Segundo() {
    }

    public Segundo(Integer idTablaSegundo) {
        this.idTablaSegundo = idTablaSegundo;
    }

    public Integer getIdTablaSegundo() {
        return idTablaSegundo;
    }

    public void setIdTablaSegundo(Integer idTablaSegundo) {
        this.idTablaSegundo = idTablaSegundo;
    }

    @XmlTransient
    public List<Primero> getPrimeroList() {
        return primeroList;
    }

    public void setPrimeroList(List<Primero> primeroList) {
        this.primeroList = primeroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTablaSegundo != null ? idTablaSegundo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Segundo)) {
            return false;
        }
        Segundo other = (Segundo) object;
        if ((this.idTablaSegundo == null && other.idTablaSegundo != null) || (this.idTablaSegundo != null && !this.idTablaSegundo.equals(other.idTablaSegundo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityClass.Segundo[ idTablaSegundo=" + idTablaSegundo + " ]";
    }
    
}
