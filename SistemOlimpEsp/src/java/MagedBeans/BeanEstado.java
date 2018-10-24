/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;
import Controller.ControllerCrud;
import Entities.Estado;
import Controller.EstadoJpaController;

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
@ManagedBean(name = "beanEstado")
@ViewScoped
public class BeanEstado implements Serializable {

    private static final long serialVersionUID = 8157811032147682553L;
    ControllerCrud controlCrud;//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
    EstadoJpaController ControlForeingKey;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    /**
     * *
     * Modificar por mantenimiento
     *
     */
    Estado r;//REFERENCIA PARA EL CONTENIDO DE LA tabla principal// <---------------------------------MODIFICAR
    EstadoJpaController ControlTabla;//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE// <---------------MODIFICAR
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA UPATE*/
    private Estado selectedTablaUpdate;// <-----------------------------------------------------------MODIFICAR

    /*VARIABLES PARA EL FORMULARIO INSERT*/
    private String descripcionInsert;
    /*VARIABLES PARA EL FORMULARIO UPDATE*/
    private Integer idTablaUpdate;
    private String descripcionUpdate;
  

    /**
     * Creates a new instance of BeanEstado
     */
     @PostConstruct
    public void initBeanEstado() {
         this.controlCrud = new ControllerCrud();//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
        /**
         * *
         * Modificar por mantenimiento
         *
         */
        this.r = new Estado();//REFERENCIA PARA EL CONTENIDO DE LA tabla principal// <----------------------------MODIFICAR
        this.ControlTabla = new EstadoJpaController();//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE// <----------MODIFICAR
    }

    
    /*CARGA EL CONTENIDO DE LA DATATABLE*/
    public List<Estado> getTabla() {// <--------------------------------------------------------------------------MODIFICAR
        return ControlTabla.findEstadoEntities();
    }

    /*GETTER AND SETTER DEL CONTENIDO DE LA FILA SELECCIONADA UPDATE*/
    public Estado getSelectedTablaUpdate() {
        if (selectedTablaUpdate != null) {
            setIdTablaUpdate(selectedTablaUpdate.getIdEstado());
            this.descripcionUpdate = selectedTablaUpdate.getEstado();
        }

        return selectedTablaUpdate;
    }

    public void setSelectedTablaUpdate(Estado selectedTablaUpdate) {
        this.selectedTablaUpdate = selectedTablaUpdate;
    }



    /**
     * **
     *
     * CONTENIDO PARA EL INSERT *
     */
    /*CAPTURA LA DATA DEL FORMULARIO A GUARDAR INSERT*/
    public void setInsert() {

        try {

            r.setEstado(this.descripcionInsert);
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
            r.setIdEstado(getIdTablaUpdate());
            r.setEstado(this.descripcionUpdate);
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


}
