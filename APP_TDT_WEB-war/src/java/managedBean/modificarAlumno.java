/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import com.tdt.entityclass.Alumno;
import com.tdt.entityclass.Alumno_;
import com.tdt.sessionbean.AlumnoFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "modificarAlumno")
@RequestScoped
public class modificarAlumno {
    @EJB
    private AlumnoFacadeLocal alumnoFacade;        

    private String nombre;
    private String fechaNacimiento;
    private String primerApellido;
    private String segundoApellido;
    private String rut;
    private String especificacion;
    private List<Alumno> alumnos;    
    private Alumno alumnoSeleccionado;
    
    public modificarAlumno() {
    }
    
    @PostConstruct
    public void init(){
        alumnos = alumnoFacade.findAll();       
    }
    
    public void modificarAlumno(){
//        alumnoSeleccionado.setApellidoMaterno(segundoApellido);                
//        alumnoSeleccionado.setApellidoMaterno(segundoApellido);
//        alumnoSeleccionado.setApellidoPaterno(primerApellido);
//        alumnoSeleccionado.setNombre(nombre);
//        alumnoSeleccionado.setRut(rut);
//        alumnoSeleccionado.setEspecificacion(especificacion);
//        alumnoSeleccionado.setFechaNacimiento(fechaNacimiento);
        alumnoFacade.edit(alumnoSeleccionado);
    }
    
     public void confirmacionModificar(ActionEvent actionEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación realizada", "Se ha modificado un alumno del sistema satisfactoriamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowSelect(SelectEvent event) {
//        System.out.println("asdasdasd");
//        setNombre(alumnoSeleccionado.getNombre());
//        setRut(alumnoSeleccionado.getRut());
//        setPrimerApellido(alumnoSeleccionado.getApellidoPaterno());
//        setSegundoApellido(alumnoSeleccionado.getApellidoMaterno());
//        setFechaNacimiento(alumnoSeleccionado.getFechaNacimiento());
//        setEspecificacion(alumnoSeleccionado.getEspecificacion());
    } 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public AlumnoFacadeLocal getAlumnoFacade() {
        return alumnoFacade;
    }

    public void setAlumnoFacade(AlumnoFacadeLocal alumnoFacade) {
        this.alumnoFacade = alumnoFacade;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Alumno getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }

    public void setAlumnoSeleccionado(Alumno alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }
    
    
}
