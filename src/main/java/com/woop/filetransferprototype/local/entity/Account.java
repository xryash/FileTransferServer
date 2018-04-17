/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.entity;

import java.security.Principal;




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
    private final String role;

    public Account(String login, String password, String token, String salt, String role) {
        this.login = login;
        this.password = password;
        this.token = token;
        this.salt = salt;
        this.role = role;
    }

    public Account(int id, String login, String password, String token, String salt, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
        this.salt = salt;
        this.role = role;
    }

    public int getId() {
        return id;
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

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", login=" + login + ", password=" + password + ", token=" + token + ", salt=" + salt + ", role=" + role + '}';
    }

    public String getLogin() {
        return login;
    }

 
    

}
