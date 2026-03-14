package com.app.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.security.enterprise.SecurityContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;

    private String password;

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    public void login() throws IOException {
        switch(processAuthentication()){
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciais inválidas", null));
                break;
            case SUCCESS:
                getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/app/index.xhtml");
                break;
        }
    }

    private AuthenticationStatus processAuthentication(){
        ExternalContext ec = getExternalContext();
        return securityContext.authenticate((HttpServletRequest)ec.getRequest(), 
                                            (HttpServletResponse)ec.getResponse(), 
                                            AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password)));
    }

    public void logout() throws IOException {
        ExternalContext ec = facesContext.getExternalContext();

        ec.invalidateSession(); // remove sessão e autenticação
        ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
    }
    
    private ExternalContext getExternalContext(){
        return facesContext.getExternalContext();
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

    public String getUsuarioLogado() {
        if (securityContext.getCallerPrincipal() != null) {
            return securityContext.getCallerPrincipal().getName();
        }
        return null;
    }

}
