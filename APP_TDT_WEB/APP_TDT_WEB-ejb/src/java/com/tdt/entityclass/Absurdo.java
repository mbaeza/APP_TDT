/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.entityclass;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcobaezasalazar
 */
@Entity
@Table(name = "ABSURDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Absurdo.findAll", query = "SELECT a FROM Absurdo a"),
    @NamedQuery(name = "Absurdo.findByIdAtributo", query = "SELECT a FROM Absurdo a WHERE a.idAtributo = :idAtributo"),
    @NamedQuery(name = "Absurdo.findByTamanoLetra", query = "SELECT a FROM Absurdo a WHERE a.tamanoLetra = :tamanoLetra"),
    @NamedQuery(name = "Absurdo.findByColorLetra", query = "SELECT a FROM Absurdo a WHERE a.colorLetra = :colorLetra"),
    @NamedQuery(name = "Absurdo.findByTextoPrincipal", query = "SELECT a FROM Absurdo a WHERE a.textoPrincipal = :textoPrincipal"),
    @NamedQuery(name = "Absurdo.findByRespuestaCorrecta1", query = "SELECT a FROM Absurdo a WHERE a.respuestaCorrecta1 = :respuestaCorrecta1"),
    @NamedQuery(name = "Absurdo.findByRespuestaCorrecta2", query = "SELECT a FROM Absurdo a WHERE a.respuestaCorrecta2 = :respuestaCorrecta2"),
    @NamedQuery(name = "Absurdo.findByRespuestaCorrecta3", query = "SELECT a FROM Absurdo a WHERE a.respuestaCorrecta3 = :respuestaCorrecta3"),
    @NamedQuery(name = "Absurdo.findByRespuestaCorrecta4", query = "SELECT a FROM Absurdo a WHERE a.respuestaCorrecta4 = :respuestaCorrecta4")})
public class Absurdo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ATRIBUTO")
    private Integer idAtributo;
    @Size(max = 100)
    @Column(name = "TAMANO_LETRA")
    private String tamanoLetra;
    @Size(max = 100)
    @Column(name = "COLOR_LETRA")
    private String colorLetra;
    @Size(max = 100)
    @Column(name = "TEXTO_PRINCIPAL")
    private String textoPrincipal;
    @Column(name = "RESPUESTA_CORRECTA1")
    private Boolean respuestaCorrecta1;
    @Column(name = "RESPUESTA_CORRECTA2")
    private Boolean respuestaCorrecta2;
    @Column(name = "RESPUESTA_CORRECTA3")
    private Boolean respuestaCorrecta3;
    @Column(name = "RESPUESTA_CORRECTA4")
    private Boolean respuestaCorrecta4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAtributo")
    private Collection<Imagen> imagenCollection;

    public Absurdo() {
    }

    public Absurdo(Integer idAtributo) {
        this.idAtributo = idAtributo;
    }

    public Integer getIdAtributo() {
        return idAtributo;
    }

    public void setIdAtributo(Integer idAtributo) {
        this.idAtributo = idAtributo;
    }

    public String getTamanoLetra() {
        return tamanoLetra;
    }

    public void setTamanoLetra(String tamanoLetra) {
        this.tamanoLetra = tamanoLetra;
    }

    public String getColorLetra() {
        return colorLetra;
    }

    public void setColorLetra(String colorLetra) {
        this.colorLetra = colorLetra;
    }

    public String getTextoPrincipal() {
        return textoPrincipal;
    }

    public void setTextoPrincipal(String textoPrincipal) {
        this.textoPrincipal = textoPrincipal;
    }

    public Boolean getRespuestaCorrecta1() {
        return respuestaCorrecta1;
    }

    public void setRespuestaCorrecta1(Boolean respuestaCorrecta1) {
        this.respuestaCorrecta1 = respuestaCorrecta1;
    }

    public Boolean getRespuestaCorrecta2() {
        return respuestaCorrecta2;
    }

    public void setRespuestaCorrecta2(Boolean respuestaCorrecta2) {
        this.respuestaCorrecta2 = respuestaCorrecta2;
    }

    public Boolean getRespuestaCorrecta3() {
        return respuestaCorrecta3;
    }

    public void setRespuestaCorrecta3(Boolean respuestaCorrecta3) {
        this.respuestaCorrecta3 = respuestaCorrecta3;
    }

    public Boolean getRespuestaCorrecta4() {
        return respuestaCorrecta4;
    }

    public void setRespuestaCorrecta4(Boolean respuestaCorrecta4) {
        this.respuestaCorrecta4 = respuestaCorrecta4;
    }

    @XmlTransient
    public Collection<Imagen> getImagenCollection() {
        return imagenCollection;
    }

    public void setImagenCollection(Collection<Imagen> imagenCollection) {
        this.imagenCollection = imagenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtributo != null ? idAtributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Absurdo)) {
            return false;
        }
        Absurdo other = (Absurdo) object;
        if ((this.idAtributo == null && other.idAtributo != null) || (this.idAtributo != null && !this.idAtributo.equals(other.idAtributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tdt.entityclass.Absurdo[ idAtributo=" + idAtributo + " ]";
    }
    
}
