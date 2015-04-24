/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.Comercios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaeza
 */
@Local
public interface ComerciosFacadeLocal {

    void create(Comercios comercios);

    void edit(Comercios comercios);

    void remove(Comercios comercios);

    Comercios find(Object id);

    List<Comercios> findAll();

    List<Comercios> findRange(int[] range);

    int count();
    
}
