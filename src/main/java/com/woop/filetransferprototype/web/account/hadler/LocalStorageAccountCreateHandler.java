/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.hadler;

import com.woop.filetransferprototype.cryptography.RandomString;
import com.woop.filetransferprototype.cryptography.Token;
import com.woop.filetransferprototype.errors.ServiceError;
import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.log.Log;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.web.account.exceptions.AccountException;
import com.woop.filetransferprototype.web.account.requests.AccountCreateRequest;
import com.woop.filetransferprototype.web.account.responses.AccountCreateResponse;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author NoID
 */
public class LocalStorageAccountCreateHandler implements IAccountCreateHandler {

    private final IAccountRepository accountRepository;
    private static final String AUTHENTICATION_SCHEME = "Basic";
    
    public LocalStorageAccountCreateHandler() {
        this.accountRepository = new LocalStorageAccountRepository();
    }
    
    
    
    public AccountCreateResponse handle(AccountCreateRequest request) {
        Log.log(LocalStorageAccountCreateHandler.class.getSimpleName(), "Запрос обрабатывается");
        
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
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: (password"));
        }
        
        if (accountRepository.getByLogin(login) != null){
            throw new AccountException(new ServiceError("repeatingAccountError", "Repeating Account data"), String.format("Repeating Parameter: request.account"));
        }
        
        String salt = RandomString.generateRandomString(60);
        String token = Token.generateToken(password,salt);
        String role = "USER";
        Account account = new Account(login,password,token,salt,role);
        
        if  (!accountRepository.save(account)){
            throw new AccountException(new ServiceError("savingAccountError", "Error saving"), String.format("Saving Account failed"));
        }
            
        System.out.println(account.toString());

        return new AccountCreateResponse(account.getLogin(),account.getToken());
    }   
    
}
