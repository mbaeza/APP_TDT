/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.creylp.ws;

import cl.tdt.objeto.Respuesta;
import cl.tdt.objeto.RespuestaEjercicio;
import cl.tdt.objeto.RespuestaUsuario;
import com.tdt.entityclass.Ejercicio;
import static com.tdt.entityclass.Grupo_.usuario;
import com.tdt.entityclass.Usuario;
import com.tdt.sessionbean.EjercicioFacadeLocal;
import com.tdt.sessionbean.UsuarioFacadeLocal;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author marcobaezasalazar
 */
@Path("ServiciosWeb")
public class ServiciosWeb {
    EjercicioFacadeLocal ejercicioFacade = lookupEjercicioFacadeLocal();
    UsuarioFacadeLocal usuarioFacade = lookupUsuarioFacadeLocal();
 
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiciosWeb
     */
    public ServiciosWeb() {
    }

    /**
     * URL: http://localhost:8080/APP_TDT_WEB-war/webresources/ServiciosWeb/ValidaSesion/ceespin1@uc.cl/123
     * Retrieves representation of an instance of cl.creylp.ws.ServiciosWeb
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("/ValidaSesion/{mail}/{password}")
    public RespuestaUsuario validarSesion(@PathParam("mail") String mail, @PathParam("password") String password) {
        Usuario usuario = usuarioFacade.validarSesionMovil(mail, password);
        RespuestaUsuario response = new RespuestaUsuario();
        Respuesta miResp = new Respuesta();
        
        if(!usuario.getUsuarioPK().getEmail().equals("no existe")){
            miResp.setCodigo("00");
            miResp.setGlosa("Success");            
            response.setRespuesta(miResp);
            response.setMail(usuario.getUsuarioPK().getEmail());
            response.setApellidoPaterno(usuario.getApellidoPaterno());
            response.setIdCentroEducacional(usuario.getIdCentroEducacional().getIdCentroEducacional().toString());
            response.setNombreUsuario(usuario.getNombreUsuario());
            return response;
        }else{
            miResp.setCodigo("99");
            miResp.setGlosa("Error");            
            response.setRespuesta(miResp);
            return response;
        }
    }
    
    
    @GET
    @Produces("application/json")
    @Path("/ObtenerEjercicios/")
    public RespuestaEjercicio obtenerEjercicios() {
        
        RespuestaEjercicio respuestaEjercicio = new RespuestaEjercicio();
        Respuesta respuesta = new Respuesta();
       
        respuesta.setCodigo("00");
        respuesta.setGlosa("Correcto");
        respuestaEjercicio.setListaEjercicios(ejercicioFacade.findAll());
        respuestaEjercicio.setRespuesta(respuesta);
        return respuestaEjercicio;
                
    }

    /**
     * PUT method for updating or creating an instance of ServiciosWeb
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    private UsuarioFacadeLocal lookupUsuarioFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UsuarioFacadeLocal) c.lookup("java:global/APP_TDT_WEB/APP_TDT_WEB-ejb/UsuarioFacade!com.tdt.sessionbean.UsuarioFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EjercicioFacadeLocal lookupEjercicioFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (EjercicioFacadeLocal) c.lookup("java:global/APP_TDT_WEB/APP_TDT_WEB-ejb/EjercicioFacade!com.tdt.sessionbean.EjercicioFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
