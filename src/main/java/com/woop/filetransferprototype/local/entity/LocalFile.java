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
public class LocalFile extends HttpFile {
    
    
    private final String targetFileName;
    private final int host;
    private int[] access;
    private int id;

    public LocalFile(String targetFileName, int host, String submittedFileName, InputStream stream, String directory, long size) {
        super(submittedFileName, stream, directory, size);
        this.targetFileName = targetFileName;
        this.host = host;
    }

    public LocalFile(String targetFileName, int host, int[] access, int id, String submittedFileName, InputStream stream, String directory, long size) {
        super(submittedFileName, stream, directory, size);
        this.targetFileName = targetFileName;
        this.host = host;
        this.access = access;
        this.id = id;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public int getHost() {
        return host;
    }

    public int[] getAccess() {
        return access;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LocalFile{" + "UUID=" + targetFileName + ", host=" + host + ", access=" + access + ", id=" + id + '}';
    }
    
    
}
