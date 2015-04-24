/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.entityclass;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcobaeza
 */
@Entity
@Table(name = "TRXS_PAGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrxsPago.findTrxAndRut", query = "SELECT t FROM TrxsPago t, Clientes c INNER JOIN t.idcliente c INNER JOIN t.idconvenio con INNER JOIN t.idconvenio.idcomercio com WHERE t.fechahoratrx >= :fechaInicio AND t.fechahoratrx <= :fechaFin AND t.estadotrxl = :estado ORDER BY t.fechahoratrx ASC"),
    @NamedQuery(name = "TrxsPago.findAll", query = "SELECT t FROM TrxsPago t"),
    @NamedQuery(name = "TrxsPago.findByIdtrxspago", query = "SELECT t FROM TrxsPago t WHERE t.idtrxspago = :idtrxspago"),
    @NamedQuery(name = "TrxsPago.findByIddispositivocliente", query = "SELECT t FROM TrxsPago t WHERE t.iddispositivocliente = :iddispositivocliente"),
    @NamedQuery(name = "TrxsPago.findByIddispositivocomercio", query = "SELECT t FROM TrxsPago t WHERE t.iddispositivocomercio = :iddispositivocomercio"),
    @NamedQuery(name = "TrxsPago.findByIdvendedor", query = "SELECT t FROM TrxsPago t WHERE t.idvendedor = :idvendedor"),
    @NamedQuery(name = "TrxsPago.findByNrocuentacliente", query = "SELECT t FROM TrxsPago t WHERE t.nrocuentacliente = :nrocuentacliente"),
    @NamedQuery(name = "TrxsPago.findByTipocuentacliente", query = "SELECT t FROM TrxsPago t WHERE t.tipocuentacliente = :tipocuentacliente"),
    @NamedQuery(name = "TrxsPago.findByNrocuentacomercio", query = "SELECT t FROM TrxsPago t WHERE t.nrocuentacomercio = :nrocuentacomercio"),
    @NamedQuery(name = "TrxsPago.findByTipocuentacomercio", query = "SELECT t FROM TrxsPago t WHERE t.tipocuentacomercio = :tipocuentacomercio"),
    @NamedQuery(name = "TrxsPago.findByMontotrx", query = "SELECT t FROM TrxsPago t WHERE t.montotrx = :montotrx"),
    @NamedQuery(name = "TrxsPago.findByGlosatrx", query = "SELECT t FROM TrxsPago t WHERE t.glosatrx = :glosatrx"),
    @NamedQuery(name = "TrxsPago.findByFechahoratrx", query = "SELECT t FROM TrxsPago t WHERE t.fechahoratrx = :fechahoratrx"),
    @NamedQuery(name = "TrxsPago.findByEstadotrxl", query = "SELECT t FROM TrxsPago t WHERE t.estadotrxl = :estadotrxl"),
    @NamedQuery(name = "TrxsPago.findByHoracobro", query = "SELECT t FROM TrxsPago t WHERE t.horacobro = :horacobro"),
    @NamedQuery(name = "TrxsPago.findByIdtrxinterno", query = "SELECT t FROM TrxsPago t WHERE t.idtrxinterno = :idtrxinterno"),
    @NamedQuery(name = "TrxsPago.findByLlavecomercio", query = "SELECT t FROM TrxsPago t WHERE t.llavecomercio = :llavecomercio"),
    @NamedQuery(name = "TrxsPago.findByPropinatrx", query = "SELECT t FROM TrxsPago t WHERE t.propinatrx = :propinatrx"),
    @NamedQuery(name = "TrxsPago.findBySubtotaltrx", query = "SELECT t FROM TrxsPago t WHERE t.subtotaltrx = :subtotaltrx")})
