/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author NoID
 */
@Path("/")
public class ExtraResource {
    @GET
    @Path("page/account/new")
    @Consumes(MediaType.TEXT_HTML)
    public Response getPageAccountCreate() {
        return Response.ok().entity("	<head>\n" +
"	</head>\n" +
"	<body>\n" +
"		<form action=\"/app/account/new/1.0\" method=\"POST\" enctype=\"multipart/form-data\">\n" +
"			<p>\n<b>Login: </b>\n" +
"			<p><input name=\"login\">\n" +
"			<p>\n<b>Password: </b>\n" +
"                       <p><input name=\"password\"></p>\n" +
"                       <p><input type=\"submit\" value=\"Create Account\"></p>" +
"		</form>\n" +
"	</body>\n" +
"</html>")
                .build();
    }
    
    @GET
    @Path("page/upload")
    @PermitAll
    @Consumes(MediaType.TEXT_HTML)
    public Response getPageUpload() {
        return Response.ok().entity(
"<html>" +
"	<head>\n" +
"	</head>\n" +
"           <body>\n" +
"		<form action=\"/app/upload/1.0\" method=\"POST\" enctype=\"multipart/form-data\">\n" +
"			<p>\n<b>File: </b>\n" +
"			<input type=\"file\" name=\"file\"  size=\"50\"/></p>\n" +
"                       <p><input name=\"token\"></p>\n" +
"			<input type=\"submit\" value=\"Upload It\">\n" +
"		</form>\n" +
"           </body>\n" +
"</html>")
                .build();
    }
}
