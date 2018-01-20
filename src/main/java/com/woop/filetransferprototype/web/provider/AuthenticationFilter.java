/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.provider;

import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.log.Log;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author NoID
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    
    @Context
    private ResourceInfo resourceInfo;
    
    private final IAccountRepository accountRepository;
    
    public AuthenticationFilter() {
        accountRepository = new LocalStorageAccountRepository();
    }
    
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
                                                        .entity("You cannot access this resource").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
                                                        .entity("Access blocked for all users !!").build();

    public void filter(ContainerRequestContext requestContext) {
        Log.log(AuthenticationFilter.class.getSimpleName(), "Начало фильтрации...");
        Method method = resourceInfo.getResourceMethod();
        
        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (!method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
            
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
             if(authorization == null || authorization.isEmpty()) {
                 requestContext.abortWith(ACCESS_DENIED);
                 return;
             }
            
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME+ " ", "");
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));;
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
            Log.log(AuthenticationFilter.class.getSimpleName(), username);
            Log.log(AuthenticationFilter.class.getSimpleName(), password);
            if(method.isAnnotationPresent(RolesAllowed.class)){
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                
                if( ! isUserAllowed(username, password, rolesSet))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            } 
        }  
        Log.log(AuthenticationFilter.class.getSimpleName(), "Фильтр пройден");
    }
    
    private boolean isUserAllowed(final String login, final String password, final Set<String> rolesSet)
    {
        boolean isAllowed = false;
        Account account = accountRepository.getByLoginAndPassword(login, password);
        if(account != null)
        {
            String userRole = account.getRole();
            if(rolesSet.contains(userRole))
            {
                isAllowed = true;
            }
        }
        return isAllowed;
    }

    
    
}
