/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import com.tdt.entityclass.Absurdo;
import com.tdt.entityclass.AbsurdoPK;
import com.tdt.entityclass.Alumno;
import com.tdt.entityclass.Ejercicio;
import com.tdt.entityclass.Imagen;
import com.tdt.sessionbean.AbsurdoFacadeLocal;
import com.tdt.sessionbean.AlumnoFacadeLocal;
import com.tdt.sessionbean.EjercicioFacadeLocal;
import com.tdt.sessionbean.ImagenFacadeLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
import org.primefaces.event.SelectEvent;
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
@Named(value = "modificarEjercicioAbsurdo")
@RequestScoped
public class ModificarEjercicioAbsurdo implements Serializable{
    @EJB
    private AlumnoFacadeLocal alumnoFacade;
    @EJB
    private ImagenFacadeLocal imagenFacade;
    @EJB
    private AbsurdoFacadeLocal absurdoFacade;
    @EJB
    private EjercicioFacadeLocal ejercicioFacade;
    
//hola
private  DashboardModel model;
private UploadedFile file;
private UploadedFile filePrincipal;
private String tituloPrincipal;
//private final DashboardColumn column1, column2, column3, column4;
private static final int BUFFER_SIZE = 6124000;   
private String folderToUpload;
private String text;
private final static List<String> nombresArchivosUpload = new ArrayList<>();
private final static List<String> nombresArchivosUploadPrincipal = new ArrayList<>();
private String imagenPrincipal;
private final static  List<Integer> cont = new ArrayList();
private final static  List<Boolean> subirArchivo = new ArrayList<Boolean>(){
    {
        add(false);
    }
};
private boolean respuestaCorrecta1 = false;
private boolean respuestaCorrecta2 = false;
private boolean respuestaCorrecta3 = false;
private boolean respuestaCorrecta4 = false;
private String style1;
private String style2;
private String style3;
private String style4;
private List<Ejercicio> listaEjercicios;
private Ejercicio ejercicioSeleccionado;
private String nombreEjercicio;
private String descripcionEjercicio;




    public ModificarEjercicioAbsurdo() {
        
//        nombresArchivosUpload = new ArrayList<>();
        if(subirArchivo.get(0) == false){
            setImagenPrincipal("interrogacion.png");
            nombresArchivosUpload.add(0, "interrogacion.png");
            nombresArchivosUpload.add(1, "interrogacion.png");
            nombresArchivosUpload.add(2, "interrogacion.png");
            nombresArchivosUpload.add(3, "interrogacion.png");
            
            respuestaCorrecta1 = false;
            respuestaCorrecta2 = false;
            respuestaCorrecta3 = false;
            respuestaCorrecta4 = false;
            nombreEjercicio= ""; 
            tituloPrincipal= "";
            descripcionEjercicio = "";
            
            nombresArchivosUploadPrincipal.add(0,"interrogacion.png");
            cont.clear();
        }
        subirArchivo.add(0, false);
//        nombresArchivosUpload = new ArrayList<>();
//        
//        model = new DefaultDashboardModel();
//        column1 = new DefaultDashboardColumn();
//        column2 = new DefaultDashboardColumn();
//        column3 = new DefaultDashboardColumn();
//        column4 = new DefaultDashboardColumn();
//        
//        
//        //column5 = new DefaultDashboardColumn();
//        //DashboardColumn column3 = new DefaultDashboardColumn();
//         
//        
//        column1.addWidget("imagen1");
//        column1.addWidget("imagen1");
//        column2.addWidget("imagen2");
//        column3.addWidget("imagen3");
//        column4.addWidget("imagen4");
//        
//        model.addColumn(column1);
//        model.addColumn(column2);
//        model.addColumn(column3);
//        model.addColumn(column4);
//        model.addColumn(column5);
       // model.addColumn(column3);
        
    }

    @PostConstruct
    public void init() {
        listaEjercicios = ejercicioFacade.findAll();
        
//        nombresArchivosUpload.add(0,"interrogacion.png");
//        nombresArchivosUpload.add(1,"interrogacion.png");
//        nombresArchivosUpload.add(2,"interrogacion.png");
//        nombresArchivosUpload.add(3,"interrogacion.png");
    }
    
