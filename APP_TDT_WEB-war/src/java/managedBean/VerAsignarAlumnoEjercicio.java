/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import com.tdt.entityclass.AlumnoColaborativo;
import com.tdt.entityclass.AsignarEjercicio;
import com.tdt.sessionbean.AsignarEjercicioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author marcobaeza
 */
@Named(value = "verAsignarAlumnoEjercicio")
@RequestScoped
public class VerAsignarAlumnoEjercicio {
    @EJB
    private AsignarEjercicioFacadeLocal asignarEjercicioFacade;

    private List<AsignarEjercicio> listaAsignaciones = new ArrayList<>();
    private AsignarAlumnoEjercicio asigancionSeleccionda;
    /**
     * Creates a new instance of VerAsignarAlumnoEjercicio
     */
    public VerAsignarAlumnoEjercicio() {
    
    }
    
    @PostConstruct
    public void init(){
        listaAsignaciones = asignarEjercicioFacade.findAll();
    }

    public List<AsignarEjercicio> getListaAsignaciones() {
        return listaAsignaciones;
    }

    public void setListaAsignaciones(List<AsignarEjercicio> listaAsignaciones) {
        this.listaAsignaciones = listaAsignaciones;
    }

    public AsignarAlumnoEjercicio getAsigancionSeleccionda() {
        return asigancionSeleccionda;
    }

    public void setAsigancionSeleccionda(AsignarAlumnoEjercicio asigancionSeleccionda) {
        this.asigancionSeleccionda = asigancionSeleccionda;
    }
     
}
