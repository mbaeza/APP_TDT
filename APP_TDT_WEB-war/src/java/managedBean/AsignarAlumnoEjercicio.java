/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import com.tdt.entityclass.Alumno;
import com.tdt.entityclass.AlumnoColaborativo;
import com.tdt.entityclass.AsignarEjercicio;
import com.tdt.entityclass.Ejercicio;
import com.tdt.entityclass.Usuario;
import com.tdt.sessionbean.AlumnoColaborativoFacadeLocal;
import com.tdt.sessionbean.AlumnoFacadeLocal;
import com.tdt.sessionbean.AsignarEjercicioFacadeLocal;
import com.tdt.sessionbean.EjercicioFacadeLocal;
import com.tdt.sessionbean.UsuarioFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "asignarAlumnoEjercicio")
@RequestScoped
public class AsignarAlumnoEjercicio {
    @EJB
    private EjercicioFacadeLocal ejercicioFacade;
    @EJB
    private AlumnoColaborativoFacadeLocal alumnoColaborativoFacade;
    @EJB
    private AsignarEjercicioFacadeLocal asignarEjercicioFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private AlumnoFacadeLocal alumnoFacade;

    private List<Alumno> listaAlumnos;
    private Alumno alumnoSeleccionado;
    private List<Ejercicio> ejercicios;
    private Ejercicio ejercicioSeleccionado;
    private Usuario usuarioSeleccionado = new LoginBean().getUsuarioActual();
    private List<Usuario> usuarios;
    private List<Alumno> alumnosSeleccionados;
    private String observacion;
    private String observacionColaboracion;
    
    
    public AsignarAlumnoEjercicio() {
    }
    
    @PostConstruct
    public void init(){
        listaAlumnos = alumnoFacade.findAll();   
        usuarios = usuarioFacade.findAll();    
        ejercicios = ejercicioFacade.findAll();
    } 
    
    public void asignarAlumnoEjercicio(){
        AsignarEjercicio asignacion = new AsignarEjercicio();
        asignacion.setObservacionAsignarEjercicio(observacion);
        asignacion.setIdEjercicio(ejercicioSeleccionado);
        asignacion.setUsuario(usuarioSeleccionado);
        System.out.println("usuario: " + usuarioSeleccionado);
        asignarEjercicioFacade.create(asignacion);
        
        for(Alumno user: alumnosSeleccionados){
            AlumnoColaborativo colaboracion = new AlumnoColaborativo();
            colaboracion.setIdAlumno(user);
            colaboracion.setIdAsignarEjercicio(asignacion);
            colaboracion.setObservacionColaboracion(observacionColaboracion);
            alumnoColaborativoFacade.create(colaboracion);
            
        }
            
    }
    
    public void confirmacionAgregar(ActionEvent actionEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignación realizada", "Se ha asignado un alumno a un ejercicio de forma satisfactoria");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public Ejercicio getEjercicioSeleccionado() {
        return ejercicioSeleccionado;
    }

    public void setEjercicioSeleccionado(Ejercicio ejercicioSeleccionado) {
        this.ejercicioSeleccionado = ejercicioSeleccionado;
    }

    
    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public AlumnoFacadeLocal getAlumnoFacade() {
        return alumnoFacade;
    }

    public void setAlumnoFacade(AlumnoFacadeLocal alumnoFacade) {
        this.alumnoFacade = alumnoFacade;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public AsignarEjercicioFacadeLocal getAsignarEjercicioFacade() {
        return asignarEjercicioFacade;
    }

    public void setAsignarEjercicioFacade(AsignarEjercicioFacadeLocal asignarEjercicioFacade) {
        this.asignarEjercicioFacade = asignarEjercicioFacade;
    }

    public Alumno getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }

    public void setAlumnoSeleccionado(Alumno alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }

    public List<Alumno> getAlumnosSeleccionados() {
        return alumnosSeleccionados;
    }

    public void setAlumnosSeleccionados(List<Alumno> alumnosSeleccionados) {
        this.alumnosSeleccionados = alumnosSeleccionados;
    }

    
    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setObservacionColaboracion(String observacionColaboracion) {
        this.observacionColaboracion = observacionColaboracion;
    }
    
    public String getObservacionColaboracion() {
        return observacionColaboracion;
    }

    public AlumnoColaborativoFacadeLocal getAlumnoColaborativoFacade() {
        return alumnoColaborativoFacade;
    }

    public void setAlumnoColaborativoFacade(AlumnoColaborativoFacadeLocal alumnoColaborativoFacade) {
        this.alumnoColaborativoFacade = alumnoColaborativoFacade;
    }

    
    
}
