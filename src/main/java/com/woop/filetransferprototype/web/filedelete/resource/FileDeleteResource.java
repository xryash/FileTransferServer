/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedelete.resource;

import com.woop.filetransferprototype.web.filedelete.handler.FileDeleteHandler;
import com.woop.filetransferprototype.web.filedelete.requests.FileDeleteRequest;
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
public class FileDeleteResource {
    
    @Context 
    private SecurityContext sc;
    
    
    @GET
    @RolesAllowed({"USER","ADMIN"})
    @Path("delete/1.0/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fileDownload(@QueryParam("id") int id) {
        System.out.println("Запрос получен");
        String login = sc.getUserPrincipal().getName();
        FileDeleteRequest fileDeleteRequest = new FileDeleteRequest(login, id);
        FileDeleteHandler fileDeleteHandler = new FileDeleteHandler();
        Response result = fileDeleteHandler.handle(fileDeleteRequest);
        return result;
    }
}
