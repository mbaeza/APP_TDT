/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Alumno;
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
public class AlumnoFacade extends AbstractFacade<Alumno> implements AlumnoFacadeLocal {
    @PersistenceContext(unitName = "APP_TDT_WEB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }

    @Override
    public List<Alumno> obtenerAlumnosPorEjercicio(String idEjercicio,String idUsuario) {      
        
        Query query = em.createNamedQuery("Alumno.findByEjercicioAndIdUsuario");
        query.setParameter("idEjercicio", Integer.parseInt(idEjercicio));
        query.setParameter("idUsuario", Integer.parseInt(idUsuario));
        return query.getResultList();
     
    }
    
}
