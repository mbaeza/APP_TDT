/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Memorize;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface MemorizeFacadeLocal {

    void create(Memorize memorize);

    void edit(Memorize memorize);

    void remove(Memorize memorize);

    Memorize find(Object id);

    List<Memorize> findAll();

    List<Memorize> findRange(int[] range);

    int count();
    
}
