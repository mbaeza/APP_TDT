/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.tdt.objeto;

import com.tdt.entityclass.Ejercicio;
import java.util.List;

/**
 *
 * @author marcobaeza
 */
public class RespuestaEjercicio {
    private List<Ejercicio> listaEjercicios;
    private Respuesta respuesta;

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
