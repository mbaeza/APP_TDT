/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.managedbeans;

import cl.bancochile.gestionmipago.entityclass.TrxsPago;
import cl.bancochile.gestionmipago.sessionbeans.TrxsPagoFacadeLocal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author marcobaeza
 */
@Named(value = "consultaTransaccionRut")
@RequestScoped
public class ConsultaTransaccionRut {
    @EJB
    private TrxsPagoFacadeLocal trxsPagoFacade;
    
    long timeInicio, timeFin;
    private List<TrxsPago> listaTrxRut = new ArrayList<TrxsPago>();
    
    
    public ConsultaTransaccionRut() {
       
    }
    
    @PostConstruct
    public void init(){
  
    }

    public void obtenerTrxRangoFecha() {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        
//        Date dateInicio = dateFormat.parse("01/04/2015");
//        timeInicio = dateInicio.getTime();
//        
//        Date dateFin = dateFormat.parse("02/04/2015");
//        timeFin = dateFin.getTime();
        
        listaTrxRut = trxsPagoFacade.consultaTransaccionRut(null,null);
    }
    public List<TrxsPago> getListaTrxRut() {
        return listaTrxRut;
    }

    public void setListaTrxRut(List<TrxsPago> listaTrxRut) {
        this.listaTrxRut = listaTrxRut;
    }
    
    
    
}
