/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedelete.requests;

/**
 *
 * @author NoID
 */
public class FileDeleteRequest {
    private final String login;
    private final int id;

    public FileDeleteRequest(String login, int id) {
        this.login = login;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FileDeleteRequest{" + "login=" + login + ", id=" + id + '}';
    }
    
    
}
