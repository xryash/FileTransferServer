/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.exceptions;

import com.woop.filetransferprototype.errors.HttpServiceError;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author NoID
 */
public class AccountExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<AccountException> {

    private static final Logger logger = LoggerFactory.getLogger(AccountExceptionMapper.class);

    @Override
    public Response toResponse(AccountException fileUploadException) {

        if(logger.isErrorEnabled()) {
            logger.error("An error occured", fileUploadException);
        }

        HttpServiceError httpServiceError = fileUploadException.getHttpServiceError();

        return Response
                .status(httpServiceError.getHttpStatusCode())
                .entity(httpServiceError.getServiceError())
                .build();
    }
}
