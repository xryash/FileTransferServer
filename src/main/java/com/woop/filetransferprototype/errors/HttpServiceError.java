/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.errors;

/**
 *
 * @author NoID
 */
public class HttpServiceError {
     private final int httpStatusCode;

    private final ServiceError serviceError;

    public HttpServiceError(int httpStatusCode, ServiceError serviceError) {
        this.httpStatusCode = httpStatusCode;
        this.serviceError = serviceError;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public ServiceError getServiceError() {
        return serviceError;
    }
}
