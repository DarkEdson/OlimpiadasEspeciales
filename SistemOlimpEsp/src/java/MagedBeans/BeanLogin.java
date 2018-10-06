/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.UsuariosJpaController;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import Util.Util;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;

@ManagedBean
public class BeanLogin implements Serializable {

    private static final long serialVersionUID = 9059286805603267070L;

    boolean result;
    private String nombre;
    private String password;
    String url = "./View/Main.xhtml";
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    UsuariosJpaController ControlUsuario = new UsuariosJpaController();

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public void send() throws IOException {

        result = ControlUsuario.getLogin(nombre, password);

        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", nombre);
            /*FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Login correcto!",
                    "Bienvenido!"));*/
            ec.redirect(url);
        } else {
            logout();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Login invalido!",
                    "Intentalo de nuevo!"));

        }
    }

    public void logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
    }

}
