/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.AlumnoColaborativo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface AlumnoColaborativoFacadeLocal {

    void create(AlumnoColaborativo alumnoColaborativo);

    void edit(AlumnoColaborativo alumnoColaborativo);

    void remove(AlumnoColaborativo alumnoColaborativo);

    AlumnoColaborativo find(Object id);

    List<AlumnoColaborativo> findAll();

    List<AlumnoColaborativo> findRange(int[] range);

    int count();
    
}
