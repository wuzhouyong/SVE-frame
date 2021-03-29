package com.ruoyi.framework.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class AppAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String paramterName;

    public AppAuthenticationFilter(String appLoginUrl, String paramterName) {
        super(appLoginUrl);
        this.paramterName = paramterName;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter(this.paramterName);
        AppAuthenticationToken authRequest = new AppAuthenticationToken(username);
        setDetails(request, authRequest);
        Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
        return authentication;
    }
    
    private void setDetails(HttpServletRequest request, AppAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}