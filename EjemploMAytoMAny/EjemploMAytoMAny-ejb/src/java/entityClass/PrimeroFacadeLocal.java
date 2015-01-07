/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaeza
 */
@Local
public interface PrimeroFacadeLocal {

    void create(Primero primero);

    void edit(Primero primero);

    void remove(Primero primero);

    Primero find(Object id);

    List<Primero> findAll();

    List<Primero> findRange(int[] range);

    int count();
    
}
