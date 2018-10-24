/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanInsert;
import Controller.ControllerCrud;
import Controller.EstadoJpaController;
import Controller.RolesJpaController;
import Entities.Estado;
import Entities.Roles;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author axel.medina
 */
//@Named(value = "beanInsertRoles")
//@RequestScoped
@ManagedBean(name="beanRoles")
@ViewScoped
public class RolesBean implements Serializable{

    private static final long serialVersionUID = 7617362545974960572L;

    
    RolesJpaController ControlTabla;//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE
    EstadoJpaController ControlForeingKey;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    ControllerCrud controlCrud;//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA*/
    private Roles selectedRoles;
    /*VARIABLES PARA EL FORMULARIO*/
    private String descripcion;
    private Integer estado;

    /**
     * Creates a new instance of BeanInsertRoles
     */
     @PostConstruct
    public void initBeanRoles() {
        this.controlCrud = new ControllerCrud();//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
         this.ControlTabla = new RolesJpaController();//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE
        this.ControlForeingKey = new EstadoJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    }
    
    /*CARGA EL CONTENIDO DE LA DATATABLE*/
    public List<Roles> getRoles() {
        return ControlTabla.findRolesEntities();
    }
    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
     public List<Estado> getListEstado() {
       return ControlForeingKey.findEstadoEntities(1, 0);
    }
     
    /*GETTER AND SETTER DEL CONTENIDO DE LA FILA SELECCIONADA*/
      public Roles getSelectedRoles() {
         if(selectedRoles != null) {
             llenaForm();
        }
        return selectedRoles;
    }
 
    public void setSelectedRoles(Roles selectedRoles) {
        this.selectedRoles = selectedRoles;
    }
    
/***LLENADO DEL FORMULARIO CON EL CONTENIDO SELECCIONADO DE LA FILA***/  
    public void llenaForm(){
        this.descripcion=selectedRoles.getRol();
        
        System.out.println(this.descripcion);
    }
    
    
    
    
    /****
     *
     * CONTENIDO PARA EL INSERT
     ***/
    /*CAPTURA LA DATA DEL FORMULARIO A GUARDAR*/
    public void setInsert(){
        
        Roles r = new Roles();
        //r.setIdRol(1);
        r.setRol(this.descripcion);
        r.setIdEstado(ControlForeingKey.findEstado(this.estado));
        controlCrud.begin();
        controlCrud.insert(r);
        controlCrud.close();
        formClear();
    }
    /**LIMPIA CONTENIDO DEL FORMULARIO**/
    public void formClear(){
        this.descripcion="";
    }
     /*GETTER AND SETTER PARA EL CONTENIDO DEL FORMULARIO*/
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
