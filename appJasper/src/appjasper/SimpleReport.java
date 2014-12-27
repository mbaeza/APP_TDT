package appjasper;

import groovy.model.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcobaeza
 */
public class SimpleReport {
    DefaultTableModel tableModel;
    
    public SimpleReport() {
        JasperPrint jasperPrint = null;
//        TableModelData();
        Map parameters = new HashMap();
        parameters.put("valor_cuota_referencia", "Basic JasperReport");
        
        try {
//            JasperCompileManager.compileReportToFile("reports/HojaResumenContratoCreditoPreAprobado.jrxml");
//            jasperPrint = JasperFillManager.fillReport("reports/HojaResumenContratoCreditoConsumo.jasper", parameters);
//            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
//            jasperViewer.setVisible(true);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "cualquiercosa.pdf");
    JasperReport report = JasperCompileManager.compileReport(
          "reports/HojaResumenContratoCreditoPreAprobado.jrxml");
      JasperPrint print = JasperFillManager.fillReport(report, parameters);
      // Exporta el informe a PDF
      JasperExportManager.exportReportToPdfFile(print,
          "cualquiercosaII.pdf");
      //Para visualizar el pdf directamente desde java
      JasperViewer.viewReport(print, false);
           
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
}
