/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.handler;

import com.woop.filetransferprototype.errors.ServiceError;
import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.entity.LocalFile;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageFileRepository;
import com.woop.filetransferprototype.web.filedownload.exceptions.FileDownloadException;
import java.util.List;

/**
 *
 * @author NoID
 */
public class FileListHandler {
    
    public List<LocalFile> handle(String login) {
        
        System.out.println("Запрос обрабатывается");
        
        LocalStorageFileRepository fileRepository = new LocalStorageFileRepository();
        LocalStorageAccountRepository accountRepository = new LocalStorageAccountRepository();
        
        Account account = accountRepository.getByLogin(login);
        
        if(account == null)
            throw new FileDownloadException(new ServiceError("NotExistingData", "Not existing account data"), String.format("Account doesnt exist"));
        
        List<LocalFile> list = fileRepository.getFilesByHost(account.getId());
        
        if(account == null)
            throw new FileDownloadException(new ServiceError("NotExistingData", "Not existing file data"), String.format("Files are null"));
        
        return list;
    }
}
