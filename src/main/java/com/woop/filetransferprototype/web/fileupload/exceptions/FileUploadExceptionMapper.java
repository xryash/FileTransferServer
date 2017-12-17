/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.fileupload.exceptions;

import com.woop.filetransferprototype.errors.HttpServiceError;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author NoID
 */
public class FileUploadExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<FileUploadException> {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadExceptionMapper.class);

    @Override
    public Response toResponse(FileUploadException fileUploadException) {

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
