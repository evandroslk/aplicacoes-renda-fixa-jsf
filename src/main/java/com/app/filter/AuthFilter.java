package com.app.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestURI = httpRequest.getRequestURI();
        
        if (requestURI.contains("login.xhtml") || 
            requestURI.contains("/jakarta.faces.resource/")) {
            chain.doFilter(request, response);
            return;
        }
        
        HttpSession session = httpRequest.getSession(false);
        boolean isLogado = session != null && session.getAttribute("usuarioLogado") != null;
        
        if (!isLogado) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }
}
