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

/**
 *
 * @author marcobaeza
 */
@Stateless
public class ClientesFacade extends AbstractFacade<Clientes> implements ClientesFacadeLocal {
    @PersistenceContext(unitName = "GestionMiPago-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientesFacade() {
        super(Clientes.class);
    }

    @Override
    public List<Clientes> consultaRutEnrolados() {
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
        
        Query query = em.createNamedQuery("Clientes.findRutEnrolado");
        query.setParameter("fechaActivacionInicio", fechaInicio,TemporalType.TIMESTAMP);
        query.setParameter("fechaActivacionInicio", fechaFin,TemporalType.TIMESTAMP);
        
        System.out.println("Resultado Query 2 " + ((List<Clientes>) query.getResultList()).get(0).getMailcliente());
        return query.getResultList();
    }
    
    
}
