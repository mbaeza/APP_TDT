/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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

    private String username;
    private String password;
    
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
    
    public String login () {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
        context.getExternalContext().getRequest();
        
        try {
            request.login(this.username, this.password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed."));
            return "loginError?faces-redirect=true";
        }
        System.out.println(request.getHeader(username));
        return "Educador/menuPrincipal?faces-redirect=true";
    }
    
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
        context.getExternalContext().getRequest();
        
        try {
            request.logout();
        } catch (ServletException e) {
        }
    }
    
}
