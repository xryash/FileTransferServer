/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jersey.multipart.MultiPart;
import com.woop.filetransferprototype.local.entity.LocalFile;
import java.io.File;
import java.io.InputStream;

/**
 *
 * @author NoID
 */
public class FileDownloadResponse {
    private final MultiPart multipartEntity;
    private final String name;

    
    

    @JsonProperty("name")
    public String getName(){
        return localFile.getSubmittedFileName();
    }
    
    
            
}
