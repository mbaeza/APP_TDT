/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import com.tdt.entityclass.CentroEducacional;
import com.tdt.entityclass.Usuario;
import com.tdt.sessionbean.CentroEducacionalFacadeLocal;
import java.io.IOException;
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
@Named(value = "modificarCentroEducacional")
@RequestScoped
public class ModificarCentroEducacional {
    @EJB
    private CentroEducacionalFacadeLocal centroEducacionalFacade;
    
    private String nombreCentroEducacional;
    private String ubicacion;
    private CentroEducacional centroSeleccionado;
    private List<CentroEducacional> centrosSeleccionados;       
    
    public ModificarCentroEducacional() {
        
    }
    
    @PostConstruct
    public void init(){
        centrosSeleccionados = centroEducacionalFacade.findAll();    
    }
    
    public void modificarCentroEducacional() throws IOException{
               
        centroSeleccionado.setUbicacion(getUbicacion());        
//        centroSeleccionado.setUsuario(new Usuario(1, "ceespin1@uc.cl"));
        centroSeleccionado.setNombreCentroEducacional(getNombreCentroEducacional());
        centroEducacionalFacade.edit(centroSeleccionado);
        FacesContext.getCurrentInstance().getExternalContext().redirect("principalAdministracion.xhtml");
    }
        
    public void onRowSelect(SelectEvent event) {
        setNombreCentroEducacional(centroSeleccionado.getNombreCentroEducacional());
        setUbicacion(centroSeleccionado.getUbicacion());
        System.out.println("askjdhaskjdhakjd");
    }
            
    public void confirmacionAgregar(ActionEvent actionEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación realizada", "Se ha modificado un centro educacional del sistema satisfactoriamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getNombreCentroEducacional() {
        return nombreCentroEducacional;
    }

    public void setNombreCentroEducacional(String nombreCentroEducacional) {
        this.nombreCentroEducacional = nombreCentroEducacional;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public CentroEducacionalFacadeLocal getCentroEducacionalFacade() {
        return centroEducacionalFacade;
    }

    public void setCentroEducacionalFacade(CentroEducacionalFacadeLocal centroEducacionalFacade) {
        this.centroEducacionalFacade = centroEducacionalFacade;
    }

    public CentroEducacional getCentroSeleccionado() {
        return centroSeleccionado;
    }

    public void setCentroSeleccionado(CentroEducacional centroSeleccionado) {
        this.centroSeleccionado = centroSeleccionado;
    }

    public List<CentroEducacional> getCentrosSeleccionados() {
        return centrosSeleccionados;
    }

    public void setCentrosSeleccionados(List<CentroEducacional> centrosSeleccionados) {
        this.centrosSeleccionados = centrosSeleccionados;
    }
    
    
}
