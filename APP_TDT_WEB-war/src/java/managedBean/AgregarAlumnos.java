/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import com.tdt.entityclass.Alumno;
import com.tdt.entityclass.CentroEducacional;
import com.tdt.sessionbean.AlumnoFacadeLocal;
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

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "agregarAlumnos")
@RequestScoped
public class AgregarAlumnos {
    @EJB
    private CentroEducacionalFacadeLocal centroEducacionalFacade;
    @EJB
    private AlumnoFacadeLocal alumnoFacade;

    private String nombre;
    private String fechaNacimiento;
    private String primerApellido;
    private String segundoApellido;
    private String rut;
    private CentroEducacional centroSeleccionado;
    private List<CentroEducacional> centrosEducacionales;
    
    
    public AgregarAlumnos() {
    }
        public void agregarAlumno() throws IOException{
        Alumno alumno = new Alumno();        
        alumno.setApellidoMaternoAlumno(segundoApellido);
        alumno.setApellidoPaternoAlumno(primerApellido);
        alumno.setNombreAlumno(nombre);
        alumno.setRut(rut);
        alumno.setIdCentroEducacional(centroSeleccionado);
//        alumno.setEspecificacion(fechaNacimiento);
        alumno.setFechaNacimiento(fechaNacimiento);
        alumnoFacade.create(alumno);
        FacesContext.getCurrentInstance().getExternalContext().redirect("principalAdministracion.xhtml");
    }

        
    @PostConstruct
    public void init(){
        centrosEducacionales = centroEducacionalFacade.findAll();    
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public AlumnoFacadeLocal getAlumnoFacade() {
        return alumnoFacade;
    }

    public void setAlumnoFacade(AlumnoFacadeLocal alumnoFacade) {
        this.alumnoFacade = alumnoFacade;
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
