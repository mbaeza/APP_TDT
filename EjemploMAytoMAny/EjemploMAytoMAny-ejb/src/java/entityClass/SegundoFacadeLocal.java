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
public interface SegundoFacadeLocal {

    void create(Segundo segundo);

    void edit(Segundo segundo);

    void remove(Segundo segundo);

    Segundo find(Object id);

    List<Segundo> findAll();

    List<Segundo> findRange(int[] range);

    int count();
    
}
