package com.jzy.alarmsystembackend.pojo.DTO;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class WebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new WebAuthenticationDetails(context);
    }
}