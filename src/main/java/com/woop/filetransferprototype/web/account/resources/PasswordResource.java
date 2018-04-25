/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

import com.woop.filetransferprototype.web.account.hadler.PasswordHandler;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author NoID
 */
@Path("/")
public class PasswordResource {
    
    @Context 
    private SecurityContext sc;
    
    @GET
    @Path("account/password/1.0")
    @RolesAllowed({"USER","ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(@QueryParam("new") String newpassword) {
        System.out.println("Запрос получен");
        String login = sc.getUserPrincipal().getName();
        
        PasswordHandler handler = new PasswordHandler();
        Response result = handler.handle(login,newpassword);
        return result;
    
    }
    
    
}
