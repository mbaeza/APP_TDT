/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Absurdo;
import com.tdt.entityclass.Secuencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marcobaezasalazar
 */
@Stateless
public class AbsurdoFacade extends AbstractFacade<Absurdo> implements AbsurdoFacadeLocal {
    @PersistenceContext(unitName = "APP_TDT_WEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbsurdoFacade() {
        super(Absurdo.class);
    }
    
    @Override
    public Absurdo obtenerEjercicioAbsurdo(String idEjercicio) {
        
        Query query = em.createNamedQuery("Absurdo.findByIdEjercicio");
        query.setParameter("idEjercicio", Integer.parseInt(idEjercicio));
        return (Absurdo) query.getSingleResult();
       
    }
    
}
