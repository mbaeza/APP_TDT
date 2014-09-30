/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "ejercicioAbsurdo")
@RequestScoped
public class EjercicioAbsurdo implements Serializable{
//hola
private  DashboardModel model;
private UploadedFile file;
private UploadedFile filePrincipal;
private String tituloPrincipal;
private final DashboardColumn column1, column2, column3, column4;
private static final int BUFFER_SIZE = 6124000;   
private String folderToUpload;
private String text;
private List<String> nombresArchivosUpload;
    
    public EjercicioAbsurdo() {
        nombresArchivosUpload = new ArrayList<>();
        
        model = new DefaultDashboardModel();
        column1 = new DefaultDashboardColumn();
        column2 = new DefaultDashboardColumn();
        column3 = new DefaultDashboardColumn();
        column4 = new DefaultDashboardColumn();
        
        
        //column5 = new DefaultDashboardColumn();
        //DashboardColumn column3 = new DefaultDashboardColumn();
         
        
        column1.addWidget("imagen1");
        column1.addWidget("imagen1");
        column2.addWidget("imagen2");
        column3.addWidget("imagen3");
        column4.addWidget("imagen4");
        
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
        model.addColumn(column4);
        //model.addColumn(column5);
       // model.addColumn(column3);
        
    }

    public List<String> getNombresArchivosUpload() {
        return nombresArchivosUpload;
    }

    public void setNombresArchivosUpload(List<String> nombresArchivosUpload) {
        this.nombresArchivosUpload = nombresArchivosUpload;
    }
     
    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
   
        addMessage(message);
    }
     
    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
         
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public DashboardModel getModel() {
        return model;
    }
    
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }          
    }
    
    public void handleKeyEvent() {
        text = tituloPrincipal.toUpperCase();
    }

//    public void addWidget(){
//        final DashboardColumn a  = new DefaultDashboardColumn();
//        final Panel p = new Panel();
//        p.setCloseTitle("asdasd");
//        p.setId("er");
//        p.setVisible(true);
//        UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("board");
//        component.getChildren().add(p);
//        a.addWidget("er");
//        getModel().addColumn(a);
//        System.out.println(getModel().getColumnCount());
//    }
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        File result = new File("/Users/marcobaezasalazar/APP_TDT/APP_TDT_WEB-war/web/files" + event.getFile().getFileName());
        System.out.println(extContext.getRealPath("//WEB-INF//files//" + event.getFile().getFileName()));
        getNombresArchivosUpload().add(event.getFile().getFileName());
        
        System.out.println(event.getFile().getFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            FacesMessage msg = new FacesMessage("File Description", "file name: " + event.getFile().getFileName() + "file size: " + event.getFile().getSize() / 1024 +
            " Kb.content type: " + event.getFile().getContentType() + "The file was uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "The files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }  
        
        
    }    
    
//    public void addPanel(ActionEvent event) {
//        UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("myPanelGrid");
//        if (component != null) {
//            Panel p = new Panel();
//            p.setClosable(true);
//            p.setHeader("Test");
//            p.setVisible(true);
//            component.getChildren().add(p);
//        }
//    }

    public UploadedFile getFilePrincipal() {
        return filePrincipal;
    }

    public void setFilePrincipal(UploadedFile filePrincipal) {
        this.filePrincipal = filePrincipal;
    }

    public String getTituloPrincipal() {
        return tituloPrincipal;
    }

    public void setTituloPrincipal(String tituloPrincipal) {
        this.tituloPrincipal = tituloPrincipal;
    }

    public String getFolderToUpload() {
        return folderToUpload;
    }

    public void setFolderToUpload(String folderToUpload) {
        this.folderToUpload = folderToUpload;
    }
}
