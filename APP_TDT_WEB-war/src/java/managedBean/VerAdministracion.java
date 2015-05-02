/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import com.tdt.entityclass.Alumno;
import com.tdt.entityclass.CentroEducacional;
import com.tdt.entityclass.Usuario;
import com.tdt.sessionbean.AlumnoFacadeLocal;
import com.tdt.sessionbean.CentroEducacionalFacadeLocal;
import com.tdt.sessionbean.UsuarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.*;

/**
 *
 * @author marcobaeza
 */
@Named(value = "verAdministracion")
@RequestScoped
public class VerAdministracion {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private CentroEducacionalFacadeLocal centroEducacionalFacade;
    @EJB
    private AlumnoFacadeLocal alumnoFacade;

    private List<Alumno> listaAlumnos = new ArrayList<>();
    private List<CentroEducacional> listaCentroEducacional = new ArrayList<>();
    private List<Usuario> listaProfesor = new ArrayList<>();
    
    public VerAdministracion() {
    }
    
    @PostConstruct
    public void init(){
        listaAlumnos = alumnoFacade.findAll();
        listaCentroEducacional = centroEducacionalFacade.findAll();
        listaProfesor = usuarioFacade.findAll();        
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public List<CentroEducacional> getListaCentroEducacional() {
        return listaCentroEducacional;
    }

    public void setListaCentroEducacional(List<CentroEducacional> listaCentroEducacional) {
        this.listaCentroEducacional = listaCentroEducacional;
    }

    public List<Usuario> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<Usuario> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }
    
    
    
}
