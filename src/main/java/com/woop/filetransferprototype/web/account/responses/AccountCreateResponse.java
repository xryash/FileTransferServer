
package com.woop.filetransferprototype.web.account.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author NoID
 */
public class AccountCreateResponse {
    private final String login;
    private final String token;

    public AccountCreateResponse(String login, String token) {
        this.login = login;
        this.token = token;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    
}
