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
import com.woop.filetransferprototype.local.provider.RootPathProvider;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
/**
 *
 * @author NoID
 */
@Path("/")
public class FileUploadResource {
    
    private RootPathProvider rootPathProvider() {
        return new RootPathProvider("d:\\out\\");
    }
    
    private final IFileUploadHandler fileUploadHandler;

    public FileUploadResource() {
        this.fileUploadHandler = new LocalStorageFileUploadHandler( rootPathProvider());
    };
    
    @POST
    @Path("upload/1.0")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fileUpload(
                               @QueryParam("token") String token,
                               @QueryParam("directory") String directory,
                               @FormDataParam("file") InputStream stream,
                               @FormDataParam("file") FormDataContentDisposition fileDetail) {
        
        HttpFile httpFile = new HttpFile(fileDetail.getFileName(), stream);
        FileUploadRequest fileUploadRequest = new FileUploadRequest(token, httpFile,directory);
        FileUploadResponse result = fileUploadHandler.handle(fileUploadRequest);

        return Response
                .status(200)
                .entity(result)
                .build();
    }
}
