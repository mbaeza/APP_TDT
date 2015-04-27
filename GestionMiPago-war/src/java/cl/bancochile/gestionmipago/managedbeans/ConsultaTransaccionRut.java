/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.managedbeans;

import cl.bancochile.gestionmipago.entityclass.Clientes;
import cl.bancochile.gestionmipago.entityclass.TrxsPago;
import cl.bancochile.gestionmipago.sessionbeans.ClientesFacadeLocal;
import cl.bancochile.gestionmipago.sessionbeans.TrxsPagoFacadeLocal;
import java.util.ArrayList;
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
    private ClientesFacadeLocal clientesFacade;
    @EJB
    private TrxsPagoFacadeLocal trxsPagoFacade;
    
    long timeInicio, timeFin;
    private List<TrxsPago> listaTrxRut = new ArrayList<TrxsPago>();
    private List<Clientes> listaRutEnrolado = new ArrayList<Clientes>();
    
    
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
        listaRutEnrolado = clientesFacade.consultaRutEnrolados();
    }
    public List<TrxsPago> getListaTrxRut() {
        return listaTrxRut;
    }

    public void setListaTrxRut(List<TrxsPago> listaTrxRut) {
        this.listaTrxRut = listaTrxRut;
    }
    
    
    
}