public class TrxsPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 29)
    @Column(name = "IDTRXSPAGO")
    private String idtrxspago;
    @Size(max = 30)
    @Column(name = "IDDISPOSITIVOCLIENTE")
    private String iddispositivocliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDDISPOSITIVOCOMERCIO")
    private String iddispositivocomercio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDVENDEDOR")
    private String idvendedor;
    @Size(max = 20)
    @Column(name = "NROCUENTACLIENTE")
    private String nrocuentacliente;
    @Size(max = 3)
    @Column(name = "TIPOCUENTACLIENTE")
    private String tipocuentacliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NROCUENTACOMERCIO")
    private String nrocuentacomercio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TIPOCUENTACOMERCIO")
    private String tipocuentacomercio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTOTRX")
    private BigInteger montotrx;
    @Size(max = 45)
    @Column(name = "GLOSATRX")
    private String glosatrx;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORATRX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoratrx;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ESTADOTRXL")
    private String estadotrxl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "HORACOBRO")
    private String horacobro;
    @Size(max = 29)
    @Column(name = "IDTRXINTERNO")
    private String idtrxinterno;
    @Size(max = 30)
    @Column(name = "LLAVECOMERCIO")
    private String llavecomercio;
    @Column(name = "PROPINATRX")
    private BigInteger propinatrx;
    @Column(name = "SUBTOTALTRX")
    private BigInteger subtotaltrx;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")
    @ManyToOne
    private Clientes idcliente;
    @JoinColumn(name = "IDCONVENIO", referencedColumnName = "IDCONVENIO")
    @ManyToOne(optional = false)
    private Convenios idconvenio;

    public TrxsPago() {
    }

    public TrxsPago(String idtrxspago) {
        this.idtrxspago = idtrxspago;
    }

    public TrxsPago(String idtrxspago, String iddispositivocomercio, String idvendedor, String nrocuentacomercio, String tipocuentacomercio, BigInteger montotrx, Date fechahoratrx, String estadotrxl, String horacobro) {
        this.idtrxspago = idtrxspago;
        this.iddispositivocomercio = iddispositivocomercio;
        this.idvendedor = idvendedor;
        this.nrocuentacomercio = nrocuentacomercio;
        this.tipocuentacomercio = tipocuentacomercio;
        this.montotrx = montotrx;
        this.fechahoratrx = fechahoratrx;
        this.estadotrxl = estadotrxl;
        this.horacobro = horacobro;
    }

    public String getIdtrxspago() {
        return idtrxspago;
    }

    public void setIdtrxspago(String idtrxspago) {
        this.idtrxspago = idtrxspago;
    }

    public String getIddispositivocliente() {
        return iddispositivocliente;
    }

    public void setIddispositivocliente(String iddispositivocliente) {
        this.iddispositivocliente = iddispositivocliente;
    }

    public String getIddispositivocomercio() {
        return iddispositivocomercio;
    }

    public void setIddispositivocomercio(String iddispositivocomercio) {
        this.iddispositivocomercio = iddispositivocomercio;
    }

    public String getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(String idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getNrocuentacliente() {
        return nrocuentacliente;
    }

    public void setNrocuentacliente(String nrocuentacliente) {
        this.nrocuentacliente = nrocuentacliente;
    }

    public String getTipocuentacliente() {
        return tipocuentacliente;
    }

    public void setTipocuentacliente(String tipocuentacliente) {
        this.tipocuentacliente = tipocuentacliente;
    }

    public String getNrocuentacomercio() {
        return nrocuentacomercio;
    }

    public void setNrocuentacomercio(String nrocuentacomercio) {
        this.nrocuentacomercio = nrocuentacomercio;
    }

    public String getTipocuentacomercio() {
        return tipocuentacomercio;
    }

    public void setTipocuentacomercio(String tipocuentacomercio) {
        this.tipocuentacomercio = tipocuentacomercio;
    }

    public BigInteger getMontotrx() {
        return montotrx;
    }

    public void setMontotrx(BigInteger montotrx) {
        this.montotrx = montotrx;
    }

    public String getGlosatrx() {
        return glosatrx;
    }

    public void setGlosatrx(String glosatrx) {
        this.glosatrx = glosatrx;
    }

    public Date getFechahoratrx() {
        return fechahoratrx;
    }

    public void setFechahoratrx(Date fechahoratrx) {
        this.fechahoratrx = fechahoratrx;
    }

    public String getEstadotrxl() {
        return estadotrxl;
    }

    public void setEstadotrxl(String estadotrxl) {
        this.estadotrxl = estadotrxl;
    }

    public String getHoracobro() {
        return horacobro;
    }

    public void setHoracobro(String horacobro) {
        this.horacobro = horacobro;
    }

    public String getIdtrxinterno() {
        return idtrxinterno;
    }

    public void setIdtrxinterno(String idtrxinterno) {
        this.idtrxinterno = idtrxinterno;
    }

    public String getLlavecomercio() {
        return llavecomercio;
    }

    public void setLlavecomercio(String llavecomercio) {
        this.llavecomercio = llavecomercio;
    }

    public BigInteger getPropinatrx() {
        return propinatrx;
    }

    public void setPropinatrx(BigInteger propinatrx) {
        this.propinatrx = propinatrx;
    }

    public BigInteger getSubtotaltrx() {
        return subtotaltrx;
    }

    public void setSubtotaltrx(BigInteger subtotaltrx) {
        this.subtotaltrx = subtotaltrx;
    }

    public Clientes getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Clientes idcliente) {
        this.idcliente = idcliente;
    }

    public Convenios getIdconvenio() {
        return idconvenio;
    }

    public void setIdconvenio(Convenios idconvenio) {
        this.idconvenio = idconvenio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtrxspago != null ? idtrxspago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrxsPago)) {
            return false;
        }
        TrxsPago other = (TrxsPago) object;
        if ((this.idtrxspago == null && other.idtrxspago != null) || (this.idtrxspago != null && !this.idtrxspago.equals(other.idtrxspago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.bancochile.gestionmipago.entityclass.TrxsPago[ idtrxspago=" + idtrxspago + " ]";
    }
    
}
