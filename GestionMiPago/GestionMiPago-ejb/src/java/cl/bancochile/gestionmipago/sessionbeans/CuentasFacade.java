/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.Cuentas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcobaeza
 */
@Stateless
public class CuentasFacade extends AbstractFacade<Cuentas> implements CuentasFacadeLocal {
    @PersistenceContext(unitName = "GestionMiPago-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentasFacade() {
        super(Cuentas.class);
    }
    
}
