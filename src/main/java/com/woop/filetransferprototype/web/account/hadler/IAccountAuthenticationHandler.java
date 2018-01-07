/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.hadler;

import com.woop.filetransferprototype.web.account.requests.AccountAuthenticationRequest;
import com.woop.filetransferprototype.web.account.responses.AccountAuthenticationResponse;

/**
 *
 * @author NoID
 */
public interface IAccountAuthenticationHandler {
    AccountAuthenticationResponse handle(AccountAuthenticationRequest request);
}
