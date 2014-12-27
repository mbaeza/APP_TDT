/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Absurdo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface AbsurdoFacadeLocal {

    void create(Absurdo absurdo);

    void edit(Absurdo absurdo);

    void remove(Absurdo absurdo);

    Absurdo find(Object id);

    List<Absurdo> findAll();

    List<Absurdo> findRange(int[] range);

    int count();
    
}
