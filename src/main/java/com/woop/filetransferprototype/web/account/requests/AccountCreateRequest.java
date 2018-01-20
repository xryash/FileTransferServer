/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.requests;

import com.woop.filetransferprototype.local.entity.Account;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author NoID
 */
public class AccountCreateRequest {

    private final String authorization;

    public AccountCreateRequest(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    
    
    
    
    
}
