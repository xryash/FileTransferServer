/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.entity;

import java.io.InputStream;

/**
 *
 * @author NoID
 */
public class HttpFile {
    private final String submittedFileName;
    private final InputStream stream;

    public HttpFile(String submittedFileName, InputStream stream) {
        this.submittedFileName = submittedFileName;
        this.stream = stream;
    }

    public String getSubmittedFileName() {
        return submittedFileName;
    }

    public InputStream getStream() {
        return stream;
    }

    @Override
    public String toString() {
        return "HttpFile{" + "submittedFileName=" + submittedFileName + ", stream=" + stream + '}';
    }

    
}

