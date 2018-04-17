/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.provider.exceptions;

import com.woop.filetransferprototype.errors.HttpServiceError;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author NoID
 */


public class ProviderExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<ProviderException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderExceptionMapper.class);

    @Override
    public Response toResponse(ProviderException providerException) {

        if(LOGGER.isErrorEnabled()) {
            LOGGER.error("An error occured", providerException);
        }

        HttpServiceError httpServiceError = providerException.getHttpServiceError();

        return Response
                .status(httpServiceError.getHttpStatusCode())
                .entity(httpServiceError.getServiceError())
                .build();
    }
}