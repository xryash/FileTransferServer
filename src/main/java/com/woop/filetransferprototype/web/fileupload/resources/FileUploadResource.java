/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.fileupload.resources;


import com.woop.filetransferprototype.web.fileupload.requests.FileUploadRequest;
import com.woop.filetransferprototype.web.fileupload.responses.FileUploadResponse;
import com.woop.filetransferprototype.local.entity.HttpFile;
import com.woop.filetransferprototype.web.fileupload.hadler.IFileUploadHandler;
import com.woop.filetransferprototype.web.fileupload.hadler.LocalStorageFileUploadHandler;
import java.io.InputStream;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
/**
 *
 * @author NoID
 */
@Path("/")
public class FileUploadResource {
        
    private final IFileUploadHandler fileUploadHandler;

    @Context 
    private SecurityContext sc;
    
    
    
     
    public FileUploadResource() {
        this.fileUploadHandler = new LocalStorageFileUploadHandler();
    };
    
    @POST
    @RolesAllowed({"USER","ADMIN"})
    @Path("upload/1.0")
    @Consumes(MediaType.MULTIPART_FORM_DATA )
    @Produces(MediaType.APPLICATION_JSON)
    public Response fileUpload(                           
                               @FormDataParam("file") InputStream stream,
                               @FormDataParam("file") FormDataContentDisposition fileMetaData,
                               @FormDataParam("directory") String directory,
                               @FormDataParam("size") long size) {
        System.out.println("Запрос получен");
        String submittedFileName = fileMetaData.getFileName();
        String login = sc.getUserPrincipal().getName();
        System.out.println(login);
        HttpFile httpFile = new HttpFile(submittedFileName, stream ,directory ,size);
        System.out.println(httpFile.toString());
        FileUploadRequest fileUploadRequest = new FileUploadRequest(httpFile,login);
        FileUploadResponse result = fileUploadHandler.handle(fileUploadRequest);
        
        return Response
                .status(200)
                .entity(result)
                .build();
    }
}
