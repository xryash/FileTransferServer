/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

import com.woop.filetransferprototype.cryptography.SHA256;
import com.woop.filetransferprototype.local.entity.Account;
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
    @Path("accountcreate/1.0")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response accountCreate(
                                 @QueryParam("login") String login,
                                 @QueryParam("password") String password) {
        
        
        //String hash = SHA256.hash(login + password);
        String token = UUID.randomUUID().toString();
        Account account = new Account(login,password,token);
        AccountCreateRequest accountRequest = new AccountCreateRequest(account);
        AccountCreateResponse result = accountCreateHandler.handle(accountRequest);
        return Response
                .status(200)
                .entity(result)
                .build();
    }
}
