/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

import com.woop.filetransferprototype.local.entity.AccountData;
import com.woop.filetransferprototype.web.account.hadler.LocalStorageAccountAuthenticationHandler;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.woop.filetransferprototype.web.account.hadler.IAccountAuthenticationHandler;
import com.woop.filetransferprototype.web.account.requests.AccountAuthenticationRequest;
import com.woop.filetransferprototype.web.account.responses.AccountAuthenticationResponse;

/**
 *
 * @author NoID
 */
public class AccountAuthenticationResource {
    private final IAccountAuthenticationHandler accountAuthenticationHandler;
    
    public AccountAuthenticationResource() {
    this.accountAuthenticationHandler = new LocalStorageAccountAuthenticationHandler();
    }
    
    @POST
    @Path("account/authentication/1.0")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response accountVerify(AccountData request) {
    
        String login = request.getLogin();
        String password = request.getPassword();
        AccountAuthenticationRequest accountRequest = new AccountAuthenticationRequest(login,password);
        AccountAuthenticationResponse result = accountAuthenticationHandler.handle(accountRequest);
        return Response
                .status(200)
                .entity(result)
                .build();
    }
    
}