    public List<String> getNombresArchivosUpload() {
        return nombresArchivosUpload;
    }
    
    public void generarEjercicio() throws IOException{
      
            //generar el ejercicio nuevo
            Ejercicio nuevoEjercicio = new Ejercicio();
            nuevoEjercicio.setNombreEjercicio(nombreEjercicio);
            nuevoEjercicio.setDescripcionEjercicio(descripcionEjercicio);
            ejercicioFacade.create(nuevoEjercicio);                


            //generar el ejercicio especifico
            Absurdo nuevoAbsurdo = new Absurdo();
            nuevoAbsurdo.setColorTexto("#0000");
            nuevoAbsurdo.setDescripcionEjercicio(descripcionEjercicio);
            nuevoAbsurdo.setEjercicio(nuevoEjercicio);
            nuevoAbsurdo.setNombreEjercicio(nombreEjercicio);
            nuevoAbsurdo.setTextoPrincipal(tituloPrincipal); 

            
            AbsurdoPK abspk = new AbsurdoPK();
            
            //Se crea imagen
            Imagen nuevaImagen = new Imagen();
            
            for (Ejercicio a : ejercicioFacade.findAll()){
                if(a.equals(nuevoEjercicio)){
                    abspk.setIdEjercicio(a.getIdEjercicio());                   
                    nuevaImagen.setIdEjercicio(a);
                            }
            } 
            nuevoAbsurdo.setAbsurdoPK(abspk);
            absurdoFacade.create(nuevoAbsurdo);

            //se suben las imagenes            
            nuevaImagen.setRespuestaCorrecta(false);
            nuevaImagen.setUrlImagen("/Users/marcobaezasalazar/APP_TDT/APP_TDT_WEB-war/web/files/"+nombresArchivosUploadPrincipal.get(0));
            nuevaImagen.setPrincipal(true);                        
            nuevaImagen.setRespuestaCorrecta(false);
            imagenFacade.create(nuevaImagen);

            List<Boolean> respuestasCorrecta = new ArrayList<Boolean>(){
                {
                    add(respuestaCorrecta1);
                    add(respuestaCorrecta2);
                    add(respuestaCorrecta3);
                    add(respuestaCorrecta4);
                }
            };

            for(int i = 0;i<4;i++){
                if(!nombresArchivosUpload.get(i).equals("interrogacion.png")){
                    Imagen nuevaImagen2 = new Imagen();
                    nuevaImagen2.setUrlImagen("/Users/marcobaezasalazar/APP_TDT/APP_TDT_WEB-war/web/files/"+nombresArchivosUpload.get(i));
                    nuevaImagen2.setPrincipal(false);
                    nuevaImagen2.setRespuestaCorrecta(respuestasCorrecta.get(i));  
                    nuevaImagen2.setIdEjercicio(nuevoEjercicio);
                    imagenFacade.create(nuevaImagen2);
                }
                System.out.println(nombresArchivosUpload.get(i));
            }

        // reinicializar variables
        respuestaCorrecta1 = false;
        respuestaCorrecta2 = false;
        respuestaCorrecta3 = false;
        respuestaCorrecta4 = false;
        nombreEjercicio= ""; 
        tituloPrincipal= "";
        descripcionEjercicio = "";
        nombresArchivosUpload.add(0,"interrogacion.png");
        nombresArchivosUpload.add(1,"interrogacion.png");
        nombresArchivosUpload.add(2,"interrogacion.png");
        nombresArchivosUpload.add(3,"interrogacion.png");
        nombresArchivosUploadPrincipal.add(0,"interrogacion.png");
        FacesContext.getCurrentInstance().getExternalContext().redirect("ejercicioAbsurdo.xhtml");
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
   
        addMessage(message);
    }
    
