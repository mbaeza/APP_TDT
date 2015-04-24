/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.Convenios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaeza
 */
@Local
public interface ConveniosFacadeLocal {

    void create(Convenios convenios);

    void edit(Convenios convenios);

    void remove(Convenios convenios);

    Convenios find(Object id);

    List<Convenios> findAll();

    List<Convenios> findRange(int[] range);

    int count();
    
}
