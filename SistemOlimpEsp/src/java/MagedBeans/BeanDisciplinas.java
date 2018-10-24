/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.ControllerCrud;
import Entities.Estado;
import Controller.EstadoJpaController;
/**
 * *
 * Modificar por mantenimiento
 *
 */
import Entities.Disciplinas;// <------------------------------------------------MODIFICAR
import Controller.DisciplinasJpaController;// <---------------------------------MODIFICAR

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author axel.medina
 */
@ManagedBean(name = "beanDisciplinas")
@ViewScoped
public class BeanDisciplinas implements Serializable {

    private static final long serialVersionUID = 7346487991662685120L;
    ControllerCrud controlCrud;//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
    EstadoJpaController ControlForeingKey;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    /**
     * *
     * Modificar por mantenimiento
     *
     */
    Disciplinas r;//REFERENCIA PARA EL CONTENIDO DE LA tabla principal// <---------------------------------MODIFICAR
    DisciplinasJpaController ControlTabla;//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE// <---------------MODIFICAR
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA UPATE*/
    private Disciplinas selectedTablaUpdate;// <-----------------------------------------------------------MODIFICAR
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA DELETE */
    private Disciplinas selectedTablaDelete;// <-----------------------------------------------------------MODIFICAR

    /*VARIABLES PARA EL FORMULARIO INSERT*/
    private String idDisciplinaInsert;

    private String descripcionInsert;
    private Integer estadoInsert;
    /*VARIABLES PARA EL FORMULARIO UPDATE*/
    private String idTablaUpdate;
    private String descripcionUpdate;
    private Integer estadoUpdate;
    /*VARIABLES PARA EL FORMULARIO DELTE*/
    private String idTablaDelete;
    private String descripcionDelete;

    /**
     * Creates a new instance of BeanDiciplinas
     */
    @PostConstruct
    public void initBeanDisciplinas() {
         this.controlCrud = new ControllerCrud();//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
        this.ControlForeingKey = new EstadoJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        /**
         * *
         * Modificar por mantenimiento
         *
         */
        this.r = new Disciplinas();//REFERENCIA PARA EL CONTENIDO DE LA tabla principal// <----------------------------MODIFICAR
        this.ControlTabla = new DisciplinasJpaController();//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE// <----------MODIFICAR
    }

    
    /*CARGA EL CONTENIDO DE LA DATATABLE*/
    public List<Disciplinas> getTabla() {// <--------------------------------------------------------------------------MODIFICAR
        return ControlTabla.findDisciplinasEntities();
    }

    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
    public List<Estado> getListEstado() {
        return ControlForeingKey.findEstadoEntities(1, 0);
    }

    /*CARGA EL CONTENIDO DEL COMOBOBOX UPDATE*/
    public List<Estado> getListEstadoUpdate() {
        return ControlForeingKey.findEstadoEntities();
    }

    /*GETTER AND SETTER DEL CONTENIDO DE LA FILA SELECCIONADA UPDATE*/
    public Disciplinas getSelectedTablaUpdate() {
        if (selectedTablaUpdate != null) {
            setIdTablaUpdate(selectedTablaUpdate.getIdDisciplina());
            this.descripcionUpdate = selectedTablaUpdate.getDisciplina();
            this.estadoUpdate = selectedTablaUpdate.getIdEstado().getIdEstado();
        }

        return selectedTablaUpdate;
    }

    public void setSelectedTablaUpdate(Disciplinas selectedTablaUpdate) {
        this.selectedTablaUpdate = selectedTablaUpdate;
    }

    /*GETTER AND SETTER DEL CONTENIDO DE LA FILA SELECCIONADA DELETE*/
    public Disciplinas getSelectedTablaDelete() {
        if (selectedTablaDelete != null) {
            setIdTablaDelete(selectedTablaDelete.getIdDisciplina());
            this.descripcionDelete = selectedTablaDelete.getDisciplina();
        }

        return selectedTablaDelete;
    }

    public void setSelectedTablaDelete(Disciplinas selectedTablaDelete) {
        this.selectedTablaDelete = selectedTablaDelete;
    }

