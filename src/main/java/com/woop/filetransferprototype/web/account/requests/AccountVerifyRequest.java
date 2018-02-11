/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.requests;

/**
 *
 * @author NoID
 */
public class AccountVerifyRequest {

    private final String authorization;

    public AccountVerifyRequest(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    
    
}
