/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.hadler;

import com.woop.filetransferprototype.errors.ServiceError;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.web.account.exceptions.AccountException;
import com.woop.filetransferprototype.web.account.requests.AccountAuthenticationRequest;
import com.woop.filetransferprototype.web.account.responses.AccountAuthenticationResponse;

/**
 *
 * @author NoID
 */
public class LocalStorageAccountAuthenticationHandler implements IAccountAuthenticationHandler {

    private final IAccountRepository accountRepository;
    
    public LocalStorageAccountAuthenticationHandler() {
        this.accountRepository = new LocalStorageAccountRepository();
    }
    
    public AccountAuthenticationResponse handle(AccountAuthenticationRequest request) {
        if(request == null) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: request"));
        }
        
        if(request.getLogin() == null) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: request.login"));
        }
        
        if(request.getPassword() == null) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: request.password"));
        }
        return null;
        
        
        
    }
    
}
