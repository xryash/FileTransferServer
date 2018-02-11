/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.provider.securitycontext;

import com.woop.filetransferprototype.local.entity.Account;
import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author NoID
 */
public class ApplicationSecurityContext implements SecurityContext {
    
    private final Account account;
    private final String scheme;

    public ApplicationSecurityContext(Account account, String scheme) {
        this.account = account;
        this.scheme = scheme;
    }
    
    
    public Principal getUserPrincipal() {
        return account;
    }

    public boolean isUserInRole(String s) {
        if (account.getRole() != null) {
            return account.getRole().contains(s);
        }
        return false;
    }

    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    public String getAuthenticationScheme() {
        return scheme;
    }
    
}
