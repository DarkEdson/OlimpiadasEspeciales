/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.InstitucionJpaController;
import Entities.Institucion;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanInstitucion")
@Dependent
public class BeanInstitucion implements Serializable{

    private static final long serialVersionUID = -971730832463725585L;
    InstitucionJpaController ControlInstitucion = new InstitucionJpaController();
    /**
     * Creates a new instance of BeanInstitucion
     */
    public BeanInstitucion() {
    }
    public List<Institucion> getInstitucion() {
        return ControlInstitucion.findInstitucionEntities();
    } 
}
