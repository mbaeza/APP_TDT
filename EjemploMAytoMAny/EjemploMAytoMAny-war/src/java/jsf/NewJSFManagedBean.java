/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entityClass.Primero;
import entityClass.PrimeroFacadeLocal;
import entityClass.Segundo;
import entityClass.SegundoFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author marcobaeza
 */
@Named(value = "newJSFManagedBean")
@RequestScoped
public class NewJSFManagedBean {
    @EJB
    private SegundoFacadeLocal segundoFacade;
    @EJB
    private PrimeroFacadeLocal primeroFacade;

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public NewJSFManagedBean() {
    }
    
    public void metodo(){
        Segundo a = new Segundo(1);
        
        
        Primero p = new Primero();
        primeroFacade.create(p);
        p.
        a.getPrimeroList().add(p);
        
        segundoFacade.create(a);
    }
    
    
    
}
