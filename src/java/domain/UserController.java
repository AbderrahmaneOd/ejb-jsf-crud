/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import services.UserFacade;

/**
 *
 * @author hp
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable{
    
    // Inject UserFacade using @EJB annotation
    @EJB
    private services.UserFacade ejbFacade;
    private List<User> items = null;
    private String username;
    private String password;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }

    public UserFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UserFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private UserFacade getFacade() {
        return ejbFacade;
    }
    
    public void login() {
        // logic to authenticate the user using the UserFacade
        
        if (items == null) {
            items = getFacade().findAll();
        }
        //System.out.println("User 1 : " + items.get(0));
        for (User user : items) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                // Authentication successful
                // Get the current session
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            
                // Set user information in the session
                session.setAttribute("loggedInUser", username);
                try {
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.redirect(ec.getRequestContextPath() + "/faces/web/employe/List.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    
}
    
}
