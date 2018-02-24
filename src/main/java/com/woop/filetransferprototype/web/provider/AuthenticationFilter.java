/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.provider;

import com.woop.filetransferprototype.errors.ServiceError;
import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.log.Log;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.web.provider.exceptions.ProviderException;
import com.woop.filetransferprototype.web.provider.securitycontext.ApplicationSecurityContext;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.StringTokenizer;
import javax.annotation.Priority;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author NoID
 */
@Priority(1)
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
            .entity("Нет доступа к ресурсу").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
            .entity("Ресурс заблокирован").build();

    public void filter(ContainerRequestContext requestContext) {
        Log.log(AuthenticationFilter.class.getSimpleName(), "Начало фильтрации...");
        Method method = resourceInfo.getResourceMethod();

        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                throw new ProviderException(Response.Status.FORBIDDEN.getStatusCode(), new ServiceError("missing", "Missing File data"), String.format("Missing Parameter: request.httpFile"));
            }

            final String authorization = requestContext.getHeaderString(AUTHORIZATION_PROPERTY);
            if (authorization == null || authorization.isEmpty()) {
                //requestContext.abortWith(ACCESS_DENIED);
                //return;
                throw new ProviderException(Response.Status.UNAUTHORIZED.getStatusCode(), new ServiceError("missing", "Missing File data"), String.format("Missing Parameter: request.httpFile"));
            }

            final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
            String usernameAndToken = new String(Base64.decode(encodedUserPassword.getBytes()));;
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndToken, ":");
            final String login = tokenizer.nextToken();
            final String token = tokenizer.nextToken();
            Log.log(AuthenticationFilter.class.getSimpleName(), login);
            Log.log(AuthenticationFilter.class.getSimpleName(), token);
            if (login == null || login.isEmpty() || token == null || token.isEmpty()) {
                //requestContext.abortWith(ACCESS_DENIED);
                //return;
                throw new ProviderException(Response.Status.UNAUTHORIZED.getStatusCode(), new ServiceError("missing", "Missing File data"), String.format("Missing Parameter: request.httpFile"));
            }

            Account account = accountRepository.getByLoginAndToken(login, token);
            if (account == null) {
                //requestContext.abortWith(ACCESS_DENIED);
                //return;
                throw new ProviderException(Response.Status.UNAUTHORIZED.getStatusCode(), new ServiceError("missing", "Missing File data"), String.format("Missing Parameter: request.httpFile"));
            }
            /*            
            if(method.isAnnotationPresent(RolesAllowed.class)){
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                
                if( ! isAccountAllowed(account, rolesSet))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            } 
             */
            String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
            requestContext.setSecurityContext(new ApplicationSecurityContext(account, scheme));

        }

        Log.log(AuthenticationFilter.class.getSimpleName(), "Фильтр пройден");

    }

    private boolean isAccountAllowed(final Account account, final Set<String> rolesSet) {
        boolean isAllowed = false;

        String userRole = account.getRole();
        if (rolesSet.contains(userRole)) {
            isAllowed = true;
        }

        return isAllowed;
    }

}
