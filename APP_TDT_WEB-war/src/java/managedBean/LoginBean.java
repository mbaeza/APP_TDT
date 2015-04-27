/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import com.tdt.entityclass.Usuario;
import com.tdt.sessionbean.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private String username;
    private String password;
    private static Usuario usuarioActual;
    
    public String getUsername() {
    return username;
    }
    public String getPassword() {
    return password;
    }
    public void setUsername(String username) {
    this.username = username;
    }
    public void setPassword(String password) {
    this.password = password;
    }

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    
    public String login () {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
        context.getExternalContext().getRequest();
        
        try {
            request.login(this.username, this.password);
            for(Usuario user: usuarioFacade.findAll()){
                if(user.getUsuarioPK().getEmail().equals(this.username)){
                    setUsuarioActual(user);
                }
            }
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed."));
            return "loginError?faces-redirect=true";
        }
        
        if(request.isUserInRole("Educador")){
            return "Educador/Ejercicios/principalEjercicios?faces-redirect=true";
        }else if (request.isUserInRole("Administrador")){
            return "Administrador/CentroEducacional/modificarCentroEducacional?faces-redirect=true";
        }
        
        return "";
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
        context.getExternalContext().getRequest();
        
        try {                       
            request.logout();
        } catch (ServletException e) {
        }
        return "login?faces-redirect=true";
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        externalContext.invalidateSession();
//        this.originalURL = null;
//        CommonFunctions.goToPage("/faces/index.xhtml");
    }
    
}
