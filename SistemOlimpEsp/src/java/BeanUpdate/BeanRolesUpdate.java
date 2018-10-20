/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanUpdate;

import Controller.RolesJpaController;
import javax.faces.bean.ViewScoped;
import Entities.Roles;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author axel.medina
 */
@ManagedBean(name="beanRolesUpdate")
@ViewScoped
public class BeanRolesUpdate implements Serializable{

    private static final long serialVersionUID = 7081625716803256821L;

    //private Roles selectedRoles;
    private Roles selectedRoles;
    private RolesJpaController ControlRoles;

    /**
     * Creates a new instance of beanRolesUpdate
     */
    @PostConstruct
    public void initBeanRolesUpdate() {
        this.ControlRoles = new RolesJpaController();
         System.out.println(this.selectedRoles+"init");
    }

    public List<Roles> getRoles() {
        //System.out.println(ControlRoles.findRolesEntities().toString());
        System.out.println(ControlRoles.findRolesEntities()+"LIST");
        return ControlRoles.findRolesEntities();
    }

    public Roles getSelectedRoles() {
        System.out.println(this.selectedRoles+"GET");
        return selectedRoles;
    }

    public void setSelectedRoles(Roles selectedRoles) {
        this.selectedRoles = selectedRoles;
        System.out.println(this.selectedRoles+"SET");
    }
}
