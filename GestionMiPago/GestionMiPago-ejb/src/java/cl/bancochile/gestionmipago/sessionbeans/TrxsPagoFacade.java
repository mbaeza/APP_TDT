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

/**
 *
 * @author marcobaeza
 */
@Stateless
public class TrxsPagoFacade extends AbstractFacade<TrxsPago> implements TrxsPagoFacadeLocal {
    @PersistenceContext(unitName = "GestionMiPago-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrxsPagoFacade() {
        super(TrxsPago.class);
    }
    
    @Override
    public List<TrxsPago> consultaTransaccionRut(String fechaInicio1, String fechaFin1){
        
        Date fechaInicio = null;
        Date fechaFin = null;

        try{
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            //Timestam time = 
            fechaInicio = formatoFecha.parse("25/03/2015 17:27:03.047");                      

            fechaFin = formatoFecha.parse("27/03/2015 17:27:03.047");
            
        }catch(ParseException e){
            System.out.println("****ERROR");
        }
        
        Query query = em.createNamedQuery("TrxsPago.findTrxAndRut");
        query.setParameter("fechaInicio", fechaInicio, TemporalType.TIMESTAMP);
        query.setParameter("fechaFin", fechaFin,TemporalType.TIMESTAMP);
        query.setParameter("estado", "PAG");
        List<TrxsPago> trx =  query.getResultList();
        System.out.println("FEcha : " + trx.get(0).getIdcliente().getNombrecliente());
        return query.getResultList();
        
    }
}
