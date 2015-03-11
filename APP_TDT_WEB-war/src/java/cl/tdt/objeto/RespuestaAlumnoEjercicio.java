/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.tdt.objeto;

import com.tdt.entityclass.Alumno;
import java.util.List;

/**
 *
 * @author marcobaeza
 */
public class RespuestaAlumnoEjercicio {
    private List<AlumnoTemporal> listaAlumnos;
    private Respuesta respuesta;

    public List<AlumnoTemporal> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<AlumnoTemporal> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
