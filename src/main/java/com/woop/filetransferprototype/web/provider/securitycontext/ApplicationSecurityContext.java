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
    private final boolean secure;
    

    public ApplicationSecurityContext(Account account, String scheme, boolean secure) {
        this.account = account;
        this.scheme = scheme;
        this.secure = secure;
    }
    
    
    public Principal getUserPrincipal() {
        return (Principal) account;
    }

    public boolean isUserInRole(String s) {
        if (account.getRole() != null) {
            return account.getRole().contains(s);
        }
        return false;
    }

    public boolean isSecure() {
        return secure;
    }

    public String getAuthenticationScheme() {
        return scheme;
    }
    
}
