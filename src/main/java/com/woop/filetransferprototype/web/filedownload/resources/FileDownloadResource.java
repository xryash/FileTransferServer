/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.resources;

import com.woop.filetransferprototype.local.entity.LocalFile;
import com.woop.filetransferprototype.web.filedownload.handler.FileListHandler;
import com.woop.filetransferprototype.web.filedownload.handler.LocalStorageFileDownloadHandler;
import com.woop.filetransferprototype.web.filedownload.requests.FileDownloadRequest;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author NoID
 */

@Path("/")
public class FileDownloadResource {
    
    @Context 
    private SecurityContext sc;
    
    @GET
    @RolesAllowed({"USER","ADMIN"})
    @Path("download/1.0/")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response fileDownload(@QueryParam("id") int id) {
        System.out.println("Запрос получен");
        String login = sc.getUserPrincipal().getName();
        FileDownloadRequest fileDownloadRequest = new FileDownloadRequest(id, login);
        
        
        
        LocalStorageFileDownloadHandler fileDownloadHandler = new LocalStorageFileDownloadHandler();
        StreamingOutput result = fileDownloadHandler.handle(fileDownloadRequest);
        
        return Response 
                .status(200)
                .entity(result)
                .build();
    }
    
    //@GET
    //@RolesAllowed({"USER","ADMIN"})
    //@Path("download/1.0/")
    //@Produces(MediaType.MULTIPART_FORM_DATA)
    
    
    @GET
    @RolesAllowed({"USER","ADMIN"})
    @Path("download/list/1.0/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFileList() {
        System.out.println("Запрос получен");
        String login = sc.getUserPrincipal().getName();
        FileListHandler handler = new FileListHandler();
        List<LocalFile> list = handler.handle(login);
        
        return Response
                .status(200)
                .entity(list)
                .build();
                
    }
    
}
