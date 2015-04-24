/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.Convenios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcobaeza
 */
@Stateless
public class ConveniosFacade extends AbstractFacade<Convenios> implements ConveniosFacadeLocal {
    @PersistenceContext(unitName = "GestionMiPago-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConveniosFacade() {
        super(Convenios.class);
    }
    
}
