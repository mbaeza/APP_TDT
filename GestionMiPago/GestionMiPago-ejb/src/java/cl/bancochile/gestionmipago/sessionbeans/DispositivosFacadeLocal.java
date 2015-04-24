/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.Dispositivos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaeza
 */
@Local
public interface DispositivosFacadeLocal {

    void create(Dispositivos dispositivos);

    void edit(Dispositivos dispositivos);

    void remove(Dispositivos dispositivos);

    Dispositivos find(Object id);

    List<Dispositivos> findAll();

    List<Dispositivos> findRange(int[] range);

    int count();
    
}
