/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.ProgramasJpaController;
import Entities.Programas;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanProgramas")
@Dependent
public class BeanProgramas {

    ProgramasJpaController ControlProgramas = new ProgramasJpaController();
    /**
     * Creates a new instance of BeanProgramas
     */
    public BeanProgramas() {
    }
    public List<Programas> getProgramas() {
        return ControlProgramas.findProgramasEntities();
    }
}
