/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.entity;

/**
 *
 * @author NoID
 */
public class LocalFile {
    private int id;
    private final String UUID;
    private final String submittedFileName;
    private final int host;
    private int[] access;
    private final String directory;

    public LocalFile(int id, String UUID, String submittedFileName, int host, int[] access, String directory) {
        this.id = id;
        this.UUID = UUID;
        this.submittedFileName = submittedFileName;
        this.host = host;
        this.access = access;
        this.directory = directory;
    }

    public LocalFile(String UUID, String submittedFileName, int host, String directory) {
        this.UUID = UUID;
        this.submittedFileName = submittedFileName;
        this.host = host;
        this.directory = directory;
    }

    public int getId() {
        return id;
    }

    public String getUUID() {
        return UUID;
    }

    public String getSubmittedFileName() {
        return submittedFileName;
    }

    public int getHost() {
        return host;
    }

    public int[] getAccess() {
        return access;
    }

    public String getDirectory() {
        return directory;
    }
    
    

}
