package com.app.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private String usuario;
    private String senha;
    private Map<String, String> usuarios;

    @PostConstruct
    public void init() {
        usuarios = new HashMap<>();
        usuarios.put("admin", "admin123");
        usuarios.put("user", "user123");
    }

    public void checkError(ComponentSystemEvent event) {
        String errorParam = FacesContext.getCurrentInstance()
            .getExternalContext().getRequestParameterMap().get("error");
        if ("true".equals(errorParam)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Você precisa fazer login para acessar esta página"));
        }
    }

    public String login() {
        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(senha)) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
            if (session == null || session.isNew()) {
                session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);
            }
            session.setAttribute("usuarioLogado", usuario);
            return "index?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário ou senha inválidos"));
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/login.xhtml");
        } catch (Exception e) {
            // log error
        }
        return null;
    }

    public boolean isLogado() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
        return session != null && session.getAttribute("usuarioLogado") != null;
    }

    public String getUsuarioLogado() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
        return session != null ? (String) session.getAttribute("usuarioLogado") : null;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
