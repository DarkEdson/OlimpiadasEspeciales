/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.UsuariosJpaController;
import Entities.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanUsuarios")
@Dependent
public class BeanUsuarios implements Serializable {

    private static final long serialVersionUID = -1461075566655989957L;
    UsuariosJpaController ControlUsuarios = new UsuariosJpaController();

    /**
     * Creates a new instance of BeanUsuarios
     */
    public BeanUsuarios() {
    }

    public List<Usuarios> getUsuarios() {
        return ControlUsuarios.findUsuariosEntities();
    }
}
