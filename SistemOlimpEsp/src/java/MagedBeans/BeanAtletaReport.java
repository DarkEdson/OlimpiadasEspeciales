/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;
//package org.primefaces.showcase.view.data.dataexporter;
 
import Entities.Atleta;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
 
//import org.primefaces.showcase.domain.Car;
//import org.primefaces.showcase.service.CarService;


/**
 *
 * @author Dark Edson
 */
@Named(value = "beanAtletaReport")
@ManagedBean
public class BeanAtletaReport implements Serializable {
        private List<Atleta> atletas;
         
    @ManagedProperty("#{AtletasService}")
    private BeanAtleta service;
     
 /**   @PostConstruct
    public void init() {
        atletas = service.createAtletas(100);
    }*/
 
    public List<Atleta> getAtletas() {
        return atletas;
    }
 
    public void setService(BeanAtleta service) {
        this.service = service;
    }
}
