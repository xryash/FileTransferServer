/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.resources;

import com.woop.filetransferprototype.web.filedownload.handler.LocalStorageFileDownloadHandler;
import com.woop.filetransferprototype.web.filedownload.requests.FileDownloadRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author NoID
 */

@Path("/")
public class FileDownloadResource {
    
    private final LocalStorageFileDownloadHandler fileDownloadHandler;

    public FileDownloadResource() {
        this.fileDownloadHandler = new LocalStorageFileDownloadHandler();
    }
    
    @Context 
    private SecurityContext sc;
    
    @GET
    @Path("download/1.0/")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response fileDownload(@HeaderParam("FileId") int id) {
        System.out.println("Запрос получен");
        String login = sc.getUserPrincipal().getName();
        FileDownloadRequest fileDownloadRequest = new FileDownloadRequest(id, login);
        FileDownloadResponse result = fileDownloadHandler.handle(fileDownloadRequest);
        
        return Response
                .status(200)
                .entity(result)
                .build();
    }
    
    
}
