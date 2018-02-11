/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

import com.woop.filetransferprototype.local.log.Log;
import com.woop.filetransferprototype.web.account.hadler.LocalStorageAccountVerifyHandler;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.woop.filetransferprototype.web.account.requests.AccountVerifyRequest;
import com.woop.filetransferprototype.web.account.responses.AccountVerifyResponse;
import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import com.woop.filetransferprototype.web.account.hadler.IAccountVerifyHandler;

/**
 *
 * @author NoID
 */
@Path("/")
public class AccountVerifyResource {
    private final IAccountVerifyHandler accountVerifyHandler;
    
    public AccountVerifyResource() {
    this.accountVerifyHandler = new LocalStorageAccountVerifyHandler();
    }
        
    @GET
    @Path("account/verify/1.0")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response accountVerify(@HeaderParam("Authorization") String authorization) {
        Log.log(AccountVerifyResource.class.getSimpleName(), "Запрос получен");
        Log.log(AccountVerifyResource.class.getSimpleName(), "Authorization "+ authorization);
        AccountVerifyRequest accountRequest = new AccountVerifyRequest(authorization);
        AccountVerifyResponse result = accountVerifyHandler.handle(accountRequest);
        return Response
                .status(200)
                .entity(result)
                .build();
    }
    
    
}
