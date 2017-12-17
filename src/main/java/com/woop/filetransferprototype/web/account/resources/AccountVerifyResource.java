/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

import com.woop.filetransferprototype.web.account.hadler.AccountVerifyHandler;
import com.woop.filetransferprototype.web.account.hadler.IAccountVerifyHandler;
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
public class AccountVerifyResource {
    private final IAccountVerifyHandler accountVerifyHandler;
    
    public AccountVerifyResource() {
    this.accountVerifyHandler = new AccountVerifyHandler();
    }
    
    @POST
    @Path("accountverify/1.0")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response accountVerify(
                                 @QueryParam("token") String token) {
    
        return Response.ok().build();
    }
    
}
