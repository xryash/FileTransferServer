/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

import com.woop.filetransferprototype.local.log.Log;
import com.woop.filetransferprototype.web.account.hadler.IAccountCreateHandler;
import com.woop.filetransferprototype.web.account.hadler.LocalStorageAccountCreateHandler;
import com.woop.filetransferprototype.web.account.requests.AccountCreateRequest;
import com.woop.filetransferprototype.web.account.responses.AccountCreateResponse;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author NoID
 */
@Path("/")
//@PermitAll
//@RolesAllowed({"manager"})
public class AccountCreateResource {

    private final IAccountCreateHandler accountCreateHandler;

    public AccountCreateResource() {
        this.accountCreateHandler = new LocalStorageAccountCreateHandler();
    }

    
    
    @GET
    @Path("map")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestHeaders(@HeaderParam("token") String token,
                                                @HeaderParam("content-type") String contentType) {
        return Response
                .status(200)
                .entity(token + contentType)
                .build();

    }

    @GET
    @Path("hi")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response hi() {
        return Response
                .status(200)
                .entity("helloblet")
                .build();
    }

    @GET
    @Path("account/new/1.0")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response accountCreate(@HeaderParam("Authorization") String authorization) {
        Log.log(AccountCreateResource.class.getSimpleName(), "Запрос получен");
        AccountCreateRequest accountRequest = new AccountCreateRequest(authorization);
        AccountCreateResponse result = accountCreateHandler.handle(accountRequest);
        return Response
                .status(200)
                .entity(result)
                .build();
    }
}
