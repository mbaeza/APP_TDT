/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.DispositivosCliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcobaeza
 */
@Stateless
public class DispositivosClienteFacade extends AbstractFacade<DispositivosCliente> implements DispositivosClienteFacadeLocal {
    @PersistenceContext(unitName = "GestionMiPago-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DispositivosClienteFacade() {
        super(DispositivosCliente.class);
    }
    
}
