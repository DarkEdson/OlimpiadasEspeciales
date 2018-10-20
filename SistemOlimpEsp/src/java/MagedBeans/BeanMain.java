/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Util.Util;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author axel.medina
 */
@Named(value = "beanMain")
@Dependent
public class BeanMain implements Serializable {

    private static final long serialVersionUID = 5525477863058816698L;
    String sessionLogin = "";
     HttpSession session = Util.getSession();
    /**
     * Creates a new instance of BeanMain
     */
    public BeanMain() {
    }

    private String dirxhtml;
    private String descripcion;

    private MenuModel menu;


    public String getSessionLogin() {
        return sessionLogin;
    }

    public void setSessionLogin(String nombre) {

        this.sessionLogin = nombre;
    }

    public void redirect(String where, String descripcion) {
       
        setSessionLogin((String) session.getAttribute("userName"));
        if (where == null || where.equals("")) {
            setDirxhtml(null);
            setDescripcion("main");
        } else {
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
            setDirxhtml("i/" + where + ".xhtml");
            setDescripcion(descripcion);
        }
    }

    private void populateMenu() {
        this.menu = new DefaultMenuModel();
        DefaultMenuItem op1 = new DefaultMenuItem("Carga masiva de inventario.");
        op1.setAjax(false);
        op1.setCommand("#{mainBean.redirect('upload','Carga masiva de inventario.')}");
        DefaultMenuItem op2 = new DefaultMenuItem("Casos de uso.");
        op2.setAjax(false);
        op2.setCommand("#{mainBean.redirect('ioManagement', 'Casos de uso.')}");
        menu.addElement(op1);
        menu.addElement(op2);
//           for(ProgramasModel programa : so.getMenu()) {
//                 DefaultMenuItem op = new DefaultMenuItem(programa.getDescripcion());
//                 op.setAjax(false);
//                 op.setCommand("#{mainBean.redirect('"+programa.getNombre()+"', '"+programa.getDescripcion()+"')}");
//                 menu.addElement(op);
//           }
    }

    private void setDirxhtml(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setDescripcion(String main) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
