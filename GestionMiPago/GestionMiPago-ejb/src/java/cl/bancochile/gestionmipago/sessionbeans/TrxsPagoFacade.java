/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.TrxsPago;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class TrxsPagoFacade extends AbstractFacade<TrxsPago> implements TrxsPagoFacadeLocal {
    @PersistenceContext(unitName = "GestionMiPago-ejbPU")
    private EntityManager em;
    Logger logger;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrxsPagoFacade() {
        super(TrxsPago.class);
        logger = LoggerFactory.getLogger(TrxsPagoFacade.class);
    }
    
    @Override
    public List<TrxsPago> consultaTransaccionRut(String fechaInicioView, String fechaFinView){
       
        Date fechaInicio = null;
        Date fechaFin = null;
        Query query = null;
        
        List<TrxsPago> listaResultados = new ArrayList<TrxsPago>();
       
        try{
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            fechaInicio = formatoFecha.parse(fechaInicioView);//Formato ejemplo "25/03/2015 17:27:03.047"                     
            fechaFin = formatoFecha.parse(fechaFinView);
            
            query = em.createNamedQuery("TrxsPago.findTrxAndRut");
            query.setParameter("fechaInicio", fechaInicio, TemporalType.TIMESTAMP);
            query.setParameter("fechaFin", fechaFin,TemporalType.TIMESTAMP);
            query.setParameter("estado", "PAG");
            listaResultados = query.getResultList();
           
            
            logger.info("Se ha realizado de correcta la consulta de Trxs por Rut en la base de datos.");
        }catch(ParseException e){
            logger.error("Ha ocurrido un problema en la fecha de consulta (Trxs por Rut).");
        }catch(QueryException q){
            logger.error("Ha ocurrido un problema en la consulta de Trxs por Rut en la base de datos.");
        }                             
        return listaResultados;
        
    }
}
