/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Semejanza;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marcobaezasalazar
 */
@Stateless
public class SemejanzaFacade extends AbstractFacade<Semejanza> implements SemejanzaFacadeLocal {
    @PersistenceContext(unitName = "APP_TDT_WEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SemejanzaFacade() {
        super(Semejanza.class);
    }
    
    @Override
    public Semejanza obtenerEjercicioAbsurdo(String idEjercicio) {
        
        Query query = em.createNamedQuery("Semejanza.findByIdEjercicio");
        query.setParameter("idEjercicio", Integer.parseInt(idEjercicio));
        return (Semejanza) query.getSingleResult();
       
    }
    
}
