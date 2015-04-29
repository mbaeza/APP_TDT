/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bancochile.gestionmipago.managedbeans;

import cl.bancochile.gestionmipago.entityclass.TrxsPago;
import cl.bancochile.gestionmipago.sessionbeans.TrxsPagoFacadeLocal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;

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
    private HtmlDataTable dataTableTrxRut;
//    private List<Clientes> listaRutEnrolado = new ArrayList<Clientes>();
    
    
    public ConsultaTransaccionRut() {
       
    }
    
    @PostConstruct
    public void init(){
        
    }

    public void obtenerTrxRangoFecha() {
        listaTrxRut = trxsPagoFacade.consultaTransaccionRut("01/03/2015 17:27:03.047", "29/03/2015 17:27:03.047");
        
        //Crear el CSV
        File file = new File("/Volumes/Macintosh HD/Users/marcobaezasalazar/archivo.csv");        
        FileWriter fw;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("RUT_ORIGEN,CUENTA_ORIGEN,RUT_DESTINO,CTA_DESTINO,MONTOTRX,FECHAHORATRX\n");
            for(TrxsPago trxRut:listaTrxRut){
                bw.write(trxRut.getIdcliente().getRutcliente(),trxRut.getIdcliente().getCuentasList().);
            }
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(ConsultaTransaccionRut.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public HtmlDataTable getDataTableTrxRut() {
        return dataTableTrxRut;
    }

    public void setDataTableTrxRut(HtmlDataTable dataTableTrxRut) {
        this.dataTableTrxRut = dataTableTrxRut;
    }
        
    public List<TrxsPago> getListaTrxRut() {
        return listaTrxRut;
    }

    public void setListaTrxRut(List<TrxsPago> listaTrxRut) {
        this.listaTrxRut = listaTrxRut;
    }
}
