/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.AsignarEjercicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcobaezasalazar
 */
@Stateless
public class AsignarEjercicioFacade extends AbstractFacade<AsignarEjercicio> implements AsignarEjercicioFacadeLocal {
    @PersistenceContext(unitName = "APP_TDT_WEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignarEjercicioFacade() {
        super(AsignarEjercicio.class);
    }
    
}