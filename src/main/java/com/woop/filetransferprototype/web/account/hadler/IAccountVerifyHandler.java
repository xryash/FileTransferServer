/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.account.hadler;

import com.woop.filetransferprototype.web.account.requests.AccountVerifyRequest;
import com.woop.filetransferprototype.web.account.responses.AccountVerifyResponse;

/**
 *
 * @author NoID
 */
public interface IAccountVerifyHandler {
    AccountVerifyResponse handle(AccountVerifyRequest request);
}
