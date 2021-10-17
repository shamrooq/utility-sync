/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTUsers;
import ae.etisalatdigital.iot.ops.utility.sync.entities.MSTRoles;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;


/**
 *
 * @author au_mobility
 */
@Named(value = "loginbean")
@SessionScoped
public class Login implements Serializable {

    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager embenny;

    private String username;
    private String password;
    private boolean loggedin;
    private String fullname;
    private boolean admin;
    private MSTUsers user;
    private String roleName;

    public Login() {

    }
    
    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
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
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public void logout() {
        
        this.username = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.loggedin = false;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            
        } catch (Exception ex) {
            
        }
    }
    
    public String validateLogin211() {
        
        if (this.username == null) {
            return "failure";
        }

        MSTUsers user = embenny.find(MSTUsers.class, this.username);
        String loginUrl = "/faces/login";

        if (user == null) {
            return "failure";
        }

        if (user.getUserCode().equals(this.password)) {
            loggedin = true;

            MSTRoles staffRole = user.getUserRole();
            if (staffRole != null) {
                this.roleName = staffRole.getRoleName();
            }

            Date dt = new Date();
            fullname = user.getUserName();

            if (staffRole.getRoleName().equals("Administrator")) {
                return "/homeadmin.xhtml?faces-redirect=true";
            }else if (staffRole.getRoleName().equals("Customer")) {
                return "/homeadmin_customer.xhtml?faces-redirect=true";
            }
            

            return "/homefield.xhtml?faces-redirect=true";
        }

        return loginUrl;
    }
}
