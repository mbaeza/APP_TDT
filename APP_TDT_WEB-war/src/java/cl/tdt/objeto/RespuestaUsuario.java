/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.tdt.objeto;

import com.tdt.entityclass.Usuario;

/**
 *
 * @author marcobaezasalazar
 */
public class RespuestaUsuario {
    private String mail;
    private String nombreUsuario;
    private String apellidoPaterno;
    private String idCentroEducacional;
    private Respuesta respuesta;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getIdCentroEducacional() {
        return idCentroEducacional;
    }

    public void setIdCentroEducacional(String idCentroEducacional) {
        this.idCentroEducacional = idCentroEducacional;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
