/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.AtletaJpaController;
import Entities.Atleta;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanAtleta")
@Dependent
public class BeanAtleta implements Serializable {

    private static final long serialVersionUID = -8757285597845344548L;
    AtletaJpaController ControlAtleta = new AtletaJpaController();

    /**
     * Creates a new instance of BeanAtleta
     */
    public BeanAtleta() {
    }

    public List<Atleta> getAtleta() {
        return ControlAtleta.findAtletaEntities();
    }
}
