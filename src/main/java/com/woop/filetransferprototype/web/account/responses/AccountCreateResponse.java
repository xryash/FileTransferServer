
package com.woop.filetransferprototype.web.account.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author NoID
 */
public class AccountCreateResponse {
    private final String data;

    public AccountCreateResponse(String data) {
        this.data = data;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }
    
}
