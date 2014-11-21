/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import com.tdt.entityclass.Absurdo;
import com.tdt.entityclass.Ejercicio;
import com.tdt.entityclass.Imagen;
import com.tdt.entityclass.Memorize;
import com.tdt.entityclass.Secuencia;
import com.tdt.entityclass.Semejanza;
import com.tdt.sessionbean.AbsurdoFacadeLocal;
import com.tdt.sessionbean.EjercicioFacadeLocal;
import com.tdt.sessionbean.ImagenFacadeLocal;
import com.tdt.sessionbean.MemorizeFacadeLocal;
import com.tdt.sessionbean.SecuenciaFacadeLocal;
import com.tdt.sessionbean.SemejanzaFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author marcobaezasalazar
 */
@Named(value = "ejerciciosVer")
@RequestScoped
public class EjerciciosVer {
    @EJB
    private ImagenFacadeLocal imagenFacade;
    @EJB
    private MemorizeFacadeLocal memorizeFacade;
    @EJB
    private SemejanzaFacadeLocal semejanzaFacade;
    @EJB
    private SecuenciaFacadeLocal secuenciaFacade;
    @EJB
    private AbsurdoFacadeLocal absurdoFacade;
    @EJB
    private EjercicioFacadeLocal ejercicioFacade;
    
    

    private Absurdo seleccionarAbsurdo;
    private Memorize seleccionarMemorize;
    private Secuencia seleccionarSecuencia;
    private Semejanza seleccionarSemejanza;    
    
    private List<Ejercicio> listaEjercicios;
    private List<Absurdo> listaAbsurdos;
    private List<Memorize> listaMemorize;
    private List<Secuencia> listaSecuenciales;
    private List<Semejanza> listaSemejanzas;
    private String resultado;
    
    private String imagenTextoPrincipal;
    private List<List<String>> listaImagenes;
    
    public EjerciciosVer() {
    }
    
    @PostConstruct
    public void init(){
        listaEjercicios = ejercicioFacade.findAll();
        listaAbsurdos = absurdoFacade.findAll();
        listaMemorize = memorizeFacade.findAll();
        listaSecuenciales = secuenciaFacade.findAll();
        listaSemejanzas = semejanzaFacade.findAll();
    }
    
    public void onRowSelectAbsurdo(SelectEvent event) {
        listaImagenes = new ArrayList<>();
        //buscar principal
        for(Imagen img: imagenFacade.findAll() ){
            if(img.getIdEjercicio().equals(seleccionarAbsurdo.getEjercicio())){
                if(img.getPrincipal()){
                    imagenTextoPrincipal = img.getUrlImagen().split("/")[7];
                }else if(img.getRespuestaCorrecta()){
                    List<String> aux = new ArrayList<>();
                    aux.add(img.getUrlImagen().split("/")[7]);
                    System.out.println(img.getUrlImagen().split("/")[7]);
                    aux.add("border-color: green;border-style:solid");
                    listaImagenes.add(aux);
                }else{
                    List<String> aux = new ArrayList<>();
                    aux.add(img.getUrlImagen().split("/")[7]);
                    aux.add("border-color: none;border-style:none");
                    listaImagenes.add(aux);
                }
            }
        }
        while(listaImagenes.size()<4){
            List<String> aux = new ArrayList<>();
            aux.add("interrogacion.png");
            aux.add("false");
            listaImagenes.add(aux);
        }
    }

    public void onRowSelectSemejanza(SelectEvent event) {
        listaImagenes = new ArrayList<>();
        //buscar principal
        for(Imagen img: imagenFacade.findAll() ){
            if(img.getIdEjercicio().equals(seleccionarSemejanza.getEjercicio())){
                if(img.getPrincipal()){
                    imagenTextoPrincipal = img.getUrlImagen().split("/")[7];
                }else {
                    List<String> aux = new ArrayList<>();
                    aux.add(img.getUrlImagen().split("/")[7]);                    
                    listaImagenes.add(aux);              
                }
            }
        }
        while(listaImagenes.size()<8){
            List<String> aux = new ArrayList<>();
            aux.add("interrogacion.png");
            
            listaImagenes.add(aux);
        }
    }
    
    
    public void onRowSelectMemoria(SelectEvent event) {
        listaImagenes = new ArrayList<>();
        //buscar principal
        for(Imagen img: imagenFacade.findAll() ){
            if(img.getIdEjercicio().equals(seleccionarMemorize.getEjercicio())){         
                    List<String> aux = new ArrayList<>();
                    aux.add(img.getUrlImagen().split("/")[7]);                    
                    listaImagenes.add(aux);              
                
            }
        }
        while(listaImagenes.size()<4){
            List<String> aux = new ArrayList<>();
            aux.add("interrogacion.png");
            
            listaImagenes.add(aux);
        }
    }
    
