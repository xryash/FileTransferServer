/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author NoID
 */
public class ServiceError {
    private final String code;
    private final String message;

    public ServiceError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
}
