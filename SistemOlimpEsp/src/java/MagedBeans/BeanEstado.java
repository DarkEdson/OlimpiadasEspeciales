/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;
import Controller.EstadoJpaController;
import Entities.Estado;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanEstado")
@Dependent
public class BeanEstado implements Serializable {

    private static final long serialVersionUID = 8157811032147682553L;
    EstadoJpaController ControlEstado = new EstadoJpaController();

    /**
     * Creates a new instance of BeanEstado
     */
    public BeanEstado() {
    }

    public List<Estado> getEstado() {
        return ControlEstado.findEstadoEntities();
    }
}
