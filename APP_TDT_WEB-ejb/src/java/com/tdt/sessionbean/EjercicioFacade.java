/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Ejercicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marcobaezasalazar
 */
@Stateless
public class EjercicioFacade extends AbstractFacade<Ejercicio> implements EjercicioFacadeLocal {
    @PersistenceContext(unitName = "APP_TDT_WEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EjercicioFacade() {
        super(Ejercicio.class);
    }

    @Override
    public List<Ejercicio> obtenerEjercicios(String tipoEjercicio, String idUsuario) {
        
        //List<Ejercicio> ejercicios = new ArrayList<>();
        Query query = em.createNamedQuery("Ejercicio.findByTipo");
        query.setParameter("nombreTipoEjercicio",tipoEjercicio);
        query.setParameter("idUsuario",Integer.parseInt(idUsuario));
        return query.getResultList();
    }
    
    
}
