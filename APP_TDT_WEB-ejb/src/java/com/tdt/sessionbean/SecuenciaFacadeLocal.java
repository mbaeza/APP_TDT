/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Secuencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface SecuenciaFacadeLocal {

    void create(Secuencia secuencia);

    void edit(Secuencia secuencia);

    void remove(Secuencia secuencia);

    Secuencia find(Object id);

    List<Secuencia> findAll();

    List<Secuencia> findRange(int[] range);

    int count();
    
}
