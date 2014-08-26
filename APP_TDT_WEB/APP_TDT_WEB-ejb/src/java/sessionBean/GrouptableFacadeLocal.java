/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionBean;

import entityClass.Grouptable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface GrouptableFacadeLocal {

    void create(Grouptable grouptable);

    void edit(Grouptable grouptable);

    void remove(Grouptable grouptable);

    Grouptable find(Object id);

    List<Grouptable> findAll();

    List<Grouptable> findRange(int[] range);

    int count();
    
}
