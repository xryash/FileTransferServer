/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.resources;

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
    @Path("page/accountcreate")
    @Consumes(MediaType.TEXT_HTML)
    public Response getPageAccountCreate() {
        return Response.ok().entity("	<head>\n" +
"	</head>\n" +
"	<body>\n" +
"		<form action=\"/app/accountcreate/1.0\" method=\"POST\" enctype=\"multipart/form-data\">\n" +
"			<p><input type=\"Ваше имя\" name=\"user\"></p> " +
"		</form>\n" +
"	</body>\n" +
"</html>")
                .build();
    }
    
    @GET
    @Path("page/upload")
    @Consumes(MediaType.TEXT_HTML)
    public Response getPageUpload() {
        return Response.ok().entity("	<head>\n" +
"	</head>\n" +
"	<body>\n" +
"		<form action=\"/app/upload/1.0\" method=\"POST\" enctype=\"multipart/form-data\">\n" +
"			<p>\n" +
"				<b>File: </b>\n" +
"				<input type=\"file\" name=\"file\"  size=\"50\"/>\n" +
"			</p>\n" +
"			\n" +
"			<input type=\"submit\" value=\"Upload It\">\n" +
"		</form>\n" +
"	</body>\n" +
"</html>")
                .build();
    }
}
