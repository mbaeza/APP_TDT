/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.DispositivosCliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaeza
 */
@Local
public interface DispositivosClienteFacadeLocal {

    void create(DispositivosCliente dispositivosCliente);

    void edit(DispositivosCliente dispositivosCliente);

    void remove(DispositivosCliente dispositivosCliente);

    DispositivosCliente find(Object id);

    List<DispositivosCliente> findAll();

    List<DispositivosCliente> findRange(int[] range);

    int count();
    
}
