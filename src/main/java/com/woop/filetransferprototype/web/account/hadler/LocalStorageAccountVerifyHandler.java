/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.hadler;

import com.woop.filetransferprototype.errors.ServiceError;
import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.log.Log;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.web.account.exceptions.AccountException;
import com.woop.filetransferprototype.web.account.requests.AccountVerifyRequest;
import com.woop.filetransferprototype.web.account.responses.AccountVerifyResponse;
import java.util.StringTokenizer;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author NoID
 */
public class LocalStorageAccountVerifyHandler implements IAccountVerifyHandler {

    private final IAccountRepository accountRepository;
    private static final String AUTHENTICATION_SCHEME = "Basic";
    
    public LocalStorageAccountVerifyHandler() {
        this.accountRepository = new LocalStorageAccountRepository();
    }
    
    public AccountVerifyResponse handle(AccountVerifyRequest request) {
        Log.log(LocalStorageAccountVerifyHandler.class.getSimpleName(), "Запрос обрабатывается");
        
        if(request == null) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: request"));
        }
        
        if(request.getAuthorization() == null || request.getAuthorization().isEmpty()) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: request.authorization"));
        }
        
        String authorization = request.getAuthorization(); 
        Log.log(LocalStorageAccountCreateHandler.class.getSimpleName(),authorization );
        final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String login = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        
        if(login == null) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: username"));
        }
        
        if(password == null) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: password"));
        }
        
        Account account = accountRepository.getByLogin(login);
        
        if(account == null) {
            throw new AccountException(new ServiceError("NonexistentAccountError", "Nonexistent Account data"), String.format("Nonexistent Account"));
        }
        Log.log(LocalStorageAccountCreateHandler.class.getSimpleName(), "Аккаунт найден");
        Log.log(LocalStorageAccountCreateHandler.class.getSimpleName(), account.toString());
        
        return new AccountVerifyResponse(new String(Base64.encode(account.getToken().getBytes())));
        
    }
    
}
