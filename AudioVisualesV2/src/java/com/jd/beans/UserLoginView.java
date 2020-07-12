/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.beans;

import com.jd.entities.Credencial;
import com.jd.facade.CredencialFacade;
import dao.LoginDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import utils.MyUtil;

/**
 *
 * @author Jesus Dicent
 */
@Named(value="UserLoginView")
@SessionScoped
public class UserLoginView implements Serializable{
     
    
    private LoginDao credencial;
    private CredencialFacade CF;

    public UserLoginView() {
        this.CF = new CredencialFacade();
        if(this.credencial == null){
            this.credencial = new LoginDao();
        }
    }

    public LoginDao getCredencial() {
        return credencial;
    }

    public void setCredencial(LoginDao credencial) {
        this.credencial = credencial;
    }
    
   
    public void login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn;
        String ruta= "";
        
        Credencial CD  = this.CF.CheckLogin(this.credencial);
        if(CD != null) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", CD.getUsername());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", CD.getUsername()+" - "+CD.getEmpleado().getNombre());
            ruta =  MyUtil.basePathLogin()+"index.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Crendenciales incorrectas");
            if(this.credencial == null){
                this.credencial = new LoginDao();
            }
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("view", ruta);
    }   
    public void logout(){
        
        String ruta= MyUtil.baseUrl();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.invalidate();
        
        
        context.addCallbackParam("loggetOut", true);        
        context.addCallbackParam("view", ruta);
        
        
    }
}
