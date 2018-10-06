/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.DepartamentoJpaController;
import Entities.Departamento;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanDepartamento")
@Dependent
public class BeanDepartamento implements Serializable {

    private static final long serialVersionUID = 810023344347442617L;
    DepartamentoJpaController ControlDepartamento = new DepartamentoJpaController();

    /**
     * Creates a new instance of BeanDepartamento
     */
    public BeanDepartamento() {
    }

    public List<Departamento> getDepartamento() {
        return ControlDepartamento.findDepartamentoEntities();
    }
}
