/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Semejanza;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface SemejanzaFacadeLocal {

    void create(Semejanza semejanza);

    void edit(Semejanza semejanza);

    void remove(Semejanza semejanza);

    Semejanza find(Object id);

    List<Semejanza> findAll();

    List<Semejanza> findRange(int[] range);

    int count();
    
}
