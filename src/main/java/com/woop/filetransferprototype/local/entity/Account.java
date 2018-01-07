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
    private final String salt;

    public Account(String login, String password, String token, String salt) {
        this.login = login;
        this.password = password;
        this.token = token;
        this.salt = salt;
    }

    public Account(int id, String login, String password, String token, String salt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", login=" + login + ", password=" + password + ", token=" + token + ", salt=" + salt + '}';
    }
    

}
