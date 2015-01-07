/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcobaeza
 */
@Stateless
public class PrimeroFacade extends AbstractFacade<Primero> implements PrimeroFacadeLocal {
    @PersistenceContext(unitName = "EjemploMAytoMAny-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrimeroFacade() {
        super(Primero.class);
    }
    
}
