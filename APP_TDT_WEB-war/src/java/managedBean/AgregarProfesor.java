/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import com.tdt.entityclass.CentroEducacional;
import com.tdt.entityclass.Grupo;
import com.tdt.entityclass.Usuario;
import com.tdt.entityclass.UsuarioPK;
import com.tdt.sessionbean.CentroEducacionalFacadeLocal;
import com.tdt.sessionbean.UsuarioFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "agregarProfesor")
@RequestScoped
public class AgregarProfesor {
    @EJB
    private CentroEducacionalFacadeLocal centroEducacionalFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private String nombre;
    private String mail;
    private String primerApellido;
    private String segundoApellido;
    private String rut;
    private String password;
    private CentroEducacional centroSeleccionado;
    private List<CentroEducacional> centrosEducacionales;
    
    public AgregarProfesor() {
    }
    
    @PostConstruct
    public void init(){
        centrosEducacionales = centroEducacionalFacade.findAll();    
    }

    public void agregarUsuario(){
        Usuario usuario = new Usuario();
        UsuarioPK usuarioPk = new UsuarioPK();
        usuarioPk.setEmail(mail);
        usuario.setApellidoMaterno(segundoApellido);
        usuario.setApellidoPaterno(primerApellido);
//        usuario.setNombre(nombre);
        usuario.setRut(rut);
        usuario.setIdCentroEducacional(centroSeleccionado);
        usuario.setPassword("Profesor");
        usuario.setUsuarioPK(usuarioPk);
        usuario.setNombreUsuario(nombre);
        usuarioFacade.create(usuario);
        
        Grupo agregarUsuario = new Grupo();
        agregarUsuario.setNombre(mail);
        agregarUsuario.setNombreGrupo("Educador");
        agregarUsuario.setUsuario(usuario);
    }
    
     public void confirmacionAgregar(ActionEvent actionEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregación realizada", "Se ha agregado un profesor del sistema satisfactoriamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    
}
