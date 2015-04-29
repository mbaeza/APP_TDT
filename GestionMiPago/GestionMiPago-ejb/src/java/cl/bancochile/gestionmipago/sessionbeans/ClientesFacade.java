/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.Clientes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.hibernate.QueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author marcobaeza
 */
@Stateless
public class ClientesFacade extends AbstractFacade<Clientes> implements ClientesFacadeLocal {
    @PersistenceContext(unitName = "GestionMiPago-ejbPU")
    private EntityManager em;
    Logger logger;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientesFacade() {
        super(Clientes.class);
        LoggerFactory.getLogger(ClientesFacade.class);
    }

    @Override
    public List<Clientes> consultaRutEnrolados(String fechaInicioView , String fechaFinView) {
        Date fechaInicio = null;
        Date fechaFin = null;
        Query query = null;

        try{
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            fechaInicio = formatoFecha.parse(fechaInicioView);//** El format es "25/03/2015 17:27:03.047"                   
            fechaFin = formatoFecha.parse(fechaFinView);
            
            query = em.createNamedQuery("Clientes.findRutEnrolado");
            query.setParameter("fechaActivacionInicio", fechaInicio,TemporalType.TIMESTAMP);
            query.setParameter("fechaActivacionFin", fechaFin,TemporalType.TIMESTAMP);
            
            logger.info("Se ha realizado de correcta la consulta de Rut enrolados en la base de datos.");
        }catch(ParseException e){
            logger.error("Ha ocurrido un problema en la fecha de consulta (Rut Enrolados).");
        }catch(QueryException q){
            logger.error("Ha ocurrido un problema en la consulta de Rut enrolados en la base de datos.");
        }
               
        return query.getResultList();
    }
    
    
}
