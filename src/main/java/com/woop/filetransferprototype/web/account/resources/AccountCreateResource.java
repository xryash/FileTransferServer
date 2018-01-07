/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

import com.woop.filetransferprototype.cryptography.RandomString;
import com.woop.filetransferprototype.cryptography.SHA256;
import com.woop.filetransferprototype.cryptography.Token;
import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.entity.AccountData;
import com.woop.filetransferprototype.web.account.hadler.IAccountCreateHandler;
import com.woop.filetransferprototype.web.account.hadler.LocalStorageAccountCreateHandler;
import com.woop.filetransferprototype.web.account.requests.AccountCreateRequest;
import com.woop.filetransferprototype.web.account.responses.AccountCreateResponse;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author NoID
 */
@Path("/")
public class AccountCreateResource {
    
    
    private final IAccountCreateHandler accountCreateHandler;
    
    public AccountCreateResource() {
    this.accountCreateHandler = new LocalStorageAccountCreateHandler();
    }
    
    
    @POST
    @Path("account/new/1.0")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response accountCreate(AccountData request) {
        
        String login = request.getLogin();
        String password = request.getPassword();
        String salt = RandomString.generateRandomString(60);
        String token = Token.generateToken(password,salt);
        
        Account account = new Account(login,password,token,salt);
        AccountCreateRequest accountRequest = new AccountCreateRequest(account);
        AccountCreateResponse result = accountCreateHandler.handle(accountRequest);
        return Response
                .status(200)
                .entity(result)
                .build();
    }
}
