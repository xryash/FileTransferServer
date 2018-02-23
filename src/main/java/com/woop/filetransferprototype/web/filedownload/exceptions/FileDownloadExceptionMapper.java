/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.exceptions;

import com.woop.filetransferprototype.errors.HttpServiceError;
import com.woop.filetransferprototype.web.fileupload.exceptions.FileUploadException;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author NoID
 */

public class FileDownloadExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<FileDownloadException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadExceptionMapper.class);

    @Override
    public Response toResponse(FileDownloadException fileDownloadException) {

        if(LOGGER.isErrorEnabled()) {
            LOGGER.error("An error occured", fileDownloadException);
        }

        HttpServiceError httpServiceError = fileDownloadException.getHttpServiceError();

        return Response
                .status(httpServiceError.getHttpStatusCode())
                .entity(httpServiceError.getServiceError())
                .build();
    }
}