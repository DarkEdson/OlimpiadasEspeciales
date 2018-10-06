/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.SitioEntrenamientoJpaController;
import Entities.SitioEntrenamiento;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanSitioEntrenamiento")
@Dependent
public class BeanSitioEntrenamiento implements Serializable {

    private static final long serialVersionUID = 3380465570545205900L;
    SitioEntrenamientoJpaController ControlSitioEntrenamiento = new SitioEntrenamientoJpaController();

    /**
     * Creates a new instance of BeanSitioEntrenamiento
     */
    public BeanSitioEntrenamiento() {
    }

    public List<SitioEntrenamiento> getSitioEntrenamiento() {
        return ControlSitioEntrenamiento.findSitioEntrenamientoEntities();
    }
}
