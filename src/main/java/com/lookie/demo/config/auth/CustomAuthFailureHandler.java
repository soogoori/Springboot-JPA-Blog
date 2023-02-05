package com.lookie.demo.config.auth;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        if (exception instanceof BadCredentialsException) {
            errorMessage = "id or password is wrong.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "system problem.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "no account. please sign up";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage = "denied";
        } else {
            errorMessage = "dontknow";
        }
        setDefaultFailureUrl("/auth/login?error=true&exception="+errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}