    /**
     * **
     *
     * CONTENIDO PARA EL INSERT *
     */
    /*CAPTURA LA DATA DEL FORMULARIO A GUARDAR INSERT*/
    public void setInsert() {

        try {
            r.setIdDisciplina(this.idDisciplinaInsert);
            r.setDisciplina(this.descripcionInsert);
            r.setIdEstado(ControlForeingKey.findEstado(this.estadoInsert));
            controlCrud.begin();
            if (controlCrud.insert(r)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK ",
                        " Resgistro insertado correctamente!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "FALLO ",
                        "El insercion del registro!"));
            }
            formClear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR ",
                    "No se inserto!"));
        }
    }

    /*CAPTURA LA DATA DEL FORMULARIO A GUARDAR UPDATE*/
    public void setUpdate() {
        try {
            r.setIdDisciplina(getIdTablaUpdate());
            r.setDisciplina(this.descripcionUpdate);
            r.setIdEstado(ControlForeingKey.findEstado(this.estadoUpdate));
            controlCrud.begin();
            if (controlCrud.update(r)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK ",
                        " Resgistro modifico correctamente!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "FALLO ",
                        "El modificacion del registro!"));
            }
            formClear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR ",
                    "No se modifico!"));
        }
    }

    /*CAPTURA LA DATA DEL FORMULARIO A GUARDAR UPDATE*/
    public void setDelete() {
        try {
            r.setIdDisciplina(getIdTablaDelete());
            r.setDisciplina(this.descripcionDelete);
            r.setIdEstado(ControlForeingKey.findEstado(2));
            controlCrud.begin();
            if (controlCrud.update(r)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK ",
                        " Resgistro eliminado correctamente!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "FALLO ",
                        "El eliminacion del registro!"));
            }
            formClear();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR ",
                    "No se elimino!"));
        }
    }

    /**
     * LIMPIA CONTENIDO DEL FORMULARIO*
     */
    public void formClear() {
        this.idDisciplinaInsert="";
        this.descripcionInsert = "";
        this.descripcionUpdate = "";
    }

    /*GETTER AND SETTER PARA EL CONTENIDO DEL FORMULARIO INSERT*/
    
    public String getIdDisciplinaInsert() {
        return idDisciplinaInsert;
    }

    public void setIdDisciplinaInsert(String idDisciplinaInsert) {
        this.idDisciplinaInsert = idDisciplinaInsert;
    }
    public String getDescripcionInsert() {
        return descripcionInsert;
    }

    public void setDescripcionInsert(String descripcionInsert) {
        this.descripcionInsert = descripcionInsert;
    }

    public Integer getEstadoInsert() {
        return estadoInsert;
    }

    public void setEstadoInsert(Integer estadoInsert) {
        this.estadoInsert = estadoInsert;
    }

    /*GETTER AND SETTER PARA EL CONTENIDO DEL FORMULARIO UPDATE*/
    public String getIdTablaUpdate() {
        return this.idTablaUpdate;
    }

    public void setIdTablaUpdate(String idTablaUpdate) {
        this.idTablaUpdate = idTablaUpdate;
    }

    public String getDescripcionUpdate() {
        return descripcionUpdate;
    }

    public void setDescripcionUpdate(String descripcionUpdate) {
        this.descripcionUpdate = descripcionUpdate;
    }

    public Integer getEstadoUpdate() {
        return estadoUpdate;
    }

    public void setEstadoUpdate(Integer estadoUpdate) {
        this.estadoUpdate = estadoUpdate;
    }

    /*GETTER AND SETTER PARA EL CONTENIDO DEL FORMULARIO DELETE*/
    public String getIdTablaDelete() {
        return this.idTablaDelete;
    }

    public void setIdTablaDelete(String idTablaDelete) {
        this.idTablaDelete = idTablaDelete;
    }

    public String getDescripcionDelete() {
        return descripcionDelete;
    }

    public void setDescripcionDelete(String descripcionDelete) {
        this.descripcionDelete = descripcionDelete;
    }

}
