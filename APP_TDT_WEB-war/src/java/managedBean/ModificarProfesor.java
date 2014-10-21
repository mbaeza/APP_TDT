/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import com.tdt.entityclass.CentroEducacional;
import com.tdt.entityclass.Usuario;
import com.tdt.entityclass.UsuarioPK;
import com.tdt.sessionbean.CentroEducacionalFacadeLocal;
import com.tdt.sessionbean.UsuarioFacade;
import com.tdt.sessionbean.UsuarioFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "modificarProfesor")
@RequestScoped
public class ModificarProfesor {
    @EJB
    private CentroEducacionalFacadeLocal centroEducacionalFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private String nombre;
    private String mail;
    private String primerApellido;
    private String segundoApellido;
    private String rut;
    private Usuario usuarioSeleccionado;
    private CentroEducacional centroSeleccionado;
    private List<CentroEducacional> centrosEducacionales;
    private List<Usuario> usuarios;
    
    public ModificarProfesor() {
    }
    
    @PostConstruct
    public void init(){
        usuarios = usuarioFacade.findAll();
        centrosEducacionales = centroEducacionalFacade.findAll();    
    }
    
        public void modificarAlumno(){
               
        usuarioSeleccionado.setApellidoMaterno(getSegundoApellido());
        usuarioSeleccionado.setApellidoPaterno(getPrimerApellido());
        
//        usuarioSeleccionado.setUsuarioPK(usuarioPk);
        usuarioSeleccionado.setNombreUsuario(getNombre());
        usuarioSeleccionado.setRut(getRut());
        usuarioSeleccionado.setIdCentroEducacional(getCentroSeleccionado());
        usuarioFacade.edit(usuarioSeleccionado);
    }
        
    public void onRowSelect(SelectEvent event) {
        setNombre(usuarioSeleccionado.getNombreUsuario());
        setPrimerApellido(usuarioSeleccionado.getApellidoPaterno());
        setSegundoApellido(usuarioSeleccionado.getApellidoMaterno());
        setRut(usuarioSeleccionado.getRut());
        setMail(usuarioSeleccionado.getUsuarioPK().getEmail());
        setCentroSeleccionado(usuarioSeleccionado.getIdCentroEducacional());
        System.out.println("askjdhaskjdhakjd");
    }
            
    public void confirmacionAgregar(ActionEvent actionEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación realizada", "Se ha modificado un profesor del sistema satisfactoriamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public CentroEducacionalFacadeLocal getCentroEducacionalFacade() {
        return centroEducacionalFacade;
    }

    public void setCentroEducacionalFacade(CentroEducacionalFacadeLocal centroEducacionalFacade) {
        this.centroEducacionalFacade = centroEducacionalFacade;
    }

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public CentroEducacional getCentroSeleccionado() {
        return centroSeleccionado;
    }

    public void setCentroSeleccionado(CentroEducacional centroSeleccionado) {
        this.centroSeleccionado = centroSeleccionado;
    }

    public List<CentroEducacional> getCentrosEducacionales() {
        return centrosEducacionales;
    }

    public void setCentrosEducacionales(List<CentroEducacional> centrosEducacionales) {
        this.centrosEducacionales = centrosEducacionales;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
