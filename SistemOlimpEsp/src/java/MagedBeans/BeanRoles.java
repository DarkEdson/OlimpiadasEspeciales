/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.RolesJpaController;
import Entities.Roles;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanRoles")
@Dependent
public class BeanRoles implements Serializable {

    private static final long serialVersionUID = 5518766583746416247L;
    RolesJpaController ControlRoles = new RolesJpaController();

    /**
     * Creates a new instance of BeanRoles
     */
    public BeanRoles() {
    }

    public List<Roles> getRoles() {
        return ControlRoles.findRolesEntities();
    }
}
