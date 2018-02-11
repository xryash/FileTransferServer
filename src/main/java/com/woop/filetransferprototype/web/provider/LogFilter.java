/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.provider;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author NoID
 */
@Priority(2)
@Provider
public class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {
        System.out.println("-- req headers --");
        log(reqContext.getUriInfo(), reqContext.getHeaders());

    }

    @Override
    public void filter(ContainerRequestContext reqContext,
            ContainerResponseContext resContext) throws IOException {
        System.out.println("-- res headers --");
        log(reqContext.getUriInfo(), resContext.getHeaders());
        
    }

    private void log(UriInfo uriInfo, MultivaluedMap<String, ?> headers) {
        System.out.println("Path: " + uriInfo.getPath());
        //for (Header h : )
        //headers.entrySet().forEach(h -> System.out.println(h.getKey() + ": " + h.getValue()));
        for ( Entry<String, ? extends List<?>> h : headers.entrySet())
            System.out.println(h.getKey() + ": " + h.getValue());
    }
}
