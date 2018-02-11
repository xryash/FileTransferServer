/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.fileupload.requests;

import com.woop.filetransferprototype.local.entity.HttpFile;

/**
 *
 * @author NoID
 */
public class FileUploadRequest {
    private final HttpFile httpFile;
    private final String login;

    public FileUploadRequest(HttpFile httpFile, String login) {
        this.httpFile = httpFile;
        this.login = login;
    }

    public HttpFile getHttpFile() {
        return httpFile;
    }

    public String getLogin() {
        return login;
    }

    

    
}
