/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.requests;

import com.woop.filetransferprototype.local.entity.Account;

/**
 *
 * @author NoID
 */
public class AccountCreateRequest {

    private final Account account;

    public AccountCreateRequest(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
    
    
    
}
