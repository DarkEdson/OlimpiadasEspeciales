
package Util;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class Util implements Serializable{

    private static final long serialVersionUID = -8601347865933000210L;

	public static HttpSession getSession() {
		return (HttpSession)
				FacesContext.
				getCurrentInstance().
				getExternalContext().
				getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.
				getCurrentInstance().
				getExternalContext().getRequest();
	}

	public static String getUserName()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return  session.getAttribute("username").toString();
	}

	public static String getUserId()
	{
		HttpSession session = getSession();
		if ( session != null )
			return (String) session.getAttribute("userid");
		else
			return null;
	}
}