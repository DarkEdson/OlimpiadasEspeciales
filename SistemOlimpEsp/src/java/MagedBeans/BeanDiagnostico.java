/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.ControllerCrud;
import Controller.DiagnosticoJpaController;
import Controller.EstadoJpaController;
import Entities.Diagnostico;
import Entities.Estado;
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

@ManagedBean(name = "beanDiagnostico")
@ViewScoped
public class BeanDiagnostico implements Serializable {

    private static final long serialVersionUID = -6794596886181381680L;
    ControllerCrud controlCrud;//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
    EstadoJpaController ControlForeingKey;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    /**
     * *
     * Modificar por mantenimiento
     *
     */
    Diagnostico r;//REFERENCIA PARA EL CONTENIDO DE LA tabla principal// <---------------------------------MODIFICAR
    DiagnosticoJpaController ControlTabla;//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE// <---------------MODIFICAR
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA UPATE*/
    private Diagnostico selectedTablaUpdate;// <-----------------------------------------------------------MODIFICAR
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA DELETE */
    private Diagnostico selectedTablaDelete;// <-----------------------------------------------------------MODIFICAR

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
     * Creates a new instance of BeanDiagnostico
     */
     @PostConstruct
    public void initBeanDiagnostico() {
         this.controlCrud = new ControllerCrud();//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
        this.ControlForeingKey = new EstadoJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        /**
         * *
         * Modificar por mantenimiento
         *
         */
        this.r = new Diagnostico();//REFERENCIA PARA EL CONTENIDO DE LA tabla principal// <----------------------------MODIFICAR
        this.ControlTabla = new DiagnosticoJpaController();//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE// <----------MODIFICAR
    }

    
    /*CARGA EL CONTENIDO DE LA DATATABLE*/
    public List<Diagnostico> getTabla() {// <--------------------------------------------------------------------------MODIFICAR
        return ControlTabla.findDiagnosticoEntities();
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
    public Diagnostico getSelectedTablaUpdate() {
        if (selectedTablaUpdate != null) {
            setIdTablaUpdate(selectedTablaUpdate.getIdDiagnostico());
            this.descripcionUpdate = selectedTablaUpdate.getDiagnostico();
            this.estadoUpdate = selectedTablaUpdate.getIdEstado().getIdEstado();
        }

        return selectedTablaUpdate;
    }

    public void setSelectedTablaUpdate(Diagnostico selectedTablaUpdate) {
        this.selectedTablaUpdate = selectedTablaUpdate;
    }

    /*GETTER AND SETTER DEL CONTENIDO DE LA FILA SELECCIONADA DELETE*/
    public Diagnostico getSelectedTablaDelete() {
        if (selectedTablaDelete != null) {
            setIdTablaDelete(selectedTablaDelete.getIdDiagnostico());
            this.descripcionDelete = selectedTablaDelete.getDiagnostico();
        }

        return selectedTablaDelete;
    }

    public void setSelectedTablaDelete(Diagnostico selectedTablaDelete) {
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

            r.setDiagnostico(this.descripcionInsert);
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
            r.setIdDiagnostico(getIdTablaUpdate());
            r.setDiagnostico(this.descripcionUpdate);
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
            r.setIdDiagnostico(getIdTablaDelete());
            r.setDiagnostico(this.descripcionDelete);
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
