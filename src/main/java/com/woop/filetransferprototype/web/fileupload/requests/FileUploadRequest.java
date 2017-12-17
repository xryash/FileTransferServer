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
    private final String token;
    private final HttpFile httpFile;
    private final String directory;

    public FileUploadRequest(String token, HttpFile httpFile, String directory) {
        this.token = token;
        this.httpFile = httpFile;
        this.directory = directory;
    }

    public String getToken() {
        return token;
    }

    public HttpFile getHttpFile() {
        return httpFile;
    }

    public String getDirectory() {
        return directory;
    }



}
