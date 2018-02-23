/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.exceptions;

import com.woop.filetransferprototype.errors.HttpServiceError;
import com.woop.filetransferprototype.errors.ServiceError;

/**
 *
 * @author NoID
 */
public class FileDownloadException extends RuntimeException  {
    private final HttpServiceError httpServiceError;

    public FileDownloadException(ServiceError serviceError) {
        this.httpServiceError = createServiceError(serviceError);
    }

    public FileDownloadException(ServiceError serviceError, String message) {
        super(message);

        this.httpServiceError = createServiceError(serviceError);
    }

    public FileDownloadException(ServiceError serviceError, String message, Throwable cause) {
        super(message, cause);

        this.httpServiceError = createServiceError(serviceError);
    }

    public HttpServiceError getHttpServiceError() {
        return httpServiceError;
    }

    private static HttpServiceError createServiceError(ServiceError serviceError) {
        return new HttpServiceError(400, serviceError);
    }
}