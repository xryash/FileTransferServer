/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.sql.repository;

import com.woop.filetransferprototype.local.entity.Account;

/**
 *
 * @author NoID
 */
public interface IAccountRepository extends IRepository<Account>{
     Account getByToken(String token);
     Account getByLogin(String login);
     Account getByLoginAndPassword(String login, String password);
     Account getByLoginAndToken(String login, String token);           
}
