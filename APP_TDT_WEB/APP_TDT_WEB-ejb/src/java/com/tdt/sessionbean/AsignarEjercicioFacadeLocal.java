/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.AsignarEjercicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface AsignarEjercicioFacadeLocal {

    void create(AsignarEjercicio asignarEjercicio);

    void edit(AsignarEjercicio asignarEjercicio);

    void remove(AsignarEjercicio asignarEjercicio);

    AsignarEjercicio find(Object id);

    List<AsignarEjercicio> findAll();

    List<AsignarEjercicio> findRange(int[] range);

    int count();
    
}
