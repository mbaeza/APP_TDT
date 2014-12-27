/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.CentroEducacional;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface CentroEducacionalFacadeLocal {

    void create(CentroEducacional centroEducacional);

    void edit(CentroEducacional centroEducacional);

    void remove(CentroEducacional centroEducacional);

    CentroEducacional find(Object id);

    List<CentroEducacional> findAll();

    List<CentroEducacional> findRange(int[] range);

    int count();
    
}