    public void confirmacionAgregar(ActionEvent actionEvent){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregación realizada", "Se ha agregado un nuevo ejercicio del sistema satisfactoriamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
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
     
    public void onCompletedUpload(){
        nombresArchivosUpload.remove(1);
        nombresArchivosUpload.add(remove2(file.getFileName()));
    }
    
    public void handleFileUpload(FileUploadEvent event) {
//        cont.add(cont.size());
        System.out.println("contador: " + cont.size());
        if(file != null) {
            FacesMessage message = new FacesMessage("Subida Realizada ", remove2(event.getFile().getFileName()) + " se ha subido con exito.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        File result = new File("/Users/marcobaezasalazar/APP_TDT/APP_TDT_WEB-war/web/files/" + event.getFile().getFileName());
        
//        if(cont.size()>4)
//            cont.clear();
                 
        //nombresArchivosUpload.remove(cont.size());
    //            System.out.println(filePrincipal.getFileName());
        nombresArchivosUpload.add(cont.size(),remove2(event.getFile().getFileName()));
        cont.add(cont.size());
//        setText(getNombresArchivosUpload().get(0)+getNombresArchivosUpload().get(1));
    //               System.out.println(extContext.getRealPath("//WEB-INF//files//" + event.getFile().getFileName()));        
        System.out.println(getNombresArchivosUpload().get(0));
        System.out.println(getNombresArchivosUpload().get(1));
        System.out.println(getNombresArchivosUpload().get(2));
        System.out.println(getNombresArchivosUpload().get(3));
        subirArchivo.add(0, true);
//        System.out.println("");
//        getNombresArchivosUpload().add(remove2(file.getFileName()));
        
//        System.out.println(event.getFile().getFileName());
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
    
    public void onRowSelect(SelectEvent event) {
//        System.out.println("asdasdasd");
        int i = 0;
        for(Imagen a: imagenFacade.findAll() ){
            
            if(ejercicioSeleccionado.getIdEjercicio().equals(a.getIdEjercicio().getIdEjercicio()) && true == a.getPrincipal()){
                String[] s = a.getUrlImagen().split("/");
                nombresArchivosUploadPrincipal.add(0,s[s.length-1]);
            }else if(ejercicioSeleccionado.getIdEjercicio().equals(a.getIdEjercicio().getIdEjercicio()) && false == a.getPrincipal()){
                String[] s = a.getUrlImagen().split("/");
                nombresArchivosUpload.add(i,s[s.length-1]);
                if(a.getRespuestaCorrecta() == true && i == 0)
                    respuestaCorrecta1 = true;
                if(a.getRespuestaCorrecta() == false && i == 0)
                    respuestaCorrecta1 = false;
                if(a.getRespuestaCorrecta() == true && i == 1)
                    respuestaCorrecta2 = true;
                if(a.getRespuestaCorrecta() == false && i == 1)
                    respuestaCorrecta2 = false;
                if(a.getRespuestaCorrecta() == true && i == 2)
                    respuestaCorrecta3 = true;
                if(a.getRespuestaCorrecta() == false && i == 2)
                    respuestaCorrecta3 = false;
                if(a.getRespuestaCorrecta() == true && i == 3)
                    respuestaCorrecta3 = true;
                if(a.getRespuestaCorrecta() == false && i == 3)
                    respuestaCorrecta3 = false;
            }
            i++;
        }
        
        for(Absurdo a: absurdoFacade.findAll() ){
            if(ejercicioSeleccionado.getIdEjercicio().equals(a.getAbsurdoPK().getIdEjercicio())){
                setText(a.getTextoPrincipal());
                setTituloPrincipal(a.getTextoPrincipal());
            }
        }
        setNombreEjercicio(ejercicioSeleccionado.getNombreEjercicio());
        setDescripcionEjercicio(ejercicioSeleccionado.getDescripcionEjercicio());
        
    } 
    
    public void generarStyle1(){
        if(respuestaCorrecta1 == true)
            setStyle1("border-color: green;border-style:solid");
        else
            setStyle1("border-color: none;border-style:none");
        
          subirArchivo.add(0, true);
    }
    public void generarStyle2(){
        if(respuestaCorrecta2 == true)
            setStyle2("border-color: green;border-style:solid");
        else
            setStyle2("border-color: none;border-style:none");
        
          subirArchivo.add(0, true);
    }
    public void generarStyle3(){
        if(respuestaCorrecta3 == true)
            setStyle3("border-color: green;border-style:solid");
        else
            setStyle3("border-color: none;border-style:none");
        
          subirArchivo.add(0, true);
    }
    public void generarStyle4(){
        if(respuestaCorrecta4 == true)
            setStyle4("border-color: green;border-style:solid");
        else
            setStyle4("border-color: none;border-style:none");
        
          subirArchivo.add(0, true);
    }
    public void handleFileUploadImagenPrincipal(FileUploadEvent event) {
        if(file != null) {
            FacesMessage message = new FacesMessage("Subida Realizada ", remove2(file.getFileName()) + " se ha subido con exito.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
//        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        File result = new File("/Users/marcobaezasalazar/APP_TDT/APP_TDT_WEB-war/web/files/" + remove2(event.getFile().getFileName()));
//        System.out.println(extContext.getRealPath("//WEB-INF//files//" + event.getFile().getFileName()));       
      
        setImagenPrincipal(remove2(event.getFile().getFileName()));
        nombresArchivosUploadPrincipal.add(0,remove2(event.getFile().getFileName()));
        subirArchivo.add(0, true);
//        System.out.println(getImagenPrincipal());
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
    public static String remove2(String input) {
        // Descomposición canónica
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Nos quedamos únicamente con los caracteres ASCII
        Pattern pattern = Pattern.compile("\\P{ASCII}");
        return pattern.matcher(normalized).replaceAll("");
    }

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
    
    public String getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(String imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public boolean isRespuestaCorrecta1() {
        return respuestaCorrecta1;
    }

    public void setRespuestaCorrecta1(boolean respuestaCorrecta1) {
        this.respuestaCorrecta1 = respuestaCorrecta1;
    }

    public AlumnoFacadeLocal getAlumnoFacade() {
        return alumnoFacade;
    }

    public void setAlumnoFacade(AlumnoFacadeLocal alumnoFacade) {
        this.alumnoFacade = alumnoFacade;
    }

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public Ejercicio getEjercicioSeleccionado() {
        return ejercicioSeleccionado;
    }

    public void setEjercicioSeleccionado(Ejercicio ejercicioSeleccionado) {
        this.ejercicioSeleccionado = ejercicioSeleccionado;
    }

    public boolean isRespuestaCorrecta2() {
        return respuestaCorrecta2;
    }

    public void setRespuestaCorrecta2(boolean respuestaCorrecta2) {
        this.respuestaCorrecta2 = respuestaCorrecta2;
    }

    public boolean isRespuestaCorrecta3() {
        return respuestaCorrecta3;
    }

    public void setRespuestaCorrecta3(boolean respuestaCorrecta3) {
        this.respuestaCorrecta3 = respuestaCorrecta3;
    }

    public boolean isRespuestaCorrecta4() {
        return respuestaCorrecta4;
    }

    public void setRespuestaCorrecta4(boolean respuestaCorrecta4) {
        this.respuestaCorrecta4 = respuestaCorrecta4;
    }

    public String getStyle1() {
        return style1;
    }

    public void setStyle1(String style1) {
        this.style1 = style1;
    }

    public String getStyle2() {
        return style2;
    }

    public void setStyle2(String style2) {
        this.style2 = style2;
    }

    public String getStyle3() {
        return style3;
    }

    public void setStyle3(String style3) {
        this.style3 = style3;
    }

    public String getStyle4() {
        return style4;
    }

    public void setStyle4(String style4) {
        this.style4 = style4;
    }

    public ImagenFacadeLocal getImagenFacade() {
        return imagenFacade;
    }

    public void setImagenFacade(ImagenFacadeLocal imagenFacade) {
        this.imagenFacade = imagenFacade;
    }

    public AbsurdoFacadeLocal getAbsurdoFacade() {
        return absurdoFacade;
    }

    public void setAbsurdoFacade(AbsurdoFacadeLocal absurdoFacade) {
        this.absurdoFacade = absurdoFacade;
    }

    public EjercicioFacadeLocal getEjercicioFacade() {
        return ejercicioFacade;
    }

    public void setEjercicioFacade(EjercicioFacadeLocal ejercicioFacade) {
        this.ejercicioFacade = ejercicioFacade;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getDescripcionEjercicio() {
        return descripcionEjercicio;
    }

    public void setDescripcionEjercicio(String descripcionEjercicio) {
        this.descripcionEjercicio = descripcionEjercicio;
    }
    
    
    
}
