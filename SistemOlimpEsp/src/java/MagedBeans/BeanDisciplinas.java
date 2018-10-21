/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.ControllerCrud;
import Controller.DisciplinasJpaController;
import Entities.Disciplinas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author axel.medina
 */
@ManagedBean(name="beanDisciplinas")
@ViewScoped
public class BeanDisciplinas implements Serializable {

    private static final long serialVersionUID = 7346487991662685120L;
    
    DisciplinasJpaController ControlTabla;
    ControllerCrud controlCrud;//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA*/
    private Disciplinas selectedDisciplina;
    /*VARIABLES PARA EL FORMULARIO*/


    /**
     * Creates a new instance of BeanInsertRoles
     */
     @PostConstruct
    public void initBeanAtletas() {
        this.controlCrud = new ControllerCrud();//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
         this.ControlTabla = new DisciplinasJpaController();//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE
    }
    

    /**
     * Creates a new instance of BeanDiciplinas
     */
    public BeanDisciplinas() {
    }

    public List<Disciplinas> getDisciplinas() {
        return ControlTabla.findDisciplinasEntities();
    }
}
