/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.DisciplinasJpaController;
import Entities.Disciplinas;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanAtletaDisciplina")
@Dependent
public class BeanAtletaDisciplina implements Serializable{

    private static final long serialVersionUID = 3878252573210480022L;
DisciplinasJpaController ControlDisciplinas = new DisciplinasJpaController();
    /**
     * Creates a new instance of BeanAtletaDisciplina
     */
    public BeanAtletaDisciplina() {
    }
    public List<Disciplinas> getDisciplina() {
        return ControlDisciplinas.findDisciplinasEntities();
    }
}
