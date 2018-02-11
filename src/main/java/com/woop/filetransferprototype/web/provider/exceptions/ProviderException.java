/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.provider.exceptions;

import com.woop.filetransferprototype.errors.HttpServiceError;
import com.woop.filetransferprototype.errors.ServiceError;

/**
 *
 * @author NoID
 */

public class ProviderException extends RuntimeException  {
    private final HttpServiceError httpServiceError;

    public ProviderException(int code, ServiceError serviceError) {
        this.httpServiceError = createServiceError(code, serviceError);
    }

    public ProviderException(int code, ServiceError serviceError, String message) {
        super(message);

        this.httpServiceError = createServiceError(code, serviceError);
    }

    public ProviderException(int code, ServiceError serviceError, String message, Throwable cause) {
        super(message, cause);

        this.httpServiceError = createServiceError(code,serviceError);
    }

    public HttpServiceError getHttpServiceError() {
        return httpServiceError;
    }

    private static HttpServiceError createServiceError(int code, ServiceError serviceError) {
        return new HttpServiceError(code, serviceError);
    }
}