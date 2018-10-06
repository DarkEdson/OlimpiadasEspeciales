/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.DiagnosticoJpaController;
import Entities.Diagnostico;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanDiagnostico")
@Dependent
public class BeanDiagnostico implements Serializable {

    private static final long serialVersionUID = -6794596886181381680L;
    DiagnosticoJpaController ControlDiagnostico = new DiagnosticoJpaController();

    /**
     * Creates a new instance of BeanDiagnostico
     */
    public BeanDiagnostico() {
    }

    public List<Diagnostico> getDiagnostico() {
        return ControlDiagnostico.findDiagnosticoEntities();
    }
}
