package javaee.team2.bean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{

	@Inject
	transient Logger log;
	
	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String password;
	
	public LoginBean() {
	}
	
	public String login(){
		HttpServletRequest request = getRequest();
		try{
			log.log(Level.INFO, "username:{0} password:{1}", new Object[]{username, password});
			request.login(username, password);
		}catch(ServletException ex){
			log.info("ServletException");
			return null;
		}
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String logout(){
		try{
			getServlet().invalidateSession();
			HttpServletRequest request = getRequest();
			request.logout();
		}catch(ServletException ex){
			log.fine("LOGOUT servletException");
		}
		return "/info/login.xhtml?faces-redirect=true";
	}
	
	public ExternalContext getServlet(){
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public HttpServletRequest getRequest(){
		return (HttpServletRequest) getServlet().getRequest();
	}
}
