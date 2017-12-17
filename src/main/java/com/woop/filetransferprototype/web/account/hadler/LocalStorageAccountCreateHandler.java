/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.hadler;

import com.woop.filetransferprototype.errors.ServiceError;
import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.web.account.exceptions.AccountException;
import com.woop.filetransferprototype.web.account.requests.AccountCreateRequest;
import com.woop.filetransferprototype.web.account.responses.AccountCreateResponse;

/**
 *
 * @author NoID
 */
public class LocalStorageAccountCreateHandler implements IAccountCreateHandler {

    private final IAccountRepository accountRepository;
    
    public LocalStorageAccountCreateHandler() {
        this.accountRepository = new LocalStorageAccountRepository();
    }
    
    
    public AccountCreateResponse handle(AccountCreateRequest request) {

        if(request == null) {
            throw new AccountException(new ServiceError("missingAccount", "Missing Account data"), String.format("Missing Parameter: request"));
        }

        Account account = request.getAccount();

        if(account == null) {
            throw new AccountException(new ServiceError("missingAccount", "Missing Account data"), String.format("Missing Parameter: request.account"));
        }
        
        if (accountRepository.getByLogin(account.getLogin()) != null){
            throw new AccountException(new ServiceError("repeatingAccount", "Repeating Account data"), String.format("Repeating Parameter: request.account"));
        }
        
        if  (!accountRepository.save(account)){
            throw new AccountException(new ServiceError("savingAccountError", "Error saving"), String.format("Saving Account failed"));
        }
            
        System.out.println(account.toString());

        return new AccountCreateResponse(account.getLogin(),account.getToken());
    }
    
    private boolean isAccountExist(Account account) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private boolean createNewAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
