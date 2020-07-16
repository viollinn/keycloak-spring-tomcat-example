package com.example.demo.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ExampleController {

    @RequestMapping(method = GET, value = "/")
    public String getHome(){
        return "Hello! You can test protected area here:  /protected/*any_page*";
    }

    @RequestMapping(method = GET, value = "/protected/**")
    public String getProtected(KeycloakAuthenticationToken principal){

        KeycloakPrincipal kp = (KeycloakPrincipal) principal.getPrincipal();
        KeycloakSecurityContext ksc = kp.getKeycloakSecurityContext();

        return "Protected area: "+ksc.getToken().getName();
    }

}
