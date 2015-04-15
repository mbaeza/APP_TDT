/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Memorize;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marcobaezasalazar
 */
@Stateless
public class MemorizeFacade extends AbstractFacade<Memorize> implements MemorizeFacadeLocal {
    @PersistenceContext(unitName = "APP_TDT_WEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemorizeFacade() {
        super(Memorize.class);
    }
    
    @Override
    public Memorize obtenerEjercicioMemorize(String idEjercicio) {
        
        Query query = em.createNamedQuery("Memorize.findByIdEjercicio");
        query.setParameter("idEjercicio", Integer.parseInt(idEjercicio));
        return (Memorize) query.getSingleResult();
       
    }
    
}
