/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author NoID
 */
public class LocalFile  {
    
    private int id;
    private int host;
    private String targetFileName;
    private String submittedFileName;
    private String directory;
    
    
    

    public LocalFile(int id, int host, String targetFileName,  String submittedFileName, String directory) {
        this.id = id;
        this.host = host;
        this.targetFileName = targetFileName;
        this.submittedFileName = submittedFileName;
        this.directory = directory;
        
    }

    public LocalFile(int host, String targetFileName,  String submittedFileName, String directory) {
        this.host = host;
        this.targetFileName = targetFileName;
        this.submittedFileName = submittedFileName;
        this.directory = directory;
    }

    @JsonProperty("targetFileName")
    public String getTargetFileName() {
        return targetFileName;
    }

    @JsonProperty("host")
    public int getHost() {
        return host;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("submittedFileName")
    public String getSubmittedFileName() {
        return submittedFileName;
    }

    @JsonProperty("directory")
    public String getDirectory() {
        return directory;
    }

    @Override
    public String toString() {
        return "LocalFile{" + "id=" + id + ", host=" + host + ", targetFileName=" + targetFileName + ", submittedFileName=" + submittedFileName + ", directory=" + directory + '}';
    }


    
    
}
