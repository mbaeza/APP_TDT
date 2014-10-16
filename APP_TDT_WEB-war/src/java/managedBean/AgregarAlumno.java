/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import com.tdt.entityclass.Alumno;
import com.tdt.sessionbean.AlumnoFacade;
import com.tdt.sessionbean.AlumnoFacadeLocal;
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
@Named(value = "agregarAlumno")
@RequestScoped
public class AgregarAlumno {
    @EJB
    private AlumnoFacadeLocal alumnoFacade;        

    private String nombre;
    private String fechaNacimiento;
    private String primerApellido;
    private String segundoApellido;
    private String rut;
    private String especificacion;
    
    public AgregarAlumno() {
    }

    public void agregarAlumno(){
        Alumno alumno = new Alumno();        
//        alumno.setApellidoMaterno(segundoApellido);
//        alumno.setApellidoPaterno(primerApellido);
//        alumno.setNombre(nombre);
//        alumno.setRut(rut);
//        alumno.setEspecificacion(fechaNacimiento);
//        alumno.setFechaNacimiento(fechaNacimiento);
        alumnoFacade.create(alumno);
    }
    
     public void confirmacionAgregar(ActionEvent actionEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregación realizada", "Se ha agregado un alumno del sistema satisfactoriamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
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
    
    
}
