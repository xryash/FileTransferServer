/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.requests;

/**
 *
 * @author NoID
 */
public class FileDownloadRequest {
    private final int id;
    private final String login;

    public FileDownloadRequest(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
    
    
    
    
    
}
