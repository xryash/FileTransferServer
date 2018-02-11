/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author NoID
 */
public class AccountVerifyResponse {
        private final String data;

    public AccountVerifyResponse(String data) {
        this.data = data;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }
    
}
