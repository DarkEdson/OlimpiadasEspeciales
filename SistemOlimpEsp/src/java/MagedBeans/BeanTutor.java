/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.TutorJpaController;
import Entities.Tutor;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanTutor")
@Dependent
public class BeanTutor implements Serializable {

    private static final long serialVersionUID = -6660393024108454059L;
    TutorJpaController ControlTutor = new TutorJpaController();

    /**
     * Creates a new instance of BeanTutor
     */
    public BeanTutor() {
    }

    public List<Tutor> getTutor() {
        return ControlTutor.findTutorEntities();
    }
}
