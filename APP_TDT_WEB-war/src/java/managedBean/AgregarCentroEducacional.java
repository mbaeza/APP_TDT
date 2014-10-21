/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import com.tdt.entityclass.Alumno;
import com.tdt.entityclass.CentroEducacional;
import com.tdt.entityclass.Usuario;
import com.tdt.sessionbean.CentroEducacionalFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "agregarCentroEducacional")
@RequestScoped
public class AgregarCentroEducacional {
    @EJB
    private CentroEducacionalFacadeLocal centroEducacionalFacade;
    
    private String nombreCentroEducacional;
    private String ubicacion;
    
    
    public AgregarCentroEducacional() {
    }
    
    public void agregarCentroEducacional(){
        CentroEducacional centroEducacional = new CentroEducacional();        
        centroEducacional.setUbicacion(ubicacion);        
        centroEducacional.setUsuario(new Usuario(1, "ceespin1@uc.cl"));
        centroEducacional.setNombreCentroEducacional(nombreCentroEducacional);
        centroEducacionalFacade.create(centroEducacional);
    }
        
    
     public void confirmacionAgregar(ActionEvent actionEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregación realizada", "Se ha agregado un centro educacional del sistema satisfactoriamente");
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
    
    
}
