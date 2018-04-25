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
import javax.ws.rs.core.Response;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author NoID
 */
public class PasswordHandler {

    private final IAccountRepository accountRepository;

    public PasswordHandler() {
        this.accountRepository = new LocalStorageAccountRepository();
    }
   
    public Response handle(String login, String newpassword) {
        System.out.println("Запрос обрабатывается");
        
        if (login == null) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: login"));
        }
        
        if (newpassword == null) {
            throw new AccountException(new ServiceError("missingAccountError", "Missing Account data"), String.format("Missing Parameter: newpassword"));
        }
        
        String decoded = new String(Base64.decode(newpassword.getBytes()));
        System.out.println("decoded " + decoded);
        Account account = accountRepository.getByLogin(login);
        
        if(account == null) {
            throw new AccountException(new ServiceError("NonexistentAccountError", "Nonexistent Account data"), String.format("Nonexistent Account"));
        }
        Log.log(PasswordHandler.class.getSimpleName(), "Аккаунт найден");
        Log.log(PasswordHandler.class.getSimpleName(), account.toString());
        
        if(!accountRepository.changePassword(login,decoded)) {
            throw new AccountException(new ServiceError("SQLError", "Cannot change a password"), String.format("Cannot change a password"));
        }
        Log.log(PasswordHandler.class.getSimpleName(), "Пароль изменен");
        return Response.ok().build();
    }
    
    
    
    
}
