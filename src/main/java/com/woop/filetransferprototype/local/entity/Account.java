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
public class Account {
    
    private int id;
    private final String login;
    private final String password;
    private final String token;

    public Account(String login, String password, String token) {
        this.login = login;
        this.password = password;
        this.token = token;
    }
    
    public Account(int id, String login, String password, String token) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
    }
    
    

    @Override
    public String toString() {
        return "Account{" + "hash=" + id + ", login=" + login + ", password=" + password + ", token=" + token + '}';
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

}