    public void onRowSelectSecuencia(SelectEvent event) {
        listaImagenes = new ArrayList<>();
        //buscar principal
        for(Imagen img: imagenFacade.findAll() ){
            if(img.getIdEjercicio().equals(seleccionarSecuencia.getEjercicio())){         
                    List<String> aux = new ArrayList<>();
                    aux.add(img.getUrlImagen().split("/")[7]);                    
                    listaImagenes.add(aux);                              
            }
        }
        while(listaImagenes.size()<4){
            List<String> aux = new ArrayList<>();
            aux.add("interrogacion.png");
            
            listaImagenes.add(aux);
        }
    }
    public MemorizeFacadeLocal getMemorizeFacade() {
        return memorizeFacade;
    }

    public void setMemorizeFacade(MemorizeFacadeLocal memorizeFacade) {
        this.memorizeFacade = memorizeFacade;
    }

    public SemejanzaFacadeLocal getSemejanzaFacade() {
        return semejanzaFacade;
    }

    public void setSemejanzaFacade(SemejanzaFacadeLocal semejanzaFacade) {
        this.semejanzaFacade = semejanzaFacade;
    }

    public SecuenciaFacadeLocal getSecuenciaFacade() {
        return secuenciaFacade;
    }

    public void setSecuenciaFacade(SecuenciaFacadeLocal secuenciaFacade) {
        this.secuenciaFacade = secuenciaFacade;
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

    public ImagenFacadeLocal getImagenFacade() {
        return imagenFacade;
    }

    public void setImagenFacade(ImagenFacadeLocal imagenFacade) {
        this.imagenFacade = imagenFacade;
    }

    public String getImagenTextoPrincipal() {
        return imagenTextoPrincipal;
    }

    public void setImagenTextoPrincipal(String nombreTextoPrincipal) {
        this.imagenTextoPrincipal = nombreTextoPrincipal;
    }

    public List<List<String>> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(List<List<String>> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }
    

    public Absurdo getSeleccionarAbsurdo() {
        return seleccionarAbsurdo;
    }

    public void setSeleccionarAbsurdo(Absurdo seleccionarAbsurdo) {
        this.seleccionarAbsurdo = seleccionarAbsurdo;
    }

    public Memorize getSeleccionarMemorize() {
        return seleccionarMemorize;
    }

    public void setSeleccionarMemorize(Memorize seleccionarMemorize) {
        this.seleccionarMemorize = seleccionarMemorize;
    }

    public Secuencia getSeleccionarSecuencia() {
        return seleccionarSecuencia;
    }

    public void setSeleccionarSecuencia(Secuencia seleccionarSecuencia) {
        this.seleccionarSecuencia = seleccionarSecuencia;
    }

    public Semejanza getSeleccionarSemejanza() {
        return seleccionarSemejanza;
    }

    public void setSeleccionarSemejanza(Semejanza seleccionarSemejanza) {
        this.seleccionarSemejanza = seleccionarSemejanza;
    }


    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public List<Absurdo> getListaAbsurdos() {
        return listaAbsurdos;
    }

    public void setListaAbsurdos(List<Absurdo> listaAbsurdos) {
        this.listaAbsurdos = listaAbsurdos;
    }

    public List<Memorize> getListaMemorize() {
        return listaMemorize;
    }

    public void setListaMemorize(List<Memorize> listaMemorize) {
        this.listaMemorize = listaMemorize;
    }

    public List<Secuencia> getListaSecuenciales() {
        return listaSecuenciales;
    }

    public void setListaSecuenciales(List<Secuencia> listaSecuenciales) {
        this.listaSecuenciales = listaSecuenciales;
    }

    public List<Semejanza> getListaSemejanzas() {
        return listaSemejanzas;
    }

    public void setListaSemejanzas(List<Semejanza> listaSemejanzas) {
        this.listaSemejanzas = listaSemejanzas;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
}
