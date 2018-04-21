/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.provider;

import com.woop.filetransferprototype.errors.ServiceError;
import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.web.provider.exceptions.ProviderException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.StringTokenizer;
import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author NoID
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class NewAuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Context
    private ResourceInfo resourceInfo;

    private final IAccountRepository accountRepository;

    public NewAuthenticationFilter() {
        accountRepository = new LocalStorageAccountRepository();
    }

    public void filter(final ContainerRequestContext requestContext) {

        Method method = resourceInfo.getResourceMethod();
        if (method.isAnnotationPresent(PermitAll.class)) {return;}
        
        final String authorization = requestContext.getHeaderString(AUTHORIZATION_PROPERTY);
        
        if (authorization == null || authorization.isEmpty()) {
                System.out.println("authorization is null");
                throw new ProviderException(
                            Response.Status.UNAUTHORIZED.getStatusCode(),
                            new ServiceError("UNAUTHORIZED", "UNAUTHORIZED"),   
                            String.format("UNAUTHORIZED"));
            }
        
        
        final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        final String usernameAndToken = new String(Base64.decode(encodedUserPassword.getBytes()));;
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndToken, ":");
        final String login = tokenizer.nextToken();
        final String token = tokenizer.nextToken();

        final Account account = accountRepository.getByLoginAndToken(login, token);
        
        if (account == null) {
                System.out.println("account is null");
                throw new ProviderException(
                        Response.Status.UNAUTHORIZED.getStatusCode(), 
                        new ServiceError("UNAUTHORIZED", "UNAUTHORIZED"), 
                        String.format("UNAUTHORIZED"));
            }

        final SecurityContext securityContext
                = requestContext.getSecurityContext();
        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public String getName() {
                        return account.getLogin();
                    }
                };
            }

            @Override
            public boolean isUserInRole(String role) {
                return account.getRole().equals(role);
            }

            @Override
            public boolean isSecure() {
                return securityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });
        
    }

}
