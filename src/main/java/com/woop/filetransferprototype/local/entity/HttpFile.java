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
    private final String directory;
    private final long size;

    public HttpFile(String submittedFileName, InputStream stream, String directory, long size) {
        this.submittedFileName = submittedFileName;
        this.stream = stream;
        this.directory = directory;
        this.size = size;
    }

    public String getSubmittedFileName() {
        return submittedFileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public String getDirectory() {
        return directory;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "HttpFile{" + "submittedFileName=" + submittedFileName + ", stream=" + stream + ", directory=" + directory + ", size=" + size + '}';
    }
    
    
    
}

