/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.sessionbeans;

import cl.bancochile.gestionmipago.entityclass.TrxsPago;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marcobaeza
 */
@Local
public interface TrxsPagoFacadeLocal {

    void create(TrxsPago trxsPago);

    void edit(TrxsPago trxsPago);

    void remove(TrxsPago trxsPago);

    TrxsPago find(Object id);

    List<TrxsPago> findAll();

    List<TrxsPago> findRange(int[] range);

    int count();
    
    /**
     *
     * @param fechaInicioView
     * @param fechaFinView
     * @return
     */
    public List<TrxsPago> consultaTransaccionRut(String fechaInicioView, String fechaFinView);
    
}
