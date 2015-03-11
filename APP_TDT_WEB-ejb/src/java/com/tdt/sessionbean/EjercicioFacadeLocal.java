/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tdt.sessionbean;

import com.tdt.entityclass.Ejercicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaezasalazar
 */
@Local
public interface EjercicioFacadeLocal {

    void create(Ejercicio ejercicio);

    void edit(Ejercicio ejercicio);

    void remove(Ejercicio ejercicio);

    Ejercicio find(Object id);

    List<Ejercicio> findAll();

    List<Ejercicio> findRange(int[] range);

    int count();

    List<Ejercicio> obtenerEjercicios(String tipoEjercicio, String idUsuario);
    
}
