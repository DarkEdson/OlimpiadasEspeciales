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
import Entities.SitioEntrenamiento;// <------------------------------------------------MODIFICAR
import Controller.SitioEntrenamientoJpaController;// <---------------------------------MODIFICAR

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
@ManagedBean(name = "beanSitioEntrenamiento")
@ViewScoped
public class BeanSitioEntrenamiento implements Serializable {

    private static final long serialVersionUID = 3380465570545205900L;
    ControllerCrud controlCrud;//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
    EstadoJpaController ControlForeingKey;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    /**
     * *
     * Modificar por mantenimiento
     *
     */
    SitioEntrenamiento r;//REFERENCIA PARA EL CONTENIDO DE LA tabla principal// <---------------------------------MODIFICAR
    SitioEntrenamientoJpaController ControlTabla;//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE// <---------------MODIFICAR
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA UPATE*/
    private SitioEntrenamiento selectedTablaUpdate;// <-----------------------------------------------------------MODIFICAR
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA DELETE */
    private SitioEntrenamiento selectedTablaDelete;// <-----------------------------------------------------------MODIFICAR

    /*VARIABLES PARA EL FORMULARIO INSERT*/
    private String descripcionInsert;
    private Integer estadoInsert;
    /*VARIABLES PARA EL FORMULARIO UPDATE*/
    private Integer idTablaUpdate;
    private String descripcionUpdate;
    private Integer estadoUpdate;
    /*VARIABLES PARA EL FORMULARIO DELTE*/
    private Integer idTablaDelete;
    private String descripcionDelete;

    /**
     * Creates a new instance of BeanSitioEntrenamiento
     */
    @PostConstruct
    public void initBeanSitioEntrenamiento() {
         this.controlCrud = new ControllerCrud();//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
        this.ControlForeingKey = new EstadoJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        /**
         * *
         * Modificar por mantenimiento
         *
         */
        this.r = new SitioEntrenamiento();//REFERENCIA PARA EL CONTENIDO DE LA tabla principal// <----------------------------MODIFICAR
        this.ControlTabla = new SitioEntrenamientoJpaController();//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE// <----------MODIFICAR
    }

    
    /*CARGA EL CONTENIDO DE LA DATATABLE*/
    public List<SitioEntrenamiento> getTabla() {// <--------------------------------------------------------------------------MODIFICAR
        return ControlTabla.findSitioEntrenamientoEntities();
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
    public SitioEntrenamiento getSelectedTablaUpdate() {
        if (selectedTablaUpdate != null) {
            setIdTablaUpdate(selectedTablaUpdate.getIdSitioEntrenamiento());
            this.descripcionUpdate = selectedTablaUpdate.getSitioEntrenamiento();
            this.estadoUpdate = selectedTablaUpdate.getIdEstado().getIdEstado();
        }

        return selectedTablaUpdate;
    }

    public void setSelectedTablaUpdate(SitioEntrenamiento selectedTablaUpdate) {
        this.selectedTablaUpdate = selectedTablaUpdate;
    }

    /*GETTER AND SETTER DEL CONTENIDO DE LA FILA SELECCIONADA DELETE*/
    public SitioEntrenamiento getSelectedTablaDelete() {
        if (selectedTablaDelete != null) {
            setIdTablaDelete(selectedTablaDelete.getIdSitioEntrenamiento());
            this.descripcionDelete = selectedTablaDelete.getSitioEntrenamiento();
        }

        return selectedTablaDelete;
    }

    public void setSelectedTablaDelete(SitioEntrenamiento selectedTablaDelete) {
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

            r.setSitioEntrenamiento(this.descripcionInsert);
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
            r.setIdSitioEntrenamiento(getIdTablaUpdate());
            r.setSitioEntrenamiento(this.descripcionUpdate);
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
            r.setIdSitioEntrenamiento(getIdTablaDelete());
            r.setSitioEntrenamiento(this.descripcionDelete);
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
        this.descripcionInsert = "";
        this.descripcionUpdate = "";
    }

    /*GETTER AND SETTER PARA EL CONTENIDO DEL FORMULARIO INSERT*/
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
    public Integer getIdTablaUpdate() {
        return this.idTablaUpdate;
    }

    public void setIdTablaUpdate(Integer idTablaUpdate) {
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
    public Integer getIdTablaDelete() {
        return this.idTablaDelete;
    }

    public void setIdTablaDelete(Integer idTablaDelete) {
        this.idTablaDelete = idTablaDelete;
    }

    public String getDescripcionDelete() {
        return descripcionDelete;
    }

    public void setDescripcionDelete(String descripcionDelete) {
        this.descripcionDelete = descripcionDelete;
    }
}